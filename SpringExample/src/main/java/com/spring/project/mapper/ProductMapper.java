package com.spring.project.mapper;

import org.apache.solr.client.solrj.SolrQuery;
import com.spring.project.model.Product;
import com.spring.project.model.ResultModel;


public interface ProductMapper {
	/**
	 * 分页条件查询所有商品
	 * @param solrQuery
	 * @return
	 * @throws Exception 
	 */
	public ResultModel<Product> findAll(SolrQuery solrQuery) throws Exception;
}
