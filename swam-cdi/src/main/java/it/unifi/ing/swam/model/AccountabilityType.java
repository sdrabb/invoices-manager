package it.unifi.ing.swam.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "accountability_types")
public class AccountabilityType extends BaseEntity {

    @ManyToOne
    private PartyType commissioner;

    @ManyToOne
    private PartyType responsible;

    private String name;

    public AccountabilityType() {
    }

    public AccountabilityType(String uuid) {
        super(uuid);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PartyType getCommissioner() {
        return commissioner;
    }

    public void setCommissioner(PartyType commissioner) {
        this.commissioner = commissioner;
    }

    public PartyType getResponsible() {
        return responsible;
    }

    public void setResponsible(PartyType responsible) {
        this.responsible = responsible;
    }
}
