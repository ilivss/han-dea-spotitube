package han.oose.dea.services;

import han.oose.dea.domain.Playlist;
import han.oose.dea.exceptions.PersistenceException;
import han.oose.dea.persistence.IPlaylistDAO;
import han.oose.dea.services.helpers.PlaylistHelper;

import javax.inject.Inject;
import java.util.List;

public class PlaylistService {
    @Inject
    private IPlaylistDAO playlistDAO;
    @Inject
    private PlaylistTrackService playlistTrackService;

    @Inject
    private PlaylistHelper playlistHelper;

    public List<Playlist> getAll() throws PersistenceException {
        List<Playlist> playlists = playlistDAO.findAll();

        playlistHelper.populatePlaylistsWithTracks(playlists);

        return playlists;
    }
    
    public void addPlaylist(Playlist playlist) throws PersistenceException {
        playlistDAO.addPlaylist(playlist);
    }


    public void deletePlaylist(int playlistId) throws PersistenceException {
        playlistDAO.deletePlaylist(playlistId);
    }

    public void editPlaylist(int playlistId, Playlist playlist) throws PersistenceException {
        playlistDAO.editPlaylist(playlistId, playlist);
    }
}
