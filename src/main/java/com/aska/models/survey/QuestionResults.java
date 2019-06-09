package com.aska.models.survey;

public class QuestionResults {

    private AnswerData values[];
    private int votes;

    public QuestionResults() {

    }


    public AnswerData[] getValues() {
        return values;
    }

    public void setValues(AnswerData[] values) {
        this.values = values;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }


}
