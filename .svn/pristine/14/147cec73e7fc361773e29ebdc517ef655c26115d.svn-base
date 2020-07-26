package com.hzzk.common.util;

import cn.com.gsoft.core.www.client.FrameCache;
import com.datanew.model.BaseOperator;
import com.datanew.util.StaticData;
import com.hzzk.common.Config;
import com.hzzk.common.dto.OperatorDTO;
import com.hzzk.common.exception.BusinessException;
import com.hzzk.common.remote.Remote;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.InetAddress;
import java.util.*;
/*
新加工具类
 */
public class LoginServletByCA {
    //ca登录
    public static void loginByCA(HttpServletRequest request, HttpServletResponse response) throws IOException, DocumentException {
        HttpSession session = request.getSession();
        String signed = request.getParameter("signed"),//key码
                randomNum = request.getParameter("ORIGINAL_DATA_KEY"),//原始随机数
                operatorName = request.getParameter("operatorName");//用户名
        OperatorDTO operator = new OperatorDTO();
        String ip,key;

        if (signed != null && !"".equals(signed)) {
            ip = Config.getAttribute("application.UsbGateWay");
            ip = ip != null && !"".equals(ip) && !"true".equals(ip.trim()) ? "0" : "1";
            key = Config.getAttribute("application.UsbGateWay");
            IJITAuthUtil keyUtil = "false".equals(key) ? new JITIIMAuthUtil() : new UsbKeyUtil2();
            Map map = keyUtil.approveCertificate(new Object[]{randomNum, signed});
            if (map == null || map.size() == 0) {
                throw new BusinessException("请选择正确的证书！");
            }

            String userToken = String.valueOf(map.get("X509Certificate.SerialNumber"));

//            if (code == null) {
//                List userList = Remote.getRemoteOperatorService().getOperatorListByOnlyToken(userToken);
////                if (userList == null || userList.size() <= 0) {
////                    throw new BusinessException("证书不正确，无法登陆系统");
////                }
//                response.setContentType("text/html;charset=UTF-8");
//                PrintWriter out = response.getWriter();
//                out.print("<actionSet>");
//                out.print("<grid version=\"2.0\">");
//                out.print("<declare columnDataTagName=\"row\" header=\"radio\" >");
//                out.print("<column name=\"name\" caption=\"用户名\" mode=\"string\" editable=\"false\"/>");
//                out.print("<column name=\"code\" caption=\"操作码\" mode=\"string\" editable=\"false\" display=\"none\"/>");
//                out.print("<column name=\"ywpost\" caption=\"业务岗位\" mode=\"string\" editable=\"false\"/>");
//                out.print("</declare>");
//                out.print("<data>");
//                String ywPost;
////                for (Iterator it = userList.iterator(); it.hasNext(); out.print("<row name=\"" + operator.getName() + "\" code=\"" + operator.getCode() + "\" ywpost=\"" + ywpost + "\" />")) {
////                    PropertyUtil.copy(operator, it.next());
////                    ywPost = "";
////                    if (operator.getYwpost() != null) {
////                        ywpost = operator.getYwpost();
////                    }
////                }
//                out.print("</data>");
//                out.print("</grid>");
//                out.print("</actionSet>");
//                out.flush();
//                out.close();
//            } else {
            String userName;
            if (operatorName != null && !"".equals(operatorName)) {
                userName = operatorName;
            } else {
                String allUserName = String.valueOf(map.get("X509Certificate.SubjectDN"));
                userName = allUserName.substring(3, allUserName.indexOf(","));
            }
//            OperatorDTO operator1 = Remote.getRemoteOperatorService().getOperatorByToken(userToken, userName);

           // PropertyUtil.copy(operator, Remote.getRemoteOperatorService().getOperatorByToken(userToken, userName));
//                if (operator != null) {
//                    if (operator.getGuId() == null) {
//                        throw new BusinessException("输入的帐户[" + code + "]证书不正确，无法登陆系统");
//                    }
//                    if (operator.getLoginType() != null && !"0".equals(operator.getLoginType())) {
//                        ip = "1".equals(operator.getLoginType()) ? "USB登录" : "混合登录";
//                        throw new BusinessException("当前帐号采用" + ip + "模式，请插入USB！");
//                    }
//                    if (operator.getLoginType() != null && !"0".equals(operator.getLoginType())) {
//                        if ("2".equals(operator.getLoginType())) {
//                            if (!operator.getCode().equals(code) || !MD5Password.checkPassword(operator.getPassword(), password)) {
//                                throw new BusinessException("当前帐户采用混合登录模式，登录名或者密码不正确，无法登陆系统");
//                            }
//                        } else if (!operator.getCode().equals(code)) {
//                            throw new BusinessException("输入的帐户[" + code + "]证书不正确，无法登陆系统");
//                        }
//                    } else
//                        if (operatorName == null || "".equals(operatorName)) {
//                        operator = null;
//                    }
//                }
//            }
//            if (code != null){
            if(operator == null || operator.getGuId() == null || (new Long(0L)).equals(operator.getGuId())) {
                throw new BusinessException("此帐号[" + userName + "]不存在");
            }
            String sessionId = session.getId();
            key = Encoder.str2md5(userName + "@" + sessionId + ":" + InetAddress.getLocalHost().getHostAddress());
            if (FrameCache.checkUserKey(key)) {
                key = key + "_01";
            }
            String appCode = Config.getAttribute("application.code");
            Object[] yearSet = Remote.getRemoteOperatorService().getDefaultYearSet(operator.getGuId(), appCode);
            Remote.getRemoteOnlineUserService().register(key, request.getRemoteAddr(), appCode, key, operator);
            Set funcs = Remote.getRemoteFunctionService().getFunctionsByOperGuId(operator.getGuId(), appCode, (Integer) yearSet[2]);
            String returnStr = Remote.getRemoteFunctionService().getMenuByOperGuId(operator.getGuId(), Config.getAttribute("application.code"), Integer.parseInt(Config.getAttribute("application.setid")));
            List menus = getMenus(returnStr);
            session.setAttribute(StaticData.LOGINUSER, new BaseOperator().convert(operator));
            session.setAttribute(StaticData.USERMENUS, menus);
            session.setAttribute(StaticData.USERBUTTONS, funcs);
            session.setAttribute("key", key);
            session.setAttribute("sessionId", sessionId);
//            }

        }
    }
    private static List<Map> getMenus(String str) throws DocumentException {
        class Depart {
            List<Map> li = new ArrayList<Map>();

            List<Map> getMenu(String x) throws DocumentException {
                x = x.substring(x.indexOf("<actionSet"), x.length());
                Document doc = DocumentHelper.parseText(x);
                List<Element> menus = doc.selectNodes("/actionSet/treeNode");
                depart(menus, "");
                return li;
            }

            void depart(List<Element> list, String pid) {
//                Map session = FrameCache.getSession(userKey);
                for (Element e : list) {
                    List<Element> listNextLevel = e.elements();
                    Map ma = new HashMap();
                    String id = e.attributeValue("id");
                    String name = e.attributeValue("name");
                    String href = e.attributeValue("href");
                    String code = e.attributeValue("code");
                    ma.put("id", id);
                    ma.put("name", name);
                    ma.put("url", href);
                    ma.put("code", code);
//                ma.put("icon","");
                    if (pid != null && !"".equals(pid)) {
                        ma.put("pId", pid);
                    } else {
                        ma.put("pId", -1);
                    }
//                ma.put("isOpen","");
                    li.add(ma);
                    if (listNextLevel.size() > 0) {
                        String canselected = e.attributeValue("canselected");
                        depart(listNextLevel, e.attributeValue("id"));
                    }
                }
            }
        }
        return new Depart().getMenu(str);
    }
}
