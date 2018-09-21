package com.carlos.manutencao.ifome.dao;

import android.util.Log;

import com.carlos.manutencao.ifome.firebase.FirebaseDatabaseConfig;
import com.carlos.manutencao.ifome.model.User;
import com.google.firebase.database.DatabaseReference;

public class DaoUser {

    public static boolean saveUser(User user){
        DatabaseReference databaseReference = FirebaseDatabaseConfig.getFirebaseDatabase();
        try {
            databaseReference.child("users").child(user.getUserId()).setValue(user);
            return true;
        } catch(Exception ex) {
            Log.i("Firebase Exception:", ex.getMessage());
            return false;
        }
    }
}
