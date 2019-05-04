package cn.e3mall.content.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jboss.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.e3mall.common.jedis.JedisClient;
import cn.e3mall.common.util.E3Result;
import cn.e3mall.common.util.JsonUtils;
import cn.e3mall.content.service.ContentService;
import cn.e3mall.mapper.TbContentMapper;
import cn.e3mall.pojo.TbContent;
import cn.e3mall.pojo.TbContentExample;
import cn.e3mall.pojo.TbContentExample.Criteria;
import redis.clients.jedis.JedisCluster;

/**
 * @author Administrator
 *
 */
@Service
public class ContentServiceImpl implements ContentService {

	/*新增内容
	 * @see cn.e3mall.content.service.ContentService#addContent(cn.e3mall.pojo.TbContent)
	 */
	@Value("${CONTENT_LIST}")
	private String CONTENT_LIST;
	@Autowired
	TbContentMapper contentMapper;
	@Autowired
	JedisCluster jedisCluster;
	
	@Override
	public E3Result addContent(TbContent content) {
		//补全内容信息
		content.setCreated(new Date());
		content.setUpdated(new Date());
		content.setCategoryId((long) 89);
		//将内容插入数据库
		contentMapper.insert(content);
		//缓存同步，删除缓存中对应的数据
		jedisCluster.hdel(CONTENT_LIST, content.getCategoryId().toString());
		return E3Result.ok();
	}
	/*内容分类ID返回轮播图片集
	 * 查询缓存
	 *如果缓存中有直接响应缓存
	 *没有再查询数据库
	 *将查询的结果添加到缓存中
	 * @see cn.e3mall.content.service.ContentService#getContentListByCid(long)
	 */
	@Override
	public List<TbContent> getContentListByCid(long cid) {

		try {
			//如果缓存中有结果则直接响应
			String json = jedisCluster.hget(CONTENT_LIST, cid+"");
			if(StringUtils.isNotBlank(json))
			{
				List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
				return list;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		//设置查询条件
		criteria.andCategoryIdEqualTo(cid);
		List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
		//把查询结果加到缓存
		try {
			jedisCluster.hset(CONTENT_LIST, cid+"", JsonUtils.objectToJson(list));
		} catch (Exception e) {
			// 
		}
		return list;
	}

}
