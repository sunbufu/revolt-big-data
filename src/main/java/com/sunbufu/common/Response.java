package com.sunbufu.common;

/**
 * 返回数据格式
 *
 * @author sunbufu
 * @param <D>   数据类型
 */
public class Response<D> {

    public static final int SUCCESS = 0;
    public static final int FAIL = -1;

    /**操作是否成功*/
    private int success = SUCCESS;
    /**错误信息*/
    private String message;
    /**数据信息*/
    private D data;
    /**分页信息*/
    private PageInfo pageInfo;

    @Override
    public String toString() {
        return "Response{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", pageInfo=" + pageInfo +
                '}';
    }

    public static int getSUCCESS() {
        return SUCCESS;
    }

    public static int getFAIL() {
        return FAIL;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }
}
