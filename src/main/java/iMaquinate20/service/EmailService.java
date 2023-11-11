package iMaquinate20.service;

import iMaquinate20.domain.EmailDTO;
import iMaquinate20.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("mail")
public class EmailService implements IService<EmailDTO>{



    @Value("${email.sender}")
    private String emailUser;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public List<EmailDTO> getAll() {
        return null;
    }

    @Override
    public Optional<EmailDTO> getById(Integer id) {
        return Optional.empty();
    }

    @Override
    public EmailDTO create(EmailDTO emailDTO) {
        return null;
    }

    @Override
    public EmailDTO update(Integer id, EmailDTO emailDTO) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Product> getProductsByCategory(int id) {
        return null;
    }

    @Override
    public void sendEmail(String[] toUser, String subject, String message) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(emailUser);
        mailMessage.setTo(toUser);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        mailSender.send(mailMessage);
    }



}