package placesafe.placesafe;

/**
 * Created by Gerson on 29/11/16.
 */
public class Opinion {
    private String user;
    private String opinion_text;

    public Opinion(String user, String opinion_text) {
        this.user = user;
        this.opinion_text = opinion_text;
    }

    public String getOpinion_text() {
        return opinion_text;
    }

    public void setOpinion_text(String opinion_text) {
        this.opinion_text = opinion_text;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
