package han.oose.dea.persistence.mappers;

import han.oose.dea.domain.Playlist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class PlaylistSQLDataMapperTest {
    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private PlaylistSQLDataMapper playlistSQLDataMapper;

    @BeforeEach
    void setUp() {
        playlistSQLDataMapper = new PlaylistSQLDataMapper();
        openMocks(this);
    }

    @Test
    void toDomainObjectSuccess() {
        try {
            // Arrange
            int expectedId = 1;
            String expectedName = "name";
            String expectedOwner = "owner";

            // Instruct Mocks
            when(resultSet.getInt("id")).thenReturn(expectedId);
            when(resultSet.getString("name")).thenReturn(expectedName);
            when(resultSet.getString("owner")).thenReturn(expectedOwner);

            // Act
            Playlist playlist = playlistSQLDataMapper.toDomainObject(resultSet);

            // Assert
            assertEquals(expectedId, playlist.getId());
            assertEquals(expectedName, playlist.getName());
            assertEquals(expectedOwner, playlist.getOwner());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void toDomainObjectFailure() throws SQLException {
        // Arrange

        // Instruct Mocks
        when(resultSet.getInt(any())).thenThrow(new SQLException());
        when(resultSet.getString(any())).thenThrow(new SQLException());

        // Act
        Playlist playlist = playlistSQLDataMapper.toDomainObject(resultSet);

        // Assert
        assertNull(playlist);
    }
}