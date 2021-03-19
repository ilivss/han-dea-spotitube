package han.oose.dea.rest;

import han.oose.dea.domain.Playlist;
import han.oose.dea.domain.Track;
import han.oose.dea.exceptions.PersistenceException;
import han.oose.dea.exceptions.TestException;
import han.oose.dea.rest.dto.PlaylistDTO;
import han.oose.dea.rest.dto.PlaylistsDTO;
import han.oose.dea.rest.dto.TrackDTO;
import han.oose.dea.rest.dto.TracksDTO;
import han.oose.dea.rest.mappers.PlaylistRestMapper;
import han.oose.dea.rest.mappers.PlaylistsRestMapper;
import han.oose.dea.rest.mappers.TrackRestMapper;
import han.oose.dea.services.PlaylistService;
import han.oose.dea.services.PlaylistTrackService;
import han.oose.dea.services.TrackService;
import han.oose.dea.services.helpers.PlaylistHelper;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Path("/playlists")
public class PlaylistEndpoint {
    @Inject
    private PlaylistService playlistService;

    @Inject
    private TrackService trackService;

    @Inject
    private PlaylistTrackService playlistTrackService;

    @Inject
    private PlaylistRestMapper playlistRestMapper;

    @Inject
    private PlaylistsRestMapper playlistsRestMapper;

    @Inject
    private TrackRestMapper trackRestMapper;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlaylists(@QueryParam("token") String token) throws PersistenceException {
        // Check token
        // TODO: Add auth check!
        String username = "john";

        List<Playlist> playlists = playlistService.getAll();

        List<PlaylistDTO> playlistDTOs = playlists.stream().map(playlist -> playlistRestMapper.toDTO(playlist, username)).collect(Collectors.toList());
        PlaylistsDTO playlistsDTO = playlistsRestMapper.toDTO(playlistDTOs);

        return Response.ok(playlistsDTO).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlaylist(@QueryParam("token") String token, PlaylistDTO playlistDTO) throws PersistenceException {
        // Check token
        // TODO: Add auth check!
        String username = "john";

        playlistService.addPlaylist(playlistRestMapper.toDomainObject(playlistDTO, username));

        return this.getAllPlaylists(token);
    }

    @DELETE
    @Path("{playlistId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@QueryParam("token") String token, @PathParam("playlistId") int playlistId) throws PersistenceException {
        // Check token
        // TODO: Add auth check!
        String username = "john";

        playlistService.deletePlaylist(playlistId);

        return this.getAllPlaylists(token);
    }

    @PUT
    @Path("{playlistId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editPlaylist(@QueryParam("token") String token, @PathParam("playlistId") int playlistId, PlaylistDTO playlistDTO) throws PersistenceException {
        // Check token
        // TODO: Add auth check!
        String username = "john";

        playlistService.editPlaylist(playlistId, playlistRestMapper.toDomainObject(playlistDTO, username));

        return this.getAllPlaylists(token);
    }

    // Playlists Tracks
    @GET
    @Path("{playlistId}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTracksInPlaylist(@QueryParam("token") String token, @PathParam("playlistId") int playlistId) throws PersistenceException {
        // Check token
        // TODO: Add auth check!
        String username = "john";

        List<Track> tracks = trackService.getInPlaylist(playlistId);

        List<TrackDTO> trackDTOs = tracks.stream().map(track -> trackRestMapper.toDTO(track)).collect(Collectors.toList());

        return Response.ok(new TracksDTO(trackDTOs)).build();
    }

    @DELETE
    @Path("{playlistId}/tracks/{trackId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeTrackFromPlaylist(@QueryParam("token") String token, @PathParam("playlistId") int playlistId, @PathParam("trackId") int trackId) throws PersistenceException {
        playlistTrackService.removeTrackFromPlaylist(playlistId, trackId);

        return this.getAllTracksInPlaylist(token,playlistId);
    }

    @POST
    @Path("{playlistId}/tracks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(@QueryParam("token") String token, @PathParam("playlistId") int playlistId, TrackDTO trackDTO) throws PersistenceException {
        // Add track to playlist
        playlistTrackService.addTrackToPlaylist(playlistId, trackDTO.id);

        // Set track Offline availability
        trackService.setTrackAvailability(trackDTO.id, trackDTO.offlineAvailable);

        return this.getAllTracksInPlaylist(token, playlistId);
    }
}
