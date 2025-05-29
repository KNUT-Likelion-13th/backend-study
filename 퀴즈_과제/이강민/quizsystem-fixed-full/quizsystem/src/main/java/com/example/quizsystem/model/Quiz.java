package com.example.quizsystem.model;

import jakarta.persistence.*;
import java.util.List;

@Entity // 이 클래스는 데이터베이스 테이블과 매핑되는 JPA 엔티티임을 의미
public class Quiz {

    @Id // 기본 키 (Primary Key)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가 설정
    private Long id;

    private String question; // 퀴즈 문제 내용

    private String correctAnswer; // 정답 (주관식이든 객관식이든 여기 저장)

    @Enumerated(EnumType.STRING) // ENUM 타입을 문자열로 저장 (예: MULTIPLE, SHORT)
    private QuizType type; // 문제 유형 (객관식인지, 주관식인지)

    @OneToMany( // Quiz 하나가 여러 개의 보기(옵션)를 가질 수 있음
            mappedBy = "quiz",             // QuizOption 클래스의 quiz 필드에 의해 매핑됨
            cascade = CascadeType.ALL,     // Quiz 삭제 시 관련 옵션도 같이 삭제
            orphanRemoval = true,          // 옵션이 Quiz에서 제거되면 DB에서도 삭제됨
            fetch = FetchType.EAGER        // 퀴즈를 불러올 때 옵션도 함께 조회
    )
    private List<QuizOption> options; // 보기 목록 (객관식일 경우만 사용)

    // getter 메서드들: 외부에서 필드값을 읽을 수 있게 해줌
    public Long getId() { return id; }

    public String getQuestion() { return question; }

    public String getCorrectAnswer() { return correctAnswer; }

    public QuizType getType() { return type; }

    public List<QuizOption> getOptions() { return options; }
}
