package net.michelison.homebitbot;

public class WeatherSample {
    private int ID;
    private String question;
    private String answer;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "WeatherSample{" +
                "ID=" + ID +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
