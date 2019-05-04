package cn.e3mall.content.service;

import java.util.List;

import cn.e3mall.common.util.E3Result;
import cn.e3mall.pojo.TbContent;
import cn.e3mall.pojo.TbContentCategory;

public interface ContentService {

	public E3Result addContent(TbContent content);
	public List<TbContent> getContentListByCid(long cid);
}
