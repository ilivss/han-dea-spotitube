package han.oose.dea.domain;

public class Track {
    private int id;
    private String title;
    private String performer;
    private int duration;
    private String album;
    private int playCount;
    private String publicationDate;
    private String description;
    private boolean offlineAvailable;

    public Track() {
    }

    public Track(int id, String title, String performer, int duration, String album) {
        this.id = id;
        this.title = title;
        this.performer = performer;
        this.duration = duration;
        this.album = album;
    }

    public Track(int id, String title, String performer, int duration, String album, int playCount, String publicationDate, String description, boolean offlineAvailable) {
        this.id = id;
        this.title = title;
        this.performer = performer;
        this.duration = duration;
        this.album = album;
        this.playCount = playCount;
        this.publicationDate = publicationDate;
        this.description = description;
        this.offlineAvailable = offlineAvailable;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPerformer() {
        return performer;
    }

    public int getDuration() {
        return duration;
    }

    public String getAlbum() {
        return album;
    }

    public int getPlayCount() {
        return playCount;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public String getDescription() {
        return description;
    }

    public boolean getOfflineAvailable() {
        return offlineAvailable;
    }
}
