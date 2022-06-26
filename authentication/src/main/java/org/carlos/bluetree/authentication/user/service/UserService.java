package org.carlos.bluetree.authentication.user.service;

import lombok.AllArgsConstructor;
import org.carlos.bluetree.authentication.user.entity.User;
import org.carlos.bluetree.authentication.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public Optional<User> findUserByUsername(String username){
        return userRepository.findUserByUsername(username);
    }

    public boolean userExistsByUsername(String username){
        return userRepository.existsUserByUsername(username);
    }

    public boolean userExistsByEmail(String email){
        return userRepository.existsUserByEmail(email);
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

}
