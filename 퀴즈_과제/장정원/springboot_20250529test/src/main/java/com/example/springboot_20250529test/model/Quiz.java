package com.example.springboot_20250529test.model;

// Quiz 클래스는 하나의 퀴즈 문제(질문과 정답)를 표현하는 데이터 모델입니다.
public class Quiz {

    // 문제 내용을 저장하는 변수 (예: "2 + 2 = ?")
    private String question;

    // 해당 문제의 정답을 저장하는 변수 (예: "4")
    private String answer;

    // 생성자: Quiz 객체를 만들 때 질문과 정답을 받아서 초기화함
    public Quiz(String question, String answer) {
        this.question = question; // 전달받은 question 값을 필드에 저장
        this.answer = answer;     // 전달받은 answer 값을 필드에 저장
    }

    // 외부에서 이 퀴즈의 질문을 가져갈 수 있게 해주는 메서드
    public String getQuestion() {
        return question; // 필드값 반환
    }

    // 외부에서 이 퀴즈의 정답을 가져갈 수 있게 해주는 메서드
    public String getAnswer() {
        return answer; // 필드값 반환
    }
}

