package liaisonappliBDopta;

public abstract class Contrainte {
	
	private int arg;
	private String type;
	
	
	Contrainte(int arg, String type){
		this.setArg(arg);
		this.setType(type);
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public int getArg() {
		return arg;
	}


	public void setArg(int arg) {
		this.arg = arg;
	}

}
