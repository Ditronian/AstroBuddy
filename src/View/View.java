package View;
import javafx.application.Application;
import javafx.event.EventHandler;
import java.time.LocalDate;
import java.util.HashMap;
import javafx.scene.input.MouseEvent;
import Controller.SimulationController;
import Model.CelestialBody;
import Model.Moon;
import Model.Planet;
import Model.Star;
import javafx.geometry.Insets; 
import javafx.geometry.Pos; 
import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text; 
import javafx.stage.Stage;  
import javafx.event.ActionEvent; 
import javafx.scene.canvas.*;
import javafx.scene.shape.Circle;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.DatePicker;


public class View extends Application { 
    
    //View Attributes
    static SimulationController controller;
    static HashMap<Integer, Circle> clickables = new HashMap<Integer, Circle>();
    
    //UI Elements
    static ScrollPane scroll;
    static Canvas canvas;
    static GraphicsContext gc;
    static DatePicker datePicker;
    static Text selectedText;
    static Text typeText;
    static Text moonCount; 
    static Text divider;
    static Text mass;
    static Text meanRadius;
    static Text surfaceGravity;
    static Text etc;
    
   //Initializes the UI
   @Override 
   public void start(Stage stage) {      
      //creating label email 
      selectedText = new Text("Selected Body: ");
      typeText = new Text("Type: ");
      moonCount = new Text("Moons: "); 
      divider = new Text("-----------------------");
      mass = new Text("Mass: ");
      meanRadius = new Text("Mean Radius: ");
      surfaceGravity = new Text("Surface Gravity: ");
       
      //Creating Text Filed for email        
      //TextField textField1 = new TextField();
      
      canvas = new Canvas(5000,5000);
      gc = canvas.getGraphicsContext2D();
      canvas.setOnMouseClicked(event -> {
          ClickHandler(event);
      });
      
      datePicker = new DatePicker();
      datePicker.setValue(LocalDate.now());
       
      //Creating Buttons 
      Button zoomInButton = new Button("+");
      zoomInButton.setOnAction(new EventHandler<ActionEvent>() {
          @Override public void handle(ActionEvent e) {
              canvas.setScaleX(canvas.getScaleX()*1.25);
              canvas.setScaleY(canvas.getScaleY()*1.25);
              canvas.setScaleZ(canvas.getScaleZ()*1.25);
          }
      });
      
      Button zoomOutButton = new Button("-");
      zoomOutButton.setOnAction(new EventHandler<ActionEvent>() {
          @Override public void handle(ActionEvent e) {
              canvas.setScaleX(canvas.getScaleX()*.75);
              canvas.setScaleY(canvas.getScaleY()*.75);
              canvas.setScaleZ(canvas.getScaleZ()*.75);
          }
      });
      
      Button downloadButton = new Button("Download System Data");
      downloadButton.setOnAction(new EventHandler<ActionEvent>() {
          @Override public void handle(ActionEvent e) {
              gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
              controller.RetrieveAPIBodies(datePicker.getValue());
          }
      });
      
      Button saveButton = new Button("Save Data");  
      saveButton.setOnAction(new EventHandler<ActionEvent>() {
          @Override public void handle(ActionEvent e) {
              System.out.println(controller.Save());
              
          }
      });
      
      //Creating a Grid Pane 
      GridPane mainWindow = new GridPane();    
      GridPane leftPane = new GridPane();
      GridPane rightPane = new GridPane();
      GridPane topPane = new GridPane();
      
      //Setting size for the pane 
      mainWindow.setMinSize(1000, 1000);
      topPane.setMinSize(1000, 50);
      leftPane.setMinSize(750, 1000);
      rightPane.setMinSize(250, 1000);
      
      //Setting the padding  
      mainWindow.setPadding(new Insets(10, 10, 10, 10)); 
      leftPane.setPadding(new Insets(10, 10, 10, 10));
      rightPane.setPadding(new Insets(10, 10, 10, 10)); 
      topPane.setPadding(new Insets(10, 10, 10, 10));
      
      //Setting the vertical and horizontal gaps between the columns 
      mainWindow.setVgap(5); 
      mainWindow.setHgap(5); 
      leftPane.setVgap(5); 
      leftPane.setHgap(5);   
      rightPane.setVgap(5); 
      rightPane.setHgap(5); 
      topPane.setVgap(5); 
      topPane.setHgap(5); 
      
      //Setting the Grid alignment 
      mainWindow.setAlignment(Pos.CENTER); 
      leftPane.setAlignment(Pos.CENTER); 
      rightPane.setAlignment(Pos.TOP_LEFT); 
      topPane.setAlignment(Pos.CENTER); 
       
      //Build Left Pane
      scroll = new ScrollPane();
      scroll.setMinSize(1000, 1000);
      scroll.setMaxSize(1000, 1000);
      //scroll.setMaxHeight(750);
      scroll.setStyle("-fx-background: BLACK;"); 
      scroll.setContent(canvas);
      scroll.setVvalue(.5);
      scroll.setHvalue(.5);  

      //Build Right Pane
      rightPane.add(selectedText, 0, 0); 
      rightPane.add(typeText, 0, 1); 
      rightPane.add(moonCount, 0, 2);       
      rightPane.add(divider, 0, 3); 
      rightPane.add(mass, 0, 4); 
      rightPane.add(meanRadius, 0, 5);
      rightPane.add(surfaceGravity, 0, 6);
      
      //Build Top Pane
      
      topPane.add(zoomInButton, 0, 0);
      topPane.add(zoomOutButton, 1, 0);
      topPane.add(datePicker, 2, 0);
      topPane.add(downloadButton, 3, 0); 
      topPane.add(saveButton, 4, 0); 
       
      //Styling  
      leftPane.setStyle("-fx-background-color: BLACK;");  
      rightPane.setStyle("-fx-background-color: WHITE;"); 
      topPane.setStyle("-fx-background-color: WHITE;"); 
      
      //Add Panes to Main Pane
      mainWindow.add(scroll, 0, 1);
      mainWindow.add(rightPane, 1, 1);
      mainWindow.add(topPane, 0, 0);
       
      // Creating a scene object 
      Scene scene = new Scene(mainWindow); 
       
      // Setting title to the Stage   
      stage.setTitle("AstroBuddy"); 
         
      // Adding scene to the stage 
      stage.setScene(scene);
      
      //Displaying the contents of the stage 
      stage.show();
      
      controller = new SimulationController();
   }
   
