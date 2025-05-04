package Project;

import java.util.*;

public class Run {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Database myDatabase = new Database();
		List<Student> cyberGroup = new ArrayList<Student>();
		List<Student> comGroup = new ArrayList<Student>();
		

		boolean run = true;
		int choice = 8, ID;
		String filename;
		Student info = null;

		while (run) {
			System.out.println("Vyberte pozadovanou cinnost:");
			System.out.println("1 .. vlozeni noveho studenta");
			System.out.println("2 .. zadani nove znamky");
			System.out.println("3 .. odstraneni studenta");
			System.out.println("4 .. vypis inforamci o studentovi");
			System.out.println("5 .. schopnost studenta");
			System.out.println("6 .. serazeny vypis");
			System.out.println("7 .. obecny studijni prumer");
			System.out.println("8 .. zapis do souboru");
			System.out.println("9 .. cteni ze souboru");
			System.out.println("0 .. ukonceni aplikace");
			
			try {
				choice = sc.nextInt();
			}
			catch(InputMismatchException e) {
				System.out.println("Chybne zadani.");
				break;
			}

			

			switch(choice) {
			case 1:
				
				System.out.println("Zadej jmeno: ");
				String name = sc.next();
				System.out.println("Zadej prijmeni: ");
				String surname = sc.next();
				System.out.println("Zadej datum narozeni (bez mezer): ");
				String birthday = sc.next();
				System.out.println("Zadej skupinu: telekomunikace (0), kyber (1): ");
			    String cyber = sc.next();
			    if(!cyber.equals("0") && !cyber.equals("1")) {
			    	System.out.println("Mimo rozmezi.");
			    	break;
			    }
			    if(cyber.equals("1")) {
			    	if(!myDatabase.setStudent(name, surname, birthday, 1)) System.out.println("Student jiz byl zadan.");
			    	//cyberGroup.add(myDatabase.getStudent(myDatabase.getHighestID()));
			    }
			    else {
			    	if(!myDatabase.setStudent(name, surname, birthday, 0)) System.out.println("Student jiz byl zadan.");
			    	//comGroup.add(myDatabase.getStudent(myDatabase.getHighestID()));
			    }
			    break;
			case 2:
				System.out.println("Zadej ID: ");
				ID = sc.nextInt();
				System.out.println("Zadej znamku: ");
				int mark = sc.nextInt();
				if(myDatabase.getStudent(ID) == null) {
					System.out.println("Student s ID " + ID +  " neexistuje.");
					break;
				}
				myDatabase.setMark(ID, mark);
				break;
			case 3:
				System.out.println("Zadej ID: ");
				ID = sc.nextInt();
				if(myDatabase.removeStudent(ID)) System.out.println("Student s ID " + ID +  " odstranen.");
				else System.out.println("Student s ID " + ID +  " neexistuje.");
				break;
			case 4:
				System.out.println("Zadej ID: ");
				ID = sc.nextInt();
				info = myDatabase.getStudent(ID);
				if(info != null) System.out.println(info.getID() + " " + info.getName() +" "+ info.getSurname() +" "+ info.getBirthday() +" "+ info.getAverage());
				else System.out.println("Student s ID " + ID +  " neexistuje.");
				break;
			case 5:
				System.out.println("Zadej ID: ");
				ID = sc.nextInt();
				info = myDatabase.getStudent(ID);
				if(info == null) {
					System.out.println("Student s ID " + ID +  " neexistuje.");
					break;
				}
				System.out.println(info.skill());
				break;
			case 6:
				cyberGroup.clear();
				comGroup.clear();
				for(int i = 1; i <= myDatabase.getHighestID(); i++) {
					info = myDatabase.getStudent(i);
					if(info == null) continue;
					if(info.getCyber() == 1) cyberGroup.add(info);
					else comGroup.add(info);
				}
				cyberGroup.sort((o1, o2) -> o1.getSurname().compareTo(o2.getSurname()));
				comGroup.sort((o1, o2) -> o1.getSurname().compareTo(o2.getSurname()));
				System.out.println("Celkovy pocet studentu ve skupine kom: " + comGroup.size());
				System.out.println("Serazeni skupiny kom:");
				for(Student student : comGroup) {
					System.out.println(student.getID() + " " + student.getName() +" "+ student.getSurname() +" "+ student.getBirthday() +" "+ student.getAverage());
				}
				System.out.println("\nCelkovy pocet studentu ve skupine kom: " + comGroup.size());
				System.out.println("Serazeni skupiny kyber:");
				for(Student student : cyberGroup) {
					System.out.println(student.getID() + " " + student.getName() +" "+ student.getSurname() +" "+ student.getBirthday() +" "+ student.getAverage());
				}
				break;
			case 7:
				cyberGroup.clear();
				comGroup.clear();
				double average = 0.0;
				for(int i = 1; i <= myDatabase.getHighestID(); i++) {
					info = myDatabase.getStudent(i);
					if(info == null) continue;
					if(info.getCyber() == 1) cyberGroup.add(info);
					else comGroup.add(info);
				}
				for(Student student : comGroup) average += student.getAverage();
				System.out.println("Prumer ve skupine kom je " + average/comGroup.size());
				average = 0.0;
				for(Student student : cyberGroup) average += student.getAverage();
				System.out.println("Prumer ve skupine kyber je " + average/cyberGroup.size());
				break;
			case 8:
				System.out.println("Zadej ID: ");
				ID = sc.nextInt();
				info = myDatabase.getStudent(ID);
				if(info == null) {
					System.out.println("Student s ID " + ID +  " neexistuje.");
					break;
				}
				filename = info.getID() + ".txt";
				if(myDatabase.studentToFile(filename, ID)) System.out.println("Student ulozen.");
				else System.out.println("Studenta se nepodarilo ulozit.");
				break;
			case 9:
				System.out.println("Zadej ID: ");
				ID = sc.nextInt();
				filename = Integer.toString(ID) + ".txt";
				if(myDatabase.studentFromFile(filename)) System.out.println("Student nacten.");
				else System.out.println("Studenta se nepodarilo nacist.");
				info = myDatabase.getStudent(ID);
				if(info == null) {
					System.out.println("Student s ID " + ID +  " neexistuje.");
					break;
				}
				break;
			default:
				/*info = myDatabase.getStudent(1);
				InsertQueries i = new InsertQueries();
				SelectQueries s = new SelectQueries();
				System.out.println(i.delete(1));
				System.out.println(i.createTable());
				s.select();
				i.insertNewUser(info.getName(), info.getSurname(), info.getBirthday(), (float) info.getAverage(), info.getCyber());*/
				run = false;
				break;
			
			}
			
		}
		
	sc.close();
	}

}
