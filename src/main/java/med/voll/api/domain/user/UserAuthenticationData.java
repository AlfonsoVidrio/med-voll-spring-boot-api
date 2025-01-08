package med.voll.api.domain.user;

public record UserAuthenticationData(
        String login,
        String key
) {
}
