package com.datanew.action;

import com.datanew.dto.Pages;
import com.datanew.dto.Result;
import com.datanew.model.BaseOperator;
import com.datanew.service.UserService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/user"})
public class UserController
{

  @Autowired
  private UserService userService;

  @ResponseBody
  @RequestMapping({"updatePassword"})
  public Object updatePassword(@RequestParam String oldpassword, @RequestParam String newpassword, HttpSession session)
  {
    Result result = this.userService.updatePassword(oldpassword, newpassword, session);
    return result;
  }
  @ResponseBody
  @RequestMapping({"getUsersInformation"})
  public Object getUsersInformation(Pages page, String user_division, String user_name, String user_phone, String user_loginname, String user_region, String user_entecode, HttpSession session) { BaseOperator operator = (BaseOperator)session.getAttribute("LOGINUSER");
    this.userService.getUsersInformation(page, user_division, user_name, user_phone, user_loginname, user_region, user_entecode);
    return page; }

  @ResponseBody
  @RequestMapping({"deleteUsers"})
  public Object deleteUsers(String id) {
    this.userService.deleteUsers(id);
    Result result = new Result();
    result.setContent("删除成功");
    result.setSuccess(true);
    return result;
  }
  @ResponseBody
  @RequestMapping({"saveUsers"})
  public Object saveUsers(BaseOperator operator, String oldPassWord, int stuts) {
    Result result = new Result();
    this.userService.saveUsers(operator, oldPassWord, result, stuts);
    return result;
  }

  @ResponseBody
  @RequestMapping({"findUserPost"})
  public Object findUserPost(String userId) {
    Result result = new Result();
    this.userService.findUserPost(userId, result);
    return result;
  }
  @ResponseBody
  @RequestMapping({"saveUserPost"})
  public Object saveUserPost(String userId, String postIds) {
    Result result = new Result();
    this.userService.saveUserPost(userId, postIds, result);
    return result;
  }
  @ResponseBody
  @RequestMapping({"findPostMenu"})
  public Object findPostMenu(String postId) {
    Result result = new Result();
    this.userService.findPostMenu(postId, result);
    return result;
  }

  @ResponseBody
  @RequestMapping({"savePostMenu"})
  public Object savePostMenu(String postId, String menus) {
    Result result = new Result();
    this.userService.savePostMenu(postId, menus, result);
    return result;
  }
  @ResponseBody
  @RequestMapping({"resetPassword"})
  public Object resetPassword(String userid) {
    Result result = new Result();
    this.userService.savePassword(userid, result);
    return result;
  }
}