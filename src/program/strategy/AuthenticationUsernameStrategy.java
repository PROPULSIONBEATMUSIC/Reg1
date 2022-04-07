package program.strategy;

import program.AuthenticationRequest;
import program.AuthenticationStrategy;
import program.User;
import program.UserRepository;
import program.config.AppConfig;

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
    private final AppConfig config = AppConfig.getInstance();

    @Override
    public User authenticate(AuthenticationRequest request) {
        for (Map.Entry<Long, User> entry: repository.findAll().entrySet()) {
            if (entry.getValue().getUsername().equals(request.getLogin())) {
                if (config.getPasswordEncoder().matches(request.getPassword(), entry.getValue().getPassword())) {
                    return entry.getValue();
                }
            }
        }

        throw new IllegalArgumentException("Bad credentials!");
    }
}
