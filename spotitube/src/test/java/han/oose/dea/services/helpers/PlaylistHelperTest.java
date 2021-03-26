package han.oose.dea.services.helpers;

import han.oose.dea.domain.Playlist;
import han.oose.dea.domain.Track;
import han.oose.dea.exceptions.PersistenceException;
import han.oose.dea.services.TrackService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.inject.Inject;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class PlaylistHelperTest {
    @Mock
    private TrackService trackService;

    @InjectMocks
    private PlaylistHelper playlistHelper;

    @BeforeEach
    void setup() {
        this.playlistHelper = new PlaylistHelper();
        openMocks(this);
    }

    @Test
    void populatePlaylistsWithTracks() throws PersistenceException {
        List<Playlist> playlists = Collections.singletonList(new Playlist(1, "name", "owner"));
        final List<Track> tracks = Collections.singletonList(new Track());
        when(trackService.getInPlaylist(1)).thenReturn(tracks);

        playlistHelper.populatePlaylistsWithTracks(playlists);

        assertEquals(tracks, playlists.get(0).getTracks());
    }
}