package nl.duckstudios.pintandpillage.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.duckstudios.pintandpillage.dao.UserDAO;
import nl.duckstudios.pintandpillage.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserDAO userDAO;

    public User getAuthenticatedUser(){
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDAO.findByEmail(email).get();
        return user;
    }
}
