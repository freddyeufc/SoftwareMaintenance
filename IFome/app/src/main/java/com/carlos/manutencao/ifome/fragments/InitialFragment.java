package com.carlos.manutencao.ifome.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.carlos.manutencao.ifome.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InitialFragment extends Fragment {

    private ImageView imageViewLogo;
    private TextView textViewTitle, textViewDescription;
    private int image;
    private String title, description;

    public static Fragment newInstance(int image, String title, String description){
        InitialFragment initialFragment = new InitialFragment();
        Bundle args = new Bundle();
        args.putInt("image", image);
        args.putString("title", title);
        args.putString("description", description);

        initialFragment.setArguments(args);

        return initialFragment;
    }

    public InitialFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        image = getArguments().getInt("image");
        title = getArguments().getString("title");
        description = getArguments().getString("description");

        View v = inflater.inflate(R.layout.fragment_initial, container, false);

        imageViewLogo = v.findViewById(R.id.logoFragmentInitialId);
        textViewTitle = v.findViewById(R.id.titleFragmentInitialId);
        textViewDescription = v.findViewById(R.id.descriptionFragmentInitialId);

        imageViewLogo.setImageResource(image);
        textViewTitle.setText(title);
        textViewDescription.setText(description);

        return v;
    }

}
