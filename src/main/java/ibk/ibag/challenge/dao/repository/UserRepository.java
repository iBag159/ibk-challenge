package ibk.ibag.challenge.dao.repository;

import ibk.ibag.challenge.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    public List<UserEntity> findByNameContaining(String name);

    @Query(value="SELECT * FROM user WHERE name LIKE '%Gaby%' LIMIT 2", nativeQuery = true)
    public List<UserEntity> findGabys(int limit);
}
