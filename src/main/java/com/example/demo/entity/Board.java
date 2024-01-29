package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="tbl_board")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board extends BaseEntity { // 등록일, 수정일 필드 상속받기

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no; //글번호

    @Column(length = 100, nullable = false)
    private String title; //제목

    @Column(length = 1500, nullable = false)
    private String content; //내용

    @ManyToOne // Board-Member 연관관계 설정 => Member은 여러개의 Board만들 수 있다.
    Member writer; // 작성자(Member 엔티티 클래스 타입) 

}
