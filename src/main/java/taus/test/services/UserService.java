package taus.test.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taus.test.entities.User;
import taus.test.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
