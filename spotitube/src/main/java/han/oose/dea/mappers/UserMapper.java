package han.oose.dea.mappers;

import han.oose.dea.domain.User;
import han.oose.dea.dto.UserDTO;

public class UserMapper implements IMapper<UserDTO, User> {
    @Override
    public UserDTO toDTO(User user) {
        return null;
    }

    @Override
    public User fromDTO(UserDTO userDTO) {
        return new User(userDTO.user, userDTO.password);
    }
}
