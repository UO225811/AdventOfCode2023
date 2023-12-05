package advent2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CounterNumbersAndWordsMeaningNumbers {

	public static void main(String[] args) {
		String path = "text.txt";
		int result = 0;

		Map<String, Integer> wordToNumber = new HashMap<>();
		wordToNumber.put("one", 1);
		wordToNumber.put("two", 2);
		wordToNumber.put("three", 3);
		wordToNumber.put("four", 4);
		wordToNumber.put("five", 5);
		wordToNumber.put("six", 6);
		wordToNumber.put("seven", 7);
		wordToNumber.put("eight", 8);
		wordToNumber.put("nine", 9);

		try (FileInputStream file = new FileInputStream(path);
				InputStreamReader input = new InputStreamReader(file);
				BufferedReader bufferedReader = new BufferedReader(input)) {

			// Read the file line by line
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				// Pattern to find all the digits or number in words inside of the string
				Pattern pattern = Pattern.compile("\\d|(?:one|two|three|four|five|six|seven|eight|nine)");
				Matcher matcher = pattern.matcher(line);
				int firstDigit = -1, lastDigit = -1;

				// Print all the digits found (included words)
				while (matcher.find()) {
					String theMatch = matcher.group();

					if (theMatch.matches("\\d+")) {
						int digit = Integer.parseInt(theMatch);
						if (firstDigit == -1) {
							firstDigit = digit;
						}							
						lastDigit = digit;
					} else {
						int wordNumber = wordToNumber.get(theMatch);

						if (firstDigit == -1) {
							firstDigit = wordNumber;
						}
						lastDigit = wordNumber;
					}
				}
				String theRes = String.valueOf(firstDigit) + String.valueOf(lastDigit);
				System.out.println(line + " should be " + theRes);
				result += Integer.parseInt(theRes);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("The result is " + result);
	}

}
