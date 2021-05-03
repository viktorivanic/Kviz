package com.example.trivia;

import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import com.example.trivia.databinding.ActivityMainBinding;
import com.example.trivia.model.Question;



public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding ;
    private int currentQuestion = 0;



    private Question[] questionBank = new Question[]{
            new Question(R.string.zupanije, true),
            new Question(R.string.sabor, true),
            new Question(R.string.ustav, true),
            new Question(R.string.predsjednik, false),


    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.textView.setText(questionBank[currentQuestion].getAnswerResId());
        binding.trueButton.setOnClickListener(view -> checkAnswer(true));
        binding.falseButton.setOnClickListener(view -> checkAnswer(false));
        binding.nextButton.setOnClickListener(view -> {
            currentQuestion = (currentQuestion + 1) % questionBank.length; //incrementing
            updateQuestion();

        });
        binding.prevButton.setOnClickListener(view -> {
            if (currentQuestion > 0) {
                currentQuestion = (currentQuestion - 1) % questionBank.length; //decrementing
                updateQuestion();
            }

        });

    }
    private void checkAnswer(boolean userChoseCorrect) {
        boolean answerIsCorrect = questionBank[currentQuestion].isAnswerTrue();
        int messageId;

        if (answerIsCorrect == userChoseCorrect) {
            messageId = R.string.tocno;
        }else {
            messageId = R.string.netocno;
        }

        Toast.makeText(MainActivity.this, messageId, Toast.LENGTH_SHORT).show();


    }

    private void updateQuestion() {
        binding.textView.setText(questionBank[currentQuestion].getAnswerResId());
    }
}