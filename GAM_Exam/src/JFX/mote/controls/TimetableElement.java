package JFX.mote.controls;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;

import JFX.mote.components.Text;
import JFX.mote.layout.Popup;
import javafx.scene.input.MouseEvent;

public class TimetableElement{
	private TemporalField WEEK_OF_YEAR = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
	private String name;
	private LocalTime dur;
	private LocalDateTime date;
	public TimetableElement(String name,LocalDateTime date) {
		this(name,date,LocalTime.of(0, 30));
	}
	public TimetableElement(String name,LocalDateTime date,LocalTime dur) {
		this.name = name;
		this.date = date;
		setDur(dur);
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public boolean isWeek(int week) {
		return date.get(WEEK_OF_YEAR)==week;
	}
	public int getDay() {
		return date.getDayOfWeek().getValue();
	}
	public String getName() {
		return name;
	}
	public int getHour() {
		return date.getHour();
	}
	public int getMinute() {
		return date.getMinute();
	}
	private int[] bound = new int[4];
	/**
	 * This method can setup the Box of the element 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void setBound(int x, int y, int w, int h) {
		bound[0] = x;
		bound[1] = y;
		bound[2] = w+x;
		bound[3] = h+y;
		
	}
	/**
	 * When you click on the timetable this method detect the collision between the mouse and this timetable element 
	 * 
	 * @param e
	 * @return
	 * 
	 * @see Timetable
	 */
	public boolean isClick(MouseEvent e) {
		return 
				(e.getX()>bound[0] && e.getX()<(bound[2])) && //test sur X
				(e.getY()>bound[1] && e.getY()<(bound[3])) 	//test sur Y
				;
	}
	/**
	 * When you click on the timetable you can add element if they are any event at this time
	 * @see Timetable
	 */
	public void createPanel() {
	}
	/**
	 * When you click on the timetable you can open element if a event is detected
	 * else you can add Event 
	 * @see TimetableElement.createPanel 
	 * <br>
	 * @see Timetable
	 */
	public void open() {
		Popup a = new Popup(getName());
		a.add(new Text(getDate()+""));
		a.open();
	}
	/*
	public static TimetableElement instance(String name, LocalDateTime date) {
		class TimetableElementExample extends TimetableElement{

			public TimetableElementExample(String name, LocalDateTime date) {
				super(name, date);
			}
			
		}
		return new TimetableElementExample(name, date);
	}*/
	/**
	 * @return the dur
	 */
	public LocalTime getDur() {
		return dur;
	}
	/**
	 * @param dur the dur to set
	 */
	protected void setDur(LocalTime dur) {
		this.dur = dur;
	}
}
