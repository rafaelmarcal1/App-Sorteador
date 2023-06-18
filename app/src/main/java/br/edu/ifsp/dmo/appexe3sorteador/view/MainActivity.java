package br.edu.ifsp.dmo.appexe3sorteador.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.ifsp.dmo.appexe3sorteador.R;
import br.edu.ifsp.dmo.appexe3sorteador.model.Sorteio;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Sorteio mSorteio;
    private Button drawButton;
    private Button limitedButton;
    private EditText limitEditText;
    private TextView outputTextView;
    private TextView limiteMessageTextView;
    private TextView msgLastNumberTextView;
    private TextView listLastNumberTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        setClickListener();
        mSorteio = new Sorteio();
        updateBorderMessage();
    }

    @Override
    public void onClick(View v) {
        if (v == drawButton) {
            executeDraw();
        }
        if (v == limitedButton) {
            defineNewLimit();
        }
    }

    /*
    Método responsável por definir um limite para o sorteio. Esse método recupera o valor
    informado pelo usuário na EditText e define como limite superior para a realização de
    sorteio. Caso o valor seja inválido ou menor que 2, o limite padrão é definido.
     */
    private void defineNewLimit() {
        int value;
        boolean error = false;
        try {
            value = Integer.valueOf(limitEditText.getText().toString());
        } catch (NumberFormatException exception) {
            error = true;
            value = -1;
        }

        if (error || value < 2) {
            Toast.makeText(this, R.string.not_apply_border, Toast.LENGTH_LONG).show();
            mSorteio = new Sorteio();
        } else {
            mSorteio = new Sorteio(value);
            Toast.makeText(this, R.string.appled_border, Toast.LENGTH_LONG).show();
        }
        updateBorderMessage();
    }

    /*
    Método responsável por realizar a vinculação dos elementos de leiaute (xml) com
    a programação.
     */
    private void findViews() {
        this.drawButton = findViewById(R.id.btn_draw);
        this.limitedButton = findViewById(R.id.btn_limited);
        this.outputTextView = findViewById(R.id.textview_output);
        this.limiteMessageTextView = findViewById(R.id.textview_limite_message);
        this.limitEditText = findViewById(R.id.edittext_limit);
        this.msgLastNumberTextView = findViewById(R.id.textview_message_last_number);
        this.listLastNumberTextView = findViewById(R.id.textview_list_last_number);
    }

    private void setClickListener() {
        drawButton.setOnClickListener(this);
        limitedButton.setOnClickListener(this);
    }

    /*
    Método executa o sorteio de um valor, em função dos limites definidos, e apresenta o
    resultado na TextView de saída.
     */
    private void executeDraw() {
        outputTextView.setText(String.format("%d", mSorteio.getNumber()));
        msgLastNumberTextView.setVisibility(View.VISIBLE);
        listLastNumberTextView.setText(mSorteio.getRegister());
    }

    /*
    Atualiza na tela as limites do sorteio que está sendo aplicado.
     */
    private void updateBorderMessage() {
        limiteMessageTextView
                .setText(
                        String.format(getString(R.string.limit_message),
                                mSorteio.getLowBorder(),
                                mSorteio.getHighBorder()));
    }
}