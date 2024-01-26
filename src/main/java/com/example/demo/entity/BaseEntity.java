package com.example.demo.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass //JPA가 해당클래스는 테이블로 생성하지 않음
@EntityListeners(value = { AuditingEntityListener.class }) //엔티티에 변화를 감지하는 리스너 지정
@Getter
abstract class BaseEntity {

    @CreatedDate //인스턴스가 생성되는 것을 감지하여 현재시간을 저장
    LocalDateTime regDate;

    @LastModifiedDate //인스턴스가 수정되는 것을 감지하여 현재시간을 저장
    LocalDateTime modDate;

    
    // 실행클래스에 @EnableJpaAuditing 이거넣어야됨.!!! 그래야 날짜 입력
}
























