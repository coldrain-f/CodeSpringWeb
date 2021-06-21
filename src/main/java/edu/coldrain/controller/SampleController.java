package edu.coldrain.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.coldrain.domain.BoardVO;
import edu.coldrain.domain.SampleVO;
import edu.coldrain.service.BoardService;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/sample/*")
@Log4j
public class SampleController {
	
	@Autowired
	BoardService service;

	@GetMapping(value = "/getText", produces = "text/plain;charset=UTF-8")
	public String getText() {
		log.info("SampleController.getText()");
		
		return "단순 문자열";
	}
	
	@GetMapping(value = "/getSample", produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE
	})
	public SampleVO getSample() {
		log.info("SampleController.getSample()");
		SampleVO sample = new SampleVO(1, "스타", "로드");
		
		return sample;
	}
	
	@GetMapping(value = "/getList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<SampleVO> getList() {
		List<SampleVO> list = new ArrayList<>();
		for (int i = 1; i < 10; i++) {
			list.add(new SampleVO(i, ( i + "first" ), ( i + "last" ) ));
		}
		
		return list;
	}
	
	//502 bad gateway
	@GetMapping(value = "/badGateway", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SampleVO> badGateway() {
		SampleVO sample = new SampleVO(1, "스타", "로드");
		
		//정상적인 데이터가 아니다.
		ResponseEntity<SampleVO> entity = null;
		entity = ResponseEntity.status(HttpStatus.BAD_GATEWAY)
							   .body(sample);
		
		return entity;
	}
	
	//200 ok
	@GetMapping(value = "/ok", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<SampleVO> ok() {
		SampleVO sample = new SampleVO(2, "그루트", "주니어");
		
		//정상적인 데이터이다.
		ResponseEntity<SampleVO> entity = null;
		entity = ResponseEntity.status(HttpStatus.OK)
							   .body(sample);
		
		return entity;
	}
	
	//@PathVariable 사용하기
	@GetMapping(value = "/get/{sno}", produces = MediaType.APPLICATION_JSON_VALUE) //값을 가져올 때는 Primitive type은 사용할 수 없다.
	public ResponseEntity<SampleVO> getPath(@PathVariable("sno") Integer sno) {
		log.info("SampleController.getPath()");
		
		log.info("SNO = " + sno);
		SampleVO sample = new SampleVO(sno, "그루트", "주니어");
		ResponseEntity<SampleVO> entity = ResponseEntity.status(HttpStatus.OK)
														.body(sample);
		
		return entity;
	}
	
	//@PathVariable 응용하기  ( 게시글 하나 조회 )
	@GetMapping(value = "/board/get/{bno}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BoardVO> getBoard(@PathVariable("bno") Long bno) {
		log.info("SampleController.getBoard()");
		
		BoardVO board = service.get(bno);
		log.info("BOARD = " + board);
		
		ResponseEntity<BoardVO> entity = ResponseEntity.status(HttpStatus.OK)
													   .body(board);
		
		return entity;
	}
	
	// 게시글 리스트 조회
	@GetMapping(value = "/boards", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BoardVO>> getBoards() {
		log.info("SampleController.getBoards()");
		
		List<BoardVO> list = service.getList();
		ResponseEntity<List<BoardVO>> entities = ResponseEntity.status(HttpStatus.OK)
													          .body(list);
		
		return entities;
	}
}
