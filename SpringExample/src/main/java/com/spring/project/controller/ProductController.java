package com.spring.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.spring.project.model.Product;
import com.spring.project.model.QueryVo;
import com.spring.project.model.ResultModel;
import com.spring.project.service.ProductService;

@Controller
@RequestMapping("/solrJD")
public class ProductController {

	@Autowired
	private ProductService productService;

	//@RequestMapping(value="/list.html", method = RequestMethod.GET)
	@RequestMapping("/list")
	public String list(QueryVo queryVo, Model model) throws Exception {
		ResultModel<Product> resultModel = productService.findAll(queryVo);
		// 将结果存入到model域中
		model.addAttribute("list", resultModel);
		// 查询条件回显
		model.addAttribute("queryString", queryVo.getQueryString());
		model.addAttribute("catalog_name", queryVo.getCatalog_name());
		model.addAttribute("product_price", queryVo.getPrice());
		model.addAttribute("curPage", queryVo.getPage());
		model.addAttribute("sort", queryVo.getSort());

		return "product_list";
	}
}
