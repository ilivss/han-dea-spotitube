package han.oose.dea.persistence;

import javax.sql.DataSource;
import java.util.List;

public interface IPlaylistTrackDAO {
    List<Integer> findTrackIdsByPlaylistId(int playlistId);

    void removeTrackFromPlaylist(int playlistId, int trackId);

    void addTrackToPlaylist(int playlistId, int trackId);

    void setDataSource(DataSource dataSource);
}
