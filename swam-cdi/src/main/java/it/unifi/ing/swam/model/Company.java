package it.unifi.ing.swam.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "companies")
public class Company extends Party {

    @OneToMany
    private List<Invoice> invoices;
    
    private String pIva;



    public Company() {
    }

    public Company(String uuid) {
        super(uuid);
        this.invoices = new ArrayList<Invoice>();
    }

    public void addInvoice(Invoice invoice) {
        this.invoices.add(invoice);
    }
    
    public String getpIva() {
        return pIva;
    }

    public void setpIva(String pIva) {
        this.pIva = pIva;
    }

}
