package getAlert;

import static spark.Spark.post;

import com.twilio.base.ResourceSet;
import com.twilio.twiml.Body;
import com.twilio.twiml.Message;
import com.twilio.twiml.MessagingResponse;

public class Back {
		
	public static void main(String[] args) {
		
		post("/receive-sms", (req,res) -> {
			
			/*ResourceSet<Message> messages = Message.reader().read();
			for (Message message : messages) {
				System.out.println(message.getBody());
			}*/
			
			Message sms = new Message.Builder()
					.body(new Body("Hey how are you??"))
					.build();
			
			MessagingResponse twiml = new MessagingResponse.Builder().message(sms).build();
			//System.out.println("SMS sent to: "+twiml.toXml().toString());
			System.out.println();
			return twiml.toXml();
		});
	}
}
