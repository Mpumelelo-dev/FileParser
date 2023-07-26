package com.eviro.assessment.grad001.Mpumelelo.Ngozo.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AccountProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountHolderName;
    private String accountHolderSurname;
    private String httpImageLink;

    // Constructors
    public AccountProfile() {
    }

    public AccountProfile(String accountHolderName, String accountHolderSurname, String httpImageLink) {
        this.accountHolderName = accountHolderName;
        this.accountHolderSurname = accountHolderSurname;
        this.httpImageLink = httpImageLink;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getAccountHolderSurname() {
        return accountHolderSurname;
    }

    public void setAccountHolderSurname(String accountHolderSurname) {
        this.accountHolderSurname = accountHolderSurname;
    }

    public String getHttpImageLink() {

        return httpImageLink;
    }

    public void setHttpImageLink(String httpImageLink) {
        this.httpImageLink = httpImageLink;
    }

    // toString method
    @Override
    public String toString() {
        return "AccountProfile{" +
                "id=" + id +
                ", accountHolderName='" + accountHolderName + '\'' +
                ", accountHolderSurname='" + accountHolderSurname + '\'' +
                ", httpImageLink='" + httpImageLink + '\'' +
                '}';
    }


}

