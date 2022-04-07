package program.strategy;

import program.AuthenticationRequest;
import program.AuthenticationStrategy;
import program.User;
import program.UserRepository;

import java.util.Map;

//AuthenticationUsernameStrategy
public class AuthenticationEmailStrategy implements AuthenticationStrategy {
    private AuthenticationEmailStrategy() {}

    public static AuthenticationEmailStrategy INSTANCE = null;

    public static AuthenticationEmailStrategy getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AuthenticationEmailStrategy();
        }

        return INSTANCE;
    }

    private final UserRepository repository = UserRepository.getInstance();

    @Override
    public User authenticate(AuthenticationRequest request) {
        for (Map.Entry<Long, User> entry: repository.findAll().entrySet()) {
            if (entry.getValue().getEmail().equals(request.getLogin()) && entry.getValue().getPassword().equals(request.getPassword())) {
                return entry.getValue();
            }
        }

        throw new IllegalArgumentException("Bad credentials!");
    }
}
