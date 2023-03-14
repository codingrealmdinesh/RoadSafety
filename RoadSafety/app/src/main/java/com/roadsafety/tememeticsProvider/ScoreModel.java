package com.roadsafety.tememeticsProvider;

public class ScoreModel {
    String rank;
    String name;
    String score;

    public ScoreModel(String rank, String name, String score){
        this.rank = rank;
        this.name = name;
        this.score = score;
    }

    public String getRank() {
        return rank;
    }

    public String getName() {
        return name;
    }

    public String getScore() {
        return score;
    }
}
