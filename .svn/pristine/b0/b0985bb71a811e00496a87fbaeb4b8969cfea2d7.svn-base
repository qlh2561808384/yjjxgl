package com.datanew.service.impl;

import cn.com.gsoft.core.www.client.FrameCache;
import cn.com.gsoft.core.www.client.servlet.LogoutServlet;
import com.datanew.service.LogOutService;
import com.datanew.util.FunctionCache;
import com.hzzk.common.Config;
import com.hzzk.common.messager.SuccessMessager;
import com.hzzk.common.remote.Remote;
import com.hzzk.common.remote.RemoteOnlineUserService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class LogOutServiceImpl extends LogoutServlet
  implements LogOutService
{
  public void logOut(HttpServletRequest request, HttpServletResponse response)
  {
    FunctionCache.clearFunction(request.getRequestedSessionId());
    try {
      String curruserkey = request.getHeader("current_user_logon_key");
      FrameCache.remove(curruserkey);
      request.getSession().invalidate();
      PrintWriter out = response.getWriter();
      out.print(new SuccessMessager(false));
      out.flush();
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}