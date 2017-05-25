package com.lingang.core.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.stereotype.Service;

import com.lingang.api.domain.basic.JsonResult;
import com.lingang.api.domain.basic.Page;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.basic.StateCodeConstant;
import com.lingang.api.domain.entity.SysImages;
import com.lingang.api.domain.entity.SysNews;
import com.lingang.api.domain.para.SysNewsPara;
import com.lingang.api.domain.vo.SysNewsVo;
import com.lingang.api.service.SysNewsService;
import com.lingang.common.util.NewsUtil;
import com.lingang.core.persistence.reader.SysImagesReaderMapper;
import com.lingang.core.persistence.reader.SysNewsReaderMapper;
import com.lingang.core.persistence.writer.SysImagesWriterMapper;
import com.lingang.core.persistence.writer.SysNewsWriterMapper;

@Service("sysNewsService")
public class SysNewsServiceImpl implements SysNewsService {

	@Resource
	private SysNewsReaderMapper sysNewsReaderMapper;
	@Resource
	private SysNewsWriterMapper sysNewsWriterMapper;

	@Resource
	private SysImagesReaderMapper sysImagesReaderMapper;

	@Resource
	private SysImagesWriterMapper sysImagesWriterMapper;

	/**
	 * 查询新闻列表
	 */
	@Override
	public ServiceResult<Page<SysNewsVo>> selectSysNewsAll(Map<String, Object> map) {
		ServiceResult<Page<SysNewsVo>> result = new ServiceResult<Page<SysNewsVo>>();
		// Map<String, Object> map = new HashMap<String, Object>();
		Integer pageIndex = (Integer) map.get("pageIndex");
		Integer pageSize = (Integer) map.get("pageSize");
		int countRecord = sysNewsReaderMapper.selectSysNewsCount(map);
		Page<SysNewsVo> page = new Page<SysNewsVo>(pageIndex, countRecord, pageSize);
		if (countRecord > 0) {
			map.put("startIndex", page.getStartIndex());
			map.put("onePageCount", pageSize);
			List<SysNewsVo> list = sysNewsReaderMapper.selectSysNewsAll(map);
			page.setList(list);
		}
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setData(page);
		return result;
	}

	/**
	 * 查询新闻详情
	 * 
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 */
	@Override
	public ServiceResult<SysNewsVo> selectByPrimaryKeys(Integer newsId) {
		ServiceResult<SysNewsVo> result = new ServiceResult<>();
		SysNewsVo news = sysNewsReaderMapper.selectByPrimaryKeys(newsId);
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		String shareurl = "lingang-consumer/share/shareNewAndVideo.do?typeId=1&Id=" + news.getNewsId();
		String newsContentTitle = news.getNewsContent();// 把a的标签去掉
		
		news.setNewsContentTitle(NewsUtil.getTextFromHtml(newsContentTitle).substring(0, 25).concat("..."));
		news.setShareUrl(shareurl);
		result.setData(news);
		return result;
	}

	/********* 后台 *********/
	@Override
	public ServiceResult<Page<SysNewsVo>> queryAllByPage(SysNewsPara para) {
		ServiceResult<Page<SysNewsVo>> result = new ServiceResult<Page<SysNewsVo>>();

		// 查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("newAuthor", para.getNewAuthor());
		map.put("newTitle", para.getNewTitle());
		map.put("newsState", para.getNewsState());

		int countRecord = sysNewsReaderMapper.querySysNewsCount(map);
		Page<SysNewsVo> page = new Page<SysNewsVo>(para.getCurrentPage(), countRecord, para.getOnePageCount());
		if (countRecord > 0) {
			map.put("startIndex", page.getStartIndex());
			map.put("onePageCount", para.getOnePageCount());
			List<SysNewsVo> list = sysNewsReaderMapper.queryAllByPage(map);
			int len = 12;
			for (SysNewsVo sysNews : list) {
				String title = sysNews.getNewsTitle();
				title = title.length() < len ? title : title.substring(0, len) + "...";
				sysNews.setNewsTitle(title);
			}
			page.setList(list);
		}
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setData(page);
		return result;
	}

	@Override
	public JsonResult queryById(HashMap<String, Object> map) {
		Integer newsId = Integer.parseInt(map.get("newsId").toString());
		JsonResult jsonResult = new JsonResult();
		Map<String, Object> imgMap = new HashMap<String, Object>();
		imgMap.put("imgPath", "");
		if (newsId != null && newsId != 0) {
			SysNews news = sysNewsReaderMapper.queryById(newsId);
			if (news != null && news.getImgId() != null) {
				SysImages images = sysImagesReaderMapper.selectByPrimaryKey(news.getImgId());
				imgMap.put("imgPath", images.getImgPath());
			}
			jsonResult.setData(news);
			jsonResult.setDataMap(imgMap);
			jsonResult.setStateCode(StateCodeConstant.SUCCESS_CODE);
		} else {
			jsonResult.setStateCode("501");
			jsonResult.setMessage("参数异常");
		}
		return jsonResult;
	}

