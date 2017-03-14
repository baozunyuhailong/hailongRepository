package com.spring.project.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.project.mapper.ProductMapper;
import com.spring.project.model.Product;
import com.spring.project.model.QueryVo;
import com.spring.project.model.ResultModel;
import com.spring.project.service.ProductService;


/**
 * 实现类
 * @author Easong
 *
 */
@Service
public class ProductServiceImpl implements ProductService {

	// 每页显示的条数
	private final static Integer PAGE_SIZE = 32;

	@Autowired
	private ProductMapper productMapper;

	@Override
	public ResultModel<Product> findAll(QueryVo queryVo) throws Exception {

		// 创建solr查询对象
		SolrQuery solrQuery = new SolrQuery();

		// 设置查询关键字
		if (queryVo.getQueryString() != null && !"".equals(queryVo.getQueryString())) {
			solrQuery.setQuery("product_name:" +queryVo.getQueryString());
		} else {
			solrQuery.setQuery("*:*");
		}

		// 设置过滤查询--商品类别
		if (queryVo.getCatalog_name() != null && !"".equals(queryVo.getCatalog_name())) {
			solrQuery.setFilterQueries("catalog_name:" + queryVo.getCatalog_name());
		}

		// 设置价格排序
		if ("1".equals(queryVo.getSort())) {
			// 升序
			solrQuery.setSort("product_price", ORDER.asc);
		} else if("0".equals(queryVo.getSort())){
			// 降序
			solrQuery.setSort("product_price", ORDER.desc);
		}
		// 设置价格查询区间
		if (queryVo.getPrice() != null && !"".equals(queryVo.getPrice())) {
			String[] split = queryVo.getPrice().split("-");
			if (split != null && split.length > 1) {
				solrQuery.setFilterQueries("product_price:[" + split[0] + " TO " + split[1] + "]");
			}
		}

		// 分页查询
		if (queryVo.getPage() == null) {
			queryVo.setPage(1);
		}
		Integer currPage = queryVo.getPage();
		// 开始索引
		solrQuery.setStart((currPage - 1) * PAGE_SIZE);
		// 每页显示条数
		solrQuery.setRows(PAGE_SIZE);

		// 设置默认搜索域
		solrQuery.set("df", "product_keywords");
		// 开启高亮显示
		solrQuery.setHighlight(true);
		// 设置显示域名
		solrQuery.addHighlightField("product_name");
		// 设置前缀
		solrQuery.setHighlightSimplePre("<span style=\"color:red\">");
		// 设置后缀
		solrQuery.setHighlightSimplePost("</span>");
		// 调用Mapper层
		ResultModel<Product> resultModel = productMapper.findAll(solrQuery);
		// 设置当前页
		resultModel.setCurPage(currPage.longValue());
		// 设置总页数
		Double pageSize = Math.ceil(resultModel.getRecordCount().doubleValue() / PAGE_SIZE);
		resultModel.setPageCount(pageSize.longValue());
		return resultModel;
	}

}
