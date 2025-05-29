package com.example.quizsystem.model;

import jakarta.persistence.*;

@Entity // 이 클래스도 데이터베이스 테이블과 매핑됨
public class QuizOption {

    @Id // 기본 키
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가
    private Long id;

    private String content; // 보기의 텍스트 내용 (예: "보기1", "정답", 등)

    @ManyToOne // 여러 개의 보기(Option)는 하나의 퀴즈(Quiz)에 속함
    @JoinColumn(name = "quiz_id") // 외래 키 컬럼: quiz 테이블의 id와 연결
    private Quiz quiz;

    // getter 메서드들
    public Long getId() { return id; }

    public String getContent() { return content; }

    public Quiz getQuiz() { return quiz; }
}
