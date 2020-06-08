package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout gameLayout;
    Button startbutton;
    Boolean gameIsActive = true;
    TextView sumtextview;
    TextView resultextview;
    TextView pointstextview;
    TextView timertextview;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    ArrayList<Integer> answer = new ArrayList<Integer>();
    int locationofcorrectanswer;
    int score = 0;
    int numberofquestions = 0;

    public void playAgain(View view){
        gameIsActive = true;
        score = 0;
        numberofquestions= 0;
        timertextview.setText("30s");
        pointstextview.setText("0/0");
        resultextview.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);

        generatequestion();
        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timertextview.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {

                playAgainButton.setVisibility(View.VISIBLE);
                timertextview.setText("0s");
                resultextview.setText("Your score: " + Integer.toString(score) + "/" + Integer.toString(numberofquestions));
                gameIsActive = false;
            }
        }.start();
    }

    public void generatequestion(){
        gameIsActive =true;
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumtextview.setText(Integer.toString(a) + "+" + Integer.toString(b));
        locationofcorrectanswer =rand.nextInt(4);
        answer.clear();
        int incorrectanswer;
        for (int i =0; i<4; i++){
            if (i== locationofcorrectanswer){
                answer.add(a + b);
            }
            else{
                incorrectanswer = rand.nextInt(41);
                while (incorrectanswer == a+b){
                    incorrectanswer = rand.nextInt(41);
                }
                answer.add(incorrectanswer);
            }
        }

        button0.setText(Integer.toString(answer.get(0)));
        button1.setText(Integer.toString(answer.get(1)));
        button2.setText(Integer.toString(answer.get(2)));
        button3.setText(Integer.toString(answer.get(3)));


    }


    public void chooseAnswer(View view){
        gameIsActive =true;
        if (view.getTag().toString().equals(Integer.toString(locationofcorrectanswer))){
            score++;
            resultextview.setText("Correct!");
        }
        else {
            resultextview.setText("Wrong!");
        }
        numberofquestions++;
        pointstextview.setText(Integer.toString(score) + "/" + Integer.toString(numberofquestions));
        generatequestion();
    }
    public void start(View view)
    {
        gameIsActive = true;
        startbutton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startbutton = findViewById(R.id.button);
        sumtextview =  findViewById(R.id.sumtextView);
        resultextview =findViewById(R.id.resulttextView);
        button0 =findViewById(R.id.button0);
        button1 =findViewById(R.id.button1);
        button2 =findViewById(R.id.button2);
        button3 =findViewById(R.id.button3);
        pointstextview =findViewById(R.id.scoretextView);
        timertextview =findViewById(R.id.timertextView);
        gameLayout = findViewById(R.id.gamelayout);
        playAgainButton = findViewById(R.id.playAgainButton);
        startbutton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);

















    }
}
