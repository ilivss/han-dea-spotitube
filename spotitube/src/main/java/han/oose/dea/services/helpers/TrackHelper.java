package han.oose.dea.services.helpers;

import han.oose.dea.exceptions.PersistenceException;
import han.oose.dea.services.PlaylistTrackService;

import javax.inject.Inject;
import java.util.List;

public class TrackHelper {
    @Inject
    private PlaylistTrackService playlistTrackService;

    public boolean isTrackInPlaylist(int trackId, int playlistId) {
        List<Integer> trackIds = playlistTrackService.getTrackIdsInPlaylist(playlistId);
        return trackIds.stream().anyMatch(id -> id.equals(trackId));
    }
}
