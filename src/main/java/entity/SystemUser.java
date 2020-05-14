/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import dto.SystemUserDto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author 5110
 */
@Entity
public class SystemUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotNull
    private String username;

    @NotNull
    private String userpassword;

    @NotNull
    private String name;

    
    @OneToOne(mappedBy = "user",  cascade = CascadeType.ALL)
    private SystemUserGroup userGroup;
    
   
    @OneToOne(mappedBy = "systemUser",cascade = CascadeType.ALL)
    private Account account;
     
    @OneToMany(mappedBy = "transactionHolder")
    private List<Transac> transactions;

    public SystemUser() {
    }

   

    public SystemUser(Long id, String username, String userpassword, String name, SystemUserGroup userGroup, Account account) {
        this.id = id;
        this.username = username;
        this.userpassword = userpassword;
        this.name = name;
        this.userGroup = userGroup;
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }


    public SystemUserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(SystemUserGroup userGroup) {
        this.userGroup = userGroup;
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
     public List<Transac> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transac> transactions) {
        this.transactions = transactions;
    }

        public SystemUserDto toDto() {
        
        return new SystemUserDto(
                this.id,
                this.username,
                this.userpassword,
                this.name,
                this.account == null ? null : this.account.asDto(),
                this.userGroup

        );
    }

    public static List<SystemUserDto> toDto(List<SystemUser> users) {
        if (users.isEmpty()) {
            return null;
        }
        List<SystemUserDto> userDtos = new ArrayList<>();

        users.forEach((user) -> {
            userDtos.add(user.toDto());
        });

        return userDtos;
    }
}
