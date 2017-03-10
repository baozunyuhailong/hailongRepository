package org.xdemo.example.SpringActivemq.mapper;

import org.apache.solr.client.solrj.SolrQuery;
import org.xdemo.example.SpringActivemq.model.Product;
import org.xdemo.example.SpringActivemq.model.ResultModel;


public interface ProductMapper {
	/**
	 * 分页条件查询所有商品
	 * @param solrQuery
	 * @return
	 * @throws Exception 
	 */
	public ResultModel<Product> findAll(SolrQuery solrQuery) throws Exception;
}
