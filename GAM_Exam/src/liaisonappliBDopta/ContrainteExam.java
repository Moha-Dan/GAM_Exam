package liaisonappliBDopta;

public class ContrainteExam extends Contrainte{
	

	private int arg2;
	
	
	ContrainteExam(int i1 , String c,int arg2){
		super(i1, c);
		this.setArg2(arg2);
	}


	public int getArg2() {
		return arg2; 
	}


	public void setArg2(int arg2) {
		this.arg2 = arg2;
	}

	
}
