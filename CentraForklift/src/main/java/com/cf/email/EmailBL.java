package com.cf.email;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class EmailBL {

	public static WebDriver driver;

	public void sendEmail(String toEmail, String emailBody, int invoiceId) {
		// Recipient's email ID needs to be mentioned.
		// String to = "sneman143@gmail.com";

		// Sender's email ID needs to be mentioned
		// String from = "emailtestauto@gmail.com";
		
		emailBody = "Dear Costumer, \n Your Invoice with #"
				+ invoiceId
				+ " has been generated. \n Click http://localhost:8080/CentraForklifts/invoice.html?invoiceid="
				+ invoiceId + " to view your invoice.";

		final String username = "centraforklift@gmail.com";// change accordingly
		final String password = "Jarvis1234";// change accordingly

		// Assuming you are sending email through relay.jangosmtp.net
		String host = "smtp.gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "25");

		// Get the Session object.
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(username));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));

			// Set Subject: header field
			message.setSubject("Invoice #" + invoiceId);

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Now set the actual message
			messageBodyPart.setText(emailBody);

			// Create a multipar message
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Part two is attachment
			messageBodyPart = new MimeBodyPart();
			String filename = invoiceId + ".html" ;
			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(filename);
			multipart.addBodyPart(messageBodyPart);

			
			// Send the complete message parts
			message.setContent(multipart);

			// Send message
			Transport.send(message);

			System.out.println("Email sent successfully...");
			

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		File file = new File(invoiceId + ".html");
		file.deleteOnExit();
	}

	public void getInvoiceHTMLContent(int invoiceId) throws Exception {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		options.addArguments("--disable-gpu");
		options.addArguments("--window-size=1024,768");
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver(options);
		driver.get("http://localhost:8080/CentraForklifts/invoice.html?invoiceId=" + invoiceId);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		String pageSource = driver.getPageSource();
		System.out.println(pageSource);
		BufferedWriter writer = new BufferedWriter(new FileWriter(invoiceId + ".html"));
		writer.write(pageSource);
		writer.close();
		driver.quit();

	}

	public static void main(String[] args) {
		EmailBL email = new EmailBL();
		try {
			email.getInvoiceHTMLContent(15679);
			email.sendEmail("t.isaacjefferson@gmail.com", "", 15679);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
