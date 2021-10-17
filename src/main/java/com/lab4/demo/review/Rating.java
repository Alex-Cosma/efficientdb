package com.lab4.demo.review;

public enum Rating {
    POOR(1),
    AVERAGE(2),
    GREAT(3),
    EXCELENT(4);

    private final int score;

    Rating(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
