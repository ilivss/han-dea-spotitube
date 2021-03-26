package han.oose.dea.rest.mappers;

import han.oose.dea.domain.Track;
import han.oose.dea.rest.dto.TrackDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrackRestMapperTest {

    private TrackRestMapper trackRestMapper;

    @BeforeEach
    void setUp() {
        trackRestMapper = new TrackRestMapper();
    }

    @Test
    void toDTO() {
        // Arrange
        int duration = 5;
        Track track = new Track(0, "title", "performer", duration, "album");

        // Act
        TrackDTO trackDTO = trackRestMapper.toDTO(track);

        // Assert
        assertEquals(track.getId(), trackDTO.id);
        assertEquals(track.getTitle(), trackDTO.title);
        assertEquals(track.getPerformer(), trackDTO.performer);
        assertEquals(track.getAlbum(), trackDTO.album);
    }
}