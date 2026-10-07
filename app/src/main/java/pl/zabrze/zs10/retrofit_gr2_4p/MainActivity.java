package pl.zabrze.zs10.retrofit_gr2_4p;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
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
    int aktualne = 0;
    int liczbaPunktow = 0;
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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/markup54/retrofit_pytania_zwierzeta/main/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Pytanie>> call = jsonPlaceHolderApi.getPytania();
        call.enqueue(
                new Callback<List<Pytanie>>() {
                    @Override
                    public void onResponse(Call<List<Pytanie>> call, Response<List<Pytanie>> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(MainActivity.this,
                                    response.code(),
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }
                        listaPytan = response.body();
                        textViewTrescPytania.setText(listaPytan.get(0).getTresc());
                        wyswietlPytanie(0);
                    }

                    @Override
                    public void onFailure(Call<List<Pytanie>> call, Throwable t) {

                    }
                }
        );
        radioGroupPytania.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        boolean odpowiedz;
                        if(i == R.id.radioButton){
                            odpowiedz = sprawdzOdpowiedz(aktualne,0);
                        }
                        else if(i == R.id.radioButton2){
                            odpowiedz =sprawdzOdpowiedz(aktualne,1);
                        }
                        else{
                            odpowiedz = sprawdzOdpowiedz(aktualne,2);
                        }
                    }
                }
        );
        buttonNastepne.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //sprawdzanie odpowiedzi
                        int klikniety = radioGroupPytania.getCheckedRadioButtonId();
                        boolean odpowiedz;
                        if(klikniety == R.id.radioButton){
                            odpowiedz = sprawdzOdpowiedz(aktualne,0);
                        }
                        else if(klikniety == R.id.radioButton2){
                            odpowiedz =sprawdzOdpowiedz(aktualne,1);
                        }
                        else{
                            odpowiedz = sprawdzOdpowiedz(aktualne,2);
                        }
                        if(odpowiedz){
                            liczbaPunktow++;
                        }
                        else {
                            liczbaPunktow --;
                        }
                        aktualne++;
                        if(aktualne<listaPytan.size()) {
                            wyswietlPytanie(aktualne);
                        }
                        else{
                            buttonNastepne.setVisibility(View.INVISIBLE);
                            textViewTrescPytania.setText("Zdobyto "+liczbaPunktow+" punktów ");
                        }
                    }
                }
        );

    }

    private boolean sprawdzOdpowiedz(int ktorePytanie , int odp){
        if(odp == listaPytan.get(ktorePytanie).getPoprawna()){
            return true;
        }
        return false;
    }
    private void wyswietlPytanie(int nr){
        textViewTrescPytania.setText(listaPytan.get(nr).getTresc());
        radioGroupPytania.clearCheck();
        radioButtonA.setText(listaPytan.get(nr).getOdp_a());
        radioButtonB.setText(listaPytan.get(nr).getOdp_b());
        radioButtonC.setText(listaPytan.get(nr).getOdp_c());
    }
}