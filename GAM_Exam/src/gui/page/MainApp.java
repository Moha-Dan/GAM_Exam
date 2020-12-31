package gui.page;

import JFX.mote.Position;
import JFX.mote.layout.Panel;
import javafx.scene.layout.BorderPane;

public class MainApp extends Panel<BorderPane>{
	public MainApp(String name) {
		super(new BorderPane(),name);
		side = new Side();
		calendar = new Calendar();
	}
	private Calendar calendar;
	private Side side;
	public Calendar getCalendar() {
		return calendar;
	}
	public Side getSide() {
		return side;
	}
	public void init() {
		side.position = Position.Left;
		add(side);
		calendar.position = Position.Center;
		add(calendar);
		System.out.println("é");
	}
}