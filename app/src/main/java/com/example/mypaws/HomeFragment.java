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

public class HomeFragment extends Fragment {

    public HomeFragment() {

    }

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    private TextView yourPets;
    private TextView storeLink;

    private CardView tipsLink;
    private CardView petsLink1;
    private CardView petsLink2;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        yourPets = view.findViewById(R.id.tv_home_your_pets_link);
        storeLink = view.findViewById(R.id.tv_home_pet_store_link);
        tipsLink = view.findViewById(R.id.cardView_home_pet_tips_link);
        petsLink1 = view.findViewById(R.id.cardView_home_pets);
        petsLink2 = view.findViewById(R.id.cardView_home_pets_2);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        yourPets.setOnClickListener(new View.OnClickListener() {

            FirebaseUser currentUser = firebaseAuth.getInstance().getCurrentUser();

            @Override
            public void onClick(View v) {
                if (currentUser != null) {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new YourPetsFragment()).commit();
                } else {
                    Toast.makeText(getActivity(), "Please sign in first!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        storeLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseUser currentUser = firebaseAuth.getInstance().getCurrentUser();

                if (currentUser != null) {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new StoreFragment()).commit();
                } else {
                    Toast.makeText(getActivity(), "Please sign in first!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        tipsLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseUser currentUser = firebaseAuth.getInstance().getCurrentUser();

                if (currentUser != null) {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new PetTipsFragment()).commit();
                } else {
                    Toast.makeText(getActivity(), "Please sign in first!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        petsLink1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser currentUser = firebaseAuth.getInstance().getCurrentUser();

                if (currentUser != null) {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new FirstPetDetailsFragment()).commit();
                } else {
                    Toast.makeText(getActivity(), "Please sign in first!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        petsLink2.setOnClickListener(new View.OnClickListener() {
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
