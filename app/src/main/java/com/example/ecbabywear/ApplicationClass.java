package com.example.ecbabywear;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;

import com.example.ecbabywear.Model.CartItem;
import com.example.ecbabywear.Model.Category;
import com.example.ecbabywear.ViewModel.PiecesViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import com.stripe.android.PaymentConfiguration;

import java.util.ArrayList;
import java.util.List;

public class ApplicationClass extends Application {

    public static Piece currentPiece;
    public static ArrayList<CartItem> cart;
    public static ArrayList<Piece> approved;
    public static FirebaseFirestore firebaseFirestore;
    public static FirebaseAuth firebaseAuth;
    public static DatabaseReference databaseReference;
    public static Double cartPrice = 0.0;

    public static List<Piece> FinalPieces;
    @Override
    public void onCreate() {
        super.onCreate();

        cart = new ArrayList<>();
        approved = new ArrayList<>();
        FinalPieces = (ArrayList<Piece>) PiecesViewModel.pieceList;
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        PaymentConfiguration.init(
                getApplicationContext(),
                "pk_test_51NR7UYDNDAkufzaAXcN5keXddWnchfuIjmJ8tjpnFPDgeLDjLaf4OkaM4bD7dDM9pKbUNE9SITtw7r1LZBvLruoJ00rp2XpCYd"
        );

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

//        ArrayList<Category> categories = new ArrayList<>();
//        categories.add(new Category("New Arrivals","https://th.bing.com/th/id/R.4080dc9abbb1adb502cbf3153e69007d?rik=hBZl0ZRAZBj6aQ&pid=ImgRaw&r=0" ));
//        categories.add(new Category("Shoes","https://cdn-fsly.yottaa.net/63505a9cd931401cad0764aa/api.carters.com/v~4b.9/on/demandware.static/-/Sites-Carters-Library/default/dw19501862/images/carters/faves/car_Q1_shopbycategory_shoes_desktop@2x.jpg?yocs=L_" ));
//        categories.add(new Category("Shorts","https://th.bing.com/th/id/OIP.z2gnBsBkkZivOLgh3qSSsgHaHa?pid=ImgDet&w=1024&h=1024&rs=1" ));
//        categories.add(new Category("Pajamas","https://th.bing.com/th/id/R.4080dc9abbb1adb502cbf3153e69007d?rik=hBZl0ZRAZBj6aQ&pid=ImgRaw&r=0" ));
//        categories.add(new Category("Sets","https://img.freepik.com/free-vector/clothes-newborn-children-isolated-icon-blue-bodysuit-with-print-locomotive-car-boyish-clothing-apparel-child-outfit-small-kids-fashion-style-babies-vector-flat_87689-2337.jpg?size=338&ext=jpg" ));
//        ref.child("Categories").setValue(categories);

// Create an ArrayList to hold the pajamas
//        ArrayList<Piece> pajamas = new ArrayList<>();
//        ArrayList<Piece> sets = new ArrayList<>();
//
//// Create instances of the Piece class and add them to the ArrayList
//        Piece p1 = new Piece("Baby Zip-Up PurelySoft Sleep & Play Pajamas", "https://cdn-fsly.yottaa.net/63505a9cd931401cad0764aa/api.carters.com/v~4b.9/dw/image/v2/AAMK_PRD/on/demandware.static/-/Sites-carters_master_catalog/default/dw16023720/productimages/1N795410.jpg?sw=800&sh=1000&sfrm=jpg&yocs=L_M_", "$11.00", "Soft, stretchy, zip-up pajamas", "These adorable pajamas are made from Carter's PurelySoft fabric, which is a silky-smooth and stretchy blend of cotton and spandex. The zip-up design makes it easy to change your little one, and the footless style allows for easy movement. The pajamas feature a cute allover print and ribbed cuffs for a snug fit.");
//        pajamas.add(p1);
//
//        Piece p2 = new Piece("Baby 1-Piece Striped 100% Snug Fit Cotton Footie Pajamas", "https://cdn-fsly.yottaa.net/63505a9cd931401cad0764aa/api.carters.com/v~4b.9/dw/image/v2/AAMK_PRD/on/demandware.static/-/Sites-carters_master_catalog/default/dw0ebafab4/productimages/1O263110.jpg?sw=800&sh=1000&sfrm=jpg&yocs=L_M_", "$12.00", "Striped, snug fit pajamas", "These footie pajamas are made from 100% cotton and have a snug fit to keep your little one cozy at bedtime. The striped design is stylish and timeless, while the snap closures make diaper changes a breeze.");
//        pajamas.add(p2);
//
//        Piece p3 = new Piece("Baby 1-Piece Star Wars™ 100% Snug Fit Cotton Pajamas", "https://cdn-fsly.yottaa.net/63505a9cd931401cad0764aa/api.carters.com/v~4b.9/dw/image/v2/AAMK_PRD/on/demandware.static/-/Sites-carters_master_catalog/default/dw327eab41/productimages/1L920512.jpg?sw=800&sh=1000&sfrm=jpg&yocs=L_M_", "$17.00", "Star Wars™ snug fit pajamas", "Your little one will love these Star Wars™ pajamas featuring their favorite characters from a galaxy far, far away. Made from 100% cotton, these pajamas have a snug fit to keep your child cozy and comfortable all night long.");
//        pajamas.add(p3);
//
//        Piece p4 = new Piece("Baby 1-Piece Sesame Street 100% Snug Fit Cotton Footie Pajamas", "https://cdn-fsly.yottaa.net/63505a9cd931401cad0764aa/api.carters.com/v~4b.9/dw/image/v2/AAMK_PRD/on/demandware.static/-/Sites-carters_master_catalog/default/dwd7701d85/productimages/1L920510.jpg?sw=800&sh=1000&sfrm=jpg&yocs=L_M_", "$17.00", "Sesame Street footie pajamas", "These adorable footie pajamas feature beloved Sesame Street characters, including Elmo, Cookie Monster, and Big Bird. Made from 100% cotton, they have a snug fit and built-in footies to keep tiny toes warm. The snap closures make it easy to change your little one, while the colorful print is sure to bring a smile to their face.");
//        pajamas.add(p4);
//
//        Piece p5 = new Piece("Baby 1-Piece Spider-Man 100% Snug Fit Cotton Footie Pajamas", "https://cdn-fsly.yottaa.net/63505a9cd931401cad0764aa/api.carters.com/v~4b.9/dw/image/v2/AAMK_PRD/on/demandware.static/-/Sites-carters_master_catalog/default/dw2ab8cd2f/productimages/1J999011.jpg?sw=800&sh=1000&sfrm=jpg&yocs=L_M_", "$17.00", "Spider-Man footie pajamas", "Your Spidey fan will be sleeping in style in these soft cotton PJs starring the friendly neighborhood Spider-Man! ©MARVEL");
//        pajamas.add(p5);
//
//        Piece p6 = new Piece("Baby 2-Pack PurelySoft Gown Set", "https://cdn-fsly.yottaa.net/63505a9cd931401cad0764aa/api.carters.com/v~4b.9/dw/image/v2/AAMK_PRD/on/demandware.static/-/Sites-carters_master_catalog/default/dw28d710c8/productimages/1O969310.jpg?sw=800&sh=1000&sfrm=jpg&yocs=L_M_", "$16.00", "Soft, stretchy newborn gowns", "Here's a comfy gown perfect for those long days and nights at home. This 2-pack newborn gown set is silky-soft, stretchy and features snug elastic to keep baby's little legs warm while keeping diaper changes easy for you. Psst...this makes a perfect baby shower gift.");
//        pajamas.add(p6);
//



//// Baby Eyelet Ruffle Sleeve Jersey Bodysuit
//        Piece product1 = new Piece("Baby Eyelet Ruffle Sleeve Jersey Bodysuit", "Extra adorable with ruffle sleeves and eyelet details, this soft cotton jersey bodysuit is chic and functional perfection!", "Adorable eyelet ruffle bodysuit", "$13.20", "https://cdn-fsly.yottaa.net/63505a9cd931401cad0764aa/api.carters.com/v~4b.9/dw/image/v2/AAMK_PRD/on/demandware.static/-/Sites-carters_master_catalog/default/dw8220a859/productimages/1O814410.jpg?sw=800&sh=1000&sfrm=jpg&yocs=L_M_");
//
//// Baby 5-Pack Short-Sleeve Bodysuits
//        Piece product2 = new Piece("Baby 5-Pack Short-Sleeve Bodysuits", "Crafted in soft cotton, this pack of five is perfect for baby.", "Soft 5-pack short-sleeve bodysuits", "$18.00", "https://cdn-fsly.yottaa.net/63505a9cd931401cad0764aa/api.carters.com/v~4b.9/dw/image/v2/AAMK_PRD/on/demandware.static/-/Sites-carters_master_catalog/default/dw8eb2311a/productimages/1P559610.jpg?sw=800&sh=1000&sfrm=jpg&yocs=L_M_");
//
//// Baby 5-Pack Tank Bodysuits
//        Piece product3 = new Piece("Baby 5-Pack Tank Bodysuits", "Crafted in soft cotton with five in one pack, this bodysuit set is perfect for filling baby's closet!", "Soft 5-pack tank bodysuits", "$15.00", "https://cdn-fsly.yottaa.net/63505a9cd931401cad0764aa/api.carters.com/v~4b.9/dw/image/v2/AAMK_PRD/on/demandware.static/-/Sites-carters_master_catalog/default/dwf20dab4e/productimages/1P184310.jpg?sw=800&sh=1000&sfrm=jpg&yocs=L_M_");
//
//// Baby Eyelet Ruffle Sleeve Jersey Bodysuit
//        Piece product4 = new Piece("Baby Eyelet Ruffle Sleeve Jersey Bodysuit", "Extra adorable with ruffle sleeves and eyelet details, this soft cotton jersey bodysuit is chic and functional perfection!", "Adorable eyelet ruffle bodysuit", "$13.20", "https://cdn-fsly.yottaa.net/63505a9cd931401cad0764aa/api.carters.com/v~4b.9/dw/image/v2/AAMK_PRD/on/demandware.static/-/Sites-carters_master_catalog/default/dw4ab28ba0/productimages/1O814310.jpg?sw=800&sh=1000&sfrm=jpg&yocs=L_M_");
//
//// Baby Organic Cotton Dress
//        Piece product5 = new Piece("Baby Organic Cotton Dress", "Made of 100% certified organic cotton, this dress is comfy and durable in any season — layer it up in fall or wear it alone on warmer days. With exclusive colors and style suited for any season, this one is sustainably made to pass down easily from one baby girl to another. Crafted in the purest organic fabrics and sustainable materials, Little Planet is a return to simplicity. Thoughtful essentials and timeless pieces to gift or to hold on to.", "Organic cotton dress", "$16.95", "https://cdn-fsly.yottaa.net/63505a9cd931401cad0764aa/api.carters.com/v~4b.9/dw/image/v2/AAMK_PRD/on/demandware.static/-/Sites-carters_master_catalog/default/dw95af9e08/productimages/1N746510.jpg?sw=800&sh=1000&sfrm=jpg&yocs=L_M_");
//
//// Baby I Love Dad Bodysuit
//        Piece product6 = new Piece("Baby I Love Dad Bodysuit", "The bodysuit says it all! In asnuggly soft material and featuring a sweet message, \"I Love Dad\", this bodysuit is an all around winner in our book for baby's first looks.", "Sweet message bodysuit for dad", "$12.00", "https://cdn-fsly.yottaa.net/63505a9cd931401cad0764aa/api.carters.com/v~4b.9/dw/image/v2/AAMK_PRD/on/demandware.static/-/Sites-carters_master_catalog/default/dw34c75ef9/productimages/1O815010.jpg?sw=800&sh=1000&sfrm=jpg&yocs=L_M_");
//
//// Baby Mint Striped Bodysuit
//        Piece product7 = new Piece("Baby Mint Striped Bodysuit", "Create endless looks with this fashion forward bodysuit for baby girl from Carter's. Mix and match this flutter sleeve bodysuit with any bottoms and she's sure to have a photo-ready look.", "Fashionable mint striped bodysuit", "$6.00", "https://cdn-fsly.yottaa.net/63505a9cd931401cad0764aa/api.carters.com/v~4b.9/dw/image/v2/AAMK_PRD/on/demandware.static/-/Sites-carters_master_catalog/default/dw37a05642/productimages/1O921410.jpg?sw=800&sh=1000&sfrm=jpg&yocs=L_M_");
//
//        sets.add(product1);
//        sets.add(product2);
//        sets.add(product3);
//        sets.add(product4);
//        sets.add(product5);
//        sets.add(product6);
//        sets.add(product7);

//        List<Piece> shoes = new ArrayList<>();
//        shoes.add(new Piece("Tie-Dye Sandal", "https://cdn-fsly.yottaa.net/63505a9cd931401cad0764aa/api.carters.com/v~4b.9/dw/image/v2/AAMK_PRD/on/demandware.static/-/Sites-carters_master_catalog/default/dw2ec6adc4/productimages/CR07311.jpg?sw=800&sh=1000&sfrm=jpg&yocs=L_M_", "11.00", "Baby Tie-Dye Hook & Loop Soft Sandal", "These soft sandals feature a colorful tie-dye design and a hook and loop closure for easy on and off."));
//        shoes.add(new Piece("Cork Sandals", "https://cdn-fsly.yottaa.net/63505a9cd931401cad0764aa/api.carters.com/v~4b.9/dw/image/v2/AAMK_PRD/on/demandware.static/-/Sites-carters_master_catalog/default/dw88aede0c/productimages/OK02636.jpg?sw=800&sh=1000&sfrm=jpg&yocs=L_M_", "15.00", "Baby Cork Sandals", "These stylish cork sandals are perfect for your little one's summer adventures."));
//        shoes.add(new Piece("Stripes Flip Flops", "https://cdn-fsly.yottaa.net/63505a9cd931401cad0764aa/api.carters.com/v~4b.9/dw/image/v2/AAMK_PRD/on/demandware.static/-/Sites-carters_master_catalog/default/dw4b43f4cd/productimages/OS23X09H.jpg?sw=800&sh=1000&sfrm=jpg&yocs=L_M_", "5.00", "Stars And Stripes Classic Flip Flops", "Show off your patriotic side with these classic flip flops featuring a stars and stripes design."));
//        shoes.add(new Piece("Flip Flops", "https://cdn-fsly.yottaa.net/63505a9cd931401cad0764aa/api.carters.com/v~4b.9/dw/image/v2/AAMK_PRD/on/demandware.static/-/Sites-carters_master_catalog/default/dw208370d5/productimages/OS23X07H.jpg?sw=800&sh=1000&sfrm=jpg&yocs=L_M_", "3.00", "Classic Flip Flops", "These classic flip flops are a summer essential for any wardrobe."));
//        shoes.add(new Piece("Fisherman Sandals", "https://cdn-fsly.yottaa.net/63505a9cd931401cad0764aa/api.carters.com/v~4b.9/dw/image/v2/AAMK_PRD/on/demandware.static/-/Sites-carters_master_catalog/default/dw1a3dde7d/productimages/OK02625.jpg?sw=800&sh=1000&sfrm=jpg&yocs=L_M_", "15.00", "Baby Soft Fisherman Sandals", "These soft fisherman sandals are perfect for keeping your little one comfortable during their summer adventures."));
//        shoes.add(new Piece(
//                "2-Pack Flip Flops",
//                "https://cdn-fsly.yottaa.net/63505a9cd931401cad0764aa/api.carters.com/v~4b.9/dw.6adc4/productimages/CR07311.jpg?sw=800&sh=1000&sfrm=jpg&yocs=L_M_",
//                "6.19",
//                "2-Pack Classic Flip Flops",
//                "This 2-pack of classic flip flops is perfect for stocking up on summer essentials."
//        ));shoes.add (new Piece(
//
//                "Flip Flops",
//                "https://cdn-fsly.yottaa.net/63505a9cd931401cad0764aa/api.carters.com/v~4b.9/dw/image/v2/AAMK_PRD/on/demandware.static/-/Sites-carters_master_catalog/default/dwf3a44876/productimages/OS23X05H.jpg?sw=800&sh=1000&sfrm=jpg&yocs=L_M_",
//                "4.00",
//                "Classic Flip Flops",
//                "These classic flip flops are a summer essential for any wardrobe."
//        ));
        ArrayList<Piece> Skirts = new ArrayList();
        Piece piece1 = new Piece("Aztec Pull-On Shorts", "https://cdn-fsly.yottaa.net/63505a9cd931401cad0764aa/api.carters.com/v~4b.9/dw/image/v2/AAMK_PRD/on/demandware.static/-/Sites-carters_master_catalog/default/dw7a659bf7/productimages/1O932613.jpg?sw=800&sh=1000&sfrm=jpg&yocs=L_M_", "8.00", "Baby Aztec Pull-On French Terry Shorts", "With an easy on design and functional drawstring, these soft cotton shorts are perfect for everyday.");

        Piece piece2 = new Piece("French Terry Shorts", "https://cdn-fsly.yottaa.net/63505a9cd931401cad0764aa/api.carters.com/v~4b.9/dw/image/v2/AAMK_PRD/on/demandware.static/-/Sites-carters_master_catalog/default/dwf1e9dd43/productimages/1O922411.jpg?sw=800&sh=1000&sfrm=jpg&yocs=L_M_", "7.00", "Baby Pull-On French Terry Shorts", "With an easy on design and functional drawstring, these soft cotton shorts are perfect for everyday.");

        Piece piece3 = new Piece("Knit Denim Shorts", "https://cdn-fsly.yottaa.net/63505a9cd931401cad0764aa/api.carters.com/v~4b.9/dw/image/v2/AAMK_PRD/on/demandware.static/-/Sites-carters_master_catalog/default/dw08d15a16/productimages/1P057510.jpg?sw=800&sh=1000&sfrm=jpg&yocs=L_M_", "8.00", "Baby Pull-On Knit Denim Shorts", "With an easy pull-on design and a functional drawstring, these shorts are perfect for playdates.");

        Piece piece4 = new Piece("Bike Shorts", "https://cdn-fsly.yottaa.net/63505a9cd931401cad0764aa/api.carters.com/v~4b.9/dw/image/v2/AAMK_PRD/on/demandware.static/-/Sites-carters_master_catalog/default/dw9419731f/productimages/1P065610.jpg?sw=800&sh=1000&sfrm=jpg&yocs=L_M_", "7.00", "Baby Bike Shorts", "Super comfy and stretchy, these bike shorts are perfect for playtime!");

        Piece piece5 = new Piece("Paperbag Sun Shorts", "https://cdn-fsly.yottaa.net/63505a9cd931401cad0764aa/api.carters.com/v~4b.9/dw/image/v2/AAMK_PRD/on/demandware.static/-/Sites-carters_master_catalog/default/dwe9fe39cc/productimages/1O986010.jpg?sw=800&sh=1000&sfrm=jpg&yocs=L_M_", "10.00", "Baby Paperbag Sun Shorts", "With a paperbag waist and cute tacked bow, these easy pull-on shorts are made for summer fun!");

        Piece piece6 = new Piece("Daisy Print Skort", "https://cdn-fsly.yottaa.net/63505a9cd931401cad0764aa/api.carters.com/v~4b.9/dw/image/v2/AAMK_PRD/on/demandware.static/-/Sites-carters_master_catalog/default/dw9d50ad7d/productimages/1P287110.jpg?sw=800&sh=1000&sfrm=jpg&yocs=L_M_", "6.00", "Baby Daisy Print Handstand Skort", "Built for play, this skort features a fun print and built in shorts. Handstand and cartwheel approved!");

        Piece piece7 = new Piece("Organic Cotton Shortall", "https://cdn-fsly.yottaa.net/63505a9cd931401cad0764aa/api.carters.com/v~4b.9/dw/image/v2/AAMK_PRD/on/demandware.static/-/Sites-carters_master_catalog/default/dwf2e5d42a/productimages/1H445210.jpg?sw=800&sh=1000&sfrm=jpg&yocs=L_M_", "12.00", "Baby Organic Cotton Gauze Shortall", "How sweet will baby look in this shortall?! Made from soft organic cotton gauze, this one-piece outfit is perfect for warmer days.");
        Skirts.add(piece1);
        Skirts.add(piece2);
        Skirts.add(piece3);
        Skirts.add(piece4);
        Skirts.add(piece5);
        Skirts.add(piece6);
        Skirts.add(piece7);

        ref.child("Shorts").setValue(Skirts);

//        ArrayList<Piece> newArrivals = new ArrayList<>();
////
//        Piece piece1 = new Piece("Soft Set", "https://cdn-fsly.yottaa.net/63505a9cd931401cad0764aa/api.carters.com/v~4b.9/dw/image/v2/AAMK_PRD/on/demandware.static/-/Sites-carters_master_catalog/default/dw589d39ac/productimages/1O860710.jpg?sw=800&sh=1000&sfrm=jpg&yocs=L_M_", "10.40", "Baby 3-Piece Little Short Set", "Made with soft cotton, this set is complete with a short-sleeve tee and a coordinating pair of pull-on shorts. Plus, add some cozy socks to complete the look.");
//        newArrivals.add(piece1);
//
//        Piece piece4 = new Piece("Lion Set", "https://cdn-fsly.yottaa.net/63505a9cd931401cad0764aa/api.carters.com/v~4b.9/dw/image/v2/AAMK_PRD/on/demandware.static/-/Sites-carters_master_catalog/default/dwce255291/productimages/1O920510.jpg?sw=800&sh=1000&sfrm=jpg&yocs=L_M_", "10.40", "Baby 3-Piece Lion Little Character Set", "Stock up on three soft cotton pieces, including a short-sleeve tee, striped bodysuit and a coordinating pair of pull on pants.");
//        newArrivals.add(piece4);
//
//        Piece piece5 = new Piece("Toucan Set", "https://cdn-fsly.yottaa.net/63505a9cd931401cad0764aa/api.carters.com/v~4b.9/dw/image/v2/AAMK_PRD/on/demandware.static/-/Sites-carters_master_catalog/default/dw6130f3bf/productimages/1O860810.jpg?sw=800&sh=1000&sfrm=jpg&yocs=L_M_", "10.40", "Baby 3-Piece Toucan Short Set", "Crafted in soft cotton with three pieces in one, this set is complete with a short-sleeve tee, toucan bodysuit and a pair of matching tropical shorts.");
//        newArrivals.add(piece5);
//
//        Piece piece6 = new Piece("Printed Rompers",  "https://cdn-fsly.yottaa.net/63505a9cd931401cad0764aa/api.carters.com/v~4b.9/dw/image/v2/AAMK_PRD/on/demandware.static/-/Sites-carters_master_catalog/default/dwf115a6ea/productimages/1O861010.jpg?sw=800&sh=1000&sfrm=jpg&yocs=L_M_", "13.00", "Baby 2-Pack Cotton Rompers", "Stock up on two sweet rompers, both complete with cute prints and short-sleeves.");
//        newArrivals.add(piece6);
//
//        Piece piece7 = new Piece("Cozy Set",  "https://cdn-fsly.yottaa.net/63505a9cd931401cad0764aa/api.carters.com/v~4b.9/dw/image/v2/AAMK_PRD/on/demandware.static/-/Sites-carters_master_catalog/default/dwd3a4d5e9/productimages/1O860410.jpg?sw=800&sh=1000&sfrm=jpg&yocs=L_M_", "11.20", "Baby 3-Piece Little Cardigan Set", "Complete with a cozy cardigan, short-sleeve bodysuit, and a pair of coordinating pants, this set is perfect for layering and keeping your baby warm and comfortable.");
//        newArrivals.add(piece7);
//
//
//        Piece piece11 = new Piece("Little Vest", "https://cdn-fsly.yottaa.net/63505a9cd931401cad0764aa/api.carters.com/v~4b.9/dw/image/v2/AAMK_PRD/on/demandware.static/-/Sites-carters_master_catalog/default/dw6d3f473a/productimages/1O860310.jpg?sw=800&sh=1000&sfrm=jpg&yocs=L_M_", "11.20", "Baby 3-Piece Little Vest Set", "Complete with a cozy vest, short-sleeve bodysuit, and a pair of coordinating pants, this set is perfect for layering and keeping your baby warm and comfortable.");
//        newArrivals.add(piece11);
//
//        Piece piece21 = new Piece("Little Jacket", "https://cdn-fsly.yottaa.net/63505a9cd931401cad0764aa/api.carters.com/v~4b.9/dw/image/v2/AAMK_PRD/on/demandware.static/-/Sites-carters_master_catalog/default/dw14f20e5b/productimages/1O860210.jpg?sw=800&sh=1000&sfrm=jpg&yocs=L_M_", "11.20", "Baby 3-Piece Little Jacket Set", "Complete with a cozy jacket, short-sleeve bodysuit, and a pair of coordinating pants, this set is perfect for layering and keeping your baby warm and comfortable.");
//        newArrivals.add(piece21);
//
//        Piece piece31 = new Piece("Little Character", "https://cdn-fsly.yottaa.net/63505a9cd931401cad0764aa/api.carters.com/v~4b.9/dw/image/v2/AAMK_PRD/on/demandware.static/-/Sites-carters_master_catalog/default/dw1b0af4cd/productimages/1O860510.jpg?sw=800&sh=1000&sfrm=jpg&yocs=L_M_", "10.40", "Baby 3-Piece Little Character Set", "Stock up on three soft cotton pieces, including a short-sleeve tee, striped bodysuit and a coordinating pair of pull on pants.");
//        newArrivals.add(piece31);
//
//        Piece piece41 = new Piece("Little Short",  "https://cdn-fsly.yottaa.net/63505a9cd931401cad0764aa/api.carters.com/v~4b.9/dw/image/v2/AAMK_PRD/on/demandware.static/-/Sites-carters_master_catalog/default/dw589d39ac/productimages/1O860710.jpg?sw=800&sh=1000&sfrm=jpg&yocs=L_M_", "10.40", "Baby 3-Piece Little Short Set", "Made with soft cotton, this set is complete with a short-sleeve tee and a coordinating pair of pull-on shorts. Plus, add some cozy socks to complete the look.");
//        newArrivals.add(piece41);
//
//        Piece piece51 = new Piece("Little Elephant",  "https://cdn-fsly.yottaa.net/63505a9cd931401cad0764aa/api.carters.com/v~4b.9/dw/image/v2/AAMK_PRD/on/demandware.static/-/Sites-carters_master_catalog/default/dw9be2a4e6/productimages/1O860610.jpg?sw=800&sh=1000&sfrm=jpg&yocs=L_M_", "10.40", "Baby 3-Piece Little Elephant Set", "Made with soft cotton, this set is complete with a short-sleeve tee, a coordinating striped bodysuit, and a pair of pull-on pants featuring an elephant on the bottom.");
//        newArrivals.add(piece51);
//
//        Piece piece61 = new Piece("Cotton Jumpsuits", "https://cdn-fsly.yottaa.net/63505a9cd931401cad0764aa/api.carters.com/v~4b.9/dw/image/v2/AAMK_PRD/on/demandware.static/-/Sites-carters_master_catalog/default/dwe6d0fd8d/productimages/1O861210.jpg?sw=800&sh=1000&sfrm=jpg&yocs=L_M_", "13.00", "Baby 2-Pack Cotton Jumpsuits", "Stock up on two cute and cozy jumpsuits, complete with short-sleeves and adorable prints.");
//        newArrivals.add(piece61);
//
//
//        ref.child("New Arrivals").setValue(newArrivals);
//        ref.child("pajamas").setValue(pajamas);
//


    }

    public static void restartActivity(Activity act){
        Intent intent=new Intent();
        intent.setClass(act, act.getClass());
        act.finish();
        act.startActivity(intent);
    }

    public static void navigateToActivity(Activity context, Class destinationActivity) {
        Intent intent = new Intent(context.getApplicationContext(), destinationActivity);
        context.startActivity(intent);
    }

}
