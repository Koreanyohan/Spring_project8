package com.example.demo.repository;


import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.Board;



@SpringBootTest
public class RepositoryTest {

	@Autowired
	BoardRepository repository;
	
	@Test
	void 게시물등록() {
		
		for (int i=1; i<=30 ; i++) {
		Board board = Board.builder()
				.title(i + "번글").content("내용입니다").writer("둘리")
				.build();  // no, 날짜는 시스템이 자동으로 입력해주므로, 생성자에 굳이 안넣어도 됨.
			repository.save(board);
		}
	}
	
	
	@Test
	void 일번페이지조회테스트() {
		// 페이지번호와 데이터개수를 담아서 페이지 정보 생성(페이지번호 데이터갯수 (+페이지정렬)정보 담은 Pageable 인스턴스 생성 - 1페이지 10개
		Pageable pageable = PageRequest.of(0, 10);  // Pageable, PageRequest domain으로 import
		// 페이지 정보를 전달하여 데이터 조회하기    - Page는 게시물 리스트 + 페이지정보 같이 가짐
		Page<Board> result = repository.findAll(pageable); // Page domain으로 import
		 
		System.out.println(result);
		
		//페이지안에 담긴 데이터 꺼내기
		List<Board> list = result.getContent();
		//게시물 리스트
		System.out.println(list);
	}	
	
	
	@Test
	void 이번페이지조회테스트() {
		// 페이지번호와 데이터개수를 담아서 페이지 정보 생성(페이지번호 데이터갯수 (+페이지정렬)정보 담은 Pageable 인스턴스 생성 - 2페이지 10개
		Pageable pageable = PageRequest.of(1, 10);
		// 페이지 정보를 전달하여 데이터 조회하기    - Page는 게시물 리스트 + 페이지정보 같이 가짐
		Page<Board> result = repository.findAll(pageable);
		 
		System.out.println(result);
		
		//페이지안에 담긴 데이터 꺼내기
		List<Board> list = result.getContent();
		//게시물 리스트
		System.out.println(list);
	}	
	
	
	@Test
	void 삼번페이지조회테스트() {
		// 페이지번호와 데이터개수를 담아서 페이지 정보 생성(페이지번호 데이터갯수 (+페이지정렬)정보 담은 Pageable 인스턴스 생성 - 3페이지 10개
		Pageable pageable = PageRequest.of(3, 10);
		// 페이지 정보를 전달하여 데이터 조회하기    - Page는 게시물 리스트 + 페이지정보 같이 가짐
		Page<Board> result = repository.findAll(pageable);
		 
		System.out.println(result);
		
		//페이지안에 담긴 데이터 꺼내기
		List<Board> list = result.getContent();
		//게시물 리스트
		System.out.println(list);
	}	
	
	
	@Test
	void 정렬조건추가하기() {
		//no 필드값을 기준으로 역정렬. 필드값은 pk나 int형 아니어도 된다. 다만, entity의 필드명이어야
		Sort sort = Sort.by("no").descending();  // Sort domain으로 import
									// <-> .ascending
		//페이지 번호, 데이터개수, 정렬조건을 담아서 페이지 정보 생성
		Pageable pageable2 = PageRequest.of(2, 10, sort);
		// 페이지 정보를 전달하여 데이터 조회
		Page<Board> result = repository.findAll(pageable2);
		
		List<Board> list = result.getContent();
		
		System.out.print(list);
		
	}		
		
	
}











