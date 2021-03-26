package han.oose.dea.rest.mappers;

import han.oose.dea.domain.Playlist;
import han.oose.dea.domain.Track;
import han.oose.dea.rest.dto.PlaylistDTO;
import han.oose.dea.rest.dto.PlaylistsDTO;
import han.oose.dea.rest.dto.TrackDTO;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class PlaylistsRestMapper implements IRestMapper<PlaylistsDTO, List<PlaylistDTO>> {

    @Override
    public PlaylistsDTO toDTO(List<PlaylistDTO> playlistDTOs) {
        return new PlaylistsDTO(
                playlistDTOs,
                this.calculateLength(playlistDTOs)
        );
    }

    @Override
    public List<PlaylistDTO> toDomainObject(PlaylistsDTO playlistsDTO) {
        return null;
    }

    protected long calculateLength(List<PlaylistDTO> playlistDTOs) {
        long seconds = 0;

        for (PlaylistDTO p : playlistDTOs) {
            for (TrackDTO t : p.tracks) {
                seconds += t.duration;
            }
        }

        return seconds;
    }
}
