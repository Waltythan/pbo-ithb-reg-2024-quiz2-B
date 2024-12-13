package Model;

public class Artworks {
    private int id;
    private String title;
    private String description;
    private String imagePath;
    private int userId;
    private String artistName;

    public Artworks(int id, String title, String description, String imagePath, int userId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imagePath = imagePath;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public int getUserId() {
        return userId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
}