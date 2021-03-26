package han.oose.dea.rest.mappers;

import han.oose.dea.domain.Playlist;
import han.oose.dea.domain.Track;
import han.oose.dea.rest.dto.PlaylistDTO;
import han.oose.dea.rest.dto.TrackDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class PlaylistRestMapperTest {

    @Mock
    private TrackRestMapper trackRestMapper;

    @InjectMocks
    private PlaylistRestMapper playlistRestMapper;

    private String username;
    private Track track;
    private TrackDTO trackDTO;

    @BeforeEach
    void setUp() {
        playlistRestMapper = new PlaylistRestMapper();
        openMocks(this);

        username = "owner";
        track = new Track(0, "title", "performer", 1, "album");
        trackDTO = new TrackDTO(track.getId(), track.getTitle(), track.getPerformer(), track.getDuration(), track.getAlbum());
    }

    @Test
    void toDTO() {
        // Arrange
        Playlist playlist = new Playlist(0, "name", "owner");
        playlist.setTracks(Collections.singletonList(track));

        when(trackRestMapper.toDTO(track)).thenReturn(trackDTO);

        // Act
        PlaylistDTO playlistDTO = playlistRestMapper.toDTO(playlist,  username);

        // Assert
        assertEquals(playlist.getId(), playlistDTO.id);
        assertEquals(playlist.getName(), playlistDTO.name);
        assertTrue(playlistDTO.owner);
        assertEquals(playlist.getTracks().get(0).getTitle(), playlistDTO.tracks.get(0).title);
    }


    @Test
    void toDomainObject() {
        // Arrange
        PlaylistDTO playlistDTO = new PlaylistDTO(0, "title", true, Collections.singletonList(trackDTO));

        // Act
        Playlist playlist = playlistRestMapper.toDomainObject(playlistDTO, username);

        // Assert
        assertEquals(playlistDTO.id, playlist.getId());
        assertEquals(playlistDTO.name, playlist.getName());
        assertEquals(username, playlist.getOwner());
    }
}