package com.sem4ikt.uni.recipefinderchatbot.database.FirebaseDB;

import android.nfc.Tag;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;



/**
 * Created by anton on 31-03-2017.
 */

public class FirebaseDBRepository implements IFirebaseDBRepository {
    private String uid = null;
    private User user;

    private FirebaseAuth fba = FirebaseAuth.getInstance();

    private DatabaseReference  userDatabase;
    private DatabaseReference  recipeDatabase;

    public FirebaseDBRepository()
    {
        user = new User("undefined",false);
        setUid("test");
    }

    @Override
    public void saveRecipe(RecipeModel recipe) {
        if(uid != null) //probably not needed, if uid get set onlogin succesfull
        {
            recipeDatabase.push().setValue(recipe);
        }
    }



    @Override
    public void updateName(String name) {
        user.username = name;
        userDatabase.setValue(user);
    }

    @Override
    public void saveUser(boolean returningUser) {
        user.returninguser = returningUser;
        userDatabase.setValue(user);
    }

    @Override
    public User getUser() {
        userDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);
                Log.e("ondatachange",user.username);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("User",databaseError.toString());
            }
        });
        return user;
    }

    @Override
    public void deleteRecipe(RecipeModel recipe) {
        Query recipeQuery = recipeDatabase.orderByChild("id").equalTo(recipe.getId());

        recipeQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot recipeSnapshot : dataSnapshot.getChildren()){
                    recipeSnapshot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("onCancelled",databaseError.toString());
            }
        });

    }

    public void userListener()
    {
        ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("LoadUser:onCancelled",databaseError.toString());
            }
        };
    }


    @Override
    public void setUid(String uid) {
        FirebaseUser user = fba.getInstance().getCurrentUser(); //delete me when correct solution is found
        if (user != null) {
            this.uid = user.getUid();
            recipeDatabase = FirebaseDatabase.getInstance().getReference("recipe/"+this.uid);
            userDatabase = FirebaseDatabase.getInstance().getReference("User/"+this.uid);
            Log.e("uid",this.uid);
        }
        else
            this.uid = null;

    }

}
