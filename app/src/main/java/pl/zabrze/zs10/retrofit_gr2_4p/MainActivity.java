package pl.zabrze.zs10.retrofit_gr2_4p;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    RadioGroup radioGroupPytania;
    RadioButton radioButtonA, radioButtonB, radioButtonC;
    Button buttonNastepne;
    TextView textViewTrescPytania;
    List<Pytanie> listaPytan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioButtonA = findViewById(R.id.radioButton);
        radioButtonB = findViewById(R.id.radioButton2);
        radioButtonC = findViewById(R.id.radioButton3);
        radioGroupPytania = findViewById(R.id.radioGroup);
        buttonNastepne = findViewById(R.id.button);
        textViewTrescPytania = findViewById(R.id.textViewPytanie);

    }
}