package han.oose.dea.persistence;

import han.oose.dea.domain.Track;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistTrackDAO implements IPlaylistTrackDAO {
    @Resource(name = "jdbc/spotitube")
    DataSource dataSource;

    // Queries
    private static final String FIND_TRACK_IDS_QUERY = "SELECT track_id FROM playlist_track WHERE playlist_id = ?";
    private static final String REMOVE_TRACK_FROM_PLAYLIST_QUERY = "DELETE FROM playlist_track WHERE playlist_id = ? AND track_id = ?";
    private static final String INSERT_TRACK_TO_PLAYLIST_QUERY = "INSERT INTO playlist_track (playlist_id, track_id) VALUES (?, ?)";

    @Override
    public List<Integer> findTrackIdsByPlaylistId(int playlistId) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_TRACK_IDS_QUERY);

            statement.setInt(1, playlistId);

            ResultSet resultSet = statement.executeQuery();

            List<Integer> trackIds = new ArrayList<>();
            while (resultSet.next()) {
                trackIds.add(resultSet.getInt("track_id"));
            }

            return trackIds;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void removeTrackFromPlaylist(int playlistId, int trackId) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(REMOVE_TRACK_FROM_PLAYLIST_QUERY);

            statement.setInt(1, playlistId);
            statement.setInt(2, trackId);

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void addTrackToPlaylist(int playlistId, int trackId) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_TRACK_TO_PLAYLIST_QUERY);

            statement.setInt(1, playlistId);
            statement.setInt(2, trackId);

            statement.execute();
        } catch (SQLException e) {
//            e.printStackTrace();
        }
    }
}

