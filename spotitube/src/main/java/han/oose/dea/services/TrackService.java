package han.oose.dea.services;

import han.oose.dea.persistence.ITrackDAO;
import han.oose.dea.domain.Track;
import han.oose.dea.exceptions.PersistenceException;
import han.oose.dea.services.helpers.TrackHelper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class TrackService {
    @Inject
    private ITrackDAO trackDAO;

    @Inject
    private TrackHelper trackHelper;

    public List<Track> getAll() throws PersistenceException {
        return trackDAO.findAll();
    }

    public List<Track> getNotInPlaylist(int playListId) throws PersistenceException {
        List<Track> tracks = trackDAO.findAll();
        return tracks.stream().filter(track -> !trackHelper.isTrackInPlaylist(track.getId(), playListId)).collect(Collectors.toList());
    }

    public List<Track> getInPlaylist(int playListId) throws PersistenceException {
        List<Track> tracks = trackDAO.findAll();
        return tracks.stream().filter(track -> trackHelper.isTrackInPlaylist(track.getId(), playListId)).collect(Collectors.toList());
    }

    public void setTrackAvailability(int trackId, boolean isOfflineAvailable) throws PersistenceException {
        trackDAO.setTrackAvailability(trackId, isOfflineAvailable);
    }
}
