package nl.han.ica.airspaceinvaders.assets.highscores;

public class Score {

    private String name;
    private int score;

    /**
     * Getter for name
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for score
     * @return int
     */
    public int getScore() {
        return score;
    }

    /**
     * Setter for score
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     *  Compare witch score is higher
     * @param other
     * @return Score
     */
    public Score isHigherScore(Score other) {
        if(getScore() > other.getScore()){
            return this;
        }
        return other;
    }
}
