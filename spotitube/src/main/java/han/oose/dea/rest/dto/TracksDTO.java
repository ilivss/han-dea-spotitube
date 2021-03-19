package han.oose.dea.rest.dto;

import java.util.List;

public class TracksDTO {
    public List<TrackDTO> tracks;

    public TracksDTO() {
    }

    public TracksDTO(List<TrackDTO> tracks) {
        this.tracks = tracks;
    }
}
