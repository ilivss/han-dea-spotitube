package han.oose.dea.rest.mappers;

import han.oose.dea.domain.Playlist;
import han.oose.dea.rest.dto.PlaylistDTO;

import javax.inject.Inject;
import java.util.stream.Collectors;

public class PlaylistRestMapper {
    @Inject
    private TrackRestMapper trackRestMapper;

    public PlaylistDTO toDTO(Playlist playlist, String username) {
        return new PlaylistDTO(
                playlist.getId(),
                playlist.getName(),
                playlist.getOwner().equals(username),
                playlist.getTracks().stream().map(track -> trackRestMapper.toDTO(track)).collect(Collectors.toList())
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
