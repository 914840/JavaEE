package controller;

import java.awt.MouseInfo;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;

import com.sun.xml.internal.ws.policy.PolicyIntersector;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.SubScene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.PaintSquare;

public class MainWindowController {

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

	// TODO do zrobienia - patrz Main
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
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void mouseEnteredEvent() {

		Image image = new Image(getClass().getResourceAsStream("cursor2.png"));
		ImageCursor cursor = new ImageCursor(image, 21, 21);
		imageCanvas.setCursor(cursor);

	}

	// Obs³uga klikniêcia myszk¹ w imageCanvas
	public void mouseClickedEventCanvas()
	{
		if (this.image != null)
		{
			PixelReader reader = this.image.getPixelReader();
			WritableImage cubeImage = new WritableImage(this.cubeSide-2, this.cubeSide-2);
			PixelWriter writer = cubeImage.getPixelWriter();
		
			imageCanvas.addEventHandler(MouseEvent.MOUSE_PRESSED,
								event -> {
										this.mouseX = event.getX();
										this.mouseY = event.getY();
										}
									);
			for (int x = 0; x < (this.cubeSide- 2); x++ )
			{
				for (int y = 0; y < (this.cubeSide -2); y++)
				{
					Color color = reader.getColor(x, y);
					writer.setColor(
								x, y,
								Color.color(
										color.getRed(),
										color.getGreen(),
										color.getBlue()
										));
				}
			}
		}
	}

	// TODO zastanowiæ siê czy potrzebne
	//Obs³uga ruchu kursora
	public void mouseMovedEventCanvas() {

	}

	// Obs³uga Button - Czyœæ
	public void clearActionEvent() {
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
