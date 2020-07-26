package com.datanew.action;

import com.datanew.dto.Pages;
import com.datanew.dto.Result;
import com.datanew.model.BasePost;
import com.datanew.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/post"})
public class PostController
{

  @Autowired
  private PostService postService;

  @ResponseBody
  @RequestMapping({"getPostInformation"})
  public Object getPostInformation(Pages page)
  {
    Pages page1 = this.postService.getPostInformation(page);
    return page;
  }
  @ResponseBody
  @RequestMapping({"deletePost"})
  public Object deletePost(String id) {
    this.postService.deletePost(id);
    Result result = new Result();
    result.setContent("删除成功");
    result.setSuccess(true);
    return result;
  }
  @ResponseBody
  @RequestMapping({"savePost"})
  public Object savePost(BasePost post) {
    Result result = new Result();
    this.postService.savePost(post, result);
    return result;
  }
}