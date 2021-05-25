package com.ttn.project.customermicroservice.token;



import com.ttn.project.customermicroservice.Entity.User;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Entity
public class ForgotPasswordToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int token_id;

    private String forgotPasswordToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate;

    @OneToOne(targetEntity = User.class,fetch = FetchType.EAGER)
    @JoinColumn(nullable = false,name = "user_id")
    private User user;

    public ForgotPasswordToken(){

    }
    public ForgotPasswordToken(User user) {
        this.user = user;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Timestamp(calendar.getTime().getTime()));
        createdDate = new Date(calendar.getTime().getTime());
        calendar.add(Calendar.MINUTE,15);
        expiryDate = new Date(calendar.getTime().getTime());
        forgotPasswordToken = UUID.randomUUID().toString();
    }

    public int getToken_id() {
        return token_id;
    }

    public void setToken_id(int token_id) {
        this.token_id = token_id;
    }

    public String getForgotPasswordToken() {
        return forgotPasswordToken;
    }

    public void setForgotPasswordToken(String forgotPasswordToken) {
        this.forgotPasswordToken = forgotPasswordToken;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
