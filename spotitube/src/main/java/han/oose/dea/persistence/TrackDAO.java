package han.oose.dea.persistence;

import han.oose.dea.persistence.mappers.TrackSQLDataMapper;
import han.oose.dea.domain.Track;
import han.oose.dea.exceptions.PersistenceException;

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
public class TrackDAO implements ITrackDAO {
    @Resource(name = "jdbc/spotitube")
    DataSource dataSource;

    @Inject
    private TrackSQLDataMapper trackSQLDataMapper;

    // Queries
    private static final String GET_TRACKS_QUERY = "SELECT * FROM track";
    private static final String SET_OFFLINE_AVAILABILITY_QUERY = "UPDATE track SET offline_available = ? WHERE id = ?";

    @Override
    public List<Track> findAll() throws PersistenceException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_TRACKS_QUERY);

            ResultSet resultSet = statement.executeQuery();

            List<Track> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(trackSQLDataMapper.toDomainObject(resultSet));
            }

            return result;
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    public void setTrackAvailability(int trackId, boolean isOfflineAvailable) throws PersistenceException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SET_OFFLINE_AVAILABILITY_QUERY);

            statement.setBoolean(1, isOfflineAvailable);
            statement.setInt(2, trackId);

            statement.execute();
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
