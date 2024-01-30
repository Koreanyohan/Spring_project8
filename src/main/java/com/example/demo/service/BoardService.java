/*39행 에서 getList 재정의*/
package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.Board;
import com.example.demo.entity.Member;

public interface BoardService {

	// 1. 게시물 등록
	int register (BoardDTO dto); // DTO받아서 int로 반환하는 추상메서드. -> BoardServiceImpl에서 구현
								// 게시물 받아서 등록 후 게시물 번호 반환
	
	 // ** dto-> entity객체로 변환하는 일반 메서드
		//	default키워드 이용해서 일반메서드 인-페에 생성 가능. (java8부터) 
		// -> 즉, 인페 상속받는 클래스가 공통으로 상용할 것은 default메서드로, 자손 각자가 구현해서 사용할것은 추상메서드로 인페에 선언.
		// 이전에 했던 것은 repository에서 바로 dto객체 생성해서 entity에 넣어버리는 거였지. 지금하는건 저장된 dto에서 꺼내기
	default Board dtoToEntity(BoardDTO dto) { // 회원등록시에 dto를 엔티티로 바꿔야 repository에 저장할 수 있지.
//				ㄴ Entity	 <=		ㄴ DTO
		Member member = Member.builder(). //8장 p.26
						id(dto.getWriter())
						.build();
		
		Board entity = Board.builder()
					.no(dto.getNo())
					.title(dto.getTitle())
					.content(dto.getContent())
					.writer(member) //8장 p.26 =>54행과 연관
					.build();

			return entity;
			// dto데이터 받아서 entity로 옮기기
		}		
   

	// 2. 게시물 목록조회 (p.19-21)
	
	//List<BoardDTO> getList(); // DTO타입 게시물목록 반환하는 추상 메서드 -> BoardServiceImpl에서 구현 - 페이지 정렬위해 삭제함	
	
	Page<BoardDTO> getList(int pageNumber);

	// ** repository에서 추출한 엔티티-> dto객체로 변환하는 일반 메서드
		//	default키워드 이용해서 일반메서드 인-페에 생성 가능. (java8부터) 
		// -> 즉, 인페 상속받는 클래스가 공통으로 상용할 것은 default메서드로, 자손 각자가 구현해서 사용할것은 추상메서드로 인페에 선언.
	default BoardDTO entityToDTO(Board entity) { //(엔티티에서 dto추출해야 화면 view단에 넘겨줄 수 있으니까)
//	   		ㄴ DTO	 	<=  		ㄴ Entity
			BoardDTO dto = BoardDTO.builder()
					.no(entity.getNo()).title(entity.getTitle())
					.content(entity.getContent())
					.writer(entity.getWriter().getId()) //8장 p.26
					.regDate(entity.getRegDate()).modDate(entity.getModDate())
					.build();
			return dto;
			
		}
	
	// 3. 게시물 상세조회 (p.38~)
		BoardDTO read(int no); // 게시물 번호 입력받아서 dto형태로 출력하는 추상메서드
  
		
	// 4. 게시물 수정 (p.46~)	
		void modify(BoardDTO dto); // 수정한 ｄｔｏ 입력받아서 데이터 수정하는 추상메서드
		
		
	// 5. 게시물 삭제 (p.56~)
		int remove(int no); // 결과 알려주려고 하다보니 반환타입들어감. 아니면 그냥 void였었음.
		
}


































