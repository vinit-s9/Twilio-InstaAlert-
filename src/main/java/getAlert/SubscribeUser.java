package getAlert;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

public class SubscribeUser {

	File file;

	static BufferedWriter bw = null;
	static FileWriter fw = null;

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {

		Properties prop = new Properties();
		InputStream input = null;
		
		BufferedWriter bw = null;
		FileWriter fw = null;
		
		fw = new FileWriter("G:\\Semester 3\\TestMavenJetty\\instaFirst\\Locate.properties", true);
		bw = new BufferedWriter(fw);
		
		bw.write("\nCS641=Added CS641\\n");
		bw.flush();
		
		input = new FileInputStream("G:\\Semester 3\\TestMavenJetty\\instaFirst\\Locate.properties");

		// load a properties file
		prop.load(input);
		
		//System.out.println(prop.getProperty("CS640"));
		//System.out.println(prop.getProperty("CS643"));
		//System.out.println(prop.getProperty("CS641"));
		//System.out.println(prop.getProperty("CS644"));
		
		
		String temp = "TT CS642";
		String temp1 = "TIMETABLE CS642";
		
		System.out.println(temp.trim());
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		//Date date = new Date();
		//System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
		
		
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		System.out.println(new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime()));
		//int day = calendar.get(Calendar.DAY_OF_WEEK);//
		
		
		//String temp = "Subscr Nikhil Kanojia";

		if (temp.toLowerCase().startsWith("subscribe")) {
			//System.out.println("True");
		}
		
		
		
		//System.out.println(temp.substring(temp.indexOf(" ") + 1, temp.length()));
		System.out.println(temp.substring(temp.indexOf(" ") + 1, temp.indexOf(" ")+6));

	}
}
