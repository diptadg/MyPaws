package com.example.mypaws;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileFragment extends Fragment {

    public ProfileFragment() {

    }

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    private TextView fullName;
    private TextView email;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        fullName = view.findViewById(R.id.profile_get_full_name);
        email = view.findViewById(R.id.profile_get_email_address);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        if (currentUser != null){

            String displayName = "Dipta";
            String userEmail = currentUser.getEmail();

            fullName.setText(displayName);
            email.setText(userEmail);
        }
    }
}
