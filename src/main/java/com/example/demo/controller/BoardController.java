package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.BoardDTO;
import com.example.demo.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService service;

    // 메인화면 (p.8)
    @GetMapping("/main")
    public void main() {
    }
    
    
    // 1. 목록화면 (p.22 -> p.26)
    @GetMapping ("/list")
    public void list(Model model) { // 컨트롤러 통해서 dto-> view. 따라서 view에 model이용해서 dto 보내면 된다.
    	List<BoardDTO> list = service.getList();
    	
    	model.addAttribute("list", list);
    }
    
    
    
    // 2-1. 등록화면 (p.32)
    @GetMapping("/register")
    public void register() { // register.html 호출
    	
    }
        
    // 2-2. 등록처리
    @PostMapping("/register") // 위의 메서드는 void 반환이고 이거는 String 반환이라 "~" 부분 같아도 노상관.
    public String registerPost(BoardDTO dto, RedirectAttributes redictRedirectAttributes) {
    						  	// ㄴ 화면에서 전달받은 게시물정보. 즉, 파라미터	ㄴ 전달자 객체 (model과 기능은 같다. redirect할때 쓸 뿐). 여기에 우리가 등록창에서 입력한 것이 들어옴.
    	// 게시물 등록하고 새로운 게시물 번호 반환
    	int no = service.register(dto);
    	
    	// 목록(list)화면에 새로운 게시물 번호 전달 (p.36-37 관련. list파일 51행가라)
    	redictRedirectAttributes.addFlashAttribute("msg", no); // 컨트롤러 -> view . cf)수정에서 post매핑된 메서드는 .addAttribute다!!
		
    	// 목록화면으로 이동. html경로 아님. url주소를 작성할 것.
    	return "redirect:/board/list"; // 매핑(mapping)되어 있는 주소. 따라서 이것은 30행의 클래스 호출
    	
    };

    
    // 3. 상세조회 (p.41)
    @GetMapping("/read")
    public void read(@RequestParam(name="no") int no, Model model) { // 게시물번호가 파라미터
    	BoardDTO dto = service.read(no); 
    	
    	model.addAttribute("dto", dto); // 컨트롤러 -> view 전달
    }
    
    
    // 4. 수정 (p.49)
    @GetMapping("/modify") // 수정 화면 메서드
    public void modify(@RequestParam(name="no") int no, Model model) {
    	
    	BoardDTO dto = service.read(no);
    	
    	model.addAttribute("dto", dto);
    	
    }
    
    @PostMapping("/modify") // 위의 메서드는 void 반환이고 이거는 String 반환이라 "~" 부분 같아도 노상관. // modify파일 56행~ 부분이다.
    public String modifyPost(BoardDTO dto, RedirectAttributes redirectAttributes) { // 수정 완료시 이루어지는 메서드다.
   						//     ㄴ 파라미터	    ㄴ 전달자 객체 (model과 기능은 같다. redirect할때 쓸 뿐). 여기에 우리가 등록창에서 입력한 것이 들어옴.
    	// 게시물 수정 메서드
    	service.modify(dto);
    	
    	// 리다이렉트 주소에 파라미터 추가 (?no=1)
    	redirectAttributes.addAttribute("no", dto.getNo()); // cf)등록에서 post매핑된 메서드는 .addFlashAttribute다!!
    	
    	// 상세화면으로 이동
    	return "redirect:/board/read"; // 매핑(mapping)되어 있는 주소. 따라서 이것은 63행의 클래스 호출
    	
    }    
    
    
    // 5. 삭제 (p.59)
    @PostMapping("/remove")
    public String removePost(@RequestParam(name="no") int no) {  // 선생님이 @RequestParam(name="no")붙이라해서 붙이긴했는데 없어도 되네.. list?=no로 표시되서 리다이렉트되는거 아니니 노상관아닌가
    	
    	service.remove(no);
    	
    	return "redirect:/board/list";
    	
    	
    }    
}




























