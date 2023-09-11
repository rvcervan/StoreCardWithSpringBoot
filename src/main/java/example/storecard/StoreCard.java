package example.storecard;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class StoreCard{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private String owner;

    public StoreCard(Long id, Double amount, String owner) {
        this.id = id;
        this.amount = amount;
        this.owner = owner;
    }

    public StoreCard(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "StoreCard{" +
                "id=" + id +
                ", amount=" + amount +
                ", owner='" + owner + '\'' +
                '}';
    }
}
