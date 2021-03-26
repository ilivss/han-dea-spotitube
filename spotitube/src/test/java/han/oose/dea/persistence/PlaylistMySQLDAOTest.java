package han.oose.dea.persistence;

import han.oose.dea.domain.Playlist;
import han.oose.dea.persistence.mappers.PlaylistSQLDataMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

class PlaylistMySQLDAOTest {
    @Mock
    private DataSource dataSource;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    ResultSet resultSet;

    @Mock
    private PlaylistSQLDataMapper playlistSQLDataMapper;

    @InjectMocks
    private IPlaylistDAO playlistMySQLDAO;

    private Playlist playlist;

    @BeforeEach
    void setup() {
        playlistMySQLDAO = new PlaylistMySQLDAO();
        openMocks(this);
        playlistMySQLDAO.setDataSource(dataSource);

        playlist = new Playlist(0, "name", "owner");
    }

    @Test
    void findAllSuccess() {
        try {
            // Arrange
            String expectedSQLQuery = "SELECT * FROM playlist;";

            // Instruct Mocks
            when(dataSource.getConnection()).thenReturn(connection);
            when(connection.prepareStatement(expectedSQLQuery)).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenReturn(resultSet);
            when(resultSet.next()).thenReturn(true).thenReturn(false);
            when(playlistSQLDataMapper.toDomainObject(resultSet)).thenReturn(playlist);

            // Act
            List<Playlist> playlists = playlistMySQLDAO.findAll();

            // Assert
            verify(connection).prepareStatement(expectedSQLQuery);
            verify(preparedStatement).executeQuery();
            assertEquals(playlist, playlists.get(0));

        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    void addPlaylist() {
        try {
            // Arrange
            String expectedSQLQuery = "INSERT INTO playlist (name, owner) VALUES (?, ?);";

            // Instruct Mocks
            when(dataSource.getConnection()).thenReturn(connection);
            when(connection.prepareStatement(expectedSQLQuery)).thenReturn(preparedStatement);

            // Act
            playlistMySQLDAO.addPlaylist(playlist);

            // Assert
            verify(connection).prepareStatement(expectedSQLQuery);
            verify(preparedStatement).execute();

        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    void deletePlaylist() {
        try {
            // Arrange
            String expectedSQLQuery = "DELETE FROM playlist WHERE id = ?";
            int playlistId = 1;

            // Instruct Mocks
            when(dataSource.getConnection()).thenReturn(connection);
            when(connection.prepareStatement(expectedSQLQuery)).thenReturn(preparedStatement);

            // Act
            playlistMySQLDAO.deletePlaylist(playlistId);

            // Assert
            verify(connection).prepareStatement(expectedSQLQuery);
            verify(preparedStatement).setInt(1, playlistId);
            verify(preparedStatement).execute();
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    void editPlaylist() {
        try {
            // Arrange
            String expectedSQLQuery = "UPDATE playlist SET name = ? WHERE id = ?";
            int playlistId = 1;

            // Instruct Mocks
            when(dataSource.getConnection()).thenReturn(connection);
            when(connection.prepareStatement(expectedSQLQuery)).thenReturn(preparedStatement);

            // Act
            playlistMySQLDAO.editPlaylist(playlistId, playlist);

            // Assert
            verify(connection).prepareStatement(expectedSQLQuery);
            verify(preparedStatement).setString(1, playlist.getName());
            verify(preparedStatement).setInt(2, playlistId);
            verify(preparedStatement).execute();
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
}