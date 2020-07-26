package com.datanew.service;

import com.datanew.dto.Pages;
import com.datanew.dto.Result;
import com.datanew.model.BaseOperator;
import javax.servlet.http.HttpSession;

public abstract interface UserService
{
  public abstract Pages getUsersInformation(Pages paramPages, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6);

  public abstract void deleteUsers(String paramString);

  public abstract void saveUsers(BaseOperator paramBaseOperator, String paramString, Result paramResult, int paramInt);

  public abstract void saveUserPost(String paramString1, String paramString2, Result paramResult);

  public abstract void findUserPost(String paramString, Result paramResult);

  public abstract void findPostMenu(String paramString, Result paramResult);

  public abstract void savePostMenu(String paramString1, String paramString2, Result paramResult);

  public abstract Result updatePassword(String paramString1, String paramString2, HttpSession paramHttpSession);

  public abstract Result savePassword(String paramString, Result paramResult);
}