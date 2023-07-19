package com.example.ecbabywear.Repositories;

import static com.example.ecbabywear.ApplicationClass.firebaseFirestore;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.ecbabywear.Model.User;
import com.example.ecbabywear.Utilis.UserRetrievedCallback;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class UserRepository {

    public UserRepository() {

    }

    public LiveData<User> getCurrentUser(String email) {
        MutableLiveData<User> user = new MutableLiveData<>();
        firebaseFirestore.collection("Users").whereEqualTo("Email", email)
                .get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        User user1 = new User();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            user1.setName(document.getString("Name"));
                            user1.setEmail(document.getString("Email"));
                            user1.setPassword(document.getString("Password"));
                            user1.setProfilePicture(document.getString("profilePicture"));
                            user1.setAddress(document.getString("Address"));
                            user.setValue(user1);
                        }

                    } else {
                        System.out.println("Error getting documents: "+ task.getException());
                    }
                }).addOnFailureListener(e -> {
                    System.out.println("Error getting user data"+ e);
                });
        return user;
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
