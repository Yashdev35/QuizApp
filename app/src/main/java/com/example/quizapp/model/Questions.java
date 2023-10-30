package com.example.quizapp.model;

public class Questions {
    /* so the idea is that each question will have an id called answerResId and will have an answer id answerTrue
    ansId will be an int and the answerTrue will be boolean and will contain the answer and we will use this to cross check the ans input
     by user
     */
    private int answerResId;
    private boolean answerTrue;

    public Questions(int answerResId, boolean answerTrue) {
        this.answerResId = answerResId;
        this.answerTrue = answerTrue;
    }

    public int getAnswerResId() {
        return answerResId;
    }

    public void setAnswerResId(int answerResId) {
        this.answerResId = answerResId;
    }

    public boolean isAnswerTrue() {
        return answerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        this.answerTrue = answerTrue;
    }
}
