package wl.hdzj.domain;


/**
 * 返回JSON 模板类
 * @author lipengbiao
 */
public class MessageVO<T>{
    //是否成功
    private boolean isSuccess;
    //错误码
    private String errorCode;
    //错误信息
    private String errorDesc;
    //返回数据
    private T data;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    protected MessageVO() {
        super();
    }
    public MessageVO(T data){
        this.data = data;
        this.isSuccess = true;
    }
    public MessageVO(String eC, String eD){
        this.errorCode = eC;
        this.errorDesc = eD;
        this.isSuccess = false;
    }

    @Override
    public String toString(){
        return "MessageVO{" +
                "isSuccess=" + isSuccess +
                ", errorCode='" + errorCode + '\'' +
                ", errorDesc=" + errorDesc + '\'' +
                ", data=" + data +
                '}';
    }
}
