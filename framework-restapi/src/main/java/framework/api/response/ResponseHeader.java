package framework.api.response;

import java.io.Serializable;

public class ResponseHeader implements Serializable {

    private static final long serialVersionUID = -5963770126090460701L;

    private Integer statusCode;

    private String statusMessage;

    private String token;

    /**
     * @return the statusCode
     */
    public Integer getStatusCode() {
        return this.statusCode;
    }

    /**
     * @return the statusMessage
     */
    public String getStatusMessage() {
        return this.statusMessage;
    }

    public String getToken() {
        return this.token;
    }

    /**
     * @param statusCode
     *            the statusCode to set
     */
    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * @param statusMessage
     *            the statusMessage to set
     */
    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
