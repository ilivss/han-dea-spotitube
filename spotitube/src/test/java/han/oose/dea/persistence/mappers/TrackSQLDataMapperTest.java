package han.oose.dea.persistence.mappers;

import han.oose.dea.domain.Playlist;
import han.oose.dea.domain.Track;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class TrackSQLDataMapperTest {

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private TrackSQLDataMapper trackSQLDataMapper;

    @BeforeEach
    void setUp() {
        trackSQLDataMapper = new TrackSQLDataMapper();
        openMocks(this);
    }

    @Test
    void toDomainObjectSuccess() {
        try {
            // Arrange
            int expectedId = 1;
            String expectedTitle = "title";
            String expectedPerformer = "performer";
            int expectedDuration = 1;
            String expectedAlbum = "album";
            int expectedPlayCount = 1;
            String expectedPublicationDate = "01-01-2020";
            String expectedDescription = "description";
            Boolean expectedOfflineAvailable = true;

            // Instruct Mocks
            when(resultSet.getInt("id")).thenReturn(expectedId);
            when(resultSet.getString("title")).thenReturn(expectedTitle);
            when(resultSet.getString("performer")).thenReturn(expectedPerformer);
            when(resultSet.getInt("duration")).thenReturn(expectedDuration);
            when(resultSet.getString("album")).thenReturn(expectedAlbum);
            when(resultSet.getInt("play_count")).thenReturn(expectedPlayCount);
            when(resultSet.getString("publication_date")).thenReturn(expectedPublicationDate);
            when(resultSet.getString("description")).thenReturn(expectedDescription);
            when(resultSet.getBoolean("offline_available")).thenReturn(expectedOfflineAvailable);

            // Act
            Track track = trackSQLDataMapper.toDomainObject(resultSet);

            // Assert
            assertEquals(expectedId, track.getId());
            assertEquals(expectedTitle, track.getTitle());
            assertEquals(expectedPerformer, track.getPerformer());
            assertEquals(expectedDuration, track.getDuration());
            assertEquals(expectedAlbum, track.getAlbum());
            assertEquals(expectedPlayCount, track.getPlayCount());
            assertEquals(expectedPublicationDate, track.getPublicationDate());
            assertEquals(expectedDescription, track.getDescription());
            assertEquals(expectedOfflineAvailable, track.getOfflineAvailable());
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
        when(resultSet.getBoolean(any())).thenThrow(new SQLException());
        // Act
        Track track = trackSQLDataMapper.toDomainObject(resultSet);

        // Assert
        assertNull(track);
    }
}