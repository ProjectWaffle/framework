package framework.api.resources;

import java.io.Serializable;

public class ServiceResponse<T> implements Serializable {

    private static final long serialVersionUID = 1303959587184354193L;

    public static ServiceResponseBuilder<String> result() {
        final ServiceResponseBuilder<String> serviceResponseBuilder = new ServiceResponseBuilder<String>();
        serviceResponseBuilder.result("");
        return serviceResponseBuilder;
    }

    public static <T> ServiceResponseBuilder<T> result(T result) {
        final ServiceResponseBuilder<T> serviceResponseBuilder = new ServiceResponseBuilder<T>();
        serviceResponseBuilder.result(result);
        return serviceResponseBuilder;
    }

    private final ResponseHeader responseHeader;

    private final T result;

    protected ServiceResponse(ResponseHeader responseHeader, T result) {
        this.responseHeader = responseHeader;
        this.result = result;
    }

    public ResponseHeader getResponseHeader() {
        return this.responseHeader;
    }

    public T getResult() {
        return this.result;
    }
}
