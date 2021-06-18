package edu.coldrain.mapper;

import java.util.List;

import edu.coldrain.domain.BoardVO;
import edu.coldrain.domain.Criteria;

public interface BoardMapper {

	public List<BoardVO> getList();
	
	public int insert(BoardVO board);
	
	public int insertSelectKey(BoardVO board);
	
	public BoardVO read(Long bno);
	
	public int delete(Long bno);
	
	public int update(BoardVO board);
	
	public List<BoardVO> getListWithpaging(Criteria criteria);
	
	int getTotalCount(Criteria criteria);
}
