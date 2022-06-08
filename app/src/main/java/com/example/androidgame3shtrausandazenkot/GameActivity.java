package com.example.androidgame3shtrausandazenkot;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class GameActivity extends Activity implements View.OnClickListener{

    int correctAnswer;
    Button buttonObjectChoice1;
    Button buttonObjectChoice2;
    Button buttonObjectChoice3;
    TextView textObjectPartA;
    TextView textObjectPartB;
    TextView textObjectScore;
    Toast message=null;
    public static int currentScore = 0;
    public static int maxCurrentScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game);

        textObjectPartA = findViewById(R.id.textPartA);
        textObjectPartB = findViewById(R.id.textPartB);
        textObjectScore = findViewById(R.id.textScore);
        buttonObjectChoice1 = findViewById(R.id.buttonChoice1);
        buttonObjectChoice2 = findViewById(R.id.buttonChoice2);
        buttonObjectChoice3 = findViewById(R.id.buttonChoice3);

        buttonObjectChoice1.setOnClickListener(this);
        buttonObjectChoice2.setOnClickListener(this);
        buttonObjectChoice3.setOnClickListener(this);

        setQuestion();

    }//onCreate ends here

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        int answerGiven=0;
        switch (view.getId()) {

            case R.id.buttonChoice1:
                //initialize a new int with the value contained in buttonObjectChoice1
                //Remember we put it there ourselves previously
                answerGiven = Integer.parseInt("" + buttonObjectChoice1.getText());
                break;

            case R.id.buttonChoice2:
                answerGiven = Integer.parseInt("" + buttonObjectChoice2.getText());
                break;

            case R.id.buttonChoice3:
                answerGiven = Integer.parseInt("" + buttonObjectChoice3.getText());
                break;

        }

        updateScore(answerGiven);
        setQuestion();

    }

    @SuppressLint("SetTextI18n")
    void setQuestion(){

        Random randInt = new Random();

        int partA = randInt.nextInt(10);
        partA++;

        int partB = randInt.nextInt(10);
        partB++;

        correctAnswer = partA * partB;
        int wrongAnswer1 = correctAnswer-10;
        int wrongAnswer2 = correctAnswer+10;

        if(wrongAnswer1 <= 0){
             wrongAnswer1 = correctAnswer+ 20;
        }
        textObjectPartA.setText(""+partA);
        textObjectPartB.setText(""+partB);

        //set the multi choice buttons
        //A number between 0 and 2
        int buttonLayout = randInt.nextInt(3);
        switch (buttonLayout){
            case 0:
                buttonObjectChoice1.setText(""+correctAnswer);
                buttonObjectChoice2.setText(""+wrongAnswer1);
                buttonObjectChoice3.setText(""+wrongAnswer2);
                break;

            case 1:
                buttonObjectChoice2.setText(""+correctAnswer);
                buttonObjectChoice3.setText(""+wrongAnswer1);
                buttonObjectChoice1.setText(""+wrongAnswer2);
                break;

            case 2:
                buttonObjectChoice3.setText(""+correctAnswer);
                buttonObjectChoice1.setText(""+wrongAnswer1);
                buttonObjectChoice2.setText(""+wrongAnswer2);
                break;
        }
    }


    @SuppressLint("SetTextI18n")
    void updateScore(int answerGiven){

        if(isCorrect(answerGiven)){
            currentScore++;
            if(currentScore>maxCurrentScore)
            maxCurrentScore=currentScore;
        }else{
            currentScore = 0;
        }

        textObjectScore.setText(" Gad dam bro you Succeeded  : " +"\n"+ currentScore + " You succeeded :)  ");
    }

    boolean isCorrect(int answerGiven){
        boolean correctTrueOrFalse;
        if(message!=null) message.cancel();

        if(answerGiven == correctAnswer){
            message=
            Toast.makeText(getApplicationContext(), "correct!", Toast.LENGTH_SHORT);
            message.show();
            correctTrueOrFalse=true;
        }else{//Uh-oh!
            message =Toast.makeText(getApplicationContext(), "incorrect!", Toast.LENGTH_SHORT);
            message.show();
            correctTrueOrFalse=false;
        }

        return correctTrueOrFalse;
    }

    @Override
    public void onBackPressed() {
        if(message!=null) message.cancel();
        super.onBackPressed();
    }
}
