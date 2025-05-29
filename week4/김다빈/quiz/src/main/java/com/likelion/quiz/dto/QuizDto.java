package com.likelion.quiz.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuizDto {
    // 문제 ID (예: "1", "2" 등)
    private String id;
    // 문제 내용
    private String question;
    // 선택지 1
    private String choice1;
    // 선택지 2
    private String choice2;
    // 선택지 3
    private String choice3;
    // 선택지 4
    private String choice4;
    // 정답 (선택지 번호 문자열, 예: "2")
    private String answer;

    // 기본 생성자 (디폴트 생성자)
    public QuizDto() {}

    // 모든 필드를 초기화하는 생성자
    public QuizDto(String id, String question, String choice1, String choice2, String choice3, String choice4, String answer) {
        this.id = id;
        this.question = question;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.choice4 = choice4;
        this.answer = answer;
    }
}