import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import JFX.mote.Frame;
import gui.page.Login;
import gui.page.MainApp;
import gui.page.TimeExamElement;
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Launcher {

	public static void main(String[] args) {
		System.out.println("app");
		Frame frame = new Frame("Xame");
		Application box = new Application() {
			@Override
			public void start(Stage arg0) throws Exception {
			
				
			}
		};
		box.launch(box.getClass(),args);
		frame.open();
		/*
		Login login = new Login("login");
		login.setErrorMessage("invalid");
		List<TimeExamElement> diary = Arrays.asList(
				new TimeExamElement("F 021","Français",LocalDateTime.of(2020, 12, 30, 12, 30),LocalTime.of(1, 30),null)
				);
		List<List<TimeExamElement>> diaries = Arrays.asList(
				diary,diary
				);
		MainApp maz = new MainApp("app");
		System.out.println(diaries);
		maz.getCalendar().setDiaries(diaries);
		login.setNext("app");
		frame.setPanel(login);
		frame.show();
		 */ 
	}

}