   //Main method
   public static void main(String args[])
   { 
       launch(args); 
   }
   
   //Draws Bodies onto the map
   public static void DrawBody(int id, double x, double y, double diameter, Color color) 
   {
       Circle body = new Circle(x,y,diameter/2.0, color);
       clickables.put(id, body);

       double xPos = x-diameter/2.0;
       double yPos = y-diameter/2.0;
       gc.setFill(color);
       gc.fillOval(xPos, yPos, diameter, diameter);
       
   }
   
   //Draws Orbit of a body
   public static void DrawOrbit(double position, double radius) 
   {
       double xPos = position-radius;
       double yPos = position-radius;
       gc.setStroke(Color.WHITE);
       gc.strokeOval(xPos, yPos, radius*2, radius*2);
   }
   
   //Handles the mouse clicking and checks if clicked on a body
   private static void ClickHandler(MouseEvent event) 
   {
       double x = event.getX();
       double y = event.getY();
       CelestialBody foundBody = null;
       
       //Try and find what was clicked on
       for(int i = 0; i<clickables.size();i++) 
       {
           Circle body = clickables.get(i);
           
           if(Math.hypot(body.getCenterX()-x, body.getCenterY()-y) <= body.getRadius()) 
           {
               foundBody = controller.getBodies().get(i);
               
               System.out.println("Clicked on " + controller.getBodies().get(i).getName());
               break;
           }
       }
       
       if(foundBody != null) UpdateRightPane(foundBody);
   }
   
   //Updates all the scientific information in the right side pane
   private static void UpdateRightPane(CelestialBody body) 
   {
       
       selectedText.setText("Selected Body: "+body.getName());
       if(body instanceof Star) typeText.setText("Type: Star");
       else if(body instanceof Planet) typeText.setText("Type: Planet");
       else if(body instanceof Moon) typeText.setText("Type: Moon");
       
       
       moonCount.setText("Moons: "+body.getSatellites().size());
       mass.setText("Mass: "+String.valueOf(body.getMass()) + " kg");
       meanRadius.setText("Mean Radius: "+String.valueOf(body.getRadius())+" km");
       surfaceGravity.setText("Surface Gravity: "+String.valueOf(body.getSurfaceGravity())+"g's");
       
   }

public static SimulationController getController()
{
    return controller;
}

public static void setController(SimulationController controller)
{
    View.controller = controller;
}

public static Canvas getCanvas()
{
    return canvas;
}

public static void setCanvas(Canvas canvas)
{
    View.canvas = canvas;
}

public static GraphicsContext getGc()
{
    return gc;
}

public static void setGc(GraphicsContext gc)
{
    View.gc = gc;
}
   
}