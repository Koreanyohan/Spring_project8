package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.dto.MemberDTO;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberRepository repository;

// 1. 페이지 정렬 이용한 목록 조회
	@Override 
	public Page<MemberDTO> getList(int pageNumber) { // 페이지 번호 받기
		// 매개변수로 받은 페이지 번호를 인덱스로 변경
		int pageIndex = (pageNumber == 0) ? 0 : pageNumber - 1;

		// 정렬방법 (등록일 기준 역정렬 => 최근 가입자부터 나오게 함)
		Sort sort = Sort.by("regDate").descending();
		// 페이지번호, 데이터개수, 정렬방법을 입력하여 페이징 조건 생성 (Pageable 인페라 PageRequest.of로 객체 생성)
		Pageable pageable = PageRequest.of(pageIndex, 10, sort);

		// 게시물 목록조회 (MemberRepository의 객체인 repository에서 조건맞는)
		Page<Member> entityPage = repository.findAll(pageable);
		// ㄴ .findAll(Pageable객체)=> Page로 반환(리스트 + 페이지정보)
		// 엔티티페이지를 DTO페이지로 변환 (스트림같지만, 최종연산없음)
		Page<MemberDTO> dtoPage = entityPage.map(entity -> entityToDto(entity));

		return dtoPage;

	}

// 2. 상세조회	
	@Override
	public MemberDTO read(String id) {

		Optional<Member> result = repository.findById(id); // ById 에서 Id는 pk의미

		if (result.isPresent()) { // 데이터 존재유무 판단
			Member member = result.get();
			return entityToDto(member);
		} else {
			return null;
		}
	}

// 3. 등록	
	@Override
	public boolean register (MemberDTO dto) {
		// 아이디 중복 여부 확인 ~
		String id = dto.getId();
		
		MemberDTO getDto = read(id);
		
		if (getDto != null) { 
			System.out.println("사용중인 아이디입니다.");
			return false; // 해당 아이디가 사용중이라면 false반환
		}		 // ~확인 끝
		
		//아이디 중복 x시에 진행
		Member entity = dtoToEntity(dto); // dto를 엔티티로 바꿔야 repository에 저장(등록)가능이지
		repository.save(entity); // dto에서 변환된 entity를 repository에 등록하기
		return true;

	}
}
