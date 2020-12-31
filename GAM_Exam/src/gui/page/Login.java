package gui.page;

import JFX.mote.Component;
import JFX.mote.components.Text;
import JFX.mote.components.Title;
import JFX.mote.controls.Button;
import JFX.mote.controls.PassField;
import JFX.mote.controls.TextField;
import JFX.mote.layout.Panel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Login extends Panel<VBox>{
	private TextField username;
	private PassField password;
	private Text errormsg;
	private Button submit;
	public Login(String name){
		this(name,null);
	}
	public Login(String name,EventHandler<ActionEvent> onclick){
		super(new VBox(),name);
		add(new Title("Connection"));
		username = new TextField("Name2");
		add(username);
		password = new PassField("Name2");
		add(password);
		if(onclick==null) {
			onclick = x->{this.next();};
		}
		submit = new Button("Name",onclick);
		add(submit);
		layout.setAlignment(Pos.CENTER);
		setAlignment(Pos.CENTER);
	}

	@Override
	protected void updateStyle() {	
	}
	public void  setSubmitAction(EventHandler<ActionEvent> onclick) {
		submit.setOnclick(onclick);
	}
	public String getUsername(){
		return username.getText();
	}
	public String getPassWord (){
		return password.getText();
	}
	public void setErrorMessage(String value) {
		if(errormsg==null) {
			errormsg = new Text(value);
			errormsg.setColor(Color.RED);
			add(errormsg);
		}else {
			errormsg.setText(value);
		}
	}
}
