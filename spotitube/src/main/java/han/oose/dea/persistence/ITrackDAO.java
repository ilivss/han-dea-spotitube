package han.oose.dea.persistence;

import han.oose.dea.domain.Track;
import han.oose.dea.exceptions.PersistenceException;

import javax.sql.DataSource;
import java.util.List;

public interface ITrackDAO {
    List<Track> findAll() throws PersistenceException;

    void setTrackAvailability(int trackId, boolean isOfflineAvailable) throws PersistenceException;

    void setDataSource(DataSource dataSource);
}
