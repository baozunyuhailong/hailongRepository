package com.spring.project.service;

import com.spring.project.model.Product;
import com.spring.project.model.QueryVo;
import com.spring.project.model.ResultModel;

public interface ProductService {
	/**
	 * 分页条件查询所有商品
	 * 
	 * @param solrQuery
	 * @return
	 * @throws Exception
	 */
	public ResultModel<Product> findAll(QueryVo queryVo) throws Exception;
}
