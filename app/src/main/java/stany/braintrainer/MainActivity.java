package stany.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity
{
    Button startButton;
    TextView resultTextView;
    TextView pointsTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView sumTextView;
    TextView timerTextView;
    RelativeLayout gameRelativeLayout;
    ArrayList<Integer> answers = new ArrayList<>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;
    Button playAgainButton;

    public void playAgain(View view)
    {
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        button0.setClickable(true);
        button1.setClickable(true);
        button2.setClickable(true);
        button3.setClickable(true);

        generateQuestion();

        new CountDownTimer(30010,  1000)
        {

            @Override
            public void onTick(long millisUntilFinished)
            {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000) + "s");
            }

            @Override
            public void onFinish()
            {
                playAgainButton.setVisibility(View.VISIBLE);
                resultTextView.setText("Your score: " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
                button0.setClickable(false);
                button1.setClickable(false);
                button2.setClickable(false);
                button3.setClickable(false);
            }
        }.start();
    }

    public void generateQuestion()
    {
        Random rand = new Random();
        int a = rand.nextInt(26);
        int b = rand.nextInt(26);

        answers.clear();

        int incorrectAnswer;

        locationOfCorrectAnswer = rand.nextInt(4);
        for(int i = 0; i < 4; i++)
        {
            if( i == locationOfCorrectAnswer)
            {
                answers.add(a + b);
            }
            else
            {
                incorrectAnswer = rand.nextInt(41);
                while( incorrectAnswer == a + b)
                {
                    incorrectAnswer = rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));
    }

    public void chooseAnswer(View view)
    {
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer)))
        {
           score++;
           resultTextView.setText("Correct!");

        }
        else
        {
            resultTextView.setText("Wrong");
        }
        numberOfQuestions++;
        pointsTextView.setText(score + "/" + numberOfQuestions);
        generateQuestion();
    }

    public void start(View view)
    {
        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);
        sumTextView = (TextView) findViewById(R.id.sumTextView);

        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);

        this.resultTextView = (TextView) findViewById(R.id.resultTextView);
        this.pointsTextView = (TextView) findViewById(R.id.scoreTextView);
        this.timerTextView = (TextView) findViewById(R.id.timerTextView);
        this.playAgainButton = (Button) findViewById(R.id.playAgainButton);
        this.gameRelativeLayout = (RelativeLayout) findViewById(R.id.gameRelativeLayout);



    }
}
