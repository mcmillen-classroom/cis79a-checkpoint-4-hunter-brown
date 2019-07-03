package com.agrais.quizapp;

public class TrueFalseQuestion extends Question {
    private boolean mAnswer;

    public TrueFalseQuestion(int TextResId, int HintResId, boolean ans) {
        super(TextResId, HintResId);
        mAnswer=ans;
    }
    @Override
    public boolean checkAnswer(boolean boolResponse){
        if(boolResponse==mAnswer){
            return true;
        }
        return false;
    }
    @Override
    public boolean isTrueFalseQuestion(){
        return true;
    }
}
