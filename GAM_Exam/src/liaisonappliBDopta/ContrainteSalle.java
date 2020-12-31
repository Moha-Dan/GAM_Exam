package liaisonappliBDopta;

public class ContrainteSalle extends Contrainte{
	
	
	private int arg2;
	
	
	ContrainteSalle(int i1 , String c,int idsalle){
		super(i1, c);
		this.setArg2(idsalle);
	}


	public int getArg2() {
		return arg2;
	}


	public void setArg2(int arg2) {
		this.arg2 = arg2;
	}

	

}
