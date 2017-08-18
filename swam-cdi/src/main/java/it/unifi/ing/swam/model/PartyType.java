package it.unifi.ing.swam.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "party_types")
public class PartyType extends BaseEntity {

    private String name;

    public PartyType() {
    }

    public PartyType(String uuid) {
        super(uuid);
    }

    public PartyType(String uuid, String name) {
        super(uuid);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
