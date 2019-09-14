package com.cf.email;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

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

public class EmailBL {

	private final String USER_AGENT = "Mozilla/5.0";
	
	public void sendEmail(String toEmail, String emailBody, int invoiceId) {
		  // Recipient's email ID needs to be mentioned.
	      //String to = "sneman143@gmail.com";

	      // Sender's email ID needs to be mentioned
	      //String from = "emailtestauto@gmail.com";

	      final String username = "emailtestauto@gmail.com";//change accordingly
	      final String password = "Welcome@123";//change accordingly

	      // Assuming you are sending email through relay.jangosmtp.net
	      String host = "smtp.gmail.com";

	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "25");

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	         new javax.mail.Authenticator() {
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
	         message.setRecipients(Message.RecipientType.TO,
	            InternetAddress.parse(toEmail));

	         // Set Subject: header field
	         message.setSubject("Invoice #" + invoiceId);

	         // Create the message part
	        // BodyPart messageBodyPart = new MimeBodyPart();

	         // Now set the actual message
	        // messageBodyPart.setText(emailBody);

	         // Create a multipar message
	         //Multipart multipart = new MimeMultipart();

	         // Set text message part
	         //multipart.addBodyPart(messageBodyPart);

	         // Part two is attachment
			/*
			 * messageBodyPart = new MimeBodyPart(); String filename =
			 * "C:\\Users\\kalai\\Downloads\\Invoice.xlsx"; DataSource source = new
			 * FileDataSource(filename); messageBodyPart.setDataHandler(new
			 * DataHandler(source)); messageBodyPart.setFileName(filename);
			 * multipart.addBodyPart(messageBodyPart);
			 */

	         // Send the complete message parts
	         message.setContent(emailBody, "text/html");

	         // Send message
	         Transport.send(message);

	         System.out.println("Email sent successfully...");
	  
	      } catch (MessagingException e) {
	         throw new RuntimeException(e);
	      }
	   
	}
	
	public String getInvoiceHTMLContent(int invoiceId) throws Exception {

		String url = "http://localhost:8080/CentraForklifts/invoice.html?invoiceId=" + invoiceId;
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		//System.out.println(response.toString());
		
		return response.toString();

	}
	
	public static void main(String[] args) {
		EmailBL email = new EmailBL();
		try {
			String messageBody = email.getInvoiceHTMLContent(30002);
			email.sendEmail("isaacjefferson.t@gmail.com", messageBody, 30002);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
