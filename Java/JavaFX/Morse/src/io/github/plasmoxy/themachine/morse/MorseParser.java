package io.github.plasmoxy.themachine.morse;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MorseParser {
	
	// singleton
	private static MorseParser instance;
	public static MorseParser getInstance() { return instance; }
	static { instance = new MorseParser(); }
	
	public static final String[][] MORSEMAP = {
			{" ", "  "}, // 2 spaces after word
			{"a", ".-"},
			{"b", "-..."},
			{"c", "-.-."},
			{"d", "-.."},
			{"e", "."},
			{"f", "..-."},
			{"g", "--."},
			{"h", "...."},
			{"i", ".."},
			{"j", ".---"},
			{"k", "-.-"},
			{"l", ".-.."},
			{"m", "--"},
			{"n", "-."},
			{"o", "---"},
			{"p", ".--."},
			{"q", "--.-"},
			{"r", ".-."},
			{"s", "..."},
			{"t", "-"},
			{"u", "..-"},
			{"v", "...-"},
			{"w", ".--"},
			{"x", "-..-"},
			{"y", "-.--"},
			{"z", "--.."},
			
			// numbers
			
			{"1", ".----"},
			{"2", "..---"},
			{"3", "...--"},
			{"4", "....-"},
			{"5", "....."},
			{"6", "-...."},
			{"7", "--..."},
			{"8", "---.."},
			{"9", "----."},
			{"0", "-----"}
	};
	
	public String toMorse(String text) {
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i<text.length(); i++) {
			// current char
			String c = String.valueOf(text.charAt(i));
			c = c.toLowerCase();
			
			for (String[] def : MORSEMAP) {
				if (def[0].equals(c)) {
					sb.append(def[1]);
					sb.append(" "); // add space after letter
				}
			}
			
		}
		return sb.toString();
	}
	
	public String toText(String text) {

		StringBuilder sb = new StringBuilder();
		
		String[]words = text.split("  "); // 2 spaces for words ( or more )
		
		for (String word : words) {
			if (word.equals("") || word.equals(" ")) continue; // skip if empty or single space
			
			String[] letters = word.split(" "); // 1space for letters
			
			for (String letter : letters) {
				for (String[] def : MORSEMAP) {
					if (def[1].equals(letter)) {
						sb.append(def[0]);
					}
				}
			}
			
			sb.append(" "); // add space after word
			
		}
		
		return sb.toString();
	}
	
}
