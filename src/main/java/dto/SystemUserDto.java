/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entity.SystemUser;
import entity.SystemUserGroup;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author 5110
 */
public class SystemUserDto implements Serializable {
    private Long id;
    private String username;
    private String userpassword;
    private String name;
    private AccountDto account;
    private SystemUserGroup userGroup;

    
    public SystemUserDto() {
    }

    public SystemUserDto(Long id, String username, String userpassword, String name, AccountDto account, SystemUserGroup userGroup) {
        this.id = id;
        this.username = username;
        this.userpassword = userpassword;
        this.name = name;
        this.account = account;
        this.userGroup = userGroup;
    }




   
   

    public SystemUser asEntity() {
        SystemUser user = new SystemUser();
        user.setId(this.id);
        user.setUsername(this.username);
        user.setUserpassword(this.userpassword);
        user.setName(this.name);
        user.setAccount(this.account != null ? this.account.asEntity() : null);


        return user;
    }

    public AccountDto getAccount() {
        return account;
    }

    public void setAccount(AccountDto account) {
        this.account = account;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.username);
        hash = 23 * hash + Objects.hashCode(this.userpassword);
        hash = 23 * hash + Objects.hashCode(this.name);
        hash = 23 * hash + Objects.hashCode(this.account);
        hash = 23 * hash + Objects.hashCode(this.userGroup);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SystemUserDto other = (SystemUserDto) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.userpassword, other.userpassword)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.account, other.account)) {
            return false;
        }
      
        if (!Objects.equals(this.userGroup, other.userGroup)) {
            return false;
        }
        return true;
    }


    }

   

