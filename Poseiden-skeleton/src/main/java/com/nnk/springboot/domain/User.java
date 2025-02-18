package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.nnk.springboot.validation.PasswordValidation;

@Entity
@Table(name = "users")
public class User{
	
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    
    @NotBlank(message = "Username is mandatory")
    @Column(name="username")
    private String username;
    
    @PasswordValidation
    @Size(min=8, message="Password should have at least 8 characters")
    @NotBlank(message = "Password is mandatory")
    @Column(name="password")
    private String password;
    
    @NotBlank(message = "FullName is mandatory")
    @Column(name="fullname")
    private String fullname;
    
   // @NotBlank(message = "Role is mandatory")
    @Column(name="role")
    private String role;
    
    
    public User() {}
    
    

    public User(String username, String password, String fullname, String role) {
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.role = role;
	}
    
    public User(Integer id, String username, String password, String fullname, String role) {
    	this.id = id;
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.role = role;
	}



	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
