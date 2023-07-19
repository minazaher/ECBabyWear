package com.example.ecbabywear;

import static com.example.ecbabywear.ApplicationClass.firebaseAuth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.ecbabywear.Model.User;
import com.example.ecbabywear.Repositories.UserRepository;

public class UserViewModel extends ViewModel {
    private UserRepository userRepository;
    private LiveData<User> user;

    public UserViewModel() {
        this.userRepository = new UserRepository();
        user = userRepository.getCurrentUser(firebaseAuth.getCurrentUser().getEmail());
    }

    public LiveData<User> getCurrentUser(){
        return user;
    }

}
