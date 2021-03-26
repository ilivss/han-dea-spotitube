package han.oose.dea.rest;

import han.oose.dea.auth.Authenticated;
import han.oose.dea.domain.Track;
import han.oose.dea.exceptions.NoRecoursesFoundException;
import han.oose.dea.rest.dto.TrackDTO;
import han.oose.dea.exceptions.PersistenceException;
import han.oose.dea.rest.dto.TracksDTO;
import han.oose.dea.rest.mappers.TrackRestMapper;
import han.oose.dea.services.TrackService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/tracks")
public class TrackEndpoint {
    @Inject
    private TrackService trackService;

    @Inject
    private TrackRestMapper trackRestMapper;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticated
    public Response getNotInPlaylist(@QueryParam("forPlaylist") int playlistId) throws PersistenceException, NoRecoursesFoundException {
        // Get tracks
        List<Track> tracks;
        if (playlistId == 0) {
            tracks = trackService.getAll();
        } else {
            tracks = trackService.getNotInPlaylist(playlistId);
        }

        if (tracks == null || tracks.size() == 0) throw new NoRecoursesFoundException("Geen tracks gevonden!");

        // Map Track to Rest
        List<TrackDTO> trackDTOs = tracks.stream().map(track -> trackRestMapper.toDTO(track)).collect(Collectors.toList());
        return Response.ok(new TracksDTO(trackDTOs)).build();
    }
}
