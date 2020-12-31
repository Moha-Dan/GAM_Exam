package JFX.mote.controls;

import JFX.mote.App;
import JFX.mote.Component;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class Button extends Component {
	private String value;
	private EventHandler<ActionEvent> onclick;
	private javafx.scene.control.Button btn;
	public Button(String value,EventHandler<ActionEvent> onclick) {
		super(); 
		this.value = value;
		this.onclick = onclick;
	}
	@Override
	public void init() {
		btn = new javafx.scene.control.Button(value);
		btn.setOnAction(onclick);
		setAlignment(Pos.CENTER);
		setPadding(new Insets(32));
		btn.setMinSize(256, 32);
		setMinSize(256, 32);
		setMaxSize(App.width, 64);
		btn.setStyle("-fx-background-radius:20;-fx-background-color:#4D8,#4D1;");
		add(btn);
		loaded =  true;
	}
	public void setOnclick(EventHandler<ActionEvent> onclick) {
		this.onclick = onclick;
		if(loaded) {
			btn.setOnAction(onclick);
		}
	}
}

