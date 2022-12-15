package com.example.android.expencecalculator;

public class ex_data {

    private boolean positive;
    private int amount;
    private String message;

    public ex_data(String message , int amount , boolean positive){
        this.amount = amount;
        this.message = message;
        this.positive = positive;
    }

    public boolean isPositive() {
        return positive;
    }

    public int getAmount() {
        return amount;
    }

    public String getMessage() {
        return message;
    }

    public void setPositive(boolean positive) {
        this.positive = positive;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
