// 8장 p.35 
package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.MemberDTO;
import com.example.demo.entity.Member;
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
	
	
// 등록화면  8장 p.44
	@GetMapping("/register")
	public void register() {
		
	}
	
// 등록처리	 (register.html에서 등록버튼 누르면 활성화)
	@PostMapping("/register") // 위의 메서드는 void 반환이고 이거는 String 반환이라 "~" 부분 같아도 노상관.
	public String registerPost (MemberDTO dto, RedirectAttributes redirectAttributes ) {
								// ㄴ 화면에서 전달받은 게시물정보. 즉, 파라미터	ㄴ 전달자 객체 (model과 기능은 같다. redirect할때 쓸 뿐). 여기에 우리가 등록창에서 입력한 것이 들어옴.
    	// 게시물 등록하고 새로운 게시물 번호 반환
		boolean isSuccess = service.register(dto);
		
		if(isSuccess) { //register메서드 성공(중복아이디 없음)
			return "redirect:/member/list";
		} else { //register메서드 실패(중복아이디 있음)
			redirectAttributes.addFlashAttribute("msg", "아이디가 중복되어 등록에 실패했습니다"); // 컨트롤러 -> view . cf)수정에서 post매핑된 메서드는 .addAttribute다!!
		}
		
		return "redirect:/member/register";				
	}
	
	
// 상세화면 8장 p.53	
	@GetMapping("/read")
	public void read (@RequestParam(name="id") String id, 
					  @RequestParam(defaultValue="0", name="page") int page, Model model) {
		MemberDTO dto = service.read(id);
		
		model.addAttribute("dto", dto);
		
		model.addAttribute("page", page);
		
		
	}
}



























