package jmsapp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.QueueConnection;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.soap.Text;

@WebServlet(urlPatterns = "/send")
public class ProdutorJms extends HttpServlet {

	
	@Resource(lookup = "java:/myjms/Mycon")
	ConnectionFactory connectionFactory;
	
	@Resource(lookup = "java:/myjms/myqueue")
	Destination destination;
     
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String mensagem = req.getParameter("mensagem");
		
		try {
			QueueConnection connection = (QueueConnection) connectionFactory.createConnection();
			
			QueueSession session = 
					connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			
			MessageProducer producer = session.createProducer(destination);
			
			TextMessage message = 
					     session.createTextMessage(" Mensagem: " + mensagem);
			
			producer.send(message);
			
			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
			out.print("Mensagem enviada " + mensagem);

			
			producer.close();
			session.close();
			connection.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		
	
	}


}
