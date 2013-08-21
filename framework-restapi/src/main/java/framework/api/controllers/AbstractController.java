package framework.api.controllers;

import java.io.Serializable;

import javax.inject.Named;

@Named
public abstract class AbstractController implements Serializable {

    private static final long serialVersionUID = -1508227485108273495L;

    protected AbstractController() {
    }

}
