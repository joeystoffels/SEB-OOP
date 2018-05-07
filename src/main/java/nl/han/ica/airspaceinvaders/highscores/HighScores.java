package nl.han.ica.airspaceinvaders.highscores;

import nl.han.ica.OOPDProcessingEngineHAN.Persistence.FilePersistence;

public class HighScores extends FilePersistence {

    private static final String STRING_SEPERATOR = ";";
    private static final String SCORE_SEPERATOR = "/";
    private static int amountOfHighScores = 10;

    /**
     * The constructor allows you to specify the filename the internal storage
     * will use.
     *
     * @param filename The name of the file that will be used for this persistance.
     */
    public HighScores(String filename) {
        super(filename);
    }

    public Score[] loadScores() {
        String[] stringArray = this.loadDataStringArray(STRING_SEPERATOR);
        Score[] scoreArray = new Score[stringArray.length];

        for (int index = 0; index < stringArray.length; index++) {
            String value = stringArray[index];
            String[] parts = value.split(SCORE_SEPERATOR, 2);

                Score newScore = new Score();
                newScore.setName(parts[0]);
                newScore.setScore(Integer.parseInt(parts[1]));
                scoreArray[index] = newScore;

        }
        return scoreArray;
    }

    public void saveScore(String name, int score) {
        Score[] scoreArray = this.loadScores();
        Boolean inserted = false;

        String[] stringArray = new String[scoreArray.length + 1];
        if (scoreArray[0] == null) {
            stringArray = new String[scoreArray.length];
        }

        for (int index = 0; index < scoreArray.length; index++) {
            if (scoreArray[index] == null) {
                stringArray[index] = name + SCORE_SEPERATOR + score;
            } else {

                String scoreName = scoreArray[index].getName();
                int scoreScore = scoreArray[index].getScore();

                if (score >= scoreScore && inserted == false) {
                    stringArray[index] = name + SCORE_SEPERATOR + score;
                    stringArray[index + 1] = scoreName + SCORE_SEPERATOR + scoreScore;
                    inserted = true;
                } else if (inserted == true) {
                    stringArray[index + 1] = scoreName + SCORE_SEPERATOR + scoreScore;

                } else if (( inserted == false && index == scoreArray.length) || inserted == false){
                    stringArray[index] = scoreName + SCORE_SEPERATOR + scoreScore;
                }
            }
        }

        if(inserted == false){
            stringArray[stringArray.length - 1] = name + SCORE_SEPERATOR + score;
        }

        this.saveData(stringArray, STRING_SEPERATOR);
    }
}
