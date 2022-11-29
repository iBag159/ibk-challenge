package ibk.ibag.challenge.service;

import ibk.ibag.challenge.dao.entity.UserEntity;
import ibk.ibag.challenge.dao.repository.UserRepository;
import ibk.ibag.challenge.mapper.UserMapper;
import ibk.ibag.challenge.model.UserDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Primary
@Service
@Qualifier("BD")
public class UserServiceImpl implements UserService{

    private UserMapper userMapper;
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Optional<UserDTO> getUserByCode(String code) {
        Optional<UserEntity> optUserEntity = userRepository.findById(code);
        UserEntity userEntity = optUserEntity.get();
        UserDTO userDTO = userMapper.getUserDto(userEntity);
        return Optional.of(userDTO);
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        UserEntity userEntity = userMapper.getUserEntity(userDTO);
        UserEntity newUser = userRepository.save(userEntity);
        UserDTO newUserDTO = userMapper.getUserDto(newUser);
        return newUserDTO;
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(userDTO.getCode());
        UserEntity userEntity = optionalUserEntity.get();
        if(userEntity == null) {
            return null;
        }
        UserEntity userEntityUpdated = userRepository.save(userMapper.getUserEntity(userDTO));
        UserDTO userDTOUpdated = userMapper.getUserDto(userEntityUpdated);
        return userDTOUpdated;
    }

    @Override
    public List<UserDTO> listAllUsers() {
        return null;
    }

    @Override
    public List<UserDTO> listUsersByName(String name) {
        List<UserEntity> userEntities = userRepository.findByNameContaining(name);
        List<UserDTO> userDTOS = userMapper.getUserDtos(userEntities);
        return userDTOS;
    }

    @Override
    public void deleteUser(String code) {
        userRepository.deleteById(code);
    }

}
