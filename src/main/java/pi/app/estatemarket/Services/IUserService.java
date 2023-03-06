package pi.app.estatemarket.Services;

import pi.app.estatemarket.Entities.User;
import pi.app.estatemarket.dto.UserDTO;

import java.util.List;


public interface IUserService {
    List<UserDTO> getAllUsers();

    User createUser(User user);

    User updateUser( User user);

    void deleteUser(long id);
    User getUserById(long id);
}

