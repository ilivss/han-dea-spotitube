package han.oose.dea.persistence;

import han.oose.dea.domain.Playlist;
import han.oose.dea.exceptions.PersistenceException;
import han.oose.dea.persistence.mappers.PlaylistSQLDataMapper;

import javax.annotation.Resource;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Default
public class PlaylistDAO implements IPlaylistDAO {
    @Resource(name = "jdbc/spotitube")
    DataSource dataSource;

    @Inject
    private PlaylistSQLDataMapper playlistSQLDataMapper;

    // Queries
    private static final String FIND_ALL_PLAYLISTS_QUERY = "SELECT * FROM playlist;";
    private static final String INSERT_PLAYLIST_QUERY = "INSERT INTO playlist (name, owner) VALUES (?, ?);";
    private static final String DELETE_PLAYLIST_QUERY = "DELETE FROM playlist WHERE id = ?";
    private static final String UPDATE_PLAYLIST_QUERY = "UPDATE playlist SET name = ? WHERE id = ?";

    @Override
    public List<Playlist> findAll() throws PersistenceException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_PLAYLISTS_QUERY);

            ResultSet resultSet = statement.executeQuery();

            List<Playlist> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(playlistSQLDataMapper.toDomainObject(resultSet));
            }

            return result;
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }


    @Override
    public void addPlaylist(Playlist playlist) throws PersistenceException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_PLAYLIST_QUERY);

            statement.setString(1, playlist.getName());
            statement.setString(2, playlist.getOwner());

            statement.execute();
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }

    }

    @Override
    public void deletePlaylist(int playlistId) throws PersistenceException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_PLAYLIST_QUERY);

            statement.setInt(1, playlistId);

            statement.execute();
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    public void editPlaylist(int playlistId, Playlist playlist) throws PersistenceException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_PLAYLIST_QUERY);

            statement.setString(1, playlist.getName());
            statement.setInt(2, playlistId);

            statement.execute();
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }
}
