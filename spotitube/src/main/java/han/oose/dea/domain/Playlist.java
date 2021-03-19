package han.oose.dea.domain;

import java.util.List;

public class Playlist {
    private int id;
    private String name;
    private String owner;
    private List<Track> tracks;

    public Playlist() {
    }

    public Playlist(int id, String name, String owner) {
        this.id = id;
        this.name = name;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}

