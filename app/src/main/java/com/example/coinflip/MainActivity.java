package com.example.coinflip;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView coin;
    private Button btnHeads;
    private Button btnTails;
    private TextView counterThrow;
    private TextView counterWin;
    private TextView counterLose;
    private int guess;
    private Random rndNum = new Random();
    private int playedGames = 0;
    private int wonGames = 0;
    private int lostGames = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        btnHeads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guess = 0;
                flipCoin();
            }
        });
        btnTails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guess = 1;
                flipCoin();
            }
        });
    }

    public void flipCoin(){
        playedGames++;
        int coinThrow = rndNum.nextInt(2);
        switch (coinThrow){
            case 0:
                coin.setImageResource(R.drawable.heads);
                Toast.makeText(this, "Fej", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                coin.setImageResource(R.drawable.tails);
                Toast.makeText(this, "Írás", Toast.LENGTH_SHORT).show();
                break;
        }
        if (guess == coinThrow){
            wonGames++;
        }else{
            lostGames++;
        }
        counterThrow.setText("Dobások: "+ playedGames);
        counterWin.setText("Győzelem: " + wonGames);
        counterLose.setText("Vereség: " + lostGames);
        if (playedGames >= 5 || wonGames > 2 || lostGames > 2){
            gameEnd();
        }
    }
    public void gameEnd(){
        String result;
        if (wonGames > lostGames){
            result = "Győzelem!";
        }else{
            result = "Vereség!";
        }
        AlertDialog.Builder end = new AlertDialog.Builder(this);
            end.setTitle(result);
            end.setMessage("Szeretne új játékot játszani?");
            end.setPositiveButton("Igen", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    coin.setImageResource(R.drawable.heads);
                    playedGames = 0;
                    wonGames = 0;
                    lostGames = 0;
                    counterThrow.setText("Dobások: "+ playedGames);
                    counterWin.setText("Győzelem: " + wonGames);
                    counterLose.setText("Vereség: " + lostGames);
                }
            });
            end.setNegativeButton("Nem", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            end.setCancelable(false);
            end.create().show();
    }

    public void init(){
        coin = findViewById(R.id.coin);
        btnHeads = findViewById(R.id.btnHeads);
        btnTails = findViewById(R.id.btnTails);
        counterThrow = findViewById(R.id.counterThrow);
        counterWin = findViewById(R.id.counterWin);
        counterLose = findViewById(R.id.counterLose);
    }
}