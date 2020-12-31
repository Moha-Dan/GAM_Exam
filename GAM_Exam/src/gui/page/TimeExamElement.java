package gui.page;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import JFX.mote.controls.TextField;
import JFX.mote.controls.TimetableElement;
import JFX.mote.layout.Popup;

public class TimeExamElement extends TimetableElement {

	private String room;
	private String topic;
	
	public TimeExamElement(String room,String topic, LocalDateTime date, LocalTime duration, List<String> members) {
		super(room+"\n"+topic, date,duration);
		this.room = room;
		this.topic = topic;
	}
	public void createPanel(){
		System.out.println("Open");
		Popup pop = new Popup("Ajouter un Examen");
		pop.add(new TextField("Nom"));
		pop.open();
	}
	
}
