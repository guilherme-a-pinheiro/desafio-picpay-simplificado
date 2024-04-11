package guilherme.pinheiro.picpaysimplificado.repositories;

import guilherme.pinheiro.picpaysimplificado.domain.transaction.Transaction;
import guilherme.pinheiro.picpaysimplificado.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
