package JFX.mote.layout;

import JFX.mote.Component;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class ListLine extends Component{
	protected String value;
	protected javafx.scene.text.Text text;
	public void init() {
		text = new javafx.scene.text.Text(value);
		text.setTextAlignment(TextAlignment.CENTER);
		add(text);
	}
	public ListLine(String value) {
		this.value = value;
	}

	public ListLine(String string, EventHandler<? super MouseEvent> onclick) {
		this(string);
		setOnMouseClicked(onclick);
	}
	@Override
	protected void updateStyle() {
		text.setFill(textColor);
		text.setFont(new Font(fontSize));
	}
}
