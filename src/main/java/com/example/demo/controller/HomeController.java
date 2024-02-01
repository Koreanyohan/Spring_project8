// 8장 p.58
package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	// 메인화면
	@GetMapping("/")  // 아무 url없는 메인창 만들기
	public String home() {
		
		return "/home/main";
		
	}
}
