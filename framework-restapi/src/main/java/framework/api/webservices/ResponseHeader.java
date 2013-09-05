package framework.api.webservices;

import java.io.Serializable;

import framework.core.constants.ApplicationStatus;

public class ResponseHeader implements Serializable {

    private static final long serialVersionUID = -5963770126090460701L;

    private Integer statusCode;

    private String statusMessage;

    public ResponseHeader() {
        
    }
    
    public ResponseHeader(ApplicationStatus status) {
        this.statusCode = status.getCode();
        this.statusMessage = status.getMessage();
    }
    
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

}
