package ibk.ibag.challenge.service;

import ibk.ibag.challenge.dao.repository.UserRepository;
import ibk.ibag.challenge.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Qualifier("CLOUD")
public class UserServiceCloudImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public Optional<UserDTO> getUserByCode(String code) {
        System.out.println("Getting a user by its unique code...");
        UserDTO user1 = new UserDTO("u1", "Gaby", "Alvarez", "1", "12345678");
        UserDTO user2 = new UserDTO("u2", "Nathaly", "Sipiran", "1", "23456789");
        UserDTO user3 = new UserDTO("u3", "Raul", "Alvarez", "1", "01234567");
        List<UserDTO> users = Arrays.asList(user1, user2, user3);
        UserDTO user = users.stream().filter(u -> code.equals(u.getCode())).findAny().orElse(null);
        return Optional.ofNullable(user);
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public List<UserDTO> listAllUsers() {
        return null;
    }

    @Override
    public List<UserDTO> listUsersByName(String name) {
        return null;
    }

    @Override
    public void deleteUser(String code) {

    }

}
