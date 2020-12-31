package JFX.mote.controls;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import JFX.mote.App;
import JFX.mote.Component;
import JFX.mote.Element;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * @author Mote
 *
 * @param <T extends TimetableElement>
 */
public class Timetable<T extends TimetableElement> extends Component implements Runnable {
	private TemporalField WEEK_OF_YEAR = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
	List<T> TimeElements;
	LocalDate lookup;
	private int lookupDays = 7;
	private int lookupHours = 12;
	private int offestHours = 8;
	List<List<Element>> content = new ArrayList<List<Element>>();
	Canvas table;
	private int divWidth;
	private int divHeight;
	private int mode;//actual only one mode exist but other mode are comming soon
	private GraphicsContext ctx;
	private Thread threader;
	/**
	 * @param List<T extends TimetableElement>
	 * <br>
	 * @see List
	 * <br>
	 * @see TimetableElement
	 */
	public Timetable(List<T> diary) {
		super();
		TimeElements = diary;
		lookup = LocalDate.now();
	}
	@Override
	public void setSize(double width, double height) {
		super.setSize(width, height);
		update();
		this.height = (int) height;
		this.width = (int) width;
		table.setWidth(width);
		table.setHeight(height);
	}
	/**
	 * upadte the timetable canvas
	 */
	public void update() {
		ctx.clearRect(0, 0, width, height);
		divWidth = width/(lookupDays+1);
		divHeight = height/(lookupHours*2+1);
		int d = 16;
		for(DayOfWeek x : DayOfWeek.values()){
			String day = x.getDisplayName(TextStyle.FULL, Locale.getDefault());
			day = day.substring(0, 1).toUpperCase()+day.substring(1) ;
			ctx.setFill(Color.grayRgb(96));
			d+=divWidth;
			ctx.fillText(day, d,16);
			ctx.setFill(Color.grayRgb(178));
			ctx.fillRect(x.getValue()*divWidth, 0, 2, height);
		}
		ctx.fillRect(0, divHeight, width, 2);
		ctx.setFill(Color.grayRgb(119));
		for(int h = 0;h<lookupHours*2;h++) {
			String hr = h%2==0?(h/2+offestHours)+":00":((h-1)/2+offestHours)+":30";
			if(h%2==0)
				ctx.fillRect(divWidth, (h+2)*divHeight, width-divWidth, 1);
			ctx.fillText(hr, 8,(h+2.5)*divHeight);
		}
		int week = lookup.get(WEEK_OF_YEAR);
		TimeElements.forEach(te ->{
			if(te.isWeek(week)) {
				drawCase(te);
			}
		});
		ctx.setFill(Color.RED);
		LocalDate date = LocalDate.now();
		LocalDateTime time = LocalDateTime.now();
		double y = ((time.getHour()+1)*2)+((time.getMinute()+0.0)/30);
		y = (y-16)*divHeight;
		ctx.fillRect(date.getDayOfWeek().getValue()*divWidth, y-2, divWidth, 2);
	}
	private void drawCase(TimetableElement el) {
		int Wc = divWidth;
		int Hd = divHeight;
		int x = el.getDay()*Wc;
		int y = (((el.getHour()+1)*2)+(el.getMinute()>30?1:0)-16)*Hd;
		x += 2;
		Wc -= 2;
		LocalTime time = el.getDur();
		Hd *=  ((time.getHour())*2)+((time.getMinute()+0.0)/30);
		int padding = 5; 
		int font = (int) ctx.getFont().getSize();
		ctx.setFill(Color.WHITE);
		ctx.fillRect(x, y, Wc, Hd);
		el.setBound(x, y, Wc, Hd);
		ctx.setFill(Color.rgb(34,176, 255));
		ctx.fillRect(x, y, 2, Hd);
		ctx.fillText(el.getName(), x+padding, y+padding+font);
	}
	private void forEachClickEvent(MouseEvent x) {
		TimetableElement c = TimeElements.stream().reduce(null,(p,o)->{
			if(o.isClick(x)) {
				return o;
			}
			return p;
		});
		if(c!=null) {
			c.open();
		}else {
			try {
				TimeElements.get(0).createPanel();				
			}catch(NullPointerException e){
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	};
	@Override
	protected void updateStyle() {
		super.updateStyle();
		table.autosize();
		autosize();
	}
	@Override
	public void init() {
		loaded = true;
		table = new Canvas();
		ctx = table.getGraphicsContext2D();
		table.setOnMouseClicked(x->{
			forEachClickEvent(x);
		});
		height = width = 512;
		width += 128;
		table.setWidth(width);
		table.setHeight(height);
		autosize();
		add(table);
		update();
		if(looping) {
			threader = App.threadManger.add(this);
			threader.start();
		}
	}
	private boolean looping = false;
	/**
	 * start the updating when the Component has added on a Panel.
	*/
	public void start(){
		looping = true;
	}
	/**
	 * stop the updating when the Component has added on a Panel.
	*/
	public void stop(){
		looping = false;
	}
	@Override
	public void run() {
		try {
			while(looping) {
				this.update();
				Thread.sleep(1000);
			}
		}catch(Exception e) {
			e.printStackTrace();
			threader.interrupt();
			looping=false;
		}
	}
}
