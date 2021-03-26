package han.oose.dea.rest.mappers;

import han.oose.dea.domain.Track;
import han.oose.dea.rest.dto.TrackDTO;

public class TrackRestMapper implements IRestMapper<TrackDTO, Track> {
    @Override
    public TrackDTO toDTO(Track track) {
        return new TrackDTO(
                track.getId(),
                track.getTitle(),
                track.getPerformer(),
                track.getDuration(),
                track.getAlbum(),
                track.getPlayCount(),
                track.getPublicationDate(),
                track.getDescription(),
                track.getOfflineAvailable()
        );
    }
}
