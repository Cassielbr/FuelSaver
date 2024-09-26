package br.ulbra.fuelsaver;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtNomeVeiculo, edtPlacaVeiculo, edtDistancia, edtConsumoMedio, edtPreco;
    TextView txtResultado;
    Button btnCalcular;
    LinearLayout layoutImagens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNomeVeiculo = findViewById(R.id.edt1);
        edtPlacaVeiculo = findViewById(R.id.edt2);
        edtDistancia = findViewById(R.id.edt3);
        edtConsumoMedio = findViewById(R.id.edt4);
        edtPreco = findViewById(R.id.edt5);
        txtResultado = findViewById(R.id.txtR1);
        btnCalcular = findViewById(R.id.btnCalcular);
        layoutImagens = findViewById(R.id.layoutImagens);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularConsumo();
            }
        });
    }

    private void calcularConsumo() {
        try {
            String distanciaStr = edtDistancia.getText().toString();
            String consumoMedioStr = edtConsumoMedio.getText().toString();
            String precoStr = edtPreco.getText().toString();

            if (distanciaStr.isEmpty() || consumoMedioStr.isEmpty() || precoStr.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }

            double distancia = Double.parseDouble(distanciaStr);
            double consumoMedio = Double.parseDouble(consumoMedioStr);
            double preco = Double.parseDouble(precoStr);

            double combustivelNecessario = distancia / consumoMedio;
            double custoViagem = combustivelNecessario * preco;

            String resultado = String.format(
                    "Veículo: %s\n" +
                            "Placa: %s\n" +
                            "Litros Necessários: %.2f L\n" +
                            "Custo Total da Viagem: R$ %.2f",
                    edtNomeVeiculo.getText().toString(),
                    edtPlacaVeiculo.getText().toString(),
                    combustivelNecessario,
                    custoViagem
            );

            txtResultado.setText(resultado);
            layoutImagens.setVisibility(View.VISIBLE);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Erro ao converter os valores. Verifique os campos!", Toast.LENGTH_SHORT).show();
        }
    }
}
