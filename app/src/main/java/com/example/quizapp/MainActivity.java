package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.example.quizapp.databinding.ActivityMainBinding;
import com.example.quizapp.model.Questions;

public class MainActivity extends AppCompatActivity {
    //integer to count the number of question
    private int countingInteger = 0;
    //creating the binding bridge for widgets java(generated).androidx.databinding.com.example.quizapp.databing has the bridge
    private ActivityMainBinding binding;// this is the bridge
    /* the idea is that we will use an array to store the questions we make using the questions class (or model class ) we will be
    passing an id int and a boolean because a string in resource file is stored as an int and has an id which will be used as answerResId and
    while creating object we will associate every object with a boolean value and will cross check the user input.
     */
private Questions[] QuestionBank = new Questions[]{
            /*
            here we have to create questions in the string resource file and also wrong and correct ans string
             */
            new Questions(R.string.quesion_independence,true),
            new Questions(R.string.Bhagat_singh,false)
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this ,R.layout.activity_main);
        binding.questionTextView.setText(QuestionBank[countingInteger].getAnswerResId());
        binding.nextButton.setOnClickListener(v -> {
            binding.scrollView.setVisibility(View.GONE);
            countingInteger++;
            updateQuestion();
        });
        binding.previousButton.setOnClickListener(v -> {
            binding.scrollView.setVisibility(View.GONE);
            countingInteger--;
            updateQuestion();
        });
        binding.trueButton.setOnClickListener(v -> {
        checkUserAns(true);
        });
        binding.falseButton.setOnClickListener(v -> {
         checkUserAns(false);
        });
    }
    //this is to update the question after pressing next or previous
    private void updateQuestion() {
        if(countingInteger>=0 &&countingInteger<QuestionBank.length){
            binding.questionTextView.setText(QuestionBank[countingInteger].getAnswerResId());
        }else if(countingInteger>=QuestionBank.length){
            //adding dead end and using toast to do it and snack bar is the one which allows to add actions but not needed now
            countingInteger--;
            Toast.makeText(MainActivity.this,R.string.deadend_msg_next,Toast.LENGTH_SHORT).show();
        }else {
            countingInteger++;
            Toast.makeText(MainActivity.this,R.string.deadend_msg_previous,Toast.LENGTH_SHORT).show();
        }
    }
    //this is method to check if the given ans wer is true
    private void checkUserAns(boolean userGivenAnswer){
        boolean answer = QuestionBank[countingInteger].isAnswerTrue();
        if(userGivenAnswer == answer){
            Toast.makeText(MainActivity.this,R.string.correct_ans,Toast.LENGTH_SHORT).show();
            binding.scrollView.setVisibility(View.VISIBLE);
            switch(countingInteger){
                case 0 :
                    binding.textView2.setText(R.string.moreinfo_independence);
                    break;
                case 1 :
                    binding.textView2.setText(R.string.Bhagat_singh_moreinfo);
                    break;
                default:
                    binding.textView2.setText("No extra info avalible");
            };
        }else {
            Toast.makeText(MainActivity.this,R.string.wrong_ans,Toast.LENGTH_SHORT).show();
            binding.scrollView.setVisibility(View.GONE);

        }
    }
}