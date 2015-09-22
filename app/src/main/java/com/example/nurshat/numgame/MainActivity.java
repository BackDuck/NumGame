package com.example.nurshat.numgame;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends ActionBarActivity {
    TextView firstNum;
    TextView secondNum;
    TextView token;
    int answer = 0;
    int[] answers = null;
    int arrCount = 0;
    TextView answerBut1;
    TextView answerBut2;
    TextView answerBut3;
    TextView answerBut4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        answers = new int[4];

       // TextView t = (TextView)findViewById(R.id.first);

        answerBut1 = (TextView) findViewById(R.id.answer1);

        answerBut2 = (TextView) findViewById(R.id.answer2);
        answerBut3 = (TextView) findViewById(R.id.answer3);
        answerBut4 = (TextView) findViewById(R.id.answer4);
        firstNum = (TextView) findViewById(R.id.first);
        secondNum = (TextView) findViewById(R.id.second);
        token = (TextView) findViewById(R.id.token);
       setNewTask();
      // ff();
    }

    Random random = new Random();
    public void setNewTask() {
        arrCount = 0;
        String symbol[] = {"+", "-", "+", "*"};





        int sNum = 0;
        int fNum = 0;

        int symNum = random.nextInt(4);
        System.out.println(symNum);
        System.out.println(symbol[symNum]);
        switch (symbol[symNum]) {
            case "/":
                sNum = random.nextInt(11);
                fNum = sNum * random.nextInt(11);
                answer = fNum / sNum;
                break;
            case "*":
                sNum = random.nextInt(11);
                fNum = random.nextInt(11);
                answer = fNum*sNum;
                break;
            case "+":
                sNum = random.nextInt(11);
                fNum = random.nextInt(11);
                answer = fNum+sNum;
                break;
            case "-":
                while(fNum<1) {
                    fNum = random.nextInt(11);
                }
                while(sNum>fNum) {
                    sNum = random.nextInt(11);
                }
                answer = fNum-sNum;
                break;
        }

        firstNum.setText(String.valueOf(fNum));
        secondNum.setText(String.valueOf(sNum));
        token.setText(symbol[symNum]);

        int answerButNum = random.nextInt(4)+1;
        for(int i=1; i<5; i++){
            if(i == answerButNum){
                switch (i){
                    case 1: answerBut1.setText(String.valueOf(answer)); break;
                    case 2: answerBut2.setText(String.valueOf(answer)); break;
                    case 3: answerBut3.setText(String.valueOf(answer)); break;
                    case 4: answerBut4.setText(String.valueOf(answer)); break;
                }
            }else{
            switch (i){
                case 1: answerBut1.setText(getWrongAnswer()); break;
                case 2: answerBut2.setText(getWrongAnswer()); break;
                case 3: answerBut3.setText(getWrongAnswer()); break;
                case 4: answerBut4.setText(getWrongAnswer()); break;
            }
        }}
        System.out.println(fNum + symbol[symNum]+sNum+"="+answer);
        answers[0] = answer;


    }

    public String getWrongAnswer(){
    boolean test = false;
        int noRepeteWrongAnswer = 0;

        while(!test){

           noRepeteWrongAnswer = random.nextInt(answer<5?answer+10:answer*2+1);
            System.out.println(noRepeteWrongAnswer);
            boolean a = true;
            for(int i=0; i<4; i++){

                if(answers[i] == noRepeteWrongAnswer){

                    a = false;
                    break;
                }
            }
            if(a){
                answers[arrCount++] = noRepeteWrongAnswer;
                System.out.println("->>>>"+arrCount);
                test = true;


            }

        }
        return String.valueOf(noRepeteWrongAnswer);

    }
    public void clickListener(View v) {
TextView tv = (TextView)v;
       int answerInButton = Integer.parseInt(String.valueOf(tv.getText()));
        if(answer == answerInButton){
            setNewTask();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
