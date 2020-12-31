package JFX.mote;

import java.util.ArrayList;
import java.util.stream.Stream;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Layout extends GridPane{
	public static final int DARKMODE = 1;
	public static final int LIGHTMODE = 0;
	private int row=0;
	private int col=0;
	private int mode;
	public int getMode() {
		return mode;
	}
	/**
	 * Set the display mode Light or Dark
	 * @param mode
	 */
	public void setMode(int mode) {
		this.mode = mode;
	}
	private LayoutDisplay display = LayoutDisplay.Column;
	private static boolean loaded;
	private double spacing = 2;
	
	public double getSpacing() {
		return spacing;
	}
	public void setSpacing(double spacing) {
		this.spacing = spacing;
	}
	private java.util.List<Component> childs = new ArrayList<Component>();
	public Layout() {
		super();
	}
	public void setDisplay(LayoutDisplay display) {
		this.display = display;
		switch(display) {
			case Row:
				//this.display = new HBox(spacing);
				col=0;
				row=0;
				break;
			case Column:
				//this.display = new VBox(spacing);
				col=0;
				row=0;
				break;
		}
		childs.clear();
		getChildren().clear();
		getColumnConstraints().clear();
		getRowConstraints().clear();
		setHgap(row);
		setVgap(col);
		setAlignment(Pos.CENTER);
		if(mode == DARKMODE) {
			setStyle("-fx-background-color:#222;");			
		}else {			
			setStyle("-fx-background-color:#eee;");
		}
		
	}

	public void add(Component ...nodes) {
		if(loaded) {	
			Stream.of(nodes).forEach(
					x->adder(x)
					);	
		}else {
			Stream.of(nodes).forEach(childs::add);			
		}
	}
	private void adder(Component node) {
		if(mode == DARKMODE) {	
			node.setColor(Color.grayRgb(0xee));	
		}else {			
			node.setColor(Color.grayRgb(0x22));
		}
		node.toNode();
		switch(display) {
			case Row:
					addColumn(node);
				break;
			case Column:
					addRow(node);
				break;
		}
	}
	/**
	 * Removes all of the elements from this list.
	 */
	public void clear() {
		childs.clear();
	}
	public void init() {
		childs.forEach(x->{
			adder(x);
		});
		loaded = true;
	}
	/**
	 * Appends node to the end of the Row list.
	 * @param node
	 */
	public void addRow(Node node) {
		setHgap(row);
		super.addRow(row++, node);
	}
	/**
	 * Appends node to the end of the Column list.
	 * @param node
	 */
	public void addColumn(Node node) {
		setVgap(col);
		super.addColumn(col++, node);
		System.out.println(node);
	}
}
