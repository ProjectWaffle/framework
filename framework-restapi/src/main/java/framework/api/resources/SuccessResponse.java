package framework.api.resources;

import framework.core.constants.ApplicationStatus;

public class SuccessResponse extends ServiceResponse<String> {

    private static final long serialVersionUID = 7962226713515799779L;

    public SuccessResponse() {
        super(new ResponseHeader(ApplicationStatus.SUCCESS), "");
    }

}
