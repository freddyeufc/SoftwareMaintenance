package com.carlos.manutencao.ifome.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseDatabaseConfig {

    private static DatabaseReference firebaseReference;

    public static DatabaseReference getFirebaseDatabase() {
        if (firebaseReference == null) {
            firebaseReference = FirebaseDatabase.getInstance().getReference();
        }

        return firebaseReference;
    }
}
