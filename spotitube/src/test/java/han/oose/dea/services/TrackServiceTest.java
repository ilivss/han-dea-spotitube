package han.oose.dea.services;

import han.oose.dea.domain.Track;
import han.oose.dea.exceptions.PersistenceException;
import han.oose.dea.persistence.ITrackDAO;
import han.oose.dea.services.helpers.TrackHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;


class TrackServiceTest {
    @Mock
    private ITrackDAO trackDAO;

    @Mock
    private TrackHelper trackHelper;

    @InjectMocks
    private TrackService trackService;

    private List<Track> mockTracks = Arrays.asList(
            new Track(1, "Track 1", "Jack", 400, "Album"),
            new Track(2, "Track 2", "Jack", 300, "Album")
    );

    @BeforeEach
    public void setup() {
        this.trackService = new TrackService();
        openMocks(this);
    }


    @Test
    public void getAll() throws PersistenceException {
        when(trackDAO.findAll()).thenReturn(mockTracks);

        List<Track> tracks = trackService.getAll();

        assertEquals(mockTracks, tracks);
    }

    @Test()
    public void getAllException() throws PersistenceException {
        when(trackDAO.findAll()).thenThrow(new PersistenceException("SQL Error"));
        try {
            List<Track> tracks = trackService.getAll();
        } catch (PersistenceException e) {
            assertEquals("SQL Error", e.getMessage());
        }
    }

    @Test()
    public void getNotInPlaylist() throws PersistenceException {
        when(trackDAO.findAll()).thenReturn(mockTracks);

        when(trackHelper.isTrackInPlaylist(1, 1)).thenReturn(false);
        when(trackHelper.isTrackInPlaylist(2, 1)).thenReturn(true);


        List<Track> tracks = trackService.getNotInPlaylist(1);

        assertEquals(1, tracks.size());
        assertEquals(1, tracks.get(0).getId());
    }


    @Test()
    public void getInPlaylist() throws PersistenceException {
        when(trackDAO.findAll()).thenReturn(mockTracks);

        when(trackHelper.isTrackInPlaylist(1, 1)).thenReturn(false);
        when(trackHelper.isTrackInPlaylist(2, 1)).thenReturn(true);


        List<Track> tracks = trackService.getInPlaylist(1);


        assertEquals(1, tracks.size());
        assertEquals(2, tracks.get(0).getId());
    }

    @Test
    public void setTrackAvailibility() throws PersistenceException {

        trackService.setTrackAvailability(1, true);

        verify(trackDAO).setTrackAvailability(1, true);

    }


}