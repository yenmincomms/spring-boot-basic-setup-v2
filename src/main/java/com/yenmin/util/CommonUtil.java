package com.yenmin.util;

import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;



public class CommonUtil {
	
	
	
	
	public static Pageable getPageRequest(Optional<Integer> pageIdx, Optional<Integer> pageSize, Optional<String> sortKey, Optional<String> orderDirection) {

		int pageSearchInx = pageIdx.isPresent() ? pageIdx.get() : Constants.PAGENATION_ID;
		int pageSearchSize = pageSize.isPresent() ? pageSize.get() : Constants.PAGENATION_SIZE;
		String pageSortKey = sortKey.isPresent() ? sortKey.get() : Constants.PAGE_SORT_KEY;
		Sort.Direction direction;
		if (orderDirection.isPresent()) {
			direction = Constants.PAGE_SEARCH_SORT_DIRECTION.equalsIgnoreCase(orderDirection.get()) ? Sort.Direction.DESC
					: Sort.Direction.ASC;
		} else {
			direction = Sort.Direction.ASC;
		}
		return PageRequest.of(pageSearchInx, pageSearchSize, direction, pageSortKey);
	}

}
