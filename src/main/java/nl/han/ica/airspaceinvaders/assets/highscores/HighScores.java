package nl.han.ica.airspaceinvaders.assets.highscores;

import nl.han.ica.OOPDProcessingEngineHAN.Persistence.FilePersistence;

public class HighScores extends FilePersistence {

    private final String stringSeparator = ";";
    private final String scoreSeparator = "/";

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
        String[] stringArray = this.loadDataStringArray(stringSeparator);
        Score[] scoreArray = new Score[stringArray.length];

        for (int index = 0; index < stringArray.length; index++) {
            String value = stringArray[index];
            String[] parts = value.split(scoreSeparator, 2);
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
                stringArray[index] = name + scoreSeparator + score;
            } else {

                String scoreName = scoreArray[index].getName();
                int scoreScore = scoreArray[index].getScore();

                if (score >= scoreScore && inserted == false) {
                    stringArray[index] = name + scoreSeparator + score;
                    stringArray[index + 1] = scoreName + scoreSeparator + scoreScore;
                    inserted = true;
                } else if (inserted == true) {
                    stringArray[index + 1] = scoreName + scoreSeparator + scoreScore;

                } else if (( inserted == false && index == scoreArray.length) || inserted == false){
                    stringArray[index] = scoreName + scoreSeparator + scoreScore;
                }
            }
        }

        if(inserted == false){
            stringArray[stringArray.length - 1] = name + scoreSeparator + score;
        }
        this.saveData(stringArray, stringSeparator);
    }
}
