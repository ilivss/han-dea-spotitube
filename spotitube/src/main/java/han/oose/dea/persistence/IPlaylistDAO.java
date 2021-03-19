package han.oose.dea.persistence;

import han.oose.dea.domain.Playlist;
import han.oose.dea.exceptions.PersistenceException;

import java.util.List;

public interface IPlaylistDAO {
    List<Playlist> findAll() throws PersistenceException;

    void addPlaylist(Playlist playlist) throws PersistenceException;

    void deletePlaylist(int playlistId) throws PersistenceException;

    void editPlaylist(int playlistId, Playlist playlist) throws PersistenceException;
}
