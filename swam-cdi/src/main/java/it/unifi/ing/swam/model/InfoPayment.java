package it.unifi.ing.swam.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity(name = "info_payment")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class InfoPayment extends BaseEntity {

    public InfoPayment() {
    }

    public InfoPayment(String uuid) {
        super(uuid);
    }

}
