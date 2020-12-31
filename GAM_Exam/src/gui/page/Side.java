package gui.page;

import JFX.mote.App;
import JFX.mote.Position;
import JFX.mote.components.Text;
import JFX.mote.components.Title;
import JFX.mote.controls.DatePicker;
import JFX.mote.controls.TextField;
import JFX.mote.layout.Flex;
import JFX.mote.layout.Panel;
import JFX.mote.layout.Popup;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class Side extends Panel<BorderPane>{
	public Side() {
		super(new BorderPane());
	}
	public Side(String name) {
		super(new BorderPane(),name);
	}
	public void init() {
		layout.setMinSize(256, App.height-32);
		setMaxSize(256, App.height-48);
		setMinSize(256,App.height-48);
		setBackground(Color.grayRgb(0x22));
		setColor(Color.grayRgb(0xee));
		setPadding(new Insets(16));
		Title t = new Title("Filtre");
		t.position = Position.Top;
		add(t);	
		Flex ttl = new Flex();
		ttl.add("name1");
		ttl.add("name2");
		ttl.add("name3");
		ttl.add("name4");
		ttl.add("name5",event->{
			Popup pop = new Popup("Filter");
			pop.add(new TextField("Filtre"));
			pop.add(new Text("blablabla blablabla blablabla blablabla blablabla \nblablabla blablabla blablabla blablabla blablabla"));
			pop.open();
			});
		ttl.position = Position.Center;
		ttl.setFontSize(16);
		ttl.setFollow(true);
		ttl.setSize(256,256);
		add(ttl);
		DatePicker calendar = new DatePicker();
		calendar.position = Position.Bottom;
		add(calendar);
		
	}
}
