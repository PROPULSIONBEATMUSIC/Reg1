package program;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private final Map<Long, User> users = new HashMap<>();

    private UserRepository() {}

    private static UserRepository INSTANCE = null;

    public static UserRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserRepository();
        }

        return INSTANCE;
    }

    public void save(Long id, User user) {
        users.put(id, user);
    }

    public boolean existsById(Long id) {
        return users.containsKey(id);
    }

    public Map<Long, User> findAll() {
        return new HashMap<>(users);
    }

    public void remove(Long id) {
        users.remove(id);
    }

    //npe produce
    public User findById(Long id) {
        return users.get(id);
    }
}
