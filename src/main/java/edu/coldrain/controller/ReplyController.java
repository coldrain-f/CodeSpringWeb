package edu.coldrain.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.coldrain.domain.Criteria;
import edu.coldrain.domain.ReplyVO;
import edu.coldrain.service.ReplyService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/replies/*")
@RestController
@AllArgsConstructor
@Log4j
public class ReplyController {

	private ReplyService service;
	
	//추가
	//클라이언트가 전송하는 데이터가 JSON을 명시 ( 소모하는 데이터가 JSON )
	//서버에서 클라이언트에 응답해주는 데이터가 단순 문자열임을 명시 ( 생산하는 데이터가 TEXT_PLAIN )
	@PostMapping(
			value = "/new", 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.TEXT_PLAIN_VALUE) //@RequestBody를 명시해서 전송 받은 JSON을 ReplyVO 객체로 변환하도록 설정
	public ResponseEntity<String> create(@RequestBody ReplyVO reply) {
		log.info("ReplyVO = " + reply);
		
		int insertCount = service.register(reply);
		
		log.info("REPLY INSERT COUNT = " + insertCount);
		
		return insertCount == 1 ? 
				new ResponseEntity<>("success", HttpStatus.OK) 
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
	}
	
	//목록
	@GetMapping(
			value = "/pages/{bno}/{page}",
			produces = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE
			})
	public ResponseEntity<List<ReplyVO>> getList(
			@PathVariable("bno") Long bno,
			@PathVariable("page") int page) {
		
		log.info("ReplyController.getList()");
		
		Criteria criteria = new Criteria(page, 10);
		log.info("CRITERIA = " + criteria);
		
		List<ReplyVO> list = service.getList(criteria, bno);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	//조회
	@GetMapping(
			value = "/{rno}",
			produces = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE
			})
	public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno) {
		log.info("RNO = " + rno);
		
		//방법 1
		ReplyVO reply = service.get(rno);
		ResponseEntity<ReplyVO> entity = ResponseEntity.status(HttpStatus.OK).body(reply);
		return entity;
		
		
		//방법 2
		// return ResponseEntity<>(service.get(rno), HttpStatus.OK);
	}
	
	//삭제
	@DeleteMapping(
			value = "/{rno}",
			produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> remove(@PathVariable("rno") Long rno) {
		log.info("RNO = " + rno);
		
		return service.remove(rno) == 1 
				? new ResponseEntity<>("success", HttpStatus.OK) 
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//수정 ( 테스트 진행시 수정 안 되는 문제 발견 )
	@RequestMapping(
			value = "/{rno}",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> modify(@RequestBody ReplyVO reply, @PathVariable("rno") Long rno) {
		log.info("RNO = " + rno);
		//오탈자 같음
		reply.setRno(rno);
		log.info("REPLY = " + reply);
		
		return service.modify(reply) == 1 
				? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
