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

    private ImageView imageView;
    private TextView textView;
    private int image;
    private String title;

    public static Fragment newInstance(int image, String title){
        InitialFragment initialFragment = new InitialFragment();
        Bundle args = new Bundle();
        args.putInt("image", image);
        args.putString("title", title);

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

        View v = inflater.inflate(R.layout.fragment_initial, container, false);

        imageView = v.findViewById(R.id.imageViewIconInitial);
        textView = v.findViewById(R.id.textViewInitial);

        imageView.setImageResource(image);
        textView.setText(title);

        return v;
    }

}
