package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Member;
//1월 29일 8장 p.24
@SpringBootTest
public class MemberRepositoryTest {
	
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired  // 8장 p.30
	BoardRepository boardRepository;
	
	@Test
	void 회원등록(){
		Member member1 = Member.builder()
				.id("yohan1").Password("1_1234").name("주요한1") .build();
		
		Member member2 = new Member("yohan2", "2_1234", "주요한2");
		
		memberRepository.save(member1);  memberRepository.save(member2);
	}
	
	@Test
	void 회원일괄등록() { // 8장
		List<Member> list = new ArrayList<>();
		
		for(int i=0; i<30;i++) {
			list.add(new Member("user"+i, "1234", "둘리"));
		}
		
		memberRepository.saveAll(list);
	}
	
	
	@Test
	void 회원목록조회() {
		List<Member> list = memberRepository.findAll();
		for(Member member : list) {
			System.out.println(member);
		}		
		
	}	
	@Test
	void 게시물단건조회() {
		Optional<Member> result = memberRepository.findById("yohan1");
		if(result.isPresent()) {
			Member member = result.get();
			System.out.println(member);
		}
	}	
	
	
	@Test
	void 회원수정() {
		Optional<Member> result = memberRepository.findById("yohan2");
		Member member = result.get();
		member.setPassword("yohanJJang");
		memberRepository.save(member); /* => insert or update */
	}
	
	
	@Test
	void 회원삭제() {
		memberRepository.deleteById("yohan2");
	}
	
	@Test
	void 회원모두삭제() { // 8장 p.29
		memberRepository.deleteAll();
	}
	
	@Test
	void 게시물을작성한회원삭제() {
		Member member = Member.builder().id("user1").build();
		
		boardRepository.deleteWriter(member);
		
		memberRepository.deleteById("user1");
	}
}












































