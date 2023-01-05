package com.example.mypaws;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    private TextView navHeaderEmail;
    private TextView navHeaderSignInStatus;
    private CircleImageView navHeaderProfilePicture;

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navHeaderSignInStatus = (TextView)navigationView.getHeaderView(0).findViewById(R.id.nav_header_email);
        navHeaderEmail = (TextView)navigationView.getHeaderView(0).findViewById(R.id.nav_header_sign_in_status);
        navHeaderProfilePicture = (CircleImageView)navigationView.getHeaderView(0).findViewById(R.id.nav_header_profile_picture);

        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();



        if (savedInstanceState == null) {
            getSupportFragmentManager(). beginTransaction(). replace(R.id.fragment_container,
                    new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }


    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = firebaseAuth.getInstance().getCurrentUser();

        if (currentUser != null){

            String email = currentUser.getEmail();
            String signInStatus = "Signed In";

            navHeaderEmail.setText(email);
            navHeaderSignInStatus.setText(signInStatus);

        }else{
            navHeaderProfilePicture.setImageResource(R.drawable.mypaws_hero);
            Toast.makeText(this, "Signed in as Guest!", Toast.LENGTH_SHORT).show();
        }



    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();


        switch (item.getItemId()){
            case R.id.nav_profile:
                if (currentUser != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new ProfileFragment()).commit();
                }else{
                    Toast.makeText(this, "Please sign in first!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.nav_store:
                if (currentUser != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new StoreFragment()).commit();
                }else{
                    Toast.makeText(this, "Please sign in first!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.nav_home:
                if (currentUser != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new HomeFragment()).commit();
                }else{
                    Toast.makeText(this, "Please sign in first!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.nav_your_pets:
                if (currentUser != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new YourPetsFragment()).commit();
                }else{
                    Toast.makeText(this, "Please sign in first!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.nav_pet_tips:
                if (currentUser != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new PetTipsFragment()).commit();
                }else{
                    Toast.makeText(this, "Please sign in first!", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.nav_logout:
                if (currentUser != null){
                    firebaseAuth.signOut();
                    Toast.makeText(this,"Signed out successfully!",Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new SignInFragment()).commit();
                    finish();

                }else{
                    Toast.makeText(this,"Not signed in!",Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new SignInFragment()).commit();
                    getSupportActionBar().hide();
                }
        }
        return true;


    }

    @Override
    public void onBackPressed(){
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
}