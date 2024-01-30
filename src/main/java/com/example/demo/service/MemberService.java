package com.example.demo.service;

import org.springframework.data.domain.Page;

import com.example.demo.dto.MemberDTO;
import com.example.demo.entity.Member;

public interface MemberService { // default 접근제한자로 선언된 메서드 => 자식이 상속받는것과 99퍼센트 흡사. 다른점은 오버라이딩이 안됨(확실 x. 책 보자)
	
// 1. 회원 목록 조회 (page번호 받아서)  (p.34)
	Page<MemberDTO> getList(int pageNumber);
  // ㄴ 회원 리스트+페이지 정보
	
	// 엔티티를 DTO로 변환하는 메서드 (엔티티에서 dto추출해야 화면 view단에 넘겨줄 수 있으니까)
	default MemberDTO entityToDto(Member entity) {
		
		MemberDTO dto = MemberDTO.builder()
				.id(entity.getId()).password(entity.getPassword())
				.name(entity.getName())
				.regDate(entity.getRegDate()).modDate(entity.getModDate())
				.build();
				
		return dto;
	};
	
	
// 2. 회원 단건 조회(상세조회)
	MemberDTO read(String id);
	
	
// 3. 회원 등록 (8장 p.42)
	boolean register(MemberDTO dto); // 회원등록시에 dto를 엔티티로 바꿔야 repository에 저장할 수 있지. ↓ 
	  //  회원등록 성공실패유무 알려주기 위해서 boolean으로 반환값 설정됨. 
	//엔티티를 DTO로 변환하는 메서드
	default Member dtoToEntity (MemberDTO dto) {
		Member member = Member.builder()
							.id(dto.getId()).Password(dto.getPassword()).name(dto.getName())
							.build();
					// cf) dto에서 입력받을때 regDate, modDate는 우리가 입력한 값이 아니므로 객체 생성시 넣지 않음.
		return member;
	}
}
