import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import JFX.mote.Frame;
import gui.page.Login;
import gui.page.MainApp;
import gui.page.TimeExamElement;

public class Launcher {

	public static void main(String[] args) {
		Frame frame = new Frame("Xame");
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
	}

}
