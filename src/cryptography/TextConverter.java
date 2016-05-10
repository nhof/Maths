package cryptography;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextConverter {


	public static List<String> convertText(String input){
		return new ArrayList<>(Arrays.asList(input.split("")));
	}
	
}
