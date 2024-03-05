package taus.test.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import taus.test.entities.Task;
import taus.test.entities.User;
import taus.test.exceptions.UserAlreadyExistsException;
import taus.test.exceptions.UserNotFoundException;
import taus.test.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User getUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()){
            return userOptional.get();
        }

        throw new UserNotFoundException();
    }

    public void createUser(User user) {
        Optional<User> u = userRepository.findByUsername(user.getUsername());
        if (u.isPresent()) {
            throw new UserAlreadyExistsException(user.getUsername());
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword("{bcrypt}" + passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
