package han.oose.dea.services;

import han.oose.dea.domain.Playlist;
import han.oose.dea.exceptions.PersistenceException;
import han.oose.dea.persistence.IPlaylistDAO;
import han.oose.dea.services.helpers.PlaylistHelper;
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

public class PlaylistServiceTest {
    @Mock
    private IPlaylistDAO playlistDAO;
    @Mock
    private PlaylistTrackService playlistTrackService;
    @Mock
    private PlaylistHelper playlistHelper;

    @InjectMocks
    private PlaylistService playlistService;

    private final List<Playlist> mockPlaylists = Arrays.asList(
            new Playlist(),
            new Playlist()
    );

    @BeforeEach
    public void setup() {
        this.playlistService = new PlaylistService();
        openMocks(this);
    }

    @Test
    public void getAll() throws PersistenceException {
        when(playlistDAO.findAll()).thenReturn(mockPlaylists);

        var playlists = this.playlistService.getAll();

        verify(playlistHelper).populatePlaylistsWithTracks(mockPlaylists);

        assertEquals(mockPlaylists, playlists);
    }

    @Test
    public void addPlaylist() throws PersistenceException {
        var playlist = new Playlist(1, "My Playlist", "Iliass");

        this.playlistService.addPlaylist(playlist);

        verify(playlistDAO).addPlaylist(playlist);
    }

    @Test
    public void deletePlaylist() throws PersistenceException {
        this.playlistService.deletePlaylist(1);

        verify(playlistDAO).deletePlaylist(1);
    }

    @Test
    public void editPlaylist() throws PersistenceException {
        // Test that playlistId is ignored
        var playlist = new Playlist(2, "My Playlist Edited", "Iliass");
        this.playlistService.editPlaylist(1, playlist);

        verify(playlistDAO).editPlaylist(1, playlist);
    }
}