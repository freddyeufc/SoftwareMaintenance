package com.carlos.manutencao.ifome.model;

import android.util.Log;

import com.carlos.manutencao.ifome.exceptions.SaveException;
import com.carlos.manutencao.ifome.firebase.FirebaseDatabaseConfig;
import com.google.firebase.FirebaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

public class User {
    private String userId;
    private String name;
    private String email;
    private String password;
    private String cpf;
    private String phone;

    public User(String userId, String name, String email, String password) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(String userId, String email, String password) {
        this.userId = userId;
        this.email = email;
        this.password = password;
    }

    /**
    * saveUser method.
    * This method should be used to save a new user on database.
    * @throws SaveException
    * @author Marília Cristina
    * @since 0.1
    */
    public void saveUser() throws SaveException {
        DatabaseReference databaseReference = FirebaseDatabaseConfig.getFirebaseDatabase();
        try {
            databaseReference.child("users").child(this.getUserId()).setValue(this);
        } catch(Exception ex) {
            throw new SaveException("User");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Exclude
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password= password;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Exclude
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
