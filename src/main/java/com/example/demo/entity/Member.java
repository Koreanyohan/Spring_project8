package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="tbl_member")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member extends BaseEntity{// 등록일, 수정일 필드 상속받기
	
	@Id
	@Column(length = 50)
	String id; // 아이디
// cf) pk의 자료형이 String이라서 키 생성방식 못넣음(@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(length = 200, nullable = false)
	String password; // 비밀번호
	
	@Column(length = 100, nullable = false)
	String name; // 이름	
	
}




















