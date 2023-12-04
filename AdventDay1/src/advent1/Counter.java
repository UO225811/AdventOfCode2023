package advent1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Counter {

	public static void main(String[] args) {
		String path = "text.txt";
		int result = 0;
		try (
			FileInputStream file = new FileInputStream(path);
			InputStreamReader input = new InputStreamReader(file);
	        BufferedReader bufferedReader = new BufferedReader(input)) {

	            // Read the file line by line
	            String line;
	            while ((line = bufferedReader.readLine()) != null) {
	            	// Use regular expression to find all digits in the string
	                Pattern pattern = Pattern.compile("\\d");
	                Matcher matcher = pattern.matcher(line);
	                int firstDigit = -1, lastDigit = -1;
	                
	                // Print all the digits found
	                while (matcher.find()) {
	                	int digit = Integer.parseInt(matcher.group());
	                    if (firstDigit == -1) {
	                    	firstDigit = digit;
	                    }
	                    lastDigit = digit;
	                }
	                if (lastDigit == -1)
	                	lastDigit = firstDigit;
	                String theRes = String.valueOf(firstDigit) + String.valueOf(lastDigit);
	                result += Integer.parseInt(theRes);
	            }			
		} catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println("The result is: " + result);
	}

}
