package com.example.springboot_20250529test.service;

import com.example.springboot_20250529test.model.Quiz;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// 이 클래스는 퀴즈 데이터를 제공하고 정답을 채점하는 비즈니스 로직을 담당합니다.
// @Service 어노테이션을 사용하여 Spring이 이 클래스를 서비스 컴포넌트로 인식하게 합니다.
@Service
public class QuizService {

    // 퀴즈 문제와 정답을 저장하는 리스트입니다.
    // 프로그램이 실행될 때 미리 데이터를 초기화합니다.
    private final List<Quiz> quizzes;

    // 생성자: QuizService 객체가 생성될 때 퀴즈 데이터를 초기화합니다.
    public QuizService() {
        quizzes = new ArrayList<>(); // 빈 리스트 생성

        // 퀴즈 문제와 정답을 직접 리스트에 추가
        quizzes.add(new Quiz("2 + 2 = ?", "4"));
        quizzes.add(new Quiz("Java는 몇 년도에 출시되었나?", "1995"));
        quizzes.add(new Quiz("Spring은 어느 언어 기반인가?", "Java"));
        quizzes.add(new Quiz("HTTP의 기본 포트 번호는?", "80"));
        quizzes.add(new Quiz("SQL에서 데이터를 조회하는 명령은?", "SELECT"));
    }

    // 퀴즈 리스트에서 최대 5개의 문제를 반환하는 메서드입니다.
    public List<Quiz> getQuizzes() {
        // 퀴즈 수가 5개 이상이면 5개만, 적으면 있는 만큼 반환
        return quizzes.subList(0, Math.min(5, quizzes.size()));
    }

    // 사용자로부터 제출받은 답안 리스트를 기준으로 정답 개수를 계산하는 메서드입니다.
    public int checkAnswers(List<String> userAnswers) {
        int correct = 0; // 정답 수를 저장할 변수
        List<Quiz> selectedQuizzes = getQuizzes(); // 퀴즈 문제 리스트 가져오기

        // 사용자의 답안을 하나씩 순회하면서 채점
        for (int i = 0; i < userAnswers.size(); i++) {
            // 인덱스 범위가 유효하고, 정답이 사용자의 입력과 같으면 정답으로 처리
            if (i < selectedQuizzes.size() && //문제 수보다 사용자의 답이 더 많이지는걸 방지
                    selectedQuizzes.get(i).getAnswer().equalsIgnoreCase(userAnswers.get(i).trim())) {
                correct++; // 정답일 경우 점수 증가 (1증가)
            }
        }

        // 최종 정답 수 반환
        return correct;
    }
}
