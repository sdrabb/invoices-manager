package it.unifi.ing.swam.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

@Entity(name = "parties")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Party extends BaseEntity {

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private PartyType partyType;
    
    private String name;
    private String email;

    private String address;
    private String CAP;

    protected Party() {
    }

    public Party(String uuid) {
        super(uuid);
    }

    public PartyType getPartyType() {
        return partyType;
    }

    public void setPartyType(PartyType partyType) {
        this.partyType = partyType;
    }
    
        public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCAP() {
        return CAP;
    }

    public void setCAP(String CAP) {
        this.CAP = CAP;
    }

}
