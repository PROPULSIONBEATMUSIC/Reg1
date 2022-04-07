package program;

public interface AuthenticationStrategy {
    User authenticate(AuthenticationRequest request);
}
