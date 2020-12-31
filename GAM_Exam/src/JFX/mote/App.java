package JFX.mote;

import JFX.mote.layout.Panel;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application{
	
	public static String title;
	public static int width=1024;
	public static int height=768;
	double clientX = 0;
	double clientY = 0;
	static Panel<?> content;
	private static VBox root = new VBox();
	public static Stage stage;
	public static boolean loaded;
	public static AppThreadManager threadManger = new AppThreadManager();

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle(title);

		Rectangle rect = new Rectangle(width,height);
		rect.setArcHeight(16);
		rect.setArcWidth(16);
		root.setStyle("-fx-background-radius: 16;-fx-background-color:#eee;");
		
		BorderPane top = new BorderPane();
		top.setStyle("-fx-background-color:#ccc;");
		top.setMaxHeight(32);
		top.setMinHeight(20);
		top.setPadding(new Insets(2, 8, 0, 8));
		
		top.setOnMousePressed(event->{
			clientX = event.getScreenX();
			clientY = event.getScreenY();
		});
		top.setOnMouseDragged(event->{
			double thisX = stage.getX();
			double thisY = stage.getY();
			double xMoved = event.getScreenX() - clientX;
			double yMoved = event.getScreenY() - clientY;
			double X = thisX + xMoved;
			double Y = thisY + yMoved;
			stage.setX(X);
			stage.setY(Y);				
			clientX = event.getScreenX();
			clientY = event.getScreenY();
		});
		Circle close = new Circle(8);
		close.setFill(Color.rgb(0xff,0x44,0));
		close.setOnMouseClicked(event->{stage.close();App.threadManger.clear();});
		
		Circle iconifier = new Circle(8);
		iconifier.setFill(Color.rgb(0x22,0x99,0xff));
		iconifier.setOnMouseClicked(event->{stage.setIconified(true);});
		
		HBox Wbtn = new HBox(8);
		Wbtn.getChildren().addAll(iconifier,close);
		
		top.setCenter(new Label(App.title));
		top.setRight(Wbtn);
		
		//Rectangle content = new Rectangle(width,height);
		//content.setFill(Color.BLACK);
		if(content!=null)
			content.toNode();
		App.loaded = true;
		
		root.setClip(rect);
		Scene sc = new Scene(root,width,height);
		
		root.getChildren().add(0, top);
		sc.setFill(Color.TRANSPARENT);
		
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.setScene(sc);
		App.stage = stage;
		stage.show();
	}

	public static VBox getRoot() {
		return root;
	}

	public static void setup() {
		root.getChildren().add(new Pane());
		
	}

	public static void setPanel(Panel<?> layout) {
		App.getRoot().getChildren().remove(App.content);
		App.getRoot().getChildren().add(layout);
		App.content = layout;
		//App.content.setMinSize(App.width,App.height);
		if(App.loaded)
			App.content.toNode();
	}

}