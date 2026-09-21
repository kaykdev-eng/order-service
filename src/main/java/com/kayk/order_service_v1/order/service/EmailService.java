package com.kayk.order_service_v1.order.service;

import com.kayk.order_service_v1.order.dtos.OrderEventDTO;
import com.kayk.order_service_v1.order.template.EmailTemplate;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void emailSender(OrderEventDTO dto) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            mimeMessageHelper.setTo(dto.email());
            mimeMessageHelper.setSubject("Pedido Confirmado #" + dto.id());
            mimeMessageHelper.setFrom("kaykapple1@gmail.com");

            String html = EmailTemplate.BuildHtlmTemplate(dto);
            mimeMessageHelper.setText(html, true);

            javaMailSender.send(mimeMessage);

        }catch (MessagingException e) {
            throw new RuntimeException("Error sending confirmation email", e);
        }
    }
}
