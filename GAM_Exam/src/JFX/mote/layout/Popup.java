package JFX.mote.layout;

import JFX.mote.App;
import JFX.mote.Component;
import JFX.mote.Layout;
import JFX.mote.LayoutDisplay;
import JFX.mote.components.Title;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author Mote
 *
 */
public class Popup {
	private Layout content  = new Layout();
	private Stage pop;
	/**
	 * 
	 * @param name
	 */
	public Popup(String name){
		pop=new Stage();
		pop.initModality(Modality.APPLICATION_MODAL);
		pop.setTitle(name);

		VBox layout= new VBox();
		content.setMode(Layout.DARKMODE);
		content.setDisplay(LayoutDisplay.Column);
		Rectangle rect = new Rectangle(App.width*.8, App.height*.8);
		rect.setArcHeight(16);
		rect.setArcWidth(16);
		
		BorderPane top = new BorderPane();
		top.setStyle("-fx-background-color:#444;");
		top.setMaxHeight(32);
		top.setMinHeight(20);
		top.setPadding(new Insets(2, 8, 0, 8));
		
		Circle close = new Circle(8);
		close.setFill(Color.rgb(0xff,0x44,0));
		close.setOnMouseClicked(event->{pop.close();});

		top.setRight(close);
	     
		add(new Title(name));
		content.init();
		content.setMinSize(App.width*.8, App.height*.8);
		content.setMaxSize(App.width*.8, App.height*.8);
		content.setAlignment(Pos.TOP_CENTER);
		
		
		layout.getChildren().addAll(top,content);   
	     
		layout.setClip(rect);
		Scene sc= new Scene(layout, App.width*.8, App.height*.8);
		sc.setFill(Color.TRANSPARENT);
		
		pop.initStyle(StageStyle.TRANSPARENT);
	
		pop.setScene(sc);      
	}
	/**
	 * Add Comopnenet to the PopUp 
	 * @param c
	 * @see Component
	 */
	public void add(Component c) {
		content.add(c);
		c.setStyle("-fx-border-color:#f00;");
	}
	/**
	 * Open PopUp
	 */
	public void open() {
		pop.showAndWait();
	}
	/**
	 * Close PopUp
	 */
	public void close() {
		pop.close();
	}
}
