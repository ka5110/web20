/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entity.SystemUser;
import entity.Transac;
import entity.TransacStatus;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;

/**
 *
 * @author 5110
 */
public class TransacDto {
    private long id;

    private double amount;
    
    private TransacStatus status;
    
    
    private SystemUser transactionHolder;
   
    private long transactionPartakerId;

    private SystemUser transactionPartaker;

    public TransacDto() {
    }

    public TransacDto(double amount, TransacStatus status, SystemUser transactionHolder, long transactionPartakerId) {
        this.amount = amount;
        this.status = status;
        this.transactionHolder = transactionHolder;
        this.transactionPartakerId = transactionPartakerId;
    }

    public TransacDto(long id, double amount, TransacStatus status, SystemUser transactionHolder, long transactionPartakerId, SystemUser transactionPartaker) {
        this.id = id;
        this.amount = amount;
        this.status = status;
        this.transactionHolder = transactionHolder;
        this.transactionPartakerId = transactionPartakerId;
        this.transactionPartaker = transactionPartaker;
    }

    public TransacDto(long id, double amount, TransacStatus status, SystemUser toDto, long transactionPartakerId) {
        this.id = id;
        this.amount = amount;
        this.status = status;
        this.transactionHolder = toDto;
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
       
        public Transac asEntity() {
        Transac transaction = new Transac();
        transaction.setId(this.id);
        transaction.setAmount(this.amount);
        transaction.setStatus(this.status);
        transaction.setTransactionHolder(this.transactionHolder);
        transaction.setTransactionPartakerId(this.transactionPartakerId);

        return transaction;
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

    public SystemUser getTransactionPartaker() {
        return transactionPartaker;
    }

    public void setTransactionPartaker(SystemUser transactionPartaker) {
        this.transactionPartaker = transactionPartaker;
    }
    
              
              
}