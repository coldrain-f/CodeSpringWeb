package edu.coldrain.mapper;

import java.util.List;

import edu.coldrain.domain.BoardVO;

public interface BoardMapper {

	public List<BoardVO> getList();
	
	public int insert(BoardVO board);
	
	public int insertSelectKey(BoardVO board);
	
	public BoardVO read(Long bno);
	
	public int delete(Long bno);
	
	public int update(BoardVO board);
}
