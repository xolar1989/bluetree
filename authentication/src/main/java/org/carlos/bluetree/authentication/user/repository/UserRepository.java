package org.carlos.bluetree.authentication.user.repository;

import org.carlos.bluetree.authentication.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByEmail(String username);

    boolean existsUserByUsername(String username);

    boolean existsUserByEmail(String email);

}
