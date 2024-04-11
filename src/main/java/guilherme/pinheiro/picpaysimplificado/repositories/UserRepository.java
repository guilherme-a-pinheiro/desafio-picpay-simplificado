package guilherme.pinheiro.picpaysimplificado.repositories;

import guilherme.pinheiro.picpaysimplificado.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
