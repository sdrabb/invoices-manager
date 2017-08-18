package it.unifi.ing.swam.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "accountabilities")
public class Accountability extends BaseEntity {

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Party commissioner;

    @ManyToOne
    private Party responsible;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private AccountabilityType accountabilityType;

    public Accountability() {
    }

    public Accountability(String uuid) {
        super(uuid);

    }

    public AccountabilityType getAccountabilityType() {
        return accountabilityType;
    }

    public void setAccountabilityType(AccountabilityType accountabilityType) {
        this.accountabilityType = accountabilityType;
    }

    public void setAccountability(Party commissioner, Party responsible) {

        if (checkAccountability(commissioner, responsible)) {

            this.commissioner = commissioner;
            this.responsible = responsible;

        } else {
            throw new IllegalArgumentException("these types of Party are not permitted in this accountability");
        }

    }

    public Party getCommissioner() {
        return commissioner;
    }

    public Party getResponsible() {
        return responsible;
    }

    public boolean checkAccountability(Party c, Party r) {

        if (c.getPartyType().getName().equals(accountabilityType.getCommissioner().getName())) {
            if (r.getPartyType().getName().equals(accountabilityType.getResponsible().getName())) {
                return true;
            }
        }

        return false;
    }

}
