package program;

public class Main {
    private static final UserService userService = UserService.getInstance();

    public static void main(String[] args) {
        User fadesml = new User("fadesml", "fadesml@ithub.ru", "qwerty123");
        User nikolai = new User("nikolai", "nikolai@ithub.ru", "bestPass123");
        User kirill = new User("kirill", "kirill@ithub.ru", "animePassword123");

        userService.register(fadesml);
        userService.register(nikolai);
        userService.register(kirill);

        userService.show();

        userService.deleteById(2L);
        userService.show();

        AuthenticationRequest request = new AuthenticationRequest("fadesml", "qwerty123");
        AuthenticationRequest badRequest = new AuthenticationRequest("unknown", "cringe");

        User authenticatedSuccessful = userService.authenticate(request);
        System.out.println(authenticatedSuccessful);

        userService.authenticate(badRequest);
    }
}