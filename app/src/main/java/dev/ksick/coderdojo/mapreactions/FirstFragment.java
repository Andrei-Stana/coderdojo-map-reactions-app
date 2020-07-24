package dev.ksick.coderdojo.mapreactions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class FirstFragment extends Fragment {

    private EditText editTextInput;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextInput = view.findViewById(R.id.edittext_input);

        view.findViewById(R.id.button_go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editTextInput.getText().toString();

                Bundle arguments = new Bundle();
                arguments.putString("phrase", text);

                NavHostFragment
                        .findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment, arguments);
            }
        });
    }
}