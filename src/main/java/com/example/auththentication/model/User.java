package com.example.auththentication.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.jboss.aerogear.security.otp.api.Base32;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User extends BaseModel {
    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "hashed_password")
    private String hashedPassword;

    @Column(name = "is_verified")
    private Boolean isVerified;

    @Column(name = "is_using_2fa")
    private boolean isUsing2FA;

    @Column(name = "secret")
    private String secret;

    public User() {
        super();
        this.secret = Base32.random();
    }


}
