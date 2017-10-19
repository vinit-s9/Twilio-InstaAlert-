package getAlert;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SendSms {

	public static final String ACCOUNT_SID = "AC0b0ea7eb42a948256da95fcef1571ca1";
	public static final String AUTH_TOKEN = "bddb0ad5fe6f8bd65a8146346b0effbf";

	public static void main(String[] args) throws FileNotFoundException {
		
		Properties prop = new Properties();
		InputStream input = null;

		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		
		FileReader fr=new FileReader("G:\\Semester 3\\TestMavenJetty\\instaFirst\\EmergencyAlert.txt");    
        BufferedReader br=new BufferedReader(fr); 

		Message message = Message.creator(new PhoneNumber("+15512295964"), // to
				new PhoneNumber("+17866810244"), // from
				"kya samjha?" // body
		).create();
		
		System.out.println(message.getSid());

	}
}
