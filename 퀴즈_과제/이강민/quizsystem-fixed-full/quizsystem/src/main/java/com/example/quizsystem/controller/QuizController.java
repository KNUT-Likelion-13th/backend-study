package com.example.quizsystem.controller;

import com.example.quizsystem.model.Quiz;
import com.example.quizsystem.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

@Controller // 사용자의 웹 요청을 처리하는 클래스임을 표시
@SessionAttributes({"quizList", "currentIndex", "score"}) // 세션에 저장할 항목 설정 (문제 목록, 현재 번호, 점수)
public class QuizController {

    private final QuizService quizService;

    @Autowired // QuizService 자동 주입
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    // 🔹 시작 화면 요청 처리 (GET "/")
    @GetMapping("/")
    public String startPage() {
        return "start"; // start.html 보여줌
    }

    // 🔹 퀴즈 시작 요청 처리 (POST "/start")
    @PostMapping("/start")
    public String startQuiz(@RequestParam int count, Model model) {
        // 문제를 랜덤으로 count 개만큼 가져옴
        List<Quiz> selected = quizService.getRandomQuizzes(count);

        // 세션에 문제 목록, 시작 인덱스, 점수를 저장
        model.addAttribute("quizList", selected);
        model.addAttribute("currentIndex", 0);
        model.addAttribute("score", 0);

        return "redirect:/quiz"; // 문제 화면으로 이동
    }

    // 🔹 현재 문제 보여주는 화면 (GET "/quiz")
    @GetMapping("/quiz")
    public String showQuiz(@SessionAttribute(value = "quizList", required = false) List<Quiz> quizList,
                           @SessionAttribute(value = "currentIndex", required = false) Integer index,
                           Model model) {
        // 세션 값이 없으면 시작 페이지로
        if (quizList == null || index == null) return "redirect:/";

        // 마지막 문제까지 다 풀었으면 결과 화면으로
        if (index >= quizList.size()) {
            return "redirect:/result";
        }

        // 현재 문제, 총 문제 수 전달
        model.addAttribute("quiz", quizList.get(index));
        model.addAttribute("total", quizList.size());
        model.addAttribute("currentIndex", index);
        return "quiz";
    }

    // 🔹 사용자가 정답을 제출했을 때 (POST "/submit")
    @PostMapping("/submit")
    public String submitAnswer(@RequestParam String answer,
                               @SessionAttribute("quizList") List<Quiz> quizList,
                               @SessionAttribute("currentIndex") Integer index,
                               @SessionAttribute("score") Integer score,
                               Model model) {

        // 현재 문제 가져오기
        Quiz currentQuiz = quizList.get(index);

        // 사용자의 답이 정답인지 비교 (대소문자 무시)
        boolean isCorrect = currentQuiz.getCorrectAnswer().equalsIgnoreCase(answer.trim());

        // 정답이면 점수 +1
        if (isCorrect) score++;

        // 결과 화면에서 사용할 정보 전달
        model.addAttribute("isCorrect", isCorrect);
        model.addAttribute("correctAnswer", currentQuiz.getCorrectAnswer());
        model.addAttribute("score", score);
        model.addAttribute("currentIndex", index);
        model.addAttribute("total", quizList.size());

        return "answer"; // answer.html 렌더링
    }

    // 🔹 다음 문제로 이동 (POST "/next")
    @PostMapping("/next")
    public String goToNext(@SessionAttribute("currentIndex") Integer index, Model model) {
        model.addAttribute("currentIndex", index + 1); // 현재 문제 번호 1 증가
        return "redirect:/quiz"; // 다음 문제 보여줌
    }

    // 🔹 최종 결과 화면 (GET "/result")
    @GetMapping("/result")
    public String showResult(
            @SessionAttribute(value = "score", required = false) Integer score,
            @SessionAttribute(value = "quizList", required = false) List<Quiz> quizList,
            Model model) {

        // null 방지 처리: 값이 없으면 기본값 사용
        int finalScore = (score != null) ? score : 0;
        int total = (quizList != null) ? quizList.size() : 0;

        // 결과 페이지로 전달
        model.addAttribute("finalScore", finalScore);
        model.addAttribute("total", total);
        return "result";
    }

    // 🔹 다시 시작 요청 (GET "/restart")
    @GetMapping("/restart")
    public String restart(SessionStatus sessionStatus) {
        sessionStatus.setComplete(); // 세션 데이터 초기화
        return "redirect:/"; // 시작 페이지로 이동
    }
}
