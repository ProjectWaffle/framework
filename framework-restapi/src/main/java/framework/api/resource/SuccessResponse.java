package framework.api.resource;

import framework.core.exceptions.ApplicationStatus;

public class SuccessResponse extends ServiceResponse<String> {

    private static final long serialVersionUID = 7962226713515799779L;

    public SuccessResponse() {
        super(new ResponseHeader(ApplicationStatus.SUCCESS), "");
    }

}
