package edu.coldrain.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.coldrain.domain.Criteria;
import edu.coldrain.domain.ReplyVO;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {
	
	@Autowired
	private ReplyMapper mapper;
	
	@Test
	public void testMapper() {
		log.info(mapper);
	}
	
	@Test
	public void testInsert() {
		for (Long i =  61L; i <= 65L; i++) {
			ReplyVO reply = new ReplyVO();
			reply.setBno(i);
			reply.setReply("댓글 테스트 " + i);
			reply.setReplyer("replyer " + i);
			
			mapper.insert(reply);
		}
	}
	
	@Test
	public void testRead() {
		ReplyVO reply = mapper.read(10L);
		log.info(reply);
	}
	
	@Test
	public void testDelete() {
		mapper.delete(5L);
	}
	
	@Test
	public void testUpdate() {
		ReplyVO reply = mapper.read(10L);
		reply.setReply("Update Reply");
		
		log.info("REPLY = " + reply);
		
		int success = mapper.update(reply);
		
		log.info("UPDATE SUCCESS = " + success);
	}
	
	@Test
	public void testGetListWithPaging() {
		List<ReplyVO> replies = mapper.getListWithPaging(new Criteria(), 61L);
		replies.forEach(reply -> log.info(reply));
	}
}
