package com.edu.fateczl.bitstobytes;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * @author Adriano M Sanchez
 */
public class MainActivity extends AppCompatActivity {

    private EditText etBits;

    private RadioGroup rg;

    private TextView txtResult;

    private Button btConvert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etBits = findViewById(R.id.etBits);

        rg = findViewById(R.id.rg);

        txtResult = findViewById(R.id.txtResult);

        btConvert = findViewById(R.id.btConvert);
        btConvert.setOnClickListener(e -> convertBits());
    }

    private void convertBits(){
        float bits = Float.parseFloat(etBits.getText().toString()) / 8f;
        RadioButton selected = findViewById(rg.getCheckedRadioButtonId());
        int divisions = 0;
        switch (selected.getText().toString()){
            case "KB":
                divisions = 1;
                break;
            case "MB":
                divisions = 2;
                break;
            case "GB":
                divisions = 3;
                break;
            case "TB":
                divisions = 4;
                break;
            default:
                divisions = -1;
        }

        if(divisions != -1)
            while(divisions > 0){
                bits /= 1024f;
                divisions--;
            }

        txtResult.setText(String.format("%.2f %s", bits, selected.getText().toString()));
    }
}