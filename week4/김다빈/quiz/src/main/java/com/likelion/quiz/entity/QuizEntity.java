package com.likelion.quiz.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity // JPA 엔티티임을 명시, DB 테이블과 매핑
@Getter
@Setter
@Table(name = "quiz") // DB 내 quiz 테이블과 매핑
public class QuizEntity {

    @Id // 기본 키(PK) 지정, DB에서 유일해야 하는 값
    private String id;

    // 문제 내용
    private String question;

    // 보기 1
    private String choice1;

    // 보기 2
    private String choice2;

    // 보기 3
    private String choice3;

    // 보기 4
    private String choice4;

    // 정답 (보기 번호, 예: "2")
    private String answer;

    // Lombok이 자동으로 getter/setter 생성
}