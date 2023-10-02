package com.hasee.fingerprint.dto;

public class FingerPrintMatchData {
    private double score;
    private double threshold;
    private boolean isMatched;

    public FingerPrintMatchData() {
    }

    public FingerPrintMatchData(double score, double threshold, boolean isMatched) {
        this.score = score;
        this.threshold = threshold;
        this.isMatched = isMatched;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public boolean isMatched() {
        return isMatched;
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
    }
}
