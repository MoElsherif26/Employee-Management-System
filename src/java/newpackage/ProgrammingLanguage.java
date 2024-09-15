package newpackage;

// ProgrammingLanguage.java
public class ProgrammingLanguage {
    private String languageName;
    private int scoreOutof100;

    // Constructors
    public ProgrammingLanguage() {
    }

    public ProgrammingLanguage(String languageName, int scoreOutof100) {
        this.languageName = languageName;
        this.scoreOutof100 = scoreOutof100;
    }

    // Getters and Setters
    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public int getScoreOutof100() {
        return scoreOutof100;
    }

    public void setScoreOutof100(int scoreOutof100) {
        this.scoreOutof100 = scoreOutof100;
    }
    @Override
    public String toString() {
        return "ProgrammingLanguage{" +
                "languageName='" + languageName + '\'' +
                ", scoreOutof100=" + scoreOutof100 +
                '}';
    }
}
