package han.oose.dea.rest.mappers;

import han.oose.dea.domain.User;
import han.oose.dea.rest.dto.UserDTO;

public class UserRestMapper implements IRestMapper<UserDTO, User> {
    @Override
    public UserDTO toDTO(User user) {
        return new UserDTO(user.getUsername(), user.getPassword());
    }

    public User toDomainObject(UserDTO userDTO) {
        return new User(userDTO.user, userDTO.password);
    }
}
