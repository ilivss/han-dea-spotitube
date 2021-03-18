package han.oose.dea.services;

import han.oose.dea.dao.ITokenDAO;
import han.oose.dea.dao.IUserDAO;
import han.oose.dea.domain.Token;
import han.oose.dea.domain.User;
import han.oose.dea.exceptions.PersistenceException;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Default
public class AuthService {
    @Inject
    private IUserDAO userDAO;

    @Inject
    private ITokenDAO tokenDAO;

    public Token login(User potentialUser) throws PersistenceException {
        // Retrieve user from db
        User user = userDAO.getUser(potentialUser);

        // if user does not exist
        if (user == null) return null;

        // Generate token and save to database;
        String username = user.getUsername();
        Token token = tokenDAO.generateAndSaveToken(user.getUsername());

        return token;
    }

}
