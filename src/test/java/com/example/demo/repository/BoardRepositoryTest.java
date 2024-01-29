// 7장 14페이지

package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Board;
import com.example.demo.entity.Member;


@SpringBootTest
public class BoardRepositoryTest {

	@Autowired
	BoardRepository repository;
	
	@Test
	void 게시물등록() {
		Board board = Board.builder()
						.title("1번글").content("내용입니다").writer("둘리")
						.build();
		repository.save(board);
	}

	@Test
	void 게시물목록조회() {
		List<Board> list = repository.findAll();
		for(Board board : list) {
			System.out.println(board);
		}
	}
	
	@Test
	void 게시물단건조회() {
		Optional<Board> result = repository.findById(1);
		if(result.isPresent()) {
			Board board = result.get();
			System.out.println(board);
		}
	}

	@Test
	void 게시물수정() {
		Optional<Board> result = repository.findById(1);
		Board board = result.get();
		board.setContent("내용이수정되었습니다~");
		repository.save(board);
	}
	
	@Test
	void 게시물삭제() {
		repository.deleteById(1);
	}

	@Test // 8장 p.27
	void 없는아이디로게시물생성하기() {
		//회원 엔티티 생성
		Member member = Member.builder()
						.id("admin")
						.build();
		
		//회원 테이블에 없는 아이디를 사용하면 에러남
		Board board = new Board(0, "1번글", "내용입니다", member);
		
		repository.save(board);
	}
	
	
	@Test // 8장 p.27
	void 있는아이디로게시물생성하기() {
		//회원 엔티티 생성
		Member member = Member.builder()
						.id("user1")
						.build();
		
		//회원 테이블에 없는 아이디를 사용하면 에러남
		Board board = new Board(0, "1번글", "내용입니다", member);
		
		repository.save(board);
	}
	
	@Test // 8장 p.27
	void 여러게시물등록() {
		Member member = Member
				.builder()
				.id("user1")				
				.build();
		List<Board> list = new ArrayList<>();
		
		list.add(new Board(0,"2번글","내용입니다", member));
		
		repository.saveAll(list);
	}
}


