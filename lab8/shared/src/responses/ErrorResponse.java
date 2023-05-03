package responses;

public class ErrorResponse extends BaseResponse {

    private final String msg;

    public ErrorResponse(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
