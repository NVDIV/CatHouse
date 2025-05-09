import org.example.models.User;
import org.example.repositories.IUserRepository;
import org.example.services.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthServiceTest {

    private IUserRepository userRepository;
    private AuthService authService;

    @BeforeEach
    public void setup() {
        userRepository = mock(IUserRepository.class);
        authService = new AuthService(userRepository);
    }

    @Test
    public void testRegister_Success() {
        String login = "newuser";
        String password = "securePass123";

        when(userRepository.findByLogin(login)).thenReturn(Optional.empty());

        Optional<User> registered = authService.register(login, password);

        assertTrue(registered.isPresent());
        assertEquals(login, registered.get().getLogin());
        assertTrue(BCrypt.checkpw(password, registered.get().getPasswordHash()));
        verify(userRepository).save(any(User.class));
    }

    @Test
    public void testRegister_LoginAlreadyTaken() {
        String login = "existingUser";
        when(userRepository.findByLogin(login)).thenReturn(Optional.of(new User()));

        Optional<User> result = authService.register(login, "anyPassword");

        assertTrue(result.isEmpty());
        verify(userRepository, never()).save(any());
    }

    @Test
    public void testLogin_Success() {
        String login = "validUser";
        String password = "correctPassword";
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        User user = new User(UUID.randomUUID().toString(), login, hashedPassword, new ArrayList<>());

        when(userRepository.findByLogin(login)).thenReturn(Optional.of(user));

        Optional<User> loggedIn = authService.login(login, password);

        assertTrue(loggedIn.isPresent());
        assertEquals(login, loggedIn.get().getLogin());
    }

    @Test
    public void testLogin_InvalidPassword() {
        String login = "user";
        String correctPassword = "correct";
        String wrongPassword = "wrong";
        String hashedPassword = BCrypt.hashpw(correctPassword, BCrypt.gensalt());
        User user = new User(UUID.randomUUID().toString(), login, hashedPassword, new ArrayList<>());

        when(userRepository.findByLogin(login)).thenReturn(Optional.of(user));

        Optional<User> result = authService.login(login, wrongPassword);

        assertTrue(result.isEmpty());
    }

    @Test
    public void testLogin_NonexistentUser() {
        when(userRepository.findByLogin("noUser")).thenReturn(Optional.empty());

        Optional<User> result = authService.login("noUser", "anyPassword");

        assertTrue(result.isEmpty());
    }
}
