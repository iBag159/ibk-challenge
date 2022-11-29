package ibk.ibag.challenge.dao.repository;

import ibk.ibag.challenge.dao.entity.UserEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void WhenFindById_thenReturnUser() {
        UserEntity userEntity = new UserEntity("n9", "Gaby", "Alvarez", "1", "99999999");
        userRepository.save(userEntity);
        Assertions.assertThat(userRepository.findById("u1")).isNotEmpty(); //initialized
        Assertions.assertThat(userRepository.findById("n9")).isNotEmpty().hasValue(userEntity);
    }
}