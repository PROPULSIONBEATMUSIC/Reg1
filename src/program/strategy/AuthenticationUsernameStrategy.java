package program.strategy;

import program.AuthenticationRequest;
import program.AuthenticationStrategy;
import program.User;
import program.UserRepository;

import java.util.Map;

public class AuthenticationUsernameStrategy implements AuthenticationStrategy {
    private AuthenticationUsernameStrategy() {}

    public static AuthenticationUsernameStrategy INSTANCE = null;

    public static AuthenticationUsernameStrategy getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AuthenticationUsernameStrategy();
        }

        return INSTANCE;
    }

    private final UserRepository repository = UserRepository.getInstance();

    @Override
    public User authenticate(AuthenticationRequest request) {
        for (Map.Entry<Long, User> entry: repository.findAll().entrySet()) {
            if (entry.getValue().getUsername().equals(request.getLogin()) && entry.getValue().getPassword().equals(request.getPassword())) {
                return entry.getValue();
            }
        }

        throw new IllegalArgumentException("Bad credentials!");
    }
}
