package it.unifi.ing.swam.model;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class State implements Serializable {

    private String state;

    public State() {
    }

    public State(String s) {
        this.state = s;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
