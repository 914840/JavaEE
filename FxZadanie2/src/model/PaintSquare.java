package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class PaintSquare {

	private Pane root;
	//private Canvas canvasCube;
	//private Image cubeImage;
	private Image imageCanvas;
	private int mouseX, mouseY;
	private ArrayList<Canvas> canvasList = new ArrayList<Canvas>();
	private ArrayList<Double> keyList = new ArrayList<Double>();
	private HashMap< Double, Canvas> canvasMap = new HashMap<Double, Canvas>();
	
	private int side, inRow, num;
	
	
	// konstruktor
	public PaintSquare(int side, int inRow, int num, Pane pane)
	{
		
		this.root = pane;
		this.side = side;
		this.inRow = inRow;
		this.num = num;
		
		
		drawWhitePaintSquare();
		drawWhitePaintSquare();

	}

	// metoda do rysowania pustych bia³ych kwadratów 
	public void drawWhitePaintSquare()
	{
		canvasList.clear();
		int x= 0, y=0, z = 5;	// zmienne: X- kwadratu, Y- kwadratu, z - odleg³oœæ miêdzy kwadratami
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
			}
			
		}
	}
	
	public void getRoot(Pane pane)
	{
		this.root = pane;
	}

	//=1=  pobiera z miejsca FileChooser'a obrazek wczytany do aplikacji. 
	public void addImageCanvas(Image imageCanvas) {
		// TODO Auto-generated method stub
		this.imageCanvas = imageCanvas;
	}

	//=2= metoda pobieraj¹ca wspó³rzêdne klikniêcia na imageCanvas
	public void setMousePoint(double mouseX, double mouseY) {
		// TODO Auto-generated method stub
		this.mouseX = (int) mouseX;
		this.mouseY = (int) mouseY;
		this.addSelectedCube();
	}
	
	//=3= metoda rysuj¹ca i zliczaj¹ca wartosc czerwieni na kwadracie
	private void addSelectedCube() 
	{
		int ox = this.mouseX;
		int oy = this.mouseY;
		
		double sumRed = 0; // suma czerwieni w kwadracie
		PixelReader reader = this.imageCanvas.getPixelReader();
		WritableImage wImage = new WritableImage(side, side);
		PixelWriter writer = wImage.getPixelWriter();
			for(int i = ox, x = 1; i < (ox + 41) && x < 42 ; i++, x++ )
			{
				for(int j = oy, y = 1; j < (oy + 41) && y < 42; j++, y++)
				{
					Color color = reader.getColor(i, j);
					sumRed += color.getRed();
					writer.setColor(x, y,
							Color.color(color.getRed(), 
									color.getGreen(),
									color.getBlue())
							);
				}
			}
		
		Canvas can = new Canvas(side, side);
		can.getGraphicsContext2D().drawImage(wImage, 0, 0);
		this.averageSaturation(sumRed, can);
	// rozwi¹zanie tymczasowe		
		//	root.getChildren().clear();
		//	root.getChildren().add(can);
					
	}	
	
	//=4= Metoda licz¹ca œredni¹ wartoœæ nasycenia kolorem czerwonym
	private void averageSaturation(Double sum, Canvas can)
	{
		//System.out.println(sum);
		double s = ((this.side-2) * (this.side - 2));
		double average = sum/s;
		//System.out.println(average);
		
		
		if(canvasMap.containsKey(average)) {return;}
		else
		{
			canvasMap.put(average, can);
			keyList.add(average);
			this.drawPaintSquareFromMap();
		}
	}

	//=5= Metoda s³u¿¹ca do sortowania i rysowania elementów mapy
	private void drawPaintSquareFromMap()
	{
		Collections.sort(keyList);
		Collections.reverse(keyList);
		int size = canvasMap.size();
		int x= 0, y=0, z = 5;	// zmienne: X- kwadratu, Y- kwadratu, z - odleg³oœæ miêdzy kwadratami
		this.root.getChildren().clear();
		if(size < num+1)
		{
		
			for(int n = 0, count = 0, row = 0; n < num; n++ )
			{
				
				{
					Canvas can;
					if(keyList.size()>n) 
					{
						can = canvasMap.get(keyList.get(n));
					
						if(count < inRow)
						{
							can.setLayoutX((z * count) + (count *side));
							can.setLayoutY((z * row) + (row * side));
							this.root.getChildren().add(can);
						
							count++;
						}
					
						else if (count == inRow)
						{
					
							row++;
							count = 0;
						
							can.setLayoutX((z * count) + (count *side));
							can.setLayoutY((z * row) + (row * side));
							this.root.getChildren().add(can);
								
						
							count++;
						}
					}
					else
					{
					
						can = new Canvas(side, side);
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
							
						}
					}
				}
			}
		}
		
		// TODO sprawdziæ najmniejsz¹ wartoœæ jeœli wiêksza ni¿ dodawana -> nie dodawaæ
		// 	jesli mniejsza to usun¹æ i dodaæ wartoœæ i posortowaæ. 
		else if(size > 25)
		{
			Collections.sort(keyList);
			Collections.reverse(keyList);
			for(int n = 0, count = 0, row = 0; n < num; n++ )
			{ //1
				if(count< num)
				{ //2
					
					
						Canvas can;
						if(keyList.size()>n) 
						{ //4
							can = canvasMap.get(keyList.get(n));
							
							if(count < inRow)
							{
								can.setLayoutX((z * count) + (count *side));
								can.setLayoutY((z * row) + (row * side));
								this.root.getChildren().add(can);
							
								count++;
							}
							
							else if (count == inRow)	
							{
						
								row++;
								count = 0;
							
								can.setLayoutX((z * count) + (count *side));
								can.setLayoutY((z * row) + (row * side));
								this.root.getChildren().add(can);
									
							
								count++;
							}
						} //4

					
				}// 2
				else
				{
					Collections.sort(keyList);
					Collections.reverse(keyList);
					canvasMap.get(keyList.get(n));

				}
			}
		}
	}

	public void cleanPaintSquare() {
		// TODO Auto-generated method stub
		keyList.clear();
		canvasList.clear();
		canvasMap.clear();
	}
}
