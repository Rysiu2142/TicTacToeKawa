package com.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[] buttons = new Button[9];
    private boolean player1 = true; // gracz O
    private int Conter = 0;
    private TextView text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = findViewById(R.id.text1);

        for (int i =0; i < 9; i++)
        {
            String IDB = "button_"+i;
            int res = getResources().getIdentifier(IDB, "id" , getPackageName());
            buttons[i] = findViewById(res);
            buttons[i] .setOnClickListener(this);
        }

        Button buttonreset = findViewById(R.id.button_r);
        buttonreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 9; i++) {
                    buttons[i].setText("");
                    text1.setText("");
                    player1 = true;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) { // Button ma text
            return;
        }

        if (player1){
            ((Button) v).setText("O");
        }
        else {
            ((Button) v).setText("X");
        }
        Conter++;
        if(WinCheck()) {
            if(player1)
            {
                player1win();
            } else {
                player2win();
            }
        } else if(Conter == 8) {
            draw();
        } else {
            player1 = !player1;
        }
    }

    private boolean WinCheck() {
        String[] fields = new String[9];

        for (int i = 0; i < 9; i++) {
            fields[i] = buttons[i].getText().toString();
        }

        /*
        0 1 2
        3 4 5
        6 7 8
        */
        // colunmy
        for (int i = 0; i < 3; i++) {
            if(fields[0+i].equals(fields[3+i]) && fields[3+i].equals(fields[6+i]) && !fields[0+i].equals("")) {
                return true;
            }
        }
        // wiersze
        for (int i = 0; i < 9; i+=3) {
            if(fields[0+i].equals(fields[1+i]) && fields[1+i].equals(fields[2+i]) && !fields[2+i].equals("")) {
                return true;
            }
        }
        // ukosy
        if(fields[0].equals(fields[4]) && fields[4].equals(fields[8]) && !fields[4].equals("")) return true;
        if(fields[2].equals(fields[4]) && fields[4].equals(fields[6]) && !fields[4].equals("")) return true;

        return false;
    }
    private void player1win() {
        text1.setText("Wygrywa Gracz O");
        Conter = 0;
    }
    private void player2win() {
        text1.setText("Wygrywa Gracz X");
        Conter = 0;
    }
    private void draw() {
        text1.setText("Remis");
        Conter = 0;
    }
}