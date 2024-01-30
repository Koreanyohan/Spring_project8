// 8장 p.34
package com.example.demo.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.example.demo.dto.MemberDTO;

@SpringBootTest
public class MemberServiceTest {

	@Autowired
	MemberService service; //MemberService인페 구현한 MemberServiceImpl 객체로서 생성됨
	
	@Test
	void 회원목록1페이지조회 () { // p.34
		
		Page<MemberDTO> page = service.getList(2);
				
		List<MemberDTO> list = page.getContent();//Page.getContent()=> List로 반환
		
		for(MemberDTO dto:list) {
			System.out.println(dto);
		}
	}
	
	
	@Test
	void 회원등록1 () { // p.43
		MemberDTO dto1 = MemberDTO.builder()
				.id("wndygks").password("1234").name("주요한")
				.build();
		MemberDTO dto2 = MemberDTO.builder()
				.id("JJanggu").password("1234").name("짱구")
				.build();
		
		service.register(dto1);
		service.register(dto2);	
		
	}
}

