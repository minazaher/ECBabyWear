package com.example.ecbabywear.ViewModel;

import static com.example.ecbabywear.ApplicationClass.firebaseAuth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.ecbabywear.Piece;
import com.example.ecbabywear.Repositories.WishlistRepository;

import java.util.List;

public class WishlistViewModel extends ViewModel {
    private WishlistRepository wishlistRepository;
    private LiveData<List<Piece>> wishlist;

    public WishlistViewModel() {
            wishlistRepository = new WishlistRepository(firebaseAuth.getCurrentUser().getUid());
            wishlist = wishlistRepository.getWishlist();
        }

        public LiveData<List<Piece>> getWishlist() {
            return wishlist;
        }


}
