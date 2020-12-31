package JFX.mote.controls;

import JFX.mote.App;
import JFX.mote.Component;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;

public class PassField extends Component{
	private Label text;
	private javafx.scene.control.TextField input;
	private javafx.scene.control.PasswordField pass;
	private EventHandler<? super KeyEvent> onchange = event->{};
	private String value;
	private String Content = "";
	private boolean visible;
	public PassField(String value,EventHandler<? super KeyEvent> onchange) {
		this(value);
		this.setOnchange(onchange);
	}
	public PassField(String value) {
		super();
		this.value = value;
	}
	@Override
	public void init() {
		VBox pane = new VBox();
		text = new Label(value);
		text.setPadding(new Insets(32));
		text.setAlignment(Pos.CENTER_LEFT);
			
		HBox field = new HBox();
		
		StackPane textfield = new StackPane();
		
		input = new javafx.scene.control.TextField();
		input.setAlignment(Pos.CENTER_LEFT);
		input.setMinSize(592, 32);
		input.setMaxSize(592, 32);
		input.setStyle("-fx-background-radius:20 0 0 20;-fx-background-color:#fff;");
		
		pass = new javafx.scene.control.PasswordField();
		pass.setAlignment(Pos.CENTER_LEFT);
		pass.setMinSize(592, 32);
		pass.setMaxSize(592, 32);
		pass.setStyle("-fx-background-radius:20 0 0 20;-fx-background-color:#fff;");
		
		String path = "M 22.637668,95.338285 C 17.190143,97.098889 10.897824,96.521385 6.1405475,93.238519 3.8578913,91.674362 1.9694339,89.518867 0.83955491,86.994422 1.857146,84.432369 3.7847956,82.310646 6.0570046,80.770511 4.8038957,79.625922 3.5177404,78.5175 2.2467116,77.392594 L 4.0807413,75.77803 29.155197,97.812479 27.299953,99.441424 Z m -3.166727,-2.923856 -2.062736,-1.817991 c -2.747735,0.187008 -5.428466,-1.527578 -4.934358,-4.112026 -0.600894,-0.567676 -1.607523,-1.574236 -2.187678,-2.091999 -0.8405779,1.9034 -0.8670266,5.210992 1.170229,6.849217 2.427317,1.951885 5.317536,1.950945 8.014543,1.172799 z m 3.449683,-3.176998 c 1.441449,-2.63116 -0.02773,-6.075721 -2.5686,-7.447862 -1.982386,-1.1369 -4.498343,-1.35878 -6.64066,-0.543385 -1.029942,-0.909943 -2.068727,-1.810172 -3.088122,-2.731769 4.592801,-1.400719 9.729909,-1.112199 14.018616,1.099215 3.128865,1.573651 5.765972,4.165813 7.197706,7.363723 -1.047006,2.340149 -2.791536,4.318529 -4.829273,5.861834 z m -6.739585,-6.024148 c 2.131091,-0.07328 4.456152,1.558323 4.333161,3.786043 -1.544979,-1.297719 -2.595715,-2.15593 -4.333161,-3.786043 z";
		SVGPath img = new SVGPath();
		img.setContent(path);
		
		img.setScaleX(.9);
		img.setScaleY(.9);
		img.setFill(Color.grayRgb(0x22));
		 
		StackPane btn = new StackPane();
		btn.setPadding(new Insets(2,8,2,2));
		btn.getChildren().add(img);
		btn.setOnMouseClicked(event->{
			visible = !visible;
			updateInput();
		});
		
		textfield.getChildren().addAll(input,pass);
		textfield.setPadding(new Insets(2));
		updateInput();
		
		
		field.setAlignment(Pos.CENTER);
		field.setStyle("-fx-background-radius:20 20 20 20;-fx-background-color:#fff;"
				+"-fx-border-color:#222;-fx-border-width:1;-fx-border-radius:20;");
		field.getChildren().addAll(textfield,btn);
		
		pane.setMinSize(624, 32);
		pane.setMaxSize(624, 160);
		pane.getChildren().addAll(text,field);
		
		setAlignment(Pos.CENTER);
		setPadding(new Insets(0,0,32,0));
		setMinSize(624, 64);
		setMaxSize(App.width, 160);
		add(pane);
	}
	private void updateInput() {
		if(visible) {
			Content = pass.getText();
			input.setText(Content);		
			input.setVisible(true);
			pass.setVisible(false);
		}else {
			Content = input.getText();
			pass.setText(Content);	
			input.setVisible(false);
			pass.setVisible(true);
		}
	}
	/**
	 * Return the value of the property onchange.
	 * @return
	 */
	public EventHandler<? super KeyEvent> getOnchange() {
		return onchange;
	}
	/**
	 * Sets the value of the property onchange. 
	 * @param onchange
	 */
	public void setOnchange(EventHandler<? super KeyEvent> onchange) {
		this.onchange = onchange;
	}
	public String getText() {
		return input.getText();
	}
}
