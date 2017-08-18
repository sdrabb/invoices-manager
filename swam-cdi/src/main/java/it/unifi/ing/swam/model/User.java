/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unifi.ing.swam.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User extends Party {

    private String surname;
    private String taxcode;
    
    private String password;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.REMOVE})
    private List<Invoice> invoices;

    public User() {
    }

    public User(String uuid) {
        super(uuid);
        this.invoices = new ArrayList<Invoice>();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTaxcode() {
        return taxcode;
    }

    public void setTaxcode(String taxcode) {
        this.taxcode = taxcode;
    }

    public void addInvoice(Invoice i) {
        this.invoices.add(i);
    }

}
