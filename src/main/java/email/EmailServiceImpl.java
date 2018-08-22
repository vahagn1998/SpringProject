package email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.FileInputStream;
import java.util.Objects;

@Service("emailServiceImpl")
public class EmailServiceImpl {

    private final JavaMailSender javaMailSender;
    private SimpleMailMessage template;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Autowired
    public void setTemplate(SimpleMailMessage template) {
        this.template = template;
    }

    public void sendSimpleEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }

    public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment, String filename) {
        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);
//            helper.addInline();
            FileSystemResource fileSystemResource = new FileSystemResource(new File(pathToAttachment));
            helper.addAttachment(filename, fileSystemResource);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendTemplateMessage(String to, String subject, Object... args) {
        String text = String.format(Objects.requireNonNull(template.getText()), args);
        sendSimpleEmail(to, subject, text);
    }

    public void sendMessageWithPreparator(String to, String subject, String text, String pathToAttachment, String filename) {
        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMultipart mimeMultipart = new MimeMultipart();
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setFileName(filename);
            mimeBodyPart.attachFile(pathToAttachment);
            mimeMultipart.addBodyPart(mimeBodyPart);
            mimeMessage.setText(text);
            mimeMessage.setSubject(subject);
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mimeMessage.setContent(mimeMultipart);
        };
        javaMailSender.send(preparator);
    }
}
