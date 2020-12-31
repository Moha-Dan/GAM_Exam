package JFX.mote;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public abstract class Component extends StackPane implements Element{
	public Position position = Position.Center;
	protected boolean loaded;
	protected void add(Node ...n) {
		getChildren().addAll(n);
	}
	public Node toNode(){
		init();
		loaded = true;
		updateStyle();
		return this;
	}
	public abstract void init();
	
	public void setColor(Paint color) {
		textColor = color;
		if(loaded)updateStyle();
	}
	public void setFontSize(double size) {
		fontSize  = size;
		if(loaded)updateStyle();
	}
	public void setBackground(Paint color) {
		backgroundColor = color;
	}
	/**
	 * Set 
	 * @param width
	 * @param height
	 */
	public void setSize(double width, double height) {
		this.width = (int) width;
		this.height = (int) height;
	}
	/**
	 * Set the Padding of element;
	 * @param padding
	 */
	public void setPadding(int padding) {
		this.padding = padding;
	}
	
	protected Paint textColor;
	protected Paint backgroundColor;
	protected double fontSize;
	protected int width;
	protected int height;
	protected int padding;
	
	protected void updateStyle() {}
	/**
	 * Create the heredity of the component on current element.
	 * @param component 
	 */
	public void heredite(Component component) {
		this.backgroundColor = backgroundColor==null?
				(component.backgroundColor!=null?component.backgroundColor:(Color.grayRgb(0xee)))
						:backgroundColor;
		this.textColor = textColor==null?
				(component.textColor!=null?component.textColor:(Color.grayRgb(0x22)))
				:textColor;
		this.fontSize = fontSize==0?
				(component.fontSize!=0?component.fontSize:16)
				:fontSize;
		this.width = width==0?component.width:width;
		this.height = height==0?component.height:height;
	}
	@Override
	public String toString() {
		return super.toString()+"{"+width+","+height+","+backgroundColor+"}";
	}
}
