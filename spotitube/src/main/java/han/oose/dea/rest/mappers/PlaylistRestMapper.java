package han.oose.dea.rest.mappers;

import han.oose.dea.domain.Playlist;
import han.oose.dea.rest.dto.PlaylistDTO;
import han.oose.dea.services.AuthService;

import javax.inject.Inject;
import java.util.stream.Collectors;

// FIXME: wat is de juiste plek om username bij te werken zonder de interface aan te passen?
public class PlaylistRestMapper {
    @Inject
    private TrackRestMapper trackMapper;

    public PlaylistDTO toDTO(Playlist playlist, String username) {
        return new PlaylistDTO(
            playlist.getId(),
                playlist.getName(),
                playlist.getOwner().equals(username),
                playlist.getTracks().stream().map(track -> trackMapper.toDTO(track)).collect(Collectors.toList())
        );
    }

    public Playlist toDomainObject(PlaylistDTO playlistDTO, String username) {
        return new Playlist(
                playlistDTO.id,
                playlistDTO.name,
                username
        );
    }

}
