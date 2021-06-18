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
		//pageNum = 1&amount=10
		//total=15
		this.Criteria = criteria;
		this.total = total;
		
		//endPage = 10
		this.endPage = (int)Math.ceil(criteria.getPageNum() / 10.0) * 10;
		
		//startPage = 1
		this.startPage = endPage - 9;
		
		//prev = false
		this.prev = this.startPage > 1;
		
		//realEnd = 2
		int realEnd = (int) Math.ceil( (double)this.total / criteria.getAmount() );
		if (realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		//endPage = 2
		
		this.next = this.endPage < realEnd;
	}
}
