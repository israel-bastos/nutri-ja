package br.com.grupo.nutrija.application.controller.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Controller
@RequestMapping("/emails")
public class SendEmailController {

    @Autowired
    private JavaMailSender javaMailSender;

    @PostMapping("/sendEmail")
    public String sendEmail(@RequestParam String from,
                            @RequestParam String to,
                            @RequestParam String subject,
                            @RequestParam String body,
                            @RequestParam("attachment") MultipartFile attachment) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setText(body);
            mimeMessageHelper.setSubject(subject);

            mimeMessageHelper.addAttachment(attachment.getOriginalFilename(), attachment);

            javaMailSender.send(mimeMessage);

        } catch (MessagingException me) {
            throw new RuntimeException(me);

        }

        return redirectAllCustomers();
    }

    public String redirectAllCustomers(){
        return "redirect:/";
    }
}
