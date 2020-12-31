package JFX.mote.controls;

import java.time.LocalDate;

import JFX.mote.layout.ListLine;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;

public class DateCell extends ListLine {

	private int day;
	private int month;
	private int year;
	
	public DateCell(String string) {
		super(string);
		value = string;
	}
	public DateCell(int i) {
		this(i+"");
	}
	public DateCell(int i, boolean b) {
		this(i);
		this.day = i;
		if(b) {
			setOnMouseClicked(event->{this.setDay();});		
		}
	}
	public DateCell(int day, int month, int year, boolean b) {
		this(day,b);
		this.month = month;
		this.year = year;
	}
	@Override
	protected void updateStyle() {
		setBackground(new Background(new BackgroundFill(backgroundColor,new CornerRadii(20),null)));
		setMaxSize(width, height);
		setMinSize(width, height);
		autosize();
	}
	public int getDay() {
		return Integer.parseInt(value);
	}
	public void setDay() {
		((DatePicker) this.getParent()
				.getParent()
				.getParent()
				.getParent())
		.setDateCell(this);
	}
	public LocalDate getDate() {
		return LocalDate.of(year, month, day);
	}
}
