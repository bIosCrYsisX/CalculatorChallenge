package tk.dalpiazsolutions.calculatorchallenge;

/**
 * Created by Christoph on 27.03.2018.
 */

public class MainModel {

    private MainActivity mainActivity;
    private int numberOneCalc;
    private int numberTwoCalc;
    private int operatorCalc;
    private int result;
    private int answerNumberOne;
    private int answerNumberTwo;
    private int answerNumberThree;
    private int answerNumberFour;
    private int correctCounter;
    private int counter;
    private int gameCounter = 0;
    private String operatorText;

    public MainModel(MainActivity mainActivity)
    {
        this.mainActivity = mainActivity;
    }

    public void setNumberOneCalc(int numberOneCalc)
    {
        this.numberOneCalc = numberOneCalc;
    }

    public int getNumberOneCalc() {
        return numberOneCalc;
    }

    public void setNumberTwoCalc(int numberTwoCalc) {
        this.numberTwoCalc = numberTwoCalc;
    }

    public int getNumberTwoCalc() {
        return numberTwoCalc;
    }

    public void setOperatorCalc(int operatorCalc)
    {
        this.operatorCalc = operatorCalc;
    }

    public int getOperatorCalc()
    {
        return operatorCalc;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getResult() {
        return result;
    }

    public void setAnswerNumberOne(int answerNumberOne) {
        this.answerNumberOne = answerNumberOne;
    }

    public int getAnswerNumberOne() {
        return answerNumberOne;
    }

    public void setAnswerNumberTwo(int answerNumberTwo) {
        this.answerNumberTwo = answerNumberTwo;
    }

    public int getAnswerNumberTwo() {
        return answerNumberTwo;
    }

    public void setAnswerNumberThree(int answerNumberThree) {
        this.answerNumberThree = answerNumberThree;
    }

    public int getAnswerNumberThree() {
        return answerNumberThree;
    }

    public void setAnswerNumberFour(int answerNumberFour) {
        this.answerNumberFour = answerNumberFour;
    }

    public int getAnswerNumberFour() {
        return answerNumberFour;
    }

    public void setOperatorText(String operatorText) {
        this.operatorText = operatorText;
    }

    public String getOperatorText() {
        return operatorText;
    }

    public void setTexts() {
        mainActivity.update(this);
    }

    public int getCorrectCounter() {
        return correctCounter;
    }

    public void setCorrectCounter(int correctCounter) {
        this.correctCounter = correctCounter;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getGameCounter() {
        return gameCounter;
    }

    public void setGameCounter(int gameCounter) {
        this.gameCounter = gameCounter;
    }
}
