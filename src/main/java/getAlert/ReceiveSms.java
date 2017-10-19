package getAlert;

import static spark.Spark.post;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import javax.servlet.http.HttpServlet;

import com.twilio.twiml.Body;
import com.twilio.twiml.Message;
import com.twilio.twiml.MessagingResponse;

public class ReceiveSms extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("null")
	public static void main(String[] args) {

		post("/receive-sms", (req, res) -> {
			
			String toMsg = "";
			
			BufferedWriter bw = null;
			FileWriter fw = null;
			
			fw = new FileWriter("G:\\Semester 3\\TestMavenJetty\\instaFirst\\NameNumbers.properties", true);
			bw = new BufferedWriter(fw);
			//bw.write("Hi This is Nikhil..!!");
			
			Properties prop = null;
			InputStream input = null;
			
			Properties prop1 = null;
			InputStream input1 = null;
			
			Properties prop2 = null;
			InputStream input2 = null;
			
			Properties prop3 = null;
			InputStream input3 = null;
			
			try {
				
				prop = new Properties();
				input = new FileInputStream("G:\\Semester 3\\TestMavenJetty\\instaFirst\\NameNumbers.properties");
				
				prop1 = new Properties();
				input1 = new FileInputStream("G:\\Semester 3\\TestMavenJetty\\instaFirst\\QuoteOfTheDay.properties");
				
				prop2 = new Properties();
				input2 = new FileInputStream("G:\\Semester 3\\TestMavenJetty\\instaFirst\\UpcomingEvents.properties");
				
				prop3 = new Properties();
				input3 = new FileInputStream("G:\\Semester 3\\TestMavenJetty\\instaFirst\\TimeTable.properties");

				// load a properties file
				prop.load(input);
				prop1.load(input1);
				prop2.load(input2);
				prop3.load(input3);
				
				
				String body = req.queryParams("Body");
				String from = req.queryParams("From");
				String toDb = "";
				
				System.out.println("\nFrom: " + from + "\nMessage: " + body);
				
				if (body.toLowerCase().startsWith("subscribe")) {
					System.out.println("In subscribe match");
					
					if(prop.getProperty(from) == (null)){
						//System.out.println("Subscribe no users");
						
						toDb = "\n"+from+"="+body.substring(body.indexOf(" ") + 1, body.length());
						
						//System.out.println("To Db: "+toDb);
						
						toMsg = "Dear "+body.substring(body.indexOf(" ") + 1, body.length())+",\n\nYou have successfully subscribed.. :)"
								+ "\n\nYou can ask for,"
								+ "\n1. Quote of the day(\"QUOTE\")"
								+ "\n2. Updates in time table(\"TT\")"
								+ "\n3. Events for the day(\"EVENTS\")";
					}
					else{
						//System.out.println("Match found in subscribed");
						toMsg = "Dear "+body.substring(body.indexOf(" ") + 1, body.length())+",\nYou are already subscribed..!!"
								+ "\n\nYou can ask for,"
								+ "\n1. Quote of the day(\"QUOTE\")"
								+ "\n2. Updates in time table(\"TT CSXXX\")"
								+ "\n3. Events for the day(\"EVENTS\")";
						//prop.getProperty(body.substring(body.indexOf(" ") + 1, body.indexOf(" ")+6));
					}
					
					//System.out.println("After if and else");
					/*fw = new FileWriter("G:\\Semester 3\\TestMavenJetty\\instaFirst\\Database.txt", true);
					bw = new BufferedWriter(fw);*/
					bw.write(toDb);
					bw.flush();
					//System.out.println("After adding to db");
					
					
				}if (body.toLowerCase().trim().startsWith("quote")) { // for quote
					Calendar calendar = Calendar.getInstance();
					Date date = calendar.getTime();
					//System.out.println(new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime()));
					
					if(prop.getProperty(from) == (null)){
						toMsg = "You need to register.\nUse SUBSCRIBE YOUR_NAME to subscribe.. :)";
					}else{
						toMsg = prop1.getProperty(new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime()));
					}
					
				}else if (body.toLowerCase().trim().startsWith("tt")) { // for tt
					
					if(prop.getProperty(from) == (null)){
						toMsg = "You need to register.\nUse SUBSCRIBE YOUR_NAME to subscribe.. :)";
					}else{
						String temp = prop3.getProperty(body.substring(body.indexOf(" ") + 1, body.indexOf(" ")+6));
						
						if(prop3.getProperty(body.substring(body.indexOf(" ") + 1, body.indexOf(" ")+6)) == (null)){ // CourseNumber not fund
							System.out.println("No class for timetable");
							toMsg = "Sorry, for this course, there are no time tables available.";
						}
						else{
							System.out.println("Class found TimeTable");
							toMsg = prop3.getProperty(body.substring(body.indexOf(" ") + 1, body.indexOf(" ")+6));
						}
					}
					
				}else if (body.toLowerCase().trim().startsWith("events")) {
					Calendar calendar = Calendar.getInstance();
					Date date = calendar.getTime();
					if(prop.getProperty(from) == (null)){
						toMsg = "You need to register.\nUse SUBSCRIBE YOUR_NAME to subscribe.. :)";
					}else{
						toMsg = prop2.getProperty(new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime()));
					}
					
					//System.out.println(new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime()));
					
				}else{
					toMsg = "Please use one of the following from the Menu,\n"
							+"\n\nYou can ask for,"
							+ "\n1. To Subscribe(\"SUBSCRIBE YOUR_NAME\")"
							+ "\n2. Quote of the day(\"QUOTE\")"
							+ "\n3. Updates in time table(\"TT CSXXX\")"
							+ "\n4. Events for the day(\"EVENTS\")";;
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (bw != null)
						bw.close();

					if (fw != null)
						fw.close();

				} catch (IOException ex) {

					ex.printStackTrace();
				}
			}

			//System.out.println("After If Else");
			Message sms = new Message.Builder().body(new Body(toMsg)).build();
			MessagingResponse twiml = new MessagingResponse.Builder().message(sms).build();
			toMsg="";
			return twiml.toXml();
		});
	}
}
