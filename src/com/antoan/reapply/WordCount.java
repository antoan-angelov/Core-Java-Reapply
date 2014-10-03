package com.antoan.reapply;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.naming.directory.InvalidAttributesException;

import org.json.JSONException;
import org.json.JSONObject;

public class WordCount {

	private final static String ARG_LINES = "-l";
	private final static String ARG_WORDS = "-w";
	private final static String ARG_CHARACTERS = "-c";

	private static class CountResult {
		public int characters = 0;
		public int words = 0;
		public int lines = 0;
	}
	
	private static CountResult countEverything(File file) {
		
		CountResult result = new CountResult();
		
		try {
			FileReader in = new FileReader(file);
			BufferedReader br = new BufferedReader(in);
			String line = null;
			while ((line = br.readLine()) != null) {
				result.lines += 1;
				result.characters += line.length();
				if(!line.trim().isEmpty()) {
					String[] words = line.split("([\\s]+)"); // split line by whitespace characters
					result.words += words.length;
				}
			}
			result.characters += result.lines - 1; // including the newline characters in character count too
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	/**
	 * Counts the words, lines, characters or all from a text file.
	 * 
	 * @param args First argument must always be the text file location.<br /> Second one can be either:<br/>
	 * <ul>
	 * 		<li>-l to count lines</li>
	 * 		<li>-c to count characters</li>
	 * 		<li>-w to count words</li>
	 * 		<li>leave empty to count them all</li>
	 * </ul>
	 * @throws InvalidAttributesException If the text file is not specified or does not exist
	 * @throws JSONException 
	 */
	public static void main(String[] args) throws IllegalArgumentException, JSONException {

		if (args.length == 0) {
			throw new IllegalArgumentException(
					"Text file location is not specified as first parameter.");
		}

		String textFileLocation = args[0];
		File textFile = new File(textFileLocation);

		if (!textFile.exists()) {
			throw new IllegalArgumentException("Text file does not exist.");
		}
		
		CountResult result = countEverything(textFile);

		if (args.length > 1) {
			String countWhat = args[1];
			switch (countWhat) {
			case ARG_LINES:
				System.out.println(result.lines);
				break;
			case ARG_CHARACTERS:
				System.out.println(result.characters);
				break;
			case ARG_WORDS:
				System.out.println(result.words);
				break;
			default:
				throw new IllegalArgumentException("Invalid attribute " + args[1]);
			}
		}
		else {
			JSONObject obj = new JSONObject();
			obj.put("lines", result.lines);
			obj.put("words", result.words);
			obj.put("chars", result.characters);			
			System.out.println(obj.toString(2));
		}
	}

}
