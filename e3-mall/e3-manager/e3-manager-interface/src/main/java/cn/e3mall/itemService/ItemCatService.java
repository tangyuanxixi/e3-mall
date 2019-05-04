package cn.e3mall.itemService;

import java.util.List;

import cn.e3mall.common.pojo.EasyUITreeNode;

public interface ItemCatService {

	public List<EasyUITreeNode> getItemCatById(long parentId);
}
