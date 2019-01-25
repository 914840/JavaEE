package controller;

import java.io.File;
import java.net.MalformedURLException;
import javafx.fxml.FXML;
import javafx.scene.ImageCursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.PaintSquare;

public class MainWindowController {

	@SuppressWarnings("unused")
	private Main main;
	private PaintSquare ps;
	private double mouseX;
	private double mouseY;
	private int cubeSide = 43;
	private Image image;
	@FXML
	private AnchorPane pane;
	@FXML
	private Pane paneCube;
	@FXML
	private Button buttonLoad, buttonClean;
	@FXML
	private Canvas canvasCube;
	@FXML
	private Canvas imageCanvas;

	//
	public void setMain(Main main) {
		this.main = main;
		PaintSquare ps = new PaintSquare(this.cubeSide, 5, 25, paneCube);
		setPaintSquare(ps);
	}

	public void setPaintSquare(PaintSquare ps) {
		this.ps = ps;
	}

	public void setPane(Pane paneCube) {
		this.paneCube = paneCube;
	}

	public void loadFileAction() {
		FileChooser fc = new FileChooser();
		fc.setTitle(" = Wybierz obrazek = ");

		fc.getExtensionFilters().add(new ExtensionFilter("obrazki", "*.png", "*.jpg", "*.gif"));
		File selectedFile = fc.showOpenDialog(null);

		if (selectedFile != null) {

			try {
				@SuppressWarnings("deprecation")
				Image image = new Image(selectedFile.toURL().toString()); // zapisuje plika wybrany FileChooser jako
																			// Image w celu narysowania na Canvas

				GraphicsContext gc = imageCanvas.getGraphicsContext2D(); // GraficsContext2D w celu dostania siê do
																			// Canvas i wyrysowania obrazka
				gc.drawImage(image, 0, 0); // wspolzedne od ktorych wyrysowywuje obrazek na Canvas
				this.image = image;
				ps.addImageCanvas(image);
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void mouseEnteredEvent() {

		Image image = new Image(getClass().getResourceAsStream("cursor2.png"));
		ImageCursor cursor = new ImageCursor(image, 0, 0);
		imageCanvas.setCursor(cursor);
		imageCanvas.addEventHandler(MouseEvent.MOUSE_MOVED,
					event -> {
						this.mouseX = event.getX();
						this.mouseY = event.getY();
					}
				);

	}

	// Obs³uga klikniêcia myszk¹ w imageCanvas
	public void mouseClickedEventCanvas()
	{
		
		if (this.image != null)
		{	
			
			ps.setMousePoint(this.mouseX, this.mouseY);
			

		}
	}

	// Obs³uga Button - Czyœæ
	public void clearActionEvent() {
		ps.cleanPaintSquare();
		ps.drawWhitePaintSquare();
		clearImageCanvas();
	}
	
	
	// Metoda prywatna do obs³ugi czyszczenia imageCanvas 
	private void clearImageCanvas()
	{
		GraphicsContext clearCanvas = this.imageCanvas.getGraphicsContext2D();
		clearCanvas.setFill(Color.WHITE);
		clearCanvas.fillRect(
							0,		// x
							0,		// y
							imageCanvas.getWidth(),			// w
							imageCanvas.getHeight()			// h
							);
		this.image = null;
	}

}