	@Override
	public JsonResult insert(HashMap<String, Object> map,Date newDate) {
		JsonResult jsonResult = new JsonResult();
		SysNews sysNews = new SysNews();
		String title = map.get("newsTitle").toString();
		String author = map.get("newsAuthor").toString();
		String content = map.get("newsContent").toString();
		if (title == null || title.isEmpty() || author == null || author.isEmpty() || content == null || content.isEmpty()) {
			jsonResult.setStateCode("501");
			jsonResult.setMessage("部分内容为空，请完善后再进行提交！");
			return jsonResult;
		}
		Integer newsIsup = Integer.parseInt(map.get("newsIsup").toString());
		Integer imgId = null;
		if (map.get("imgId") != null && !map.get("imgId").equals("")) {
			imgId = (Integer) map.get("imgId");
		}
		sysNews.setNewsTitle(title);
		sysNews.setNewsAuthor(author);
		sysNews.setNewsContent(content);
		sysNews.setNewsState(newsIsup);
		sysNews.setImgId(imgId);
		sysNews.setLookNumber(0);
		sysNews.setShareNumber(0);
		sysNews.setCreateTime(newDate);
		sysNews.setUpdateTime(newDate);
		int i = sysNewsWriterMapper.insertSelective(sysNews);
		if (i == 1) {
			jsonResult.setStateCode(StateCodeConstant.SUCCESS_CODE);
			if (sysNews.getImgId() != null) {
				SysImages images = new SysImages();
				images.setImgId(imgId);
				images.setObjId(sysNews.getNewsId());
				images.setImgState(1);
				sysImagesWriterMapper.updateByPrimaryKeySelective(images);
			}
		} else {
			jsonResult.setStateCode(StateCodeConstant.ERROR_CODE);
		}
		return jsonResult;
	}

	@Override
	public JsonResult update(SysNews news) {
		JsonResult jsonResult = new JsonResult();
		if (news != null) {
//			news.setUpdateTime(new Date());
			Integer success = sysNewsWriterMapper.updateByPrimaryKeyWithBLOBs(news);
			if (news.getImgId() != null) {
				SysImages img = sysImagesReaderMapper.selectByPrimaryKey(news.getImgId());
				if (img.getImgState() != 1) {
					img.setImgState(1);
					sysImagesWriterMapper.updateByPrimaryKeySelective(img);
				}
			}
			if (success > 0) {
				jsonResult.setStateCode(StateCodeConstant.SUCCESS_CODE);
				jsonResult.setMessage("修改成功！");
			} else {
				jsonResult.setStateCode("500");
				jsonResult.setMessage("修改失败，系统异常，请稍后再试！");
			}
		} else {
			jsonResult.setStateCode("501");
			jsonResult.setMessage("参数异常！");
		}

		return jsonResult;
	}

	@Override
	public JsonResult del(HashMap<String, Object> map,Date newDate) {
		JsonResult jsonResult = new JsonResult();
		try {
			Integer newsId = Integer.parseInt(map.get("newsId").toString());
			if (newsId != null && newsId > 0) {
//				int success = sysNewsWriterMapper.del(newsId);
				SysNews news=new SysNews();
				news.setNewsState(3);
				news.setNewsId(newsId);
				news.setUpdateTime(newDate);
				int success =sysNewsWriterMapper.updateByPrimaryKeySelective(news);
				if (success > 0) {
					jsonResult.setStateCode(StateCodeConstant.SUCCESS_CODE);
					jsonResult.setData(success);
				} else {
					jsonResult.setStateCode("502");
					jsonResult.setMessage("删除失败，请稍后再试！");
				}
			} else {
				jsonResult.setStateCode("501");
				jsonResult.setMessage("参数异常！");
			}
		} catch (Exception e) {
			jsonResult.setStateCode("503");
			jsonResult.setMessage("系统异常！");
		}
		return jsonResult;
	}

//	/**
//	 * 查询新闻详情--分享新闻用
//	 * 
//	 */
//	@Override
//	public ServiceResult<SysNewsVo> selectByPrimaryKeys(Integer newsId) {
//		ServiceResult<SysNewsVo> result = new ServiceResult<>();
//		SysNewsVo sysNewsVo = sysNewsReaderMapper.selectByPrimaryKeys(newsId);
//		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
//		result.setData(sysNewsVo);
//		return result;
//	}

}
