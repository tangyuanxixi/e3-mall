package cn.e3mall.itemService;


import java.util.List;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.util.E3Result;
import cn.e3mall.pojo.TbItem;

public interface ItemService {
	
	TbItem getItemById(long itemId);
	EasyUIDataGridResult getItemList(int page,int rows);
	E3Result addItem(TbItem item,String desc);
	E3Result deleteItem(List<Long> itemId);
	E3Result updateItem(TbItem item,String desc);
}
