package kryptologia;

import java.util.regex.Pattern;

import sun.net.www.content.text.plain;

public class Decode {

	private String decodeText, plainText;
	
	public Decode() {
	}
	
	private void setPlainText(String str) {
		this.plainText = str;
	}
	
	private void setDecodeText(String str) {
		this.decodeText = str;
	}
	
	
	public String getPlainText() {
		return this.plainText;
	}
	
	public void plainText(String str) {
		this.setDecodeText(str); // plain
		String letter = "";
		String arg = "";
		String text = "";
		String num = "";
		int j = 0;
		
		   for (int i = 0, count = 1 ; i < decodeText.length(); i++) {
			   // TODO porównaæ znami za pomoc¹ wyra¿eñ regularnych
			   // 1. czy string pasuje do wzoru [a-Z_0-9][0-9][0-9 lub ,] - jeœli przecinek to trzeba wypisaæ znak i zacz¹æ ilczyæ od nowa.
			   letter = decodeText.charAt(i) + "";
			   if( count == 1) {
				   arg = letter;
				   text += arg;
				   count++;  
			   }
			   else if (count > 1) {
				   if( letter.matches("[0-9]")) {
					   
					   // w tym miejscu obs³uga braku przecinka na koncu wiersza. 
					   num += letter;
					   count++;
					   
					   if (i+1 == decodeText.length()) {
						   j = Integer.parseInt(num);
							   
						   for (int a = 1; a < j; a++) {
							   text += arg;
							   }
						   }
						   
				   }
				   else if( letter.matches("\\D[^,]")) {
					   arg = letter;
					   text += arg;
					   num = "";
					   count++;
					   // b³êdny kod
				   }
				   else if( letter.matches(",")) {
					   if(num.matches("")) {
						   text += letter;
						   count++;
						   arg = letter;
					   }
					   else {
						   j = Integer.parseInt(num);
						   
						   for (int a = 1; a < j; a++) {
							   text += arg;
						   }
						   num = "";
						   arg = "";
						   count = 1;
					   }
				   }
				   
			   }
		   }
			   
		setPlainText(text);
	}
	
}
