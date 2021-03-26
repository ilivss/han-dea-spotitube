package han.oose.dea.rest.mappers;

import han.oose.dea.rest.dto.PlaylistDTO;
import han.oose.dea.rest.dto.PlaylistsDTO;
import han.oose.dea.rest.dto.TrackDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PlaylistsRestMapperTest {

    private PlaylistsRestMapper playlistsRestMapper;

    @BeforeEach
    void setUp() {
        playlistsRestMapper = new PlaylistsRestMapper();
    }

    @Test
    void toDTO() {
        // Arrange
        PlaylistDTO playlistDTO = new PlaylistDTO();
        int duration = 5;
        playlistDTO.name = "test playlist";
        playlistDTO.tracks = Collections.singletonList(new TrackDTO(0, "title", "performer", duration, "album"));
        List<PlaylistDTO> playlistDTOs = Collections.singletonList(playlistDTO);

        // Act
        PlaylistsDTO playlistsDTO = playlistsRestMapper.toDTO(playlistDTOs);

        // Assert
        assertEquals(playlistDTO.name, playlistsDTO.playlists.get(0).name);
        assertEquals(duration, playlistsDTO.length);
    }
}