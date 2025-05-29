package com.example.quizsystem.repository;

import com.example.quizsystem.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

// 🔹 퀴즈 문제(Quiz)를 데이터베이스에서 읽고 쓰는 인터페이스
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    // JpaRepository<엔티티 클래스, 기본키 타입>
    // 이 인터페이스 덕분에 Quiz 문제를 불러오고 저장하는 기능이 자동으로 만들어짐
}
