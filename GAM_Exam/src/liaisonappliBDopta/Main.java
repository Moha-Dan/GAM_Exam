package liaisonappliBDopta;

public class Main {

	public static void main(String[] args) {
		
		Login a = new Login("Tom","deusvult");
		Login b = new Login("PasTom","deusvult");
		Login c = new Login("PasTom","Pasdeusvult");
		Login d = new Login("Tom","Pasdeusvult");
		if (a.getAutorise() == true) {
			System.out.println("ça marche");
		}
		else if (a.getAutorise() == false)  {
			System.out.println("ça marche pas ");
		}
		if (b.getAutorise() == true) {
			System.out.println("ça marche");
		}
		else if (b.getAutorise() == false)  {
			System.out.println("ça marche pas ");
		}
		if (c.getAutorise() == true) {
			System.out.println("ça marche");
		}
		else if (c.getAutorise() == false)  {
			System.out.println("ça marche pas ");
		}
		if (d.getAutorise() == true) {
			System.out.println("ça marche");
		}
		else if (d.getAutorise() == false)  {
			System.out.println("ça marche pas ");
		}

	}

}
