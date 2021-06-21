package edu.coldrain.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	@Test
	public void testGetTotalCount() {
		Criteria criteria = new Criteria();
		int totalCount = boardMapper.getTotalCount(criteria);
		log.info("TOTAL COUNT = " + totalCount);
	}
	
	// 검색 처리
	@Test
	public void testSearch() {
		Map<String, String> map = new HashMap<>();
		map.put("T", "TTT");
		map.put("C", "CCC");
		map.put("W", "WWW");
		
		Map<String, Map<String, String>> outer = new HashMap<>();
		outer.put("map", map);
		
		List<BoardVO> list = boardMapper.searchTest(outer);
	}
	// 검색 처리 테스트 2
	@Test
	public void testSearch2() {
		// pageNum = 1 and amount = 10
		Criteria criteria = new Criteria();
		
		criteria.setType("WT");
		criteria.setKeyword("USER01");
		
		List<BoardVO> list = boardMapper.searchTest2(criteria);
		list.forEach(board -> log.info(board));
	}
	
	@Test
	public void testSearchPaging() {
		Criteria criteria = new Criteria();
		
		criteria.setPageNum(2);
		criteria.setAmount(10);
		criteria.setType("W");
		criteria.setKeyword("USER01");
		
		List<BoardVO> list = boardMapper.getListWithpaging(criteria);
	}
	
}