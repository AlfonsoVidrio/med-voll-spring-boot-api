package med.voll.api.domain.users;

public record UserAuthenticationData(
        String login,
        String key
) {
}
