package com.datanew.service.impl;

import com.datanew.dao.BaseDao;
import com.datanew.dto.Pages;
import com.datanew.dto.Result;
import com.datanew.model.BasePost;
import com.datanew.service.PostService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("postService")
public class PostServiceImpl
  implements PostService
{

  @Autowired
  BaseDao baseDao;

  public Pages getPostInformation(Pages page)
  {
    String sql = "select new map(i.postid as POSTID,i.postname as POSTNAME ,i.remark as REMARK) from BasePost i";
    String countsql = "select count(1) from BASE_POST";
    List list = this.baseDao.selectByHql(sql, null, page.getOffset().intValue(), page.getLimit().intValue());
    List listcount = this.baseDao.selectBySql(countsql);
    if (list.size() > 0) {
      page.setRows(list);
      page.setTotal(Integer.valueOf(Integer.parseInt(listcount.get(0).toString())));
    }
    return page;
  }

  public void deletePost(String id) {
    String sql = "delete from BASE_POST  where postid = " + id;
    this.baseDao.executeBySql(sql);
    String hsql = "delete from BASE_POSTTOMENU where postid = " + id;
    this.baseDao.executeBySql(hsql);
  }

  public void savePost(BasePost post, Result result) {
    this.baseDao.saveOrUpdate(post);
    result.setSuccess(true);
    result.setContent("保存成功");
  }
}