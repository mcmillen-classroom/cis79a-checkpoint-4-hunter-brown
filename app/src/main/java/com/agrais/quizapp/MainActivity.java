package com.agrais.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextView;
    private TextView mScoreView;

    private EditText mEditText;

    private LinearLayout mTrueFalseContainer;
    private LinearLayout mFillTheBlankContainer;
    private LinearLayout mMultipleChoiceContainer;

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mHintButton;
    private ImageButton mNextButton;
    private ImageButton mBackButton;
    private Button mCheckButton;
    private Button mAButton;
    private Button mBButton;
    private Button mCButton;
    private Button mDButton;

    private Question[] mQuestions;
    private int mIndex;
    private int mScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Wiring up Buttons
        mTrueButton=(Button)findViewById(R.id.true_button);
        mFalseButton=(Button)findViewById(R.id.false_button);
        mHintButton= (Button)findViewById(R.id.hint_button);
        mNextButton = (ImageButton)findViewById(R.id.next_button);
        mBackButton = (ImageButton)findViewById(R.id.back_button);
        mCheckButton=(Button)findViewById(R.id.check_button);
        mAButton=(Button)findViewById(R.id.A_button);
        mBButton=(Button)findViewById(R.id.B_button);
        mCButton=(Button)findViewById(R.id.C_button);
        mDButton=(Button)findViewById(R.id.D_button);

        //Wiring Containers
        mTrueFalseContainer=(LinearLayout)findViewById(R.id.true_false_container);
        mFillTheBlankContainer=(LinearLayout)findViewById(R.id.fill_the_blank_container);
        mMultipleChoiceContainer=(LinearLayout)findViewById(R.id.multiple_choice_container);

        mTrueButton.setOnClickListener(this);
        mFalseButton.setOnClickListener(this);
        mHintButton.setOnClickListener(this);
        mNextButton.setOnClickListener(this);
        mBackButton.setOnClickListener(this);
        mCheckButton.setOnClickListener(this);
        mAButton.setOnClickListener(this);
        mBButton.setOnClickListener(this);
        mCButton.setOnClickListener(this);
        mDButton.setOnClickListener(this);

        mEditText=(EditText) findViewById(R.id.edit_text);


        mTextView=(TextView)findViewById(R.id.text_view);

        mScoreView=(TextView)findViewById(R.id.score_value);



        //Initialize Array of Questions
        mQuestions= new Question[7];
        mIndex = 0;


        //Initialize Score
        mScore= 0;
        String scr= String.valueOf(mScore);
        mScoreView.setText(scr);

        //Initialize each slot in the Questions array
        mQuestions[0] = new TrueFalseQuestion(R.string.question_1,R.string.hint_1,true);
        mQuestions[1] = new TrueFalseQuestion(R.string.question_2,R.string.hint_2,true);

        String[] q4Answers=getResources().getStringArray(R.array.question_3_answers);
        mQuestions[2] = new FillTheBlankQuestion(R.string.question_3,R.string.hint_3,q4Answers);
        mQuestions[3] = new MultipleChoiceQuestion(R.string.question_4,R.string.hint_4,R.array.question_4_options,3);
        mQuestions[4] = new TrueFalseQuestion(R.string.question_5,R.string.hint_5,false);
        mQuestions[5] = new TrueFalseQuestion(R.string.question_6,R.string.hint_6,true);
        mQuestions[6] = new TrueFalseQuestion(R.string.question_7,R.string.hint_7,false);


        //Set up the first Question
        setupQuestion();
    }

    @Override
    public void onClick(View view) {
        if (view.getId()== R.id.true_button) {
            ButtonColorChange(R.id.true_button,checkAnswer(true));

        }
        else if (view.getId()== R.id.false_button) {
            ButtonColorChange(R.id.false_button,checkAnswer(false));
        }
        else if (view.getId()== R.id.check_button){
            ButtonColorChange(R.id.check_button, checkAnswer(mEditText.getText().toString()));
        }
        else if (view.getId() == R.id.A_button){
            ButtonColorChange(R.id.A_button,checkAnswer(0));
        }
        else if (view.getId() == R.id.B_button){
            ButtonColorChange(R.id.B_button,checkAnswer(1));
        }
        else if (view.getId() == R.id.C_button){
            ButtonColorChange(R.id.C_button,checkAnswer(2));
        }
        else if (view.getId() == R.id.D_button){
            ButtonColorChange(R.id.D_button, checkAnswer(3));
        }
        else if (view.getId()== R.id.hint_button) {
            Toast myToast = Toast.makeText(this,  mQuestions[mIndex].getHintResId(), Toast.LENGTH_LONG);
            myToast.setGravity(Gravity.CENTER, 0, 0);
            myToast.show();
        }
        else if (view.getId()== R.id.next_button) {
            //Reset Button Colors
            mTrueButton.setBackgroundResource(android.R.drawable.btn_default);
            mFalseButton.setBackgroundResource(android.R.drawable.btn_default);
            mCheckButton.setBackgroundResource(android.R.drawable.btn_default);
            mAButton.setBackgroundResource(android.R.drawable.btn_default);
            mBButton.setBackgroundResource(android.R.drawable.btn_default);
            mCButton.setBackgroundResource(android.R.drawable.btn_default);
            mDButton.setBackgroundResource(android.R.drawable.btn_default);
                if( mIndex+1 < mQuestions.length) {
                    //Increment Question Index by 1
                    mIndex++;

                    //change to next question
                    setupQuestion();
                }
                else {
                    mIndex = 0;
                    setupQuestion();
                }
        }
        else if (view.getId()== R.id.back_button) {
            //Reset Button Color
            mTrueButton.setBackgroundResource(android.R.drawable.btn_default);
            mFalseButton.setBackgroundResource(android.R.drawable.btn_default);
            mCheckButton.setBackgroundResource(android.R.drawable.btn_default);
            mAButton.setBackgroundResource(android.R.drawable.btn_default);
            mBButton.setBackgroundResource(android.R.drawable.btn_default);
            mCButton.setBackgroundResource(android.R.drawable.btn_default);
            mDButton.setBackgroundResource(android.R.drawable.btn_default);
            if( mIndex > 0) {
                //Increment Question Index by 1
                mIndex--;

                //change to next question
                setupQuestion();
            }
            else {
                mIndex = mQuestions.length-1;
                setupQuestion();
            }
        }


    }

    public void setupQuestion(){
        mTextView.setText(mQuestions[mIndex].getTextResId());
        if(mQuestions[mIndex].isTrueFalseQuestion()){
            mTrueFalseContainer.setVisibility(View.VISIBLE);
            mFillTheBlankContainer.setVisibility((View.GONE));
            mMultipleChoiceContainer.setVisibility(View.GONE);
        }
        else if (mQuestions[mIndex].isFillTheBlankQuestion()){
            mTrueFalseContainer.setVisibility(View.GONE);
            mFillTheBlankContainer.setVisibility((View.VISIBLE));
            mMultipleChoiceContainer.setVisibility(View.GONE);
        }
        else if (mQuestions[mIndex].isMultipleChoiceQuestion()){
            mTrueFalseContainer.setVisibility(View.GONE);
            mFillTheBlankContainer.setVisibility((View.GONE));
            mMultipleChoiceContainer.setVisibility(View.VISIBLE);
            int OptionsResId = ((MultipleChoiceQuestion)mQuestions[mIndex]).getOptionsResId();
            String[] options = getResources().getStringArray(OptionsResId);
            mAButton.setText(options[0]);
            mBButton.setText(options[1]);
            mCButton.setText(options[2]);
            mDButton.setText(options[3]);
        }

    }
    public boolean checkAnswer(boolean userInput){
        if( mQuestions[mIndex].checkAnswer(userInput)){
            Toast myToast = Toast.makeText(this, "You are correct,", Toast.LENGTH_SHORT);
            myToast.setGravity(Gravity.TOP, 0, 0);
            myToast.show();
            mScore++;
            String scr= String.valueOf(mScore);
            mScoreView.setText(scr);
            return true;
        }
        else{
            Toast myToast = Toast.makeText(this, "You are incorrect,", Toast.LENGTH_SHORT);
            myToast.setGravity(Gravity.TOP, 0, 0);
            myToast.show();
            mScore--;
            String scr= String.valueOf(mScore);
            mScoreView.setText(scr);
            return false;
        }
    }
    public boolean checkAnswer(String userInput){
        if( mQuestions[mIndex].checkAnswer(userInput)){
            Toast myToast = Toast.makeText(this, "You are correct,", Toast.LENGTH_SHORT);
            myToast.setGravity(Gravity.TOP, 0, 0);
            myToast.show();
            mScore++;
            String scr= String.valueOf(mScore);
            mScoreView.setText(scr);
            return true;
        }
        else {
            Toast myToast = Toast.makeText(this, "You are incorrect,", Toast.LENGTH_SHORT);
            myToast.setGravity(Gravity.TOP, 0, 0);
            myToast.show();
            mScore--;
            String scr = String.valueOf(mScore);
            mScoreView.setText(scr);
            return false;
        }
    }
    public boolean checkAnswer(int userInput){
        if( mQuestions[mIndex].checkAnswer(userInput)){
            Toast myToast = Toast.makeText(this, "You are correct,", Toast.LENGTH_SHORT);
            myToast.setGravity(Gravity.TOP, 0, 0);
            myToast.show();
            mScore++;
            String scr= String.valueOf(mScore);
            mScoreView.setText(scr);
            return true;
        }
        else {
            Toast myToast = Toast.makeText(this, "You are incorrect,", Toast.LENGTH_SHORT);
            myToast.setGravity(Gravity.TOP, 0, 0);
            myToast.show();
            mScore--;
            String scr = String.valueOf(mScore);
            mScoreView.setText(scr);
            return false;
        }
    }
    public void ButtonColorChange(int ButtonResId, boolean Answer){
        //True Button
        if (ButtonResId == R.id.true_button && true == Answer){
            mTrueButton.setBackgroundColor(getResources().getColor( R.color.colorTrue));
        }
        else if(ButtonResId == R.id.true_button && false == Answer) {
            mTrueButton.setBackgroundColor(getResources().getColor( R.color.colorFalse));
        }

        //False Button
        if (ButtonResId == R.id.false_button && true == Answer){
            mFalseButton.setBackgroundColor(getResources().getColor( R.color.colorTrue));
        }
        else if(ButtonResId == R.id.false_button && false == Answer) {
            mFalseButton.setBackgroundColor(getResources().getColor( R.color.colorFalse));
        }

        //Fill in The Blank Button
        if (ButtonResId == R.id.check_button && true == Answer){
            mEditText.setBackgroundColor(getResources().getColor( R.color.colorTrue));
        }
        else if(ButtonResId == R.id.check_button && false == Answer){
            mEditText.setBackgroundColor(getResources().getColor( R.color.colorFalse));
        }

        //MC A Button
        if (ButtonResId == R.id.A_button && true == Answer){
            mAButton.setBackgroundColor(getResources().getColor( R.color.colorTrue));
        }
        else if(ButtonResId == R.id.A_button && false == Answer) {
            mAButton.setBackgroundColor(getResources().getColor( R.color.colorFalse));
        }
        //MC B Button
        if (ButtonResId == R.id.B_button && true == Answer){
            mBButton.setBackgroundColor(getResources().getColor( R.color.colorTrue));
        }
        else if(ButtonResId == R.id.B_button && false == Answer) {
            mBButton.setBackgroundColor(getResources().getColor( R.color.colorFalse));
        }
        //MC C Button
        if (ButtonResId == R.id.C_button && true == Answer){
            mCButton.setBackgroundColor(getResources().getColor( R.color.colorTrue));
        }
        else if(ButtonResId == R.id.C_button && false == Answer) {
            mCButton.setBackgroundColor(getResources().getColor( R.color.colorFalse));
        }
        if (ButtonResId == R.id.D_button && true == Answer){
            mDButton.setBackgroundColor(getResources().getColor( R.color.colorTrue));
        }
        else if(ButtonResId == R.id.D_button && false == Answer) {
            mDButton.setBackgroundColor(getResources().getColor( R.color.colorFalse));
        }

    }

}
