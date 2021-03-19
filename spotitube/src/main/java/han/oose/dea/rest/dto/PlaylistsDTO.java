package han.oose.dea.rest.dto;

import java.util.List;

public class PlaylistsDTO {
    public List<PlaylistDTO> playlists;
    public long length;

    public PlaylistsDTO() {
    }

    public PlaylistsDTO(List<PlaylistDTO> playlists, long length) {
        this.playlists = playlists;
        this.length = length;
    }
}
