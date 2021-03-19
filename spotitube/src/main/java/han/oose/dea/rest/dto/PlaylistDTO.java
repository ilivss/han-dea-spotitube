package han.oose.dea.rest.dto;

import java.util.List;

public class PlaylistDTO {
    public int id;
    public String name;
    public boolean owner;
    public List<TrackDTO> tracks;

    public PlaylistDTO() {
    }

    public PlaylistDTO(int id, String name, boolean owner, List<TrackDTO> tracks) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.tracks = tracks;
    }
}
