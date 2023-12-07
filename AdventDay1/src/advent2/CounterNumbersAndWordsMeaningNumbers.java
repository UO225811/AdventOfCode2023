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
		
		//Map for searching the previous occurrences
		Map<String, Integer> wordToNumber1 = new HashMap<>();
		wordToNumber1.put("one", 1);
		wordToNumber1.put("two", 2);
		wordToNumber1.put("three", 3);
		wordToNumber1.put("four", 4);
		wordToNumber1.put("five", 5);
		wordToNumber1.put("six", 6);
		wordToNumber1.put("seven", 7);
		wordToNumber1.put("eight", 8);
		wordToNumber1.put("nine", 9);
		
		wordToNumber1.put("oneight", 1);
		wordToNumber1.put("twone", 2);
		wordToNumber1.put("threeight", 3);
		wordToNumber1.put("fiveight", 5);
		wordToNumber1.put("nineight", 9);
		wordToNumber1.put("eighthree", 8);
		wordToNumber1.put("eightwo", 8);
		wordToNumber1.put("sevenine", 7);
		
		//Map for searching after the first occurrence was found
		Map<String, Integer> wordToNumber2 = new HashMap<>();
		wordToNumber2.put("one", 1);
		wordToNumber2.put("two", 2);
		wordToNumber2.put("three", 3);
		wordToNumber2.put("four", 4);
		wordToNumber2.put("five", 5);
		wordToNumber2.put("six", 6);
		wordToNumber2.put("seven", 7);
		wordToNumber2.put("eight", 8);
		wordToNumber2.put("nine", 9);
		
		wordToNumber2.put("oneight", 8);
		wordToNumber2.put("twone", 1);
		wordToNumber2.put("threeight", 8);
		wordToNumber2.put("fiveight", 8);
		wordToNumber2.put("nineight", 8);
		wordToNumber2.put("eighthree", 3);
		wordToNumber2.put("eightwo", 2);
		wordToNumber2.put("sevenine", 9);

		try (FileInputStream file = new FileInputStream(path);
				InputStreamReader input = new InputStreamReader(file);
				BufferedReader bufferedReader = new BufferedReader(input)) {

			// Read the file line by line
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				// Pattern to find all the digits or number in words inside of the string
				Pattern pattern = Pattern.compile("(\\d)|(twone|oneight|threeight|fiveight|nineight|eighthree|eightwo|nineight|sevenine|one|two|three|four|five|six|seven|eight|nine)");
				Matcher matcher = pattern.matcher(line);
				int firstDigit = -1, lastDigit = -1;

				// Print all the digits found (included words)
				while (matcher.find()) {
					String theMatch = matcher.group();
					if (theMatch.matches("\\d")) {
						int digit = Integer.parseInt(theMatch);
						if (firstDigit == -1) {
							firstDigit = digit;
						}
						lastDigit = digit;
					} else {
						int wordNumber = wordToNumber1.get(theMatch);

						if (firstDigit == -1) {
							wordNumber = wordToNumber1.get(theMatch);
							firstDigit = wordNumber;
						} else wordNumber = wordToNumber2.get(theMatch);
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
