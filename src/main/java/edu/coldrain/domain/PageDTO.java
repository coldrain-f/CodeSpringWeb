package edu.coldrain.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageDTO {

	private int startPage, endPage;
	private boolean prev, next;
	
	private int total;
	private Criteria Criteria;
	
	public PageDTO(Criteria criteria, int total) {
		this.Criteria = criteria;
		this.total = total;
		
		this.endPage = (int)Math.ceil(criteria.getPageNum() / 10.0) * 10;
		this.startPage = endPage - 9;
		
		this.prev = this.startPage > 1;
		
		int realEnd = (int)( Math.ceil(this.total * 1.0) / criteria.getAmount() );
		if (realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		
		this.next = this.endPage < realEnd;
	}
}
