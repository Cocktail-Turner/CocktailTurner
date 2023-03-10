package com.team5.cocktailturner.ui.main.fragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.team5.cocktailturner.R;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LiquidFirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LiquidFirstFragment extends Fragment {

    public LiquidFirstFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_liquid_first, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Button button = view.findViewById(R.id.liquid_next_button);
        button.setOnClickListener(v -> {
            EditText editField = view.findViewById(R.id.liquid1);
            String editText = "";
            editText = editField.getText().toString();
            if (editText.matches("")) {
                Toast.makeText(getActivity(),
                        "first ingredient is required",
                        Toast.LENGTH_LONG).show();
                return;
            }

            Resources r = getResources();
            String name = getActivity().getPackageName();
            ArrayList<String> liquidData = new ArrayList<>();
            for (int i = 1; i < 7; i++) {
                EditText liquidIngredient = view.findViewById(r.getIdentifier("liquid" + i, "id", name));
                String liquidText = String.valueOf(liquidIngredient.getText());
                if (StringUtils.isNotEmpty(liquidText)) {
                    liquidData.add(liquidText);
                }
            }

            Fragment ingredientSecondFragment = new IngredientSecondFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            Bundle arguments = new Bundle();
            arguments.putStringArrayList("liquidData", liquidData);
            ingredientSecondFragment.setArguments(arguments);
            transaction.replace(R.id.container, ingredientSecondFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });
    }
}
