package com.example.mypaws;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class YourPetsFragment extends Fragment {

    public YourPetsFragment() {

    }

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    private CardView firstPet;
    private CardView secondPet;
    private CardView thirdPet;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_your_pets, container, false);

        firstPet = view.findViewById(R.id.cardView_your_pets);
        secondPet = view.findViewById(R.id.cardView_your_pets_2);
        thirdPet = view.findViewById(R.id.cardView_your_pets_3);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firstPet.setOnClickListener(new View.OnClickListener() {

            FirebaseUser currentUser = firebaseAuth.getInstance().getCurrentUser();

            @Override
            public void onClick(View v) {
                if (currentUser != null) {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new FirstPetDetailsFragment()).commit();
                } else {
                    Toast.makeText(getActivity(), "Please sign in first!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        secondPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseUser currentUser = firebaseAuth.getInstance().getCurrentUser();

                if (currentUser != null) {

                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new SecondPetDetailsFragment()).commit();

                } else {
                    Toast.makeText(getActivity(), "Please sign in first!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        thirdPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseUser currentUser = firebaseAuth.getInstance().getCurrentUser();

                if (currentUser != null) {

                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new ThirdPetDetailsFragment()).commit();

                } else {
                    Toast.makeText(getActivity(), "Please sign in first!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
