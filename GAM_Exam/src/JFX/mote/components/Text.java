package JFX.mote.components;

import JFX.mote.Component;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.text.Font;

public class Text extends Component{
	protected String value;
	protected Label text;
	public Text(String value) {
		this.value = value;
	}
	@Override
	public void init() {
		text = new Label(value);
		text.setPadding(new Insets(32));
		text.setAlignment(Pos.CENTER);
		add(text);
	}
	@Override
	protected void updateStyle() {
		fontSize = 16;
		text.setTextFill(textColor);
		text.setPadding(new Insets(padding));
		setBackground(new Background(new BackgroundFill(backgroundColor,null,null)));
		text.setFont(new Font(fontSize));
		autosize();
	}
	public void setText(String string) {
		if(loaded) {
			text.setText(string);
			value = string;			
		}
		
	}
}
