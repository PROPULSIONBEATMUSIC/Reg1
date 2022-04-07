package program;

import program.strategy.AuthenticationEmailStrategy;
import program.strategy.AuthenticationUsernameStrategy;

import java.util.Map;

public class UserService {
    private static UserService INSTANCE;
    private final AuthenticationEmailStrategy authenticationEmailStrategy = AuthenticationEmailStrategy.getInstance();
    private final AuthenticationUsernameStrategy authenticationUsernameStrategy = AuthenticationUsernameStrategy.getInstance();
    private final UserRepository repository = UserRepository.getInstance();

    private UserService() {
    }

    public static UserService getInstance(){
        if(INSTANCE == null){
            INSTANCE = new UserService();
        }
        return INSTANCE;
    }

    public void register(User user) {
        for (Map.Entry<Long, User> entry : repository.findAll().entrySet()) {
            if (entry.getValue().getEmail().equals(user.getEmail()) || entry.getValue().getUsername().equals(user.getUsername())) {
                throw new IllegalArgumentException("User already exists with this username or email!");
            }
        }

        this.repository.save(nextIdentifier(), user);
        System.out.println("User successfully register!");
    }

    public User authenticate(AuthenticationRequest request) {
        if (request.isEmail()) {
            return authenticationEmailStrategy.authenticate(request);
        }

        return authenticationUsernameStrategy.authenticate(request);
    }

    public void show() {
        System.out.println(repository.findAll());
    }

    public void deleteById(Long id) {
        if (repository.existsById(id)) {
            repository.remove(id);
            System.out.println("User successfully deleted by id: " + id);
            return;
        }

        throw new IllegalArgumentException("User not found with id: " + id);
    }

    private Long nextIdentifier() {
        Long i = 0L;
        for (Map.Entry<Long, User> entry: this.repository.findAll().entrySet()) {
            if (entry.getKey() > i) {
                i = entry.getKey();
            }
        }

        return ++i;
    }
}