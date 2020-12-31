package JFX.mote.components;

import javafx.scene.text.Font;

public class Title extends Text{
	public Title(String value) {
		super(value);
	}
	@Override
	protected void updateStyle() {
		text.setTextFill(textColor);
		text.setFont(new Font(64));
	}
}
