package kr.nutee.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import kr.nutee.model.user.UserUpdateModel;

@Service
public class MailService {
	
	public static void gmailSend(UserUpdateModel userModel) {
		String mailId = ""; // 네이버일 경우 네이버 계정, gmail경우 gmail 계정 (보내는계정)
		String password = ""; // 패스워드

		// SMTP 서버 정보를 설정한다.
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 465);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mailId, password);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(mailId));

			// 수신자메일주소
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(userModel.getEmail()));

			// Subject
			message.setSubject("Nutee"); // 메일 제목을 입력

			// Text
			message.setText("가입해주신 Nutee 계정정보입니다.\n\n" + "nickname: " + userModel.getNickname() + "\npw: " + userModel.getPw()); // 메일 내용을 입력

			// send the message
			Transport.send(message); //// 전송
			System.out.println("message sent successfully...");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
