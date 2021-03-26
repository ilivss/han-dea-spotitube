package han.oose.dea.services.helpers;

import han.oose.dea.services.PlaylistTrackService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.inject.Inject;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class TrackHelperTest {
    @Mock
    private PlaylistTrackService playlistTrackService;

    @InjectMocks
    private TrackHelper trackHelper;

    @BeforeEach
    private void setup() {
        trackHelper = new TrackHelper();
        openMocks(this);
    }

    @Test
    void isTrackInPlaylist() {
        when(playlistTrackService.getTrackIdsInPlaylist(2)).thenReturn(Arrays.asList(1, 2, 3));

        boolean result = trackHelper.isTrackInPlaylist(1, 2);

        assertTrue(result);
    }
}