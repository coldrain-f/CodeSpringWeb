package edu.coldrain.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.coldrain.domain.BoardVO;
import edu.coldrain.domain.Criteria;
import edu.coldrain.domain.PageDTO;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {

	@Autowired
	private BoardMapper boardMapper;
	
	@Test
	public void testGetList() {
		log.info("BoardMapperTests.testGetList()");
		
		List<BoardVO> list = boardMapper.getList();
		list.forEach(board -> log.info(board));
	}
	
	@Test
	public void testInsert() {
		log.info("BoardMapperTests.testInsert()");
		
		BoardVO board = new BoardVO();
		board.setTitle("테스트 제목");
		board.setContent("테스트 내용");
		board.setWriter("테스트 작성자");
		
		boardMapper.insert(board);
		
		log.info("BOARD.BNO = " + board.getBno());
	}
	
	@Test
	public void testInsertSelectKey() {
		log.info("BoardMapperTests.testInsertSelectKey()");
		
		BoardVO board = new BoardVO();
		board.setTitle("테스트 제목");
		board.setContent("테스트 내용");
		board.setWriter("테스트 작성자");
		
		boardMapper.insertSelectKey(board);
		
		log.info("BOARD.BNO = " + board.getBno());
	}
	
	@Test
	public void testRead() {
		log.info("BoardMapperTests.testRead()");
		
		BoardVO board = boardMapper.read(162L);
		log.info(board);
	}
	
	@Test
	public void testDelete() {
		log.info("BoardMapperTests.testDelete()");
		
		int count = boardMapper.delete(162L);
		log.info("DELETE COUNT = " + count);
	}
	
	@Test
	public void testUpdate() {
		log.info("BoardMapperTests.testUpdate()");
		
		BoardVO board = boardMapper.read(161L);
		board.setTitle("테스트 제목 ( 수정 )");
		board.setContent("테스트 내용 ( 수정 )");
		board.setWriter("테스트 작성자 ( 수정 )");
		
		int count = boardMapper.update(board);
		log.info("UPDATE COUNT = " + count);
	}
	
	@Test
	public void testPaging() {
		Criteria criteria = new Criteria();
		
		List<BoardVO> list = boardMapper.getListWithpaging(criteria);
	}
	
	@Test
	public void testPageDTO() {
		Criteria criteria = new Criteria();
		PageDTO pageDTO = new PageDTO(criteria, 150);
		log.info(pageDTO);
	}
	
}