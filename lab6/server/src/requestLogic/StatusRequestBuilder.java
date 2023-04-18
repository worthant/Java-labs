package requestLogic;

import java.io.InputStream;

public class StatusRequestBuilder {

    final StatusRequest result;

    private StatusRequestBuilder() {
        result = new StatusRequest();
    }

    public static StatusRequestBuilder initialize() {
        return new StatusRequestBuilder();
    }

    public StatusRequest build() {
        return result;
    }

    public StatusRequestBuilder setObjectStream(InputStream stream) {
        result.setInputStream(stream);
        return this;
    }

    public StatusRequestBuilder setCallerBack(CallerBack callerBack) {
        result.setCallerBack(callerBack);
        return this;
    }

    public StatusRequestBuilder setCode(int code) {
        result.setCode(code);
        return this;
    }
}
