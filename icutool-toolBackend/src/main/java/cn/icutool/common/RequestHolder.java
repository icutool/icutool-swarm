package cn.icutool.common;


import cn.icutool.domain.dto.RequestInfo;

/**
 * Description: 请求上下文
 * @author xietao
 */
public class RequestHolder {

    private static final ThreadLocal<RequestInfo> THREAD_LOCAL = new ThreadLocal<>();

    public static void set(RequestInfo requestInfo) {
        THREAD_LOCAL.set(requestInfo);
    }

    public static RequestInfo get() {
        return THREAD_LOCAL.get();
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }
}
