package han.oose.dea.rest;

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
import han.oose.dea.services.TrackService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class PlaylistEndpointTest {
    @Mock
    private PlaylistService playlistService;

    @Mock
    private TrackService trackService;

    @Mock
    private PlaylistRestMapper playlistRestMapper;

    @Mock
    private PlaylistsRestMapper playlistsRestMapper;

    @Mock
    private TrackRestMapper trackRestMapper;

    @Mock
    private HttpServletRequest servletRequest;

    @InjectMocks
    private PlaylistEndpoint playlistEndpoint;

    private PlaylistsDTO playlistsDTO;
    private PlaylistDTO playlistDTO;

    @BeforeEach
    public void setup() throws PersistenceException {
        this.playlistEndpoint = new PlaylistEndpoint();
        openMocks(this);

        User user = new User();
        when(servletRequest.getAttribute("currentUser")).thenReturn(user);

        List<Playlist> playlists = new ArrayList<>();
        Playlist playlist = new Playlist();
        playlistDTO = new PlaylistDTO();
        playlistsDTO = new PlaylistsDTO();
        String username = "test";

        // Mock getAll()
        when(playlistRestMapper.toDTO(playlist, username)).thenReturn(playlistDTO);
        when(playlistsRestMapper.toDTO(any())).thenReturn(playlistsDTO);
        when(playlistService.getAll()).thenReturn(playlists);

        // Mock getAllTracks
        int id = 1;
        var tracks = new ArrayList<Track>();
        var track = new Track();
        var trackDTO = new TrackDTO();
        when(trackService.getInPlaylist(id)).thenReturn(tracks);
        when(trackRestMapper.toDTO(track)).thenReturn(trackDTO);
    }

    @Test
    public void getAllPlaylists() throws PersistenceException {
        // Arrange

        // Act
        Response response = playlistEndpoint.getAllPlaylists();

        // Assert
        assertEquals(Response.Status.OK, response.getStatusInfo());
        assertEquals(playlistsDTO, response.getEntity());
    }

    @Test
    public void addPlaylist() throws PersistenceException {
        // Arrange

        // Act
        Response response = playlistEndpoint.addPlaylist(playlistDTO);

        // Assert
        assertEquals(Response.Status.OK, response.getStatusInfo());
        assertEquals(playlistsDTO, response.getEntity());
    }

    @Test
    public void deletePlaylist() throws PersistenceException {
        // Arrange
        int id = 1;

        // Act
        Response response = playlistEndpoint.deletePlaylist(id);

        // Assert
        assertEquals(Response.Status.OK, response.getStatusInfo());
        assertEquals(playlistsDTO, response.getEntity());
    }

    @Test
    public void editPlaylist() throws PersistenceException {
        // Arrange
        int id = 1;

        // Act
        Response response = playlistEndpoint.editPlaylist(id, playlistDTO);

        // Assert
        assertEquals(Response.Status.OK, response.getStatusInfo());
        assertEquals(playlistsDTO, response.getEntity());
    }

    @Test
    public  void getAllTracksInPlaylist() {
        // Arrange

        // Act
        Response response = new Play

        // Assert

    }
}