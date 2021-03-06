package be.g00glen00b.service.data;

import javax.persistence.PostPersist;
import be.g00glen00b.service.model.Registration;
import be.g00glen00b.service.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;

public class UserRegistrationListener {
    private Registration registration;

    public UserRegistrationListener() {
    }

    @PostPersist
    public void register(User user) {
        getRegistration().newUserUaaCompleted().send(MessageBuilder.withPayload(user.getEmail()).build());
    }

    private Registration getRegistration() {
        if (registration == null) {
            this.registration = BeanUtils.getBean(Registration.class);
        }
        return registration;
    }
}
