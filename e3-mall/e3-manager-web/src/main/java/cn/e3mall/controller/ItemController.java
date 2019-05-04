package cn.e3mall.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.util.E3Result;
import cn.e3mall.itemService.ItemService;
import cn.e3mall.pojo.TbItem;
@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	/**
	 * 获取商品分类信息
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId){
		TbItem tbItem = itemService.getItemById(itemId);
		return tbItem;
	}
	/**
	 * 获取所有商品信息
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(Integer page,Integer rows){
		EasyUIDataGridResult result = itemService.getItemList(page, rows);
		return result;
	}
	/**
	 * 增加商品信息
	 * @param item
	 * @param desc
	 * @return
	 */
	@RequestMapping(value="/item/save",method=RequestMethod.POST)
	@ResponseBody
	public E3Result addItem(TbItem item,String desc){
		E3Result result = itemService.addItem(item, desc);
		return result;
	}
	@RequestMapping(value="/item/update",method=RequestMethod.POST)
	@ResponseBody
	public E3Result editItem(TbItem item,String desc){
		E3Result result = itemService.updateItem(item, desc);		
			return result;
	}
	/**删除商品信息
	 * @param ids
	 * @return
	 */
	@RequestMapping("/rest/item/delete")
	@ResponseBody
	public  E3Result deleteItemList(String ids)
	{
		List<Long> list = new ArrayList<Long>();
		if(ids.contains(","))
		{
		
			String[] str_ids = ids.split(",");
			for (String id : str_ids) {
				list.add(Long.parseLong(id));
			}
		}
		else
		{
			Long id = Long.parseLong(ids);
			list.add(id);
		}
		E3Result result = itemService.deleteItem(list);

		return result;
	}
}
