package guilherme.pinheiro.picpaysimplificado.domain.user;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String document;
    @Column(unique = true)
    private String email;
    private String password;
    private UserType userType;
    private BigDecimal balance;

    public User(UserDTO obj){
        this.name = obj.name();
        this.document = obj.document();
        this.email = obj.email();
        this.password = obj.password();
        this.userType = obj.userType();
        this.balance = obj.balance();
    }
}
