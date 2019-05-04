package cn.e3mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.common.util.E3Result;
import cn.e3mall.content.service.ContentCategoryService;

@Controller
public class ContentCatController {

	@Autowired
	private ContentCategoryService contentCategoryService;
	
	/**
	 * 获取内容列表
	 * @param parentId
	 * @return
	 */
	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<EasyUITreeNode> getContentCatList(@RequestParam(name="id",defaultValue="0") Long parentId){
		
		List<EasyUITreeNode> contentCatList = contentCategoryService.getContentCatList(parentId);
		return contentCatList;
	}
	/**
	 * 添加分类节点
	 * @param parentId
	 * @param name
	 * @return
	 */
	@RequestMapping(value="/content/category/create",method=RequestMethod.POST)
	@ResponseBody
	public E3Result createContentCategory(long parentId,String name){
		E3Result result = contentCategoryService.addContentCategory(parentId, name);
		return result;
	}
	/**删除分类节点
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/content/category/delete/",method=RequestMethod.POST)
	@ResponseBody
	public E3Result deleteContentCategory(long id)
	{
		E3Result result = contentCategoryService.deleteContentCategory(id);
		return result;
	}
	/**
	 * 更新分类节点
	 * @param id
	 * @param name
	 * @return
	 */
	@RequestMapping(value="/content/category/update",method=RequestMethod.POST)
	@ResponseBody
	public E3Result updateContentCategory(long id,String name){
		E3Result result = contentCategoryService.updateContentCategory(id, name);
		return result;
	}
}
