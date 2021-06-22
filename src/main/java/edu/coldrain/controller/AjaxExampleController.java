package edu.coldrain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sample/*")
public class AjaxExampleController {


	@GetMapping("")
	public String sample() {
		
		return "/board/ajaxExample";
	}
}
