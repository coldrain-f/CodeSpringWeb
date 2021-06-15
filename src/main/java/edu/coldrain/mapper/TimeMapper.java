package edu.coldrain.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {

	@Select("SELECT SYSDATE FROM DUAL")
	String getTime();
}
