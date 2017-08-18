package it.unifi.ing.swam.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "invoices")
public class Invoice extends BaseEntity {

    @Embedded
    private State invoiceState;

    @Temporal(TemporalType.DATE)
    private Date releaseDate;

    @ManyToOne
    private User user;

    @ManyToOne
    private Company buyer;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private InfoPayment infoPayment;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<InvoiceOrder> orders;

    private Float taxable;
    private Float iva;
    private Float total;

    public Invoice() {
    }

    public Invoice(String uuid) {
        super(uuid);
        this.orders = new ArrayList<InvoiceOrder>();
        this.invoiceState = new State("creato");
    }

    public Company getBuyer() {
        return buyer;
    }

    public void setBuyer(Company buyer) {
        this.buyer = buyer;
    }

    public State getInvoiceState() {
        return invoiceState;
    }

    public void setInvoiceState(State invoiceState) {
        this.invoiceState = invoiceState;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Float getTaxable() {
        return taxable;
    }

    public void setTaxable(Float taxable) {
        this.taxable = taxable;
    }

    public Float getIva() {
        return iva;
    }

    public void setIva(Float iva) {
        this.iva = iva;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public InfoPayment getInfoPayment() {
        return infoPayment;
    }

    public void setInfoPayment(InfoPayment infoPayment) {
        this.infoPayment = infoPayment;
    }

    public List<InvoiceOrder> getOrders() {
        return orders;
    }

    public InvoiceOrder findInvoiceOrderByUuid(String uuid) {
        for (int i = 0; i < this.orders.size(); i++) {
            if (this.orders.get(i).getUuid().equals(uuid)) {
                return this.orders.get(i);
            }
        }
        return null;
    }

    public boolean removeInvoiceOrderByUuid(String uuid) {
        for (int i = 0; i < this.orders.size(); i++) {
            if (this.orders.get(i).getUuid().equals(uuid)) {

                this.orders.remove(i);
                return true;
            }
        }
        return false;
    }

    public void setOrders(List<InvoiceOrder> orders) {
        this.orders = orders;
    }

    public void addOrder(InvoiceOrder o) {
        this.orders.add(o);
    }
}
