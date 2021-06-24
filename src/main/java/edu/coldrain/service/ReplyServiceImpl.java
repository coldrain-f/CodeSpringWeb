package edu.coldrain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.coldrain.domain.Criteria;
import edu.coldrain.domain.ReplyPageDTO;
import edu.coldrain.domain.ReplyVO;
import edu.coldrain.mapper.ReplyMapper;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class ReplyServiceImpl implements ReplyService {

	
	@Autowired
	private ReplyMapper mapper;
	
	@Override
	public int register(ReplyVO reply) {
		log.info("ReplyServiceImpl.register()");
		return mapper.insert(reply);
	}

	@Override
	public ReplyVO get(Long rno) {
		log.info("ReplyServiceImpl.get()");
		return mapper.read(rno);
	}

	@Override
	public int modify(ReplyVO reply) {
		log.info("ReplyServiceImpl.modify()");
		return mapper.update(reply);
	}

	@Override
	public int remove(Long rno) {
		log.info("ReplyServiceImpl.remove()");
		return mapper.delete(rno);
	}

	@Override
	public List<ReplyVO> getList(Criteria criteria, Long bno) {
		log.info("ReplyServiceImpl.getList()");
		return mapper.getListWithPaging(criteria, bno);
	}

	@Override
	public ReplyPageDTO getListPage(Criteria criteria, Long bno) {
		
		return new ReplyPageDTO(mapper.getCountByBno(bno), mapper.getListWithPaging(criteria, bno));
	}

}
