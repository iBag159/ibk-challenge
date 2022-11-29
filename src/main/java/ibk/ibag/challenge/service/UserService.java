package ibk.ibag.challenge.service;

import ibk.ibag.challenge.model.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public Optional<UserDTO> getUserByCode(String code);
    public UserDTO saveUser(UserDTO userDTO);
    public UserDTO updateUser(UserDTO userDTO);
    public List<UserDTO> listAllUsers();
    public List<UserDTO> listUsersByName(String name);
    public void deleteUser(String code);
}
