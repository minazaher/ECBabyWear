package com.example.ecbabywear.Repositories;

import static com.example.ecbabywear.ApplicationClass.firebaseFirestore;

import com.example.ecbabywear.Model.User;
import com.example.ecbabywear.Utilis.UserRetrievedCallback;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class UserRepository {

    public UserRepository() {

    }

    public void getCurrentUser(String email, UserRetrievedCallback callback) {
        User user = new User();
        firebaseFirestore.collection("Users").whereEqualTo("Email", email)
                .get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            user.setName(document.getString("Name"));
                            user.setEmail(document.getString("Email"));
                            user.setPassword(document.getString("Password"));
                            user.setProfilePicture(document.getString("profilePicture"));
                            user.setAddress(document.getString("Address"));

                        }
                        callback.onUserRetrieved(user);

                    } else {
                        System.out.println("Error getting documents: "+ task.getException());
                    }
                }).addOnFailureListener(e -> {
                    System.out.println("Error getting user data"+ e);
                });
    }

    public void updateUsername(String id,String newName){
        firebaseFirestore.collection("Users")
                .document(id)
                .update("Name", newName);
    }

    public void updateAddress(String id,String newAddress){
        firebaseFirestore.collection("Users")
                .document(id)
                .update("Address", newAddress);
    }
}
