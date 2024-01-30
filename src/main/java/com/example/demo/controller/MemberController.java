// 8장 p.35 
package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.MemberDTO;
import com.example.demo.service.MemberService;

@Controller
@RequestMapping ("/member")
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	
	@GetMapping("/list") // 8장 p.35 
	public void list(@RequestParam(defaultValue="0", name="page") int page, Model model) { //(파라미터 / 모델 객체=>view단에 데이터 전달)
									// ㄴ !! 사용자가 파라미터(page) 안적었을 경우(400에러) 대입시킬 기본 값
    	// 게시물 목록 조회
		Page<MemberDTO> list = service.getList(page);
		// 화면에 결과 데이터 전달
		model.addAttribute("list", list);
		
		System.out.println("전체 페이지 수: " + list.getTotalPages());
    	System.out.println("전체 게시물 수: " + list.getTotalElements());
    	System.out.println("현재 페이지 번호 : " + list.getNumber() + 1);
    	System.out.println("페이지에 표시할 게시물 수 : " + list.getNumberOfElements());
	}
}
