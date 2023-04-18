package requestLogic;

import java.io.InputStream;

public class StatusRequest {
    private InputStream inputStream;
    private CallerBack callerBack;
    private int code;

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public CallerBack getCallerBack() {
        return callerBack;
    }

    public void setCallerBack(CallerBack callerBack) {
        this.callerBack = callerBack;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
