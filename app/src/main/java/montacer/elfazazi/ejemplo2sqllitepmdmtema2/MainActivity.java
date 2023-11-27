package montacer.elfazazi.ejemplo2sqllitepmdmtema2;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import montacer.elfazazi.ejemplo2sqllitepmdmtema2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}