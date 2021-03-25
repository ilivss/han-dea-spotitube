package han.oose.dea.services;

import han.oose.dea.persistence.IPlaylistTrackDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class PlaylistTrackServiceTest {
    @Mock
    private IPlaylistTrackDAO playlistTrackDAO;

    @InjectMocks
    private PlaylistTrackService playlistTrackService;

    private final List<Integer> mockTrackIds = Arrays.asList(1, 2, 3);

    @BeforeEach
    public void setup() {
        this.playlistTrackService = new PlaylistTrackService();
        openMocks(this);
    }

    @Test
    void getTrackIdsInPlaylist() {
        when(playlistTrackDAO.findTrackIdsByPlaylistId(1)).thenReturn(mockTrackIds);


        var trackIds = playlistTrackService.getTrackIdsInPlaylist(1);

        assertEquals(mockTrackIds, trackIds);
    }

    @Test
    void removeTrackFromPlaylist() {
        playlistTrackService.removeTrackFromPlaylist(1, 2);
        verify(playlistTrackDAO).removeTrackFromPlaylist(1, 2);
    }

    @Test
    void addTrackToPlaylist() {
        playlistTrackService.addTrackToPlaylist(1, 2);
        verify(playlistTrackDAO).addTrackToPlaylist(1, 2);
    }
}