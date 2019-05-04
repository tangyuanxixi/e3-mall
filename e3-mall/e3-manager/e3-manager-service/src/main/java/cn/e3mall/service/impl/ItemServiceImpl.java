package cn.e3mall.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.util.E3Result;
import cn.e3mall.common.util.IDUtils;
import cn.e3mall.itemService.ItemService;
import cn.e3mall.mapper.TbItemDescMapper;
import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.pojo.TbItemExample;
import cn.e3mall.pojo.TbItemExample.Criteria;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	/*  根据商品id查询商品
	 * @see cn.e3mall.itemService.ItemService#getItemById(long)
	 */
	@Override
	public TbItem getItemById(long itemId) {
		
		TbItem item = itemMapper.selectByPrimaryKey(itemId);
		return item;
		
	}
	/* 返回所有商品信息
	 * @see cn.e3mall.itemService.ItemService#getItemList(int, int)
	 */
	@Override
	public EasyUIDataGridResult getItemList(int page, int rows) {
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询,按条件查询
		TbItemExample example = new TbItemExample();
		//查询到所有商品
		List<TbItem> list = itemMapper.selectByExample(example);
		//取分页结果
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		
		return result;
	}
	/* 添加商品信息
	 * @see cn.e3mall.itemService.ItemService#addItem(cn.e3mall.pojo.TbItem, java.lang.String)
	 */
	@Override
	public E3Result addItem(TbItem item, String desc) {
		//生成商品id
		long itemId = IDUtils.genItemId();
		//补全商品信息，在商品录入中并没有ID
		item.setId(itemId);
		//设置商品状态，录入中并没有状态
		//商品状态，1-正常，2-下架，3-删除
		item.setStatus((byte) 1);
		Date date = new Date();
		item.setCreated(date);
		item.setUpdated(date);
		//插入商品信息表
		itemMapper.insert(item);
		//创建商品描述表
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		//插入商品描述表
		itemDescMapper.insert(itemDesc);
		return E3Result.ok();
	}
	/* 根据商品id删除商品
	 * @see cn.e3mall.itemService.ItemService#deleteItem(long)
	 */
	@Override
	public E3Result deleteItem(List<Long> itemId) {
		TbItemExample itemExample = new TbItemExample();
		Criteria criteria = itemExample.createCriteria();
		criteria.andIdIn(itemId);
		itemMapper.deleteByExample(itemExample);
		return E3Result.ok();
	}
	/* update item
	 * @see cn.e3mall.itemService.ItemService#updateItem(cn.e3mall.pojo.TbItem, java.lang.String)
	 */
	@Override
	public E3Result updateItem(TbItem item,String desc) {
		item.setStatus((byte) 1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		itemMapper.updateByPrimaryKey(item);
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setUpdated(new Date());
		itemDescMapper.updateByPrimaryKey(itemDesc);
		return E3Result.ok();
	}

}
