package han.oose.dea.rest;

import han.oose.dea.auth.Authenticated;
import han.oose.dea.domain.Playlist;
import han.oose.dea.domain.Track;
import han.oose.dea.domain.User;
import han.oose.dea.exceptions.PersistenceException;
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

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
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

    @Inject
    @Context HttpServletRequest servletRequest;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticated
    public Response getAllPlaylists() throws PersistenceException {
        User user = (User) servletRequest.getAttribute("currentUser");

        return this.getAll(user);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticated
    public Response addPlaylist(PlaylistDTO playlistDTO) throws PersistenceException {
        User user = (User) servletRequest.getAttribute("currentUser");

        playlistService.addPlaylist(playlistRestMapper.toDomainObject(playlistDTO, user.getUsername()));

        return this.getAll(user);
    }

    @DELETE
    @Path("{playlistId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticated
    public Response deletePlaylist(@PathParam("playlistId") int playlistId) throws PersistenceException {
        User user = (User) servletRequest.getAttribute("currentUser");

        playlistService.deletePlaylist(playlistId);

        return this.getAll(user);
    }

    @PUT
    @Path("{playlistId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticated
    public Response editPlaylist(@PathParam("playlistId") int playlistId, PlaylistDTO playlistDTO) throws PersistenceException {
        User user = (User) servletRequest.getAttribute("currentUser");

        playlistService.editPlaylist(playlistId, playlistRestMapper.toDomainObject(playlistDTO, user.getUsername()));

        return this.getAll(user);
    }

    // Playlists Tracks
    @GET
    @Path("{playlistId}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticated
    public Response getAllTracksInPlaylist(@PathParam("playlistId") int playlistId) throws PersistenceException {
        return this.getAllTracks(playlistId);
    }

    @DELETE
    @Path("{playlistId}/tracks/{trackId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticated
    public Response removeTrackFromPlaylist(@PathParam("playlistId") int playlistId, @PathParam("trackId") int trackId) throws PersistenceException {
        playlistTrackService.removeTrackFromPlaylist(playlistId, trackId);

        return this.getAllTracks(playlistId);
    }

    @POST
    @Path("{playlistId}/tracks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticated
    public Response addTrackToPlaylist(@PathParam("playlistId") int playlistId, TrackDTO trackDTO) throws PersistenceException {
        // Add track to playlist
        playlistTrackService.addTrackToPlaylist(playlistId, trackDTO.id);

        // Set track Offline availability
        trackService.setTrackAvailability(trackDTO.id, trackDTO.offlineAvailable);

        return this.getAllTracks(playlistId);
    }

    // Helper functions
    private Response getAll(User user) throws PersistenceException {
        List<Playlist> playlists = playlistService.getAll();
        List<PlaylistDTO> playlistDTOs = playlists.stream().map(playlist -> playlistRestMapper.toDTO(playlist, user.getUsername())).collect(Collectors.toList());
        PlaylistsDTO playlistsDTO = playlistsRestMapper.toDTO(playlistDTOs);

        return Response.ok(playlistsDTO).build();
    }

    private Response getAllTracks(int playlistId) throws PersistenceException {
        List<Track> tracks = trackService.getInPlaylist(playlistId);
        List<TrackDTO> trackDTOs = tracks.stream().map(track -> trackRestMapper.toDTO(track)).collect(Collectors.toList());

        return Response.ok(new TracksDTO(trackDTOs)).build();
    }
}
