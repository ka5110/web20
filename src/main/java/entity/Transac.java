/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import dto.TransacDto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author 5110
 * 
 * Transactions
 */

@Entity
public class Transac {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private double amount;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private TransacStatus status;

   
    @NotNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    private SystemUser transactionHolder;

 
    @NotNull
    private long transactionPartakerId;

    public Transac() {
    }

    public Transac(TransacStatus status, SystemUser transactionHolder, Long transactionPartakerId, double amount) {
        this.status = status;
        this.transactionHolder = transactionHolder;
        this.transactionPartakerId = transactionPartakerId;
        this.amount = amount;

    }

    public Transac(long id, double amount, TransacStatus status, SystemUser transactionHolder, long transactionPartakerId) {
        this.id = id;
        this.amount = amount;
        this.status = status;
        this.transactionHolder = transactionHolder;
        this.transactionPartakerId = transactionPartakerId;
    }
   
    
        
        public TransacDto asDto() {
        TransacDto t = new TransacDto(
                this.id,
                this.amount,
                this.status,
                this.transactionHolder,
                this.transactionPartakerId
        );
        return t;
    }
        public static List<TransacDto> asDto(List<TransacDto> transactions) {
        if (transactions.isEmpty()) {
            return null;
        }
        List<TransacDto> dtos = new ArrayList<>();

        for (TransacDto transaction : transactions) {
            dtos.add(transaction.asDto());
        }

        return dtos;
    }
        
            public static List<Transac> asEntity(List<TransacDto> transactions) {
        List<Transac> entities = new ArrayList<>();

        for (TransacDto transaction : transactions) {
            entities.add(transaction.asEntity());
        }

        return entities;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TransacStatus getStatus() {
        return status;
    }

    public void setStatus(TransacStatus status) {
        this.status = status;
    }

    public SystemUser getTransactionHolder() {
        return transactionHolder;
    }

    public void setTransactionHolder(SystemUser transactionHolder) {
        this.transactionHolder = transactionHolder;
    }

    public long getTransactionPartakerId() {
        return transactionPartakerId;
    }

    public void setTransactionPartakerId(long transactionPartakerId) {
        this.transactionPartakerId = transactionPartakerId;
    }
            
  
}
