package com.carlos.manutencao.ifome.firebase;

import com.google.firebase.auth.FirebaseAuth;

public class FirebaseAuthConfig {
    private static FirebaseAuth auth;

    public static FirebaseAuth getFirebaseAuth() {
        if (auth == null) {
            auth = FirebaseAuth.getInstance();
        }

        return auth;
    }
}
