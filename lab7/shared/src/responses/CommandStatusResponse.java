package responses;

public class CommandStatusResponse extends BaseResponse {

    private final String response;
    private final int statusCode;

    public CommandStatusResponse(String response, int statusCode) {
        this.response = response;
        this.statusCode = statusCode;
    }

    public static CommandStatusResponse ofString(String s) {
        return new CommandStatusResponse(s, 0);
    }

    public String getResponse() {
        return response;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
