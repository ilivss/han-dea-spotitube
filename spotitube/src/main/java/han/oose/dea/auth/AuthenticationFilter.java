package han.oose.dea.auth;

import han.oose.dea.domain.Token;
import han.oose.dea.domain.User;
import han.oose.dea.persistence.ITokenDAO;
import han.oose.dea.persistence.IUserDAO;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Date;

@Provider
@Authenticated
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
    @Inject
    private ITokenDAO tokenDAO;

    @Inject
    private IUserDAO userDAO;


    @Override
    public void filter(ContainerRequestContext containerRequestContext) {
        String tokenString = containerRequestContext.getUriInfo().getQueryParameters().getFirst("token");
        if (tokenString == null || tokenString.length() == 0) {
            unAuthorizeRequest(containerRequestContext);
            return;
        }

        Token token = tokenDAO.getToken(tokenString);
        if (token == null) {
            unAuthorizeRequest(containerRequestContext);
            return;
        }

        if (false) { // FIXME: Check expiry date. Not part of this exercise...
            unAuthorizeRequest(containerRequestContext);
            return;
        }

        User currentUser = userDAO.getUser(token.getUsername());
        if (currentUser == null) {
            unAuthorizeRequest(containerRequestContext);
            return;
        }

        containerRequestContext.setProperty("currentUser", currentUser);
    }

    private void unAuthorizeRequest(ContainerRequestContext containerRequestContext) {
        Response response = Response.status(403).build();
        containerRequestContext.abortWith(response);
    }
}
