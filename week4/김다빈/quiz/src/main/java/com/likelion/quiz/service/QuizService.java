package com.likelion.quiz.service;

import com.likelion.quiz.dto.QuizDto;
import com.likelion.quiz.entity.QuizEntity;
import com.likelion.quiz.repository.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service  // 서비스 레이어임을 나타내며, 스프링이 빈으로 자동 등록
public class QuizService {

    private final QuizRepository quizRepository;

    // 생성자를 통한 의존성 주입 (DI)
    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    // ID로 퀴즈 정보를 조회해 QuizDto로 변환 후 반환하는 메서드
    public QuizDto getQuizById(String id) {
        Optional<QuizEntity> quizEntityOpt = quizRepository.findById(id); // DB에서 QuizEntity 조회, Optional: 있을수도, 없을수도 있는 값을 감싸는 박스
        if (quizEntityOpt.isPresent()) {
            QuizEntity quizEntity = quizEntityOpt.get(); // quizEntityOpt.get(): Optional 객체 안에 값이 있을 때, 그 값을 꺼내주는 메소드
            // QuizEntity -> QuizDto 변환
            return new QuizDto(
                    quizEntity.getId(),
                    quizEntity.getQuestion(),
                    quizEntity.getChoice1(),
                    quizEntity.getChoice2(),
                    quizEntity.getChoice3(),
                    quizEntity.getChoice4(),
                    quizEntity.getAnswer()
            );
        }
        return null;  // 데이터가 없으면 null 반환, 필요시 예외 처리 가능
    }
}