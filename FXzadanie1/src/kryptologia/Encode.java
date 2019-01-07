package kryptologia;

public class Encode {

	private String encodeText, plainText;
	
	public Encode() {	
	}
	
	private void setPlainText(String str) {
		this.plainText = str;
	}
	
	private void setEncodeText(String str) {
		this.encodeText = str;
	}
	
	public String getEncodeText() {
		return this.encodeText;
	}
	
	public void encodeText(String str) {
		this.setPlainText(str);
		String text = "";
		
		   for (int i = 0, count = 1; i < plainText.length(); i++) {
		        if (i + 1 < plainText.length() && plainText.charAt(i) == plainText.charAt(i + 1)) {
		            count++;
		        }
		        else if(i + 1 < plainText.length() && plainText.charAt(i) !=  plainText.charAt(i + 1)) {
		        	text += plainText.charAt(i) + Integer.toString(count) + ",";
		        	count = 1;
		        }
		        else if(i + 1 >= plainText.length()) {
		        	text += plainText.charAt(i) + Integer.toString(count);
		        }
		   
		}
		setEncodeText(text);
	}
	
	
}
