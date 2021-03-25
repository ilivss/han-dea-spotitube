package han.oose.dea.persistence.mappers;

import han.oose.dea.domain.Playlist;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaylistSQLDataMapper implements IDataMapper<ResultSet, Playlist> {
    @Override
    public Playlist toDomainObject(ResultSet resultSet) {
        try {
            return new Playlist(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("owner")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
