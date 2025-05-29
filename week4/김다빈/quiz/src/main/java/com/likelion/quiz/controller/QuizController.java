package com.likelion.quiz.controller;

import com.likelion.quiz.dto.QuizDto;
import com.likelion.quiz.service.QuizService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/quiz")
@Controller
public class QuizController {
    private final QuizService quizService; //서비스 객체를 사용하기 위해 선언
    private static final int TOTAL_QUIZ_COUNT = 3; // 총 문제 개수 상수로 정의

    // 생성자 주입으로 QuizService 주입받음
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    // 퀴즈 시작 페이지 (/quiz/start) 요청 처리
    @GetMapping("/start")
    public String showStartPage() {
        return "start";  // start.html 뷰를 렌더링
    }

    // 문제 보여주는 메서드 (/quiz/{id} GET 요청)
    // PathVariable로 문제 번호 받아옴, result는 정답 여부 메시지용 쿼리 파라미터
    @GetMapping("/{id}")
    public String showQuiz(@PathVariable("id") String id, // url의 일부인 id를 변수로 받아옴
                           @RequestParam(value = "result", required = false) String result,
                           Model model) { // required = false는 result의 값이 없으면 null로 들어가 에러가 없다.\
        QuizDto quiz = quizService.getQuizById(id); // 문제 데이터 조회
        model.addAttribute("quiz", quiz); // 뷰에 문제 데이터 전달

        // 정답 여부에 따른 메시지 추가
        if ("correct".equals(result)) {
            model.addAttribute("result", "맞았습니다!");
        } else if ("wrong".equals(result)) {
            model.addAttribute("result", "틀렸습니다!");
        }

        return "quiz"; // quiz.html 뷰 렌더링
    }

    // 퀴즈 제출 처리 (/quiz/submit POST 요청)
    @PostMapping("/submit")
    public String submitQuiz(@RequestParam("id") String id,
                             @RequestParam("answer") String selectedAnswer,
                             HttpSession session,
                             Model model) {
        QuizDto quiz = quizService.getQuizById(id);

        // 세션에서 맞은 개수 가져오기, 없으면 0으로 초기화
        Integer correctCount = (Integer) session.getAttribute("correctCount");
        if (correctCount == null) {
            correctCount = 0;
        }

        // 정답이면 맞은 개수 1 증가 후 세션에 저장
        if (quiz.getAnswer().equals(selectedAnswer)) {
            correctCount++;
            session.setAttribute("correctCount", correctCount);
        }

        int currentId = Integer.parseInt(id);

        // 마지막 문제면 결과 페이지로 이동
        if (currentId >= TOTAL_QUIZ_COUNT) {
            return "redirect:/quiz/result";
        } else {
            // 다음 문제로 이동, 정답 여부 쿼리 파라미터로 전달
            int nextId = currentId + 1;
            String result = quiz.getAnswer().equals(selectedAnswer) ? "correct" : "wrong";
            return "redirect:/quiz/" + nextId + "?result=" + result;
        }
    }

    // 결과 페이지 (/quiz/result GET 요청) 처리
    @GetMapping("/result")
    public String showResult(HttpSession session, Model model) {
        // 세션에서 맞은 개수 가져오기, 없으면 0
        Integer correctCount = (Integer) session.getAttribute("correctCount");
        if (correctCount == null) correctCount = 0;

        // 뷰에 맞은 개수와 총 문제 개수 전달
        model.addAttribute("score", correctCount);
        model.addAttribute("total", TOTAL_QUIZ_COUNT);

        // 세션 초기화 (퀴즈 재시작을 위해)
        session.invalidate();

        return "result"; // result.html 뷰 렌더링
    }
}