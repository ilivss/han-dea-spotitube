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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class PlaylistTrackMySQLDAOTest {
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
    private IPlaylistTrackDAO playlistTrackDAO;

    @BeforeEach
    void setUp() {
        playlistTrackDAO = new PlaylistTrackMySQLDAO();
        openMocks(this);
        playlistTrackDAO.setDataSource(dataSource);
    }

    @Test
    void findTrackIdsByPlaylistId() {
        try {
            // Arrange
            String expectedSQLQuery = "SELECT track_id FROM playlist_track WHERE playlist_id = ?";
            int id = 1;

            // Instruct Mocks
            when(dataSource.getConnection()).thenReturn(connection);
            when(connection.prepareStatement(expectedSQLQuery)).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenReturn(resultSet);
            when(resultSet.next()).thenReturn(true).thenReturn(false);
            when(resultSet.getInt("track_id")).thenReturn(id);

            // Act
            List<Integer> trackIds = playlistTrackDAO.findTrackIdsByPlaylistId(id);

            // Assert
            verify(connection).prepareStatement(expectedSQLQuery);
            verify(preparedStatement).setInt(1, id);
            verify(preparedStatement).executeQuery();
            assertEquals(id, trackIds.get(0));

        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }

    }

    @Test
    void removeTrackFromPlaylist() throws SQLException {
        // Arrange
        String expectedSQLQuery = "DELETE FROM playlist_track WHERE playlist_id = ? AND track_id = ?";
        int id = 1;

        // Instruct Mocks
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(expectedSQLQuery)).thenReturn(preparedStatement);

        // Act
        playlistTrackDAO.removeTrackFromPlaylist(id, id);

        // Assert
        verify(connection).prepareStatement(expectedSQLQuery);
        verify(preparedStatement).setInt(1, id);
        verify(preparedStatement).setInt(2, id);
        verify(preparedStatement).execute();
    }

    @Test
    void addTrackToPlaylist() throws SQLException {
        // Arrange
        String expectedSQLQuery = "INSERT INTO playlist_track (playlist_id, track_id) VALUES (?, ?)";
        int id = 1;

        // Instruct Mocks
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(expectedSQLQuery)).thenReturn(preparedStatement);

        // Act
        playlistTrackDAO.addTrackToPlaylist(id, id);

        // Assert
        verify(connection).prepareStatement(expectedSQLQuery);
        verify(preparedStatement).setInt(1, id);
        verify(preparedStatement).setInt(2, id);
        verify(preparedStatement).execute();
    }
}