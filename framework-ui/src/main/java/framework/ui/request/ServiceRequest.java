package framework.ui.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

public class ServiceRequest<T> implements Serializable {

    private static final long serialVersionUID = -4675056771193159091L;

    @XmlElement
    private T request;

    @XmlElement
    private RequestHeader requestHeader;

    public T getRequest() {
        return this.request;
    }

    public RequestHeader getRequestHeader() {
        return this.requestHeader;
    }

    public void setRequest(T request) {
        this.request = request;
    }

    public void setRequestHeader(RequestHeader requestHeader) {
        this.requestHeader = requestHeader;
    }
}
