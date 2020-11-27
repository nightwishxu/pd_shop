package com.api.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

@ApiModel
public class PageLimit {

	@ApiModelProperty(value="页数")
	@ApiParam(required = true)
	private Integer pageNum;
	@ApiModelProperty(value="每页条数")
	@ApiParam(required = true)
	private Integer pageSize;

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
