package cryptography;

import java.util.ArrayList;
import java.util.List;

public class Skytale implements Coder {

	int lines;
	String text;

	public Skytale(int diameter, int height, String text) {
		this.text = text;
		lines = (int) ((double) diameter / (double) height);
	}

	@Override
	public String code() {
		List<String> t = TextConverter.convertText(text);
		for (int i = 0; i < t.size(); i++) {
			if ("\n".equals(t.get(i))) {
				t.remove(i);
				for (int j = i; j % lines != 0; j++) {
					t.add(j, " ");
				}
			}
		}
		while (t.size() % lines != 0) {
			t.add(" ");
		}
		List<List<String>> matText = new ArrayList<List<String>>();
		for (int i = 0; i < (double) text.length() / lines; i++) {
			List<String> row = new ArrayList<String>();
			for (int j = 0; j < lines; j++) {
				if (i * lines + j < t.size()) {
					row.add(t.get(i * lines + j));
				}
			}
			matText.add(row);
			System.out.println(matText.get(i));
		}
		String code = "";
		for (int j = 0; j < matText.get(0).size(); j++) {
			for (int i = 0; i < matText.size(); i++) {
				code += matText.get(i).get(j);
			}
		}
		System.out.println(code);
		return code;
	}

	public static void main(String[] args) {
		String a = "This is all not so funny\n This is all not so funny\nThis is all not so funny";
		Skytale b = new Skytale(32, 2, a);
		b.code();
	}
}
