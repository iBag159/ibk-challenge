package ibk.ibag.challenge.mapper;

import ibk.ibag.challenge.dao.entity.UserEntity;
import ibk.ibag.challenge.model.UserDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    public UserDTO getUserDto(UserEntity userEntity);
    public UserEntity getUserEntity(UserDTO userDTO);
    public List<UserDTO> getUserDtos(List<UserEntity> userEntities);
}
