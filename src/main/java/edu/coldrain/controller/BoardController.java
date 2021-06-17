package edu.coldrain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.coldrain.domain.BoardVO;
import edu.coldrain.domain.Criteria;
import edu.coldrain.domain.PageDTO;
import edu.coldrain.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board/*")
@Log4j
public class BoardController {
	
	private final BoardService service;
	
	@GetMapping("/list")
	public void list(Criteria criteria, Model model) {
		log.info("BoardController.list()");
		
		log.info("CRITERIA = " + criteria);
		
		model.addAttribute("list", service.getList(criteria));
		model.addAttribute("pageMaker", new PageDTO(criteria, 250));
	}
	
	@GetMapping("/register")
	public void registerForm() {
		
	}
	
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		log.info("BoardController.register()");
		
		log.info("BOARD = " + board);
		Long bno = service.register(board);
		
		log.info("BNO = " + bno);
		
		rttr.addFlashAttribute("result", bno); //내부적으로 HttpSession으로 저장된다.
		
		return "redirect:/board/list";
	}
	
	@GetMapping({ "/get", "/modify" })
	public void get(@RequestParam("bno") Long bno, Model model) {
		model.addAttribute("board", service.get(bno));
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO board, RedirectAttributes rttr) {
		
		boolean success = service.modify(board);
		if (success) {
			rttr.addFlashAttribute("result", "success");
		}
		
		return "redirect:/board/list";
	}
	
	@PostMapping("/remove")
	public String remove(Long bno, RedirectAttributes rttr) {
		
		boolean success = service.remove(bno);
		if (success) {
			rttr.addFlashAttribute("result", "success");
		}
		
		return "redirect:/board/list";
	}
}
