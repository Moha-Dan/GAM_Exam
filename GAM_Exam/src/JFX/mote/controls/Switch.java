package JFX.mote.controls;

import java.util.function.Predicate;

import JFX.mote.Component;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Switch extends Component{
	private Canvas canvas;
	private boolean value = false;
	private Node True;
	private Node False;
	private Predicate<Switch> onchange = event->{
		System.out.println(event);
		return value;};
	private GraphicsContext ctx; 
	public Switch(String string, String string2) {
		this(new Label(string),new Label(string2));
		((Label) True).setAlignment(Pos.CENTER);
		((Label) False).setAlignment(Pos.CENTER);
	}

	public Switch(Node a, Node b) {
		True = a;
		False = b;
			
	}
	public void init() {
		VBox pane = new VBox();
		canvas = new Canvas();
		ctx = canvas.getGraphicsContext2D();
		setOnMouseClicked(x->{
			swicthOnOff();
		});
		height = 32;
		width = 128;
		canvas.setWidth(width);
		canvas.setHeight(height);
		setWidth(width);
		setHeight(height);
		add(canvas);
		add(True);
		add(False);
		autosize();
		swicthOnOff();
		setPadding(16);
	}
	private void swicthOnOff() {
		value = !value;
		ctx.clearRect(0, 0, width, height);
		if(value) {
			True.setVisible(true);
			False.setVisible(false);
			ctx.setFill(Color.rgb(128, 200, 255));
			ctx.fillRoundRect(width*0.05, height/4, width*0.8, height/2,height/2, height/2);
			ctx.setFill(Color.rgb(255, 255, 255));
			ctx.fillOval(width*0.7, height/4, height/2, height/2);
			ctx.setStroke(Color.rgb(32, 170, 255));
			ctx.setLineWidth(4);
			ctx.strokeOval(width*0.7, height/4, height/2, height/2);
		}else {
			True.setVisible(false);
			False.setVisible(true);
			ctx.setFill(Color.rgb(255, 200, 128));
			ctx.fillRoundRect(width*0.05, height/4, width*0.8, height/2,height/2, height/2);
			ctx.setFill(Color.rgb(255, 255, 255));
			ctx.fillOval(width*0.05, height/4, height/2, height/2);
			ctx.setStroke(Color.rgb(255, 170, 32));
			ctx.setLineWidth(4);
			ctx.strokeOval(width*0.05, height/4, height/2, height/2);
		}
		onchange.test(this);
	}
	/**
	 * Return the value of the property onchange.
	 * @return
	 */
	public Predicate<Switch> getOnchange() {
		return onchange;
	}
	/**
	 * Sets the value of the property onchange. 
	 * @param onchange
	 */
	public void setOnchange(Predicate<Switch> onchange) {
		this.onchange = onchange;
	}

	public boolean isValue() {
		return value;
	}
	
} 