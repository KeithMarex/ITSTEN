package nl.duckstudios.pintandpillage.dao;

import lombok.RequiredArgsConstructor;
import nl.duckstudios.pintandpillage.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDAO {

    private final UserRepository userRepository;

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User save(User user) {
        return this.userRepository.save(user);
    }

    public Optional<User> findUsernameById(long userId) {
        return this.userRepository.findById(userId);
    }
}
