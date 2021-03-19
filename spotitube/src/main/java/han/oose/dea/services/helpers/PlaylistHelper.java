package han.oose.dea.services.helpers;

import han.oose.dea.domain.Playlist;
import han.oose.dea.domain.Track;
import han.oose.dea.exceptions.PersistenceException;
import han.oose.dea.services.TrackService;

import javax.inject.Inject;
import java.util.List;

public class PlaylistHelper {
    @Inject
    private TrackService trackService;

    public void populatePlaylistsWithTracks(List<Playlist> playlists) throws PersistenceException {
        // Populate playlists with tracks
        for (Playlist playlist : playlists) {
            playlist.setTracks(trackService.getInPlaylist(playlist.getId()));
        }
    }

    public long calculatePlaylistLength(List<Playlist> playlist) {
        long seconds = 0;

        for(Playlist p : playlist) {
            for (Track t : p.getTracks()) {
                seconds += t.getDuration();
            }
        }

        return seconds;
    }
}
