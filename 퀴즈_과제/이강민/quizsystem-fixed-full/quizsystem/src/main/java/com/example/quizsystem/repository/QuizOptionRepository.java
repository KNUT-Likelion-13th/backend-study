package com.example.quizsystem.repository;

import com.example.quizsystem.model.QuizOption;
import org.springframework.data.jpa.repository.JpaRepository;

// 🔹 퀴즈 보기(QuizOption)를 데이터베이스에서 읽고 쓰는 기능을 자동으로 만들어주는 인터페이스
public interface QuizOptionRepository extends JpaRepository<QuizOption, Long> {
    // JpaRepository<엔티티 클래스, 기본키 타입>
    // 여기에 아무 메서드도 없어도, 기본적인 CRUD 기능은 자동으로 제공됩니다!
}
