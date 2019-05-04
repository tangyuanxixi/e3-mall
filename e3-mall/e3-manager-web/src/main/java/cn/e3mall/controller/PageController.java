package cn.e3mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.itemService.ItemService;

/**
 * 页面跳转视图控制器
 * @author Administrator
 *
 */
@Controller
public class PageController {

	@Autowired
	private ItemService itemService;
	@RequestMapping("/")
	public String showIndex(){
		return "index";
	}
	@RequestMapping("/{page}")
	public String PageInfo(@PathVariable String page)
	{
		return page;
	}
}
