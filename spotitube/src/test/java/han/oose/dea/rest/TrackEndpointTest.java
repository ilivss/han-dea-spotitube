package han.oose.dea.rest;

import han.oose.dea.domain.Track;
import han.oose.dea.exceptions.NoRecoursesFoundException;
import han.oose.dea.exceptions.PersistenceException;
import han.oose.dea.rest.dto.TrackDTO;
import han.oose.dea.rest.dto.TracksDTO;
import han.oose.dea.rest.mappers.TrackRestMapper;
import han.oose.dea.services.TrackService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.ws.rs.core.Response;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class TrackEndpointTest {

    @Mock
    private TrackService trackService;

    @Mock
    private TrackRestMapper trackRestMapper;

    @InjectMocks
    private TrackEndpoint trackEndpoint;

    private TrackDTO trackDTO;
    private List<Track> tracks;

    @BeforeEach
    void setUp() {
        this.trackEndpoint = new TrackEndpoint();
        openMocks(this);

        trackDTO = new TrackDTO();
        Track track = new Track();
        tracks = Collections.singletonList(track);

        when(trackRestMapper.toDTO(track)).thenReturn(trackDTO);
    }

    @Test
    void getNotInPlaylist() throws NoRecoursesFoundException, PersistenceException {
        // Arrange
        when(trackService.getNotInPlaylist(1)).thenReturn(tracks);

        // Act
        Response response = trackEndpoint.getNotInPlaylist(1);

        // Assert
        assertEquals(Response.Status.OK, response.getStatusInfo());
        assertEquals(trackDTO, ((TracksDTO) response.getEntity()).tracks.get(0));
    }

    @Test
    void getNotInPlaylistNoId() throws NoRecoursesFoundException, PersistenceException {
        // Arrange
        when(trackService.getAll()).thenReturn(tracks);

        // Act
        Response response = trackEndpoint.getNotInPlaylist(0);

        // Assert
        assertEquals(Response.Status.OK, response.getStatusInfo());
        assertEquals(trackDTO, ((TracksDTO) response.getEntity()).tracks.get(0));
    }
}