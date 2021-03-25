package han.oose.dea.persistence.mappers;

import han.oose.dea.domain.Track;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrackSQLDataMapper implements IDataMapper<ResultSet, Track> {
    @Override
    public Track toDomainObject(ResultSet resultSet) {
        try {
            return new Track(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("performer"),
                    resultSet.getInt("duration"),
                    resultSet.getString("album"),
                    resultSet.getInt("play_count"),
                    resultSet.getString("publication_date"),
                    resultSet.getString("description"),
                    resultSet.getBoolean("offline_available")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
