// 8장 p.8 
package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.Board;
//service.~ 메서드들 BoardServiceImpl에 정의된 것들이다
@SpringBootTest
public class BoardServiceTest {  // cf) BoardService라고 클래스이름 만들면 ㅈ된다.
	
	@Autowired
	BoardService service; // BoardService인페 구현한 BoardServiceImpl 객체로서 생성됨

		
	@Test
	void 게시물목록조회1페이지() {
		
		Page<BoardDTO> page = service.getList(1);
		
		List<BoardDTO> list = page.getContent();
		
		for(BoardDTO dto : list) {
			System.out.println(dto);
		}
	}	
	
	
	
	// 7장것들
	
	/*
	 * @Test public void 게시물등록() { // p.18
	 * 
	 * BoardDTO dto = BoardDTO.builder() .title("2번글").content("내용입니다").writer("또치")
	 * .build();
	 * 
	 * int no = service.register(dto); // 인페인 BoardService 타입의 service변수의 추상클래스
	 * register 호출 // -> @Service선언하여 추상메서드 구현한 BoardServiceImpl의 register클래스 호출
	 * 
	 * System.out.println("새로운 게시물 번호 : " + no); }
	 * 
	 * @Test public void 게시물목록조회() { //(p.19-21) List<BoardDTO> list =
	 * service.getList(); // DTO타입 게시물목록 반환하는 추상 메서드 getList이용해서 BoardService인페 구현한
	 * BoardServiceImpl 객체(service)에서 entity 리스트추출해서 dto리스트로 넣음 for (BoardDTO dto :
	 * list) { System.out.println(dto); } }
	 * 
	 * @Test void 게시물단건조회() { // 3. 게시물 상세조회 (p.38~) BoardDTO dto = service.read(3);
	 * 
	 * System.out.println(dto);
	 * 
	 * }
	 * 
	 * @Test void 게시물수정() { // 4. 게시물 수정 (p.46~) BoardDTO dto = service.read(4);
	 * 
	 * dto.setContent("내용이 수정되었습니다~");
	 * 
	 * service.modify(dto);;
	 * 
	 * }
	 * 
	 * @Test void 게시물삭제() { // 5. 게시물 삭제 (p.56~) int result = service.remove(12);
	 * 
	 * System.out.println("결과가 1이면 삭제에 성공한 거고 결과가 0이면 삭제에 실패한거다 : "+result);
	 * 
	 */
}





























