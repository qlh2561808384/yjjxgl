package com.datanew.service;

import com.datanew.dto.Pages;
import com.datanew.dto.Result;
import com.datanew.model.BasePost;

public abstract interface PostService
{
  public abstract Pages getPostInformation(Pages paramPages);

  public abstract void deletePost(String paramString);

  public abstract void savePost(BasePost paramBasePost, Result paramResult);
}