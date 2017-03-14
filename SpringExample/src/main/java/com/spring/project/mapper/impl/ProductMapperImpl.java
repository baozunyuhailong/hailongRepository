package com.spring.project.mapper.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.soap.Text;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Repository;
import com.spring.project.mapper.ProductMapper;
import com.spring.project.model.Product;
import com.spring.project.model.ResultModel;

@Repository
public class ProductMapperImpl implements ProductMapper {

	@Override
	public ResultModel<Product> findAll(SolrQuery solrQuery) throws Exception {
		// 创建连接solr服务器对象
		SolrServer solrServer = new HttpSolrServer("http://192.168.92.130:8080/solr/collection1");
		// 创建ResultModel对象
		ResultModel<Product> results = new ResultModel<Product>();
		// 创建集合，存储Product
		List<Product> productList = new ArrayList<Product>();
		// 执行查询
		QueryResponse queryResponse = solrServer.query(solrQuery);
		// 获取Document结果集
		SolrDocumentList solrDocumentList = queryResponse.getResults();
		// 设置总记录数
		results.setRecordCount(solrDocumentList.getNumFound());
		// 高亮显示
		Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
		// 遍历结果集
		for (SolrDocument doc : solrDocumentList) {
			// 创建Product对象
			Product product = new Product();

			// 设置商品的编号
			product.setId((String) doc.get("id"));
			List<String> list = highlighting.get(doc.get("id")).get("product_name");

			if (list == null) {
				// 设置商品的名称
				product.setProduct_name(doc.get("product_name").toString());
			} else {
				// 设置高亮显示名称
				product.setProduct_name(list.get(0));
			}
			
			// 设置商品分类名称
			product.setCatalog_name((String) doc.get("catalog_name"));
			// 设置商品价格
			product.setProduct_price((Float) doc.get("product_price"));
			// 设置商品图片名称
			product.setProduct_picture((String) doc.get("product_picture"));
			// 将商品添加到集合中
			product.setDescription((String) doc.get("description"));
			productList.add(product);
		}
		results.setList(productList);
		return results;
	}

}
