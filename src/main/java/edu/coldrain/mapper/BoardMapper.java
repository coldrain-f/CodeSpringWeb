package edu.coldrain.mapper;

import java.util.List;
import java.util.Map;

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
	
	//검색 처리
	public List<BoardVO> searchTest(Map<String, Map<String, String>> map);
}