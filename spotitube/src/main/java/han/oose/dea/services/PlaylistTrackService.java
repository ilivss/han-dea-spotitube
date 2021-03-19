package han.oose.dea.services;

import han.oose.dea.persistence.IPlaylistTrackDAO;

import javax.inject.Inject;
import java.util.List;

public class PlaylistTrackService {
    @Inject
    private IPlaylistTrackDAO playlistTrackDAO;

    public List<Integer> getTrackIdsInPlaylist(int playlistId) {
        return playlistTrackDAO.findTrackIdsByPlaylistId(playlistId);
    }

    public void removeTrackFromPlaylist(int playlistId, int trackId) {
        playlistTrackDAO.removeTrackFromPlaylist(playlistId, trackId);
    }

    public void addTrackToPlaylist(int playlistId, int trackId) {
        playlistTrackDAO.addTrackToPlaylist(playlistId, trackId);
    }

}
