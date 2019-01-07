package kryptologia;




import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

public class SampleController  {
	@FXML
	private AnchorPane aPane;
	@FXML
	private Button buttonCopy, buttonResult;
	@FXML
	private Label lab1, lab2, lab3;
	@FXML
	private RadioButton rButton1, rButton2;
	@FXML
	private TextField tField1, tField2;
	@FXML
	private ToggleGroup tGroup;
	private String str;
	private boolean flag = true; // jeœli true to kodowanie
	
	// Publiczny konstruktor domyœlny
	public SampleController() {
	}
	@FXML
	private void initialize() {
	}
	
	/*
	 * Metoda obs³ugi zdarzenia CopyEvent
	 * kopiuje pola result do pola source a nastêpnie czyœci to pierwsze. 
	 */
	@FXML
	private void copyEvent() {
		if(tField2.getText() == null) {
			return;
		}
		else {
			this.str = tField2.getText().toString();
			tField1.setText(str);
			tField2.setText(null);
		}
	}
	
	@FXML
	private void resultEvent() {
		//TODO liczenie wyniku  * Dokoñczyæ
		
		if (tField1 == null) {return;}
		if (tField1 != null) {
			if (this.flag == true) {
				Encode e = new Encode();
				e.encodeText(tField1.getText().toString());
				this.tField2.setText(e.getEncodeText());
			}
			
			if (this.flag == false) {
				Decode e = new Decode();
				//TODO klasa Decode
				e.plainText(tField1.getText().toString());
				this.tField2.setText(e.getPlainText());
			}
		}
	}
	
	@FXML
	private void kodEvent() {
		this.flag = true;
	}
	
	@FXML
	private void dekodEvent() {
		this.flag = false;
	}
	
	public void setFlag(boolean b) {
		this.flag = b;
	}
}
