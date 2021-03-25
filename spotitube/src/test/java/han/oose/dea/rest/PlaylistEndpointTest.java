package han.oose.dea.rest;

import han.oose.dea.domain.Playlist;
import han.oose.dea.domain.User;
import han.oose.dea.exceptions.PersistenceException;
import han.oose.dea.rest.dto.PlaylistDTO;
import han.oose.dea.rest.dto.PlaylistsDTO;
import han.oose.dea.rest.mappers.PlaylistRestMapper;
import han.oose.dea.rest.mappers.PlaylistsRestMapper;
import han.oose.dea.services.PlaylistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class PlaylistEndpointTest {
    @Mock
    private PlaylistService playlistService;

    @Mock
    private PlaylistRestMapper  playlistRestMapper;

    @Mock
    private PlaylistsRestMapper playlistsRestMapper;

    @Mock
    private HttpServletRequest servletRequest;


    @InjectMocks
    private PlaylistEndpoint playlistEndpoint;

    @BeforeEach
    public void setup() {
        this.playlistEndpoint= new PlaylistEndpoint();
        openMocks(this);

        User user = new User();
        when(servletRequest.getAttribute("currentUser")).thenReturn(user);
    }

    @Test
    void getAllPlaylistsSuccess() throws PersistenceException {
        // Arrange
        List<Playlist> playlists  = new ArrayList<>();
        PlaylistsDTO playlistsDTO = new PlaylistsDTO();

        when(playlistService.getAll()).thenReturn(playlists);
        when(playlistsRestMapper.toDTO(any())).thenReturn(playlistsDTO);

        // Act
        Response response = playlistEndpoint.getAllPlaylists();

        // Assert
        assertEquals(Response.Status.OK, response.getStatusInfo());
        assertEquals(playlistsDTO, response.getEntity());
    }
}