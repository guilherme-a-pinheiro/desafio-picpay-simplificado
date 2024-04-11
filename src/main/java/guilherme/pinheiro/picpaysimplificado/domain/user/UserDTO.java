package guilherme.pinheiro.picpaysimplificado.domain.user;

import jakarta.persistence.Column;

import java.math.BigDecimal;

public record UserDTO(
        String name,
        String document,
        String email,
        String password,
        UserType userType,
        BigDecimal balance
) {

}
