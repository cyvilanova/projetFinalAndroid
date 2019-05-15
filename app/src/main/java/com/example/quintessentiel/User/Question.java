package com.example.quintessentiel.User;

public class Question {

    private Integer idQuestion;
    private String question;

    public Question(Integer idQuestion,String question){
        this.setIdQuestion(idQuestion);
        this.setQuestion(question);
    }


    @Override
    public String toString() {
        return this.getQuestion();
    }

    public Integer getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(Integer idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
