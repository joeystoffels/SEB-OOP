package nl.han.ica.airspaceinvaders.highscores;

public class Score {

    private String name;
    private int score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Score isHigherScore(Score other) {
        if(getScore() > other.getScore()){
            return this;
        }
        return other;
    }
}
