package com.example.springboot_20250529test.controller;

// Quiz 데이터 모델과 서비스 로직을 사용하기 위해 필요한 클래스들을 import
import com.example.springboot_20250529test.model.Quiz;
import com.example.springboot_20250529test.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 이 클래스는 사용자의 웹 요청을 받아 처리하는 Spring MVC 컨트롤러 역할을 합니다.
@Controller
public class QuizController {

    // QuizService 객체를 자동으로 주입받아 사용합니다.
    // 퀴즈 데이터를 가져오거나 정답을 채점하는 기능을 담당합니다.
    @Autowired
    private QuizService quizService;

    // GET 방식으로 "/quiz" 경로에 요청이 들어오면 이 메서드가 실행됩니다.
    // 퀴즈 문제들을 가져와서 모델에 담고, quiz.html 뷰를 렌더링합니다.
    @GetMapping("/quiz")
    public String showQuiz(Model model) {
        // 서비스에서 퀴즈 문제 리스트를 가져옴
        List<Quiz> quizzes = quizService.getQuizzes();

        // 뷰에 전달할 데이터를 모델에 저장 (key: "quizzes", value: 퀴즈 리스트)
        model.addAttribute("quizzes", quizzes);

        // templates/quiz.html 파일을 사용자에게 반환 (문제 표시용 페이지)
        return "quiz";
    }

    // POST 방식으로 "/submit" 경로에 요청이 들어오면 이 메서드가 실행됩니다.
    // 사용자가 입력한 정답 리스트를 받아 채점하고 결과를 result.html로 전달합니다.
    @PostMapping("/submit")
    public String submitAnswers(@RequestParam List<String> answers, Model model) {
        // 사용자의 답안을 채점하여 맞힌 개수를 반환받음
        int score = quizService.checkAnswers(answers);

        // 뷰에 전달할 점수 데이터를 모델에 저장
        model.addAttribute("score", score);           // 맞힌 문제 수
        model.addAttribute("total", answers.size());  // 총 문제 수

        // templates/result.html 파일을 사용자에게 반환 (결과 표시용 페이지)
        return "result";
    }
}
