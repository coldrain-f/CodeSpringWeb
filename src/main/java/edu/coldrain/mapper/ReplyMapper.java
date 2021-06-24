package edu.coldrain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import edu.coldrain.domain.Criteria;
import edu.coldrain.domain.ReplyVO;

public interface ReplyMapper {

	public int insert(ReplyVO reply);
	
	public ReplyVO read(Long rno);
	
	public int delete(Long rno);
	
	public int update(ReplyVO reply);
	
	public List<ReplyVO> getListWithPaging(
			@Param("criteria") Criteria criteria,
			@Param("bno") Long bno);
	
	public int getCountByBno(Long bno);
}
