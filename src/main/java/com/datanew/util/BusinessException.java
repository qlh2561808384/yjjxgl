package com.datanew.util;

public class BusinessException extends Exception {
    private boolean relogin = false; //是否需重新登录系统
    private boolean refresh = false; //是否需重刷新主界面
    private boolean popup = true; //是否需显示错误信息给用户看
    /**
     * 尚未登录默认异常
     * @return
     */
    public static BusinessException nonLoginExcetion(){
        return new BusinessException("尚未登录或长时间没有操作，请重新登录！", true);
    }

    /**
     * @param msg
     *            描述信息
     */
    public BusinessException(String msg) {
        super(msg);
    }

    /**
     * @param msg
     *            描述信息
     * @param relogin
     *            信息提示后返回的路径
     */
    public BusinessException(String msg, boolean relogin) {
        super(msg);
        this.relogin = relogin;
    }

    /**
     * @param msg
     *            描述信息
     * @param t
     *            异常原因
     */
    public BusinessException(String msg, Throwable t) {
        super(msg, t);
    }

    /**
     * @param msg
     *            描述信息
     * @param t
     *            异常原因
     * @param relogin
     *            信息提示后返回的路径
     */
    public BusinessException(String msg, Throwable t, boolean relogin) {
        super(msg, t);
        this.relogin = relogin;
    }

    /**
     * @return Returns the relogin.
     */
    public boolean isRelogin() {
        return relogin;
    }

    /**
     * @return Returns the popup.
     */
    public boolean isPopup() {
        return popup;
    }
    /**
     * @return Returns the refresh.
     */
    public boolean isRefresh() {
        return refresh;
    }
}