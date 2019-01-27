package com.company;

import com.sun.org.apache.bcel.internal.generic.DDIV;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private String question;
    private List<String> answers;
    private int answerIndex;

    Question(){
        question = "NULL";
        answers = new ArrayList<>();
        answerIndex = 0;
    }

    Question(String question, List<String> answers, int answerIndex){
        this.question = question;
        this.answers = answers;
        this.answerIndex = answerIndex;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public void setAnswerIndex(int answerIndex) {
        if(answerIndex > answers.size() - 1){
            this.answerIndex = answers.size() - 1;
        } else if (answerIndex < 0){
            this.answerIndex = 0;
        }
        else {
            this.answerIndex = answerIndex;
        }
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public int getAnswerIndex() {
        return answerIndex;
    }

    @Override
    public String toString() {
        return question;
    }
}
