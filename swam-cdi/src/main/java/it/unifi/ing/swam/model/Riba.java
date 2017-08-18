package it.unifi.ing.swam.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "riba")
public class Riba extends InfoPayment {

    private String bankSupport;

    public Riba() {
    }

    public Riba(String uuid) {
        super(uuid);
    }

    public Riba(String uuid, String bankSupport) {
        super(uuid);
        this.bankSupport = bankSupport;
    }

    public String getBankSupport() {
        return bankSupport;
    }

    public void setBankSupport(String bankSupport) {
        this.bankSupport = bankSupport;
    }

}
