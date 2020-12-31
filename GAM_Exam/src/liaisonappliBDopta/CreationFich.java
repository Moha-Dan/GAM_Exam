package liaisonappliBDopta;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CreationFich {
	
	private List<Examen> examens;
	private List<CrenauHoraire> crenaux;
	private List<Salle> salles ;
	private List<ContrainteExam> contraintesExam = new ArrayList();
	private List<ContrainteSalle> contraintesSalle = new ArrayList();
	
	private String directory;
	
	
	
	public CreationFich(List<Examen> examens, List<CrenauHoraire> crenaux, List<Salle> salles , String directory) {
		this.examens = examens;
		this.crenaux = crenaux;
		this.salles = salles;
		this.directory = directory;
		for(Examen e : examens) {
			for(ContrainteExam c : e.getContraintesExam()) {
			contraintesExam.add(c);
		}
			for(ContrainteSalle c : e.getContraintesSalle()) {
				contraintesSalle.add(c);
			}
			}
		
	}


	public File creerFile(){
		
		File f = new File(directory);
		try {
			FileWriter fw = new FileWriter(f);
			fw.write("[Exams:"+examens.size()+"]"+System.getProperty("line.separator")); 
			for (Examen e : examens) {
				String po = ""+e.getDuree()+", ";
				fw.write(po);
				List<Etudiant> c = e.getEtudiants();
				int i = 0 ;
				for (Etudiant et : c) {
					if (i < c.size()-1) {
					fw.write(et.getNumeroetu()+", ");
					i++;
					}
					else {
					fw.write(et.getNumeroetu()+"");
					}
				}
				fw.write(System.getProperty("line.separator"));	
			}
			fw.write("[Periods:"+crenaux.size()+"]"+System.getProperty("line.separator")); 
			for (CrenauHoraire c : crenaux) {
				
				fw.write(c.getDate()+", ");
				fw.write(c.getHeure()+", ");
				fw.write(c.getDuree()+", 0");
				fw.write(System.getProperty("line.separator"));	
				}
			fw.write("[Rooms:"+salles.size()+"]"+System.getProperty("line.separator"));
			for(Salle s : salles) {
				fw.write(s.getCapacite()+", 0"+System.getProperty("line.separator"));
			}
			
			fw.write("[PeriodHardConstraints]"+System.getProperty("line.separator"));
			for (Examen e : examens) {
				contraintesExam = e.getContraintesExam();
			for(ContrainteExam cu : contraintesExam) {
				fw.write(cu.getArg()+", ");
				fw.write(cu.getType()+", ");
				fw.write(cu.getArg2()+System.getProperty("line.separator"));
			}
			}
			fw.write("[RoomHardConstraints]"+System.getProperty("line.separator"));
			for (Examen e : examens) {
				contraintesSalle = e.getContraintesSalle();
			for(ContrainteSalle cs : contraintesSalle) {
				fw.write( cs.getArg() +", ");
				fw.write(cs.getType() +System.getProperty("line.separator"));
			}
			}
			fw.write("[InstitutionalWeightings]"+System.getProperty("line.separator"));
			fw.write("TWOINAROW, 7"+System.getProperty("line.separator"));
			fw.write("TWOINADAY, 5"+System.getProperty("line.separator"));
			fw.write("PERIODSPREAD, 1"+System.getProperty("line.separator"));
			fw.write("NONMIXEDDURATIONS,10"+System.getProperty("line.separator"));
			fw.write("FRONTLOAD,100,30,5"+System.getProperty("line.separator"));
		
			fw.flush();
			fw.close();
		}
		catch (IOException e) {
			System.out.print("erreur : ");
			e.printStackTrace();
		}
		
		return f;
	}
	
	
	
	
}


