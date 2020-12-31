package JFX.mote.controls;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.stream.Stream;

import JFX.mote.components.Text;
import JFX.mote.layout.Panel;
import JFX.mote.layout.Table;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;

public class DatePicker extends Panel<VBox>{
	
	private int day;
	private int month;
	private int year;
	private LocalDate value;
	private Label label;
	private Table t;
	private TemporalField WEEK_OF_YEAR = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
	private Text text;

	public DatePicker() {
		this(LocalDate.now());
	}
	public DatePicker(LocalDate localDate) {
		super(new VBox());
		text = new Text("");
		setValue(localDate);
		t = new Table();
	}
	private void configureCalendar() {
		value.getMonth();
		label.setText(
			Month.of(month)
				.getDisplayName(TextStyle.FULL,Locale.getDefault())
			+"  "+
			year
				);
		DayOfWeek FOM = LocalDate.of(year, month, 1).getDayOfWeek();
		int offset = 6 - Stream.of(DayOfWeek.values())
				.mapToInt(x->(x.equals(FOM))?0:1)
				.reduce(0,(p,o)->{
					if(o==1) {
						p+=o;
					}else {
						p*=0;
					}
					return p;
				});
		int firstday = 1-offset;
		for(int y = 1;y<=5;y++) {
			t.clearColumn(y);
			if(firstday>=Month.of(month).length(false)+1) {
				firstday = 1;
			}else {
				LocalDate date = null;
				int day = 1;
				if(firstday >= 1) {
					date = LocalDate.of(year, month, firstday);
					day = firstday;
				}else {
					day = Month.of(month==1?12:month-1).length(false)+firstday;
					date = LocalDate.of(year, month==0?12:month==12?1:month, day);
					
				}
				t.addColumn(y, new DateCell(date.get(WEEK_OF_YEAR)));
				for(int x=0;x<7;x++){
					if(firstday==1) {
						day = 1;
					}
					DateCell dl = null;
					if(firstday>=Month.of(month).length(false)+1) {
						if(day>=Month.of(month).length(false)+1)
							day = 1;
						dl = new DateCell(day,month==12?1:month+1,month==12?year+1:year,true);
					}else {
							
						dl = new DateCell(day,(firstday<1?(month==1?12:month-1):month),year,true);
					}
					t.addColumn(y,dl );
					if(value.getDayOfMonth()==day && value.getMonth().getValue() == month && value.getYear() == year) {
						setDateCell(dl);
					}
					day++;
					firstday++;
				}				
			}
		}
		t.update();
	}
	@Override
	public void init() {
		BorderPane top = new BorderPane();
		
		String pathP = "M0,5 L10,0 V10 Z";
		SVGPath imgP = new SVGPath();
		imgP.setContent(pathP);
		
		imgP.setFill(textColor);
		 
		StackPane pMb = new StackPane();
		pMb.setPadding(new Insets(2,8,2,2));
		pMb.getChildren().add(imgP);
		pMb.setOnMouseClicked(event->{
			if(month<=1) {
				month = 12;
				year--;
			}else {
				--month;
			};configureCalendar();});
		
		label = new Label();
		label.setTextFill(textColor);
		
		String pathN = "M10,5 L0,0 V10 Z";
		SVGPath imgN = new SVGPath();
		imgN.setContent(pathN);
		
		imgN.setFill(textColor);
		 
		StackPane nMb = new StackPane();
		nMb.setPadding(new Insets(2,8,2,2));
		nMb.getChildren().add(imgN);
		nMb.setOnMouseClicked(event->{
			if(month>=12) {
				month = 1;
				year++;
			}else {
				++month;
			};configureCalendar();});
		
		top.setCenter(label);
		top.setLeft(pMb);
		top.setRight(nMb);
		add(top);
		
		width = 258;
			
		t.addColumn(0, new DateCell("    "));
		Stream.of(DayOfWeek.values()).forEach(x->{
			String day = x.getDisplayName(TextStyle.SHORT, Locale.getDefault());
			day = day.substring(0, 1).toUpperCase()+day.substring(1, 2)+" " ;
			t.addColumn(0, new DateCell(day));
		});;
		t.setBackground(Color.grayRgb(0xee));
		t.setColor(Color.grayRgb(0x22));
		t.setSize(width,200);
		t.init();
		add(t);
		text.setBackground(Color.grayRgb(0xee));
		text.setColor(Color.grayRgb(0x22));
		text.setPadding(0);
		text.setSize(width, 32);
		text.init();
		add(text);
		setValue(value);
		setMaxSize(width, 226);
		configureCalendar();
	}
	private DateCell lastDateCell = null;
	public void setDateCell(DateCell dateCell) {
		dateCell.setBackground(Color.rgb(0x22, 0xaa, 0xff));
		dateCell.updateStyle();
		if(lastDateCell!=null) {
			lastDateCell.setBackground(Color.TRANSPARENT);
			lastDateCell.updateStyle();
		}
		lastDateCell = dateCell;
		LocalDate localDate = dateCell.getDate();
		setValue(localDate);
	}
	private void setValue(LocalDate localDate) {
		value = (localDate);
		day = localDate.getDayOfMonth();
		month = localDate.getMonthValue();
		year = localDate.getYear();
		text.setText(value.toString());
	}

}
