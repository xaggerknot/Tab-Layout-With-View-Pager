package com.jagteshwar.tablayoutwithpageviwer.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.util.SizeF;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jagteshwar.tablayoutwithpageviwer.R;
import com.jagteshwar.tablayoutwithpageviwer.activity.SendActivity;
import com.jagteshwar.tablayoutwithpageviwer.fragments.SendFragment;


public class FirstFragment extends Fragment {

    Context context;
    LinearLayout sendLinearLayout;
    SendFragment sendFragment;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        Log.d("my tag", context + "            +++++++++++++++++++");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("my tag", "onCreateView");
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        sendLinearLayout = view.findViewById(R.id.sendLinearLayout);
        Toast.makeText(context, "onCreateView", Toast.LENGTH_SHORT).show();

        onclick();
        return view;
    }

    private void onclick() {
        sendLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SendActivity.class);
                startActivity(intent);


       /*         if (sendFragment != null) {
                    Log.d("my tag", "Fragment already attached");
                } else {
                    sendFragment = new SendFragment();
                    Toast.makeText(context, "SENDCLICKED", Toast.LENGTH_SHORT).show();
                    Log.d("my tag", "send clicked");
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.add(R.id.fragment_container, sendFragment).commit();

                }*/
              /*  SendFragment fragment = new SendFragment();
                getChildFragmentManager().beginTransaction()
                        .add(R.id.fragment_container, fragment)
                        .addToBackStack(null)
                        .commit();*/
            }
        });
    }

}