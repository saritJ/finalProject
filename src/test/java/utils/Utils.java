package utils;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Utils {

	public static String readProperty(String key) {
		Properties prop = new Properties();
		String value = "";

		InputStream input = null;
		try {
			input = new FileInputStream("./src/test/resources/data/configuration.properties");
			// load a properties file
			prop.load(input);
			value = prop.getProperty(key);
		} catch (Exception e) {
		}
		return value;

	}
}
