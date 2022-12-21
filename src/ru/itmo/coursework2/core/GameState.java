package ru.itmo.coursework2.core;

import java.io.Serializable;

public class GameState implements Serializable {
    private String title;
    private String text;
    private String firstChoiceTitle;
    private String secondChoiceTitle;
    private GameState firstChoice;
    private GameState secondChoice;
    private State state;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFirstChoiceTitle() {
        return firstChoiceTitle;
    }

    public void setFirstChoiceTitle(String firstChoiceTitle) {
        this.firstChoiceTitle = firstChoiceTitle;
    }

    public String getSecondChoiceTitle() {
        return secondChoiceTitle;
    }

    public void setSecondChoiceTitle(String secondChoiceTitle) {
        this.secondChoiceTitle = secondChoiceTitle;
    }

    public GameState getFirstChoice() {
        return firstChoice;
    }

    public void setFirstChoice(GameState firstChoice) {
        this.firstChoice = firstChoice;
    }

    public GameState getSecondChoice() {
        return secondChoice;
    }

    public void setSecondChoice(GameState secondChoice) {
        this.secondChoice = secondChoice;
    }

    public enum State {
        WIN, LOSE, RUNNING
    }
}
