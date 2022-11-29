package ibk.ibag.challenge.service;

import ibk.ibag.challenge.dao.entity.UserEntity;
import ibk.ibag.challenge.dao.repository.UserRepository;
import ibk.ibag.challenge.mapper.UserMapper;
import ibk.ibag.challenge.model.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

@Slf4j
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    private static List<UserDTO> userDTOS;
    private static List<UserEntity> userEntities;
    private static final String QUERY_CODE = "u1";

    @BeforeAll
    static void setup() {
        UserDTO user1 = new UserDTO("u1", "Gaby", "Alvarez", "1", "12345678");
        UserDTO user2 = new UserDTO("u2", "Nathaly", "Sipiran", "1", "23456789");
        UserDTO user3 = new UserDTO("u3", "Raul", "Alvarez", "1", "01234567");
        userDTOS = Arrays.asList(user1, user2, user3);
        UserEntity entity1 = new UserEntity("u1", "Gaby", "Alvarez", "1", "12345678");
        UserEntity entity2 = new UserEntity("u2", "Nathaly", "Sipiran", "1", "23456789");
        UserEntity entity3 = new UserEntity("u3", "Raul", "Alvarez", "1", "01234567");
        userEntities = Arrays.asList(entity1, entity2, entity3);

        log.info("@BeforeAll - Executes once before all test methods in this class");
    }
    @BeforeEach
    public void setUpBefore() {
        MockitoAnnotations.initMocks(this);
        log.info("Build Mock for userRepository");
        final Optional<UserEntity> userEntity = userEntities.stream().filter(ue -> ue.getCode().equals(QUERY_CODE)).findAny();
        final Optional<UserDTO> userDTO = userDTOS.stream().filter(ue -> ue.getCode().equals(QUERY_CODE)).findAny();
        Mockito.when(userRepository.findById(QUERY_CODE)).thenReturn(userEntity);
        Mockito.when(userMapper.getUserDto(userEntity.get())).thenReturn(userDTO.get());
    }
    @Test
    @DisplayName("Test retrieve user found by code")
    public void test_when_searchUser_then_returnUser() {
        log.info("Searching a user by code "+ QUERY_CODE);
        Optional<UserDTO> user = userServiceImpl.getUserByCode(QUERY_CODE);
        verify(userRepository, atLeast(1)).findById(QUERY_CODE);
        assertEquals(user.get().getCode(), QUERY_CODE);
    }
    @AfterEach
    void tearDown() {
        log.info("@AfterEach - Executes after each test method");
    }
    @AfterAll
    static void done() {
        log.info("@AfterAll - executed after all test methods");
    }
}
