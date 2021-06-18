package edu.coldrain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.coldrain.domain.BoardVO;
import edu.coldrain.domain.Criteria;
import edu.coldrain.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //final 키워드가 붙은 필드를 자동으로 주입해 준다.
public class BoardServiceImpl implements BoardService {
	
	private final BoardMapper mapper;

	@Override
	public Long register(BoardVO board) {
		mapper.insertSelectKey(board);
		return board.getBno();
	}

	@Override
	public BoardVO get(Long bno) {
		return mapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO board) {
		return mapper.update(board) == 1;
	}

	@Override
	public boolean remove(Long bno) {
		return mapper.delete(bno) == 1;
	}

	@Override
	public List<BoardVO> getList() {
		return mapper.getList();
	}

	@Override
	public List<BoardVO> getList(Criteria criteria) {
		return mapper.getListWithpaging(criteria);
	}

	@Override
	public int getTotalCount(Criteria criteria) {
		return mapper.getTotalCount(criteria);
	}
	
	
}
