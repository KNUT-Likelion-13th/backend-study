package com.example.quizsystem.service;

import com.example.quizsystem.model.Quiz;
import com.example.quizsystem.model.QuizType;
import com.example.quizsystem.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service // 이 클래스는 서비스 역할임을 표시 (비즈니스 로직 처리 담당)
public class QuizService {

    private final QuizRepository quizRepository;

    @Autowired // 의존성 자동 주입 (QuizRepository를 사용하도록 설정)
    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    // 🔹 사용자가 요청한 수(count)만큼 퀴즈를 랜덤하게 반환하는 메서드
    public List<Quiz> getRandomQuizzes(int count) {
        List<Quiz> all = quizRepository.findAll(); // DB에서 모든 문제 가져오기

        // 객관식 문제만 필터링
        List<Quiz> multiple = all.stream()
                .filter(q -> q.getType() == QuizType.MULTIPLE)
                .collect(Collectors.toList());

        // 주관식 문제만 필터링
        List<Quiz> shortAnswer = all.stream()
                .filter(q -> q.getType() == QuizType.SHORT)
                .collect(Collectors.toList());

        // 두 문제 유형 모두 무작위로 섞기
        Collections.shuffle(multiple);
        Collections.shuffle(shortAnswer);

        // 객관식 최대 25개, 주관식 최대 25개를 선택
        List<Quiz> selected = multiple.stream().limit(25).collect(Collectors.toList());
        selected.addAll(shortAnswer.stream().limit(25).collect(Collectors.toList()));

        // 최종적으로 섞어서 사용자 요청 수(count)만큼 반환
        Collections.shuffle(selected);
        return selected.stream().limit(count).collect(Collectors.toList());
    }
}
