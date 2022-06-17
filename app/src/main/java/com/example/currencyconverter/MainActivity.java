package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    EditText value_ET;
    Button convert_btn;
    Spinner spinner;
    TextView converted_currency;

    //Ein Arrayadapter ist eine Art "Brücke" zwischen der UI / Benutzeroberfläche / Frontend und dem Backend
    ArrayAdapter adapter;

    //Erstellt die Variabeln list und getMoney. In der Variable list wird immer die Endwährung, die man im Spinner ausgewählt hat, gespeichert. in der Variable getMoney wird der Wert, den der Benutzer im Feld eingibt, gespeichert
    String list, getMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Verknüpft die Java-Variablen mit den XML-Elementen (Buttons, Textfelder, Spinner etc.)
        value_ET = findViewById(R.id.value_ET);
        convert_btn = findViewById(R.id.convert_btn);
        converted_currency = findViewById(R.id.converted_currency);
        spinner = findViewById(R.id.spinner);

        adapter = ArrayAdapter.createFromResource(this, R.array.currencies, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Konvertiert zum String
                list = parent.getItemAtPosition(position).toString();
                //Da die Währungen verschieden viel Wert sind bzw. alle einen anderen Kurs haben, wird ein Switch case erstellt. Als Variable wird list genommen.
                switch (list) {
                    //Der erste Case ist der Fall, wenn im Spinner Euro gewählt werden. Dann wird nämlich "EUR - Euro" in der Variable "list" gespeichert, somit tritt dieser Case ein.
                    case "EUR - Euro": {
                        convert_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //"value_ET" wird abgerufen, zu einem String konvertiert und in die Variable getMoney gespeichert.
                                getMoney = value_ET.getText().toString();
                                //IF-Else Abfrage: wenn getMoney leer ist (also null - nicht verwechseln mit 0), Kommt Eine Fehlermeldung, ansonsten wird berechnet, wie viel der eingegebene Wert in der ausgewählten Währung wert ist.
                                if (getMoney.isEmpty()) {
                                    Toast.makeText(getApplicationContext(), "Please enter an Amount to convert it", Toast.LENGTH_SHORT).show();
                                } else {
                                    //getMoney wird zu einem Double konvertiert.
                                    double convertToDouble_et = Double.parseDouble(getMoney);
                                    //Store ist die Double-Variable, in der der endgültige, konvertierte Wert gepeichert wird. Es wird ein neues Dezimal-Format gewählt. Wert wird mit dem entsprechenden Kurs multipliziert.
                                    double store = Double.parseDouble(new DecimalFormat("##.####").format(convertToDouble_et * 0.96));
                                    //Diese Zeile befüllt den TextView vom XML. Die Variable Store, die den konvertierten Wert beinhaltet und zusätzlich die entsprechende Währung.
                                    converted_currency.setText("" + store + " EUR");
                                }
                            }
                        });
                        break;
                    }
                    //Der zweite Case ist der Fall, wenn im Spinner Dollar gewählt werden. Dann wird nämlich "USD - US Dollar" in der Variable "list" gespeichert, somit tritt dieser Case ein.
                    case "USD - US Dollar": {
                        convert_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                getMoney = value_ET.getText().toString();
                                if (getMoney.isEmpty()) {
                                    Toast.makeText(getApplicationContext(), "Please enter an Amount to convert it", Toast.LENGTH_SHORT).show();
                                } else {
                                    double convertToDouble_et = Double.parseDouble(getMoney);
                                    double store = Double.parseDouble(new DecimalFormat("##.####").format(convertToDouble_et * 1.01));
                                    converted_currency.setText("" + store + " USD");
                                }
                            }
                        });
                        //Tritt dieser Fall ein, gibt es am Schluss einen Break - Der Switch-Case wird somit nicht mehr ausgeführt.
                        break;
                    }
                    //Der dritte Case ist der Fall, wenn im Spinner Britische Pfund gewählt werden. Dann wird nämlich "GBP - Pound" in der Variable "list" gespeichert, somit tritt dieser Case ein.
                    case "GBP - Pound": {
                        convert_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                getMoney = value_ET.getText().toString();
                                if (getMoney.isEmpty()) {
                                    Toast.makeText(getApplicationContext(), "Please enter an Amount to convert it", Toast.LENGTH_SHORT).show();
                                } else {
                                    double convertToDouble_et = Double.parseDouble(getMoney);
                                    double store = Double.parseDouble(new DecimalFormat("##.####").format(convertToDouble_et * 0.82));
                                    converted_currency.setText("" + store + " GBP");
                                }
                            }

                        });
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}