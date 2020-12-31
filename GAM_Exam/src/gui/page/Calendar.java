package gui.page;

import java.util.List;

import JFX.mote.Position;
import JFX.mote.controls.Switch;
import JFX.mote.controls.Timetable;
import JFX.mote.layout.Flex;
import JFX.mote.layout.Panel;
import javafx.scene.layout.BorderPane;

public class Calendar extends Panel<BorderPane>{
	Timetable<?> table;
	public Calendar() {
		super(new BorderPane());
	}
	private Flex flex;
	private List<List<TimeExamElement>> diaries;
	public void setDiaries(List<List<TimeExamElement>> diaries) {
		this.diaries = diaries;
		updateDiaries();
	}
		public void updateDiaries() {
			if(loaded) {
				flex.clear();
				diaries.forEach(diary->{
					Timetable<TimeExamElement> temp = new Timetable<TimeExamElement>(diary);
					flex.add(temp);
				});
			}
		}
	public void init() {
		loaded = true;
		Switch viewmode = new Switch("colonne","ligne");
		System.out.println(viewmode);
		flex = new Flex();
		viewmode.setOnchange(x->{
			flex.setType(x.isValue()?Flex.Column:Flex.Row);
			return true;
		});
		updateDiaries();
		viewmode.position = Position.Top;
		flex.position = Position.Center;
		flex.setFollow(true);
		flex.setSize(640, 512);
		add(flex);
		add(viewmode);
		setSize(720, 640);
	}
	public void start(){
		table.start();
	}
}
