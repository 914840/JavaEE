package model;

import java.util.ArrayList;
import java.util.TreeMap;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class PaintSquare {

	private Pane root;
	private Canvas canvasCube;
	private ArrayList<Canvas> canvasList = new ArrayList<Canvas>();
	private TreeMap< Double, Canvas> canvasMap = new TreeMap<Double, Canvas>();
	
	private int side, inRow, num;
	
	
	// konstruktor
	public PaintSquare(int side, int inRow, int num, Pane pane)
	{
		
		this.root = pane;
		this.side = side;
		this.inRow = inRow;
		this.num = num;
		//int x= 0, y=0, z = 5;
		
		drawWhitePaintSquare();
		drawWhitePaintSquare();

	}

	// metoda do rysowania pustych bia³ych kwadratów 
	public void drawWhitePaintSquare()
	{
		canvasList.clear();
		int x= 0, y=0, z = 5;
		for(int n = 0, count = 0, row = 0; n < num; n++ ) 
		{
			Canvas can = new Canvas(side, side);
			GraphicsContext gcc = can.getGraphicsContext2D();
			
			if(count < inRow)
			{
				can.setLayoutX((z * count) + (count *side));
				can.setLayoutY((z * row) + (row * side));
				this.root.getChildren().add(can);
				
				gcc.strokeRect( 
						x	,	//x
						y	,	//y
						side,	//w
						side	//h
						);
				gcc.setFill(Color.WHITE);
				gcc.fillRect( 
						x+1	, 	//x
						y+1	,	//y
						side-2,	//w
						side-2	//h
						);
				
				count++;
				canvasList.add(can);
			}
			else if (count == inRow)
			{
				row++;
				count = 0;
				
				can.setLayoutX((z * count) + (count *side));
				can.setLayoutY((z * row) + (row * side));
				this.root.getChildren().add(can);
				
				gcc.strokeRect( 
						x	,	//x
						y	,	//y
						side,	//w
						side	//h
						);
				gcc.setFill(Color.WHITE);
				gcc.fillRect( 
						x+1	, 	//x
						y+1	,	//y
						side-2,	//w
						side-2	//h
						);
				
				
				count++;
				canvasList.add(can);
				
			}
			
		}
	}
	
	// metoda do rysowania canvasCube pobranym obrazkiem
	public void drawCanvasCube() 
	{
		// jeœli pierwsza kostka
		if (canvasMap.isEmpty())
		{
			
		}
	}
	
	
	public void getRoot(Pane pane)
	{
		this.root = pane;
	}
}
