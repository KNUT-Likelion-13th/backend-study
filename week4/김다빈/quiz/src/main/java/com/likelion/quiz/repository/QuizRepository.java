package com.likelion.quiz.repository;

import com.likelion.quiz.entity.QuizEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // 이 인터페이스가 스프링의 빈으로 등록되는 저장소 컴포넌트임을 명시
public interface QuizRepository extends JpaRepository<QuizEntity, String> {
    // JpaRepository를 상속받아 QuizEntity를 다루며,
    // 기본 키 타입은 String임을 지정

    // JpaRepository는 기본 CRUD 메서드 제공
    // findById(id): Optional<QuizEntity> — ID로 조회
    // save(entity): 저장 및 수정
    // delete(entity): 삭제
    // findAll(): 전체 조회 등
}