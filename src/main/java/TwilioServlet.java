import static spark.Spark.post;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twilio.twiml.Body;
import com.twilio.twiml.Message;
import com.twilio.twiml.MessagingResponse;

@SuppressWarnings("serial")
public class TwilioServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("In the Post Method..!!");
		service(request, response);
		
		/*

		post("/receive-sms", (req, res) -> {

			Message sms = new Message.Builder().body(new Body("Hey Nikhil, how are you??")).build();

			MessagingResponse twiml = new MessagingResponse.Builder().message(sms).build();

			return twiml.toXml();
		});

		
		 * Message sms = new Message.Builder().body(new
		 * Body("The Robots are coming! Head for the hills!")).build();
		 * 
		 * MessagingResponse twiml = new
		 * MessagingResponse.Builder().message(sms).build();
		 * 
		 * response.setContentType("application/xml");
		 * 
		 * try { response.getWriter().print(twiml.toXml()); } catch
		 * (TwiMLException e) { e.printStackTrace(); }
		 * 
		 * // TODO Auto-generated method stub super.doPost(request, response);
		 
	*/}

	// service() responds to both GET and POST requests.
	// You can also use doGet() or doPost()
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		System.out.println("In service method..!!");
		
		post("/receive-sms", (req, res) -> {
			
			/*String body = request.getParameter("Body");
			String message ;
			*/
			/*System.out.println("The contents in the Body is: "+body);

			if(body.toLowerCase().equals("hi")){
				
				message = "You sent "+body;
			}
			else{
				message = "Not a valid mesage...!!";
			}*/
			
			Message sms = new Message.Builder().body(new Body("Hello! All working fine..")).build();
			MessagingResponse twiml = new MessagingResponse.Builder().message(sms).build();
			return twiml.toXml();
			
			
			/*Message sms = new Message.Builder().body(new Body("Hey Nikhil, how are you??")).build();

			MessagingResponse twiml = new MessagingResponse.Builder().message(sms).build();

			return twiml.toXml();*/
		});
	}
}