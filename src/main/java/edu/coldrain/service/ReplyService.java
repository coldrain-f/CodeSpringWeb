package edu.coldrain.service;

import java.util.List;

import edu.coldrain.domain.Criteria;
import edu.coldrain.domain.ReplyPageDTO;
import edu.coldrain.domain.ReplyVO;

public interface ReplyService {

	public int register(ReplyVO reply);
	
	public ReplyVO get(Long rno);
	
	public int modify(ReplyVO reply);
	
	public int remove(Long rno);
	
	public List<ReplyVO> getList(Criteria criteria, Long bno);
	
	public ReplyPageDTO getListPage(Criteria criteria, Long bno);
}
