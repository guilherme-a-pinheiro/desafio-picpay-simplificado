package guilherme.pinheiro.picpaysimplificado.services;

import guilherme.pinheiro.picpaysimplificado.domain.user.User;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    public String sendNotification(User user, String msg){
        String email = user.getEmail();

        return email + " " + msg;
    }
}
