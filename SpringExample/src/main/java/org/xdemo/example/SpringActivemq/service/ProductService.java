package org.xdemo.example.SpringActivemq.service;

import org.xdemo.example.SpringActivemq.model.Product;
import org.xdemo.example.SpringActivemq.model.QueryVo;
import org.xdemo.example.SpringActivemq.model.ResultModel;

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
