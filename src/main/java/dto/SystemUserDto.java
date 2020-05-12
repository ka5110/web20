/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entity.SystemUser;
import java.io.Serializable;
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
    
    public SystemUserDto() {
    }

    public SystemUserDto(
            Long id,
            String username,
            String userpassword,
            String name
          
            ) {
        this.id = id;
        this.username = username;
        this.userpassword = userpassword;
        this.name = name;
   
            }

   

    public SystemUser asEntity() {
        SystemUser user = new SystemUser();
        user.setId(this.id);
        user.setUsername(this.username);
        user.setUserpassword(this.userpassword);
        user.setName(this.name);

        return user;
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
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.username);
        hash = 53 * hash + Objects.hashCode(this.userpassword);
        hash = 53 * hash + Objects.hashCode(this.name);
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
        return true;
    }

    }

   

