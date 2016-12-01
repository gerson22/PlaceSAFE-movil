package placesafe.placesafe;

/**
 * Created by Gerson on 01/12/16.
 */
public class Reaction {
    private String url_image;
    private String name;

    public Reaction(String name, String url_image) {
        this.name = name;
        this.url_image = url_image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }
}
