package com.datanew.filter;

import cn.com.gsoft.core.www.client.FrameCache;
import com.datanew.util.ConfigureParser;
import com.datanew.util.FunctionCache;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/**
 * Created by Lustin on 2016/12/27.
 */
public class CustomFilterSecurityInterceptor  implements Filter {
    private static  final  String exString=ConfigureParser.getPropert("pageAuthorityExclude");
    private  String[] exArr;
    public void init(FilterConfig filterConfig) throws ServletException {
         exArr=exString.split(",");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request=((HttpServletRequest) servletRequest);
        HttpServletResponse response= ((HttpServletResponse) servletResponse);
        System.out.println(request.getRequestURI());
        System.out.println(request.getHeader("key")+"  " +request.getHeader("sessID"));
        if(request.getRequestURI().indexOf(request.getContextPath()+"/login")==0||request.getRequestURI().indexOf(request.getContextPath()+"/pay/payInfoExcel.do")==0||request.getRequestURI().indexOf(request.getContextPath()+"/pay/downloadFile.do")==0||request.getRequestURI().indexOf(request.getContextPath()+"/pay/returnPayAccount.do")==0){
            filterChain.doFilter(request, response);
        }else{
            String key=null;
            String sessionid=null;
            String cursessionid=request.getRequestedSessionId();
            String query=request.getQueryString()==null?"":request.getQueryString();
            int index=query.indexOf("&");
            if((request.getHeader("key"))!=null&&(request.getHeader("sessID"))!=null){//.do请求
                key=request.getHeader("key");
                sessionid=request.getHeader("sessID");
                if (sessionid==null|| (cursessionid) == null || (FrameCache.getSession(key) == null)||!sessionid.equals(cursessionid)||!FrameCache.checkTime(key)) {
                    if(!FrameCache.checkTime(key)){
                        FrameCache.remove(key);
                        FunctionCache.clearFunction(cursessionid);
                    }
                    //response.sendRedirect(request.getContextPath());
                    response.getWriter().write("<script>top.location.href='"+request.getContextPath()+"'</script>");

                }else{
                    filterChain.doFilter(request, response);
                }

            } else{
                sessionid=request.getParameter("sessionId");
                key=null;
                if (query == null ||sessionid==null|| index < 0 || (cursessionid) == null || (FrameCache.getSession(key=query.substring(0, index)) == null)||!sessionid.equals(cursessionid)||!FrameCache.checkTime(key)) {
                    //response.sendRedirect(request.getContextPath());
                    response.getWriter().write("<script>top.location.href='"+request.getContextPath()+"'</script>");

                } else {
                    boolean ex=false;
                    for(String s:exArr){
                        if(request.getRequestURI().equals(request.getContextPath()+"/framework"+s)){

                            ex=true;
                            break;
                        }
                    }
                    if(ex){
                        filterChain.doFilter(request, response);
                    }else{
                    	if(!FunctionCache.isAuthoried(cursessionid,request.getRequestURI())){
                            response.getWriter().write("<script>top.location.href='"+request.getContextPath()+"'</script>");
                            //response.sendRedirect(request.getContextPath());
                        }else{
                            filterChain.doFilter(request, response);
                        }
                    }

                }
            }


        }






    }

    public void destroy() {

    }
}