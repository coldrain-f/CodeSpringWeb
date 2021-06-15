package edu.coldrain.service;

import java.util.List;

import edu.coldrain.domain.BoardVO;

public interface BoardService {

	public Long register(BoardVO board);
	
	public BoardVO get(Long bno);
	
	public boolean modify(BoardVO board);
	
	public boolean remove(Long bno);
	
	public List<BoardVO> getList();
}
