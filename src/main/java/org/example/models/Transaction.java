package org.example.models;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "transaction_log")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long ID;
    @Column(nullable = false,precision = 10,scale = 2, columnDefinition = "NUMERIC(10,2) CHECK(amount>0)")
    private BigDecimal amount;
    @Column(nullable = false,columnDefinition = "VARCHAR(10) CHECK(status='SUCCESS' OR status='FAILED') DEFAULT 'SUCCESS'")
    private String status;
    @Column
    private String error_message;
    @Column(nullable = false,columnDefinition = "DATE CHECK(created_at<=now())")
    private Date created_at;

    public Transaction(){}
    public Transaction(BigDecimal amount){
        this.amount = amount;
        this.created_at = Date.valueOf(LocalDate.now());
    }


    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
