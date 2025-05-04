package Project;
import java.util.*;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	private Map<Integer, Student> database;
	private int size = 1;
	
	Database(){
		database = new HashMap<Integer, Student>();
	}
	
	public boolean setStudent(String name, String surname, String birthday, int cyber) {
		int ID = database.size() + size;
		System.out.println("ID je " + ID);
		if(database.put(ID, new Student(ID, name, surname, birthday, cyber)) == null) return true;
		else return false;
	}
	
	public Student getStudent(int ID) {
		return database.get(ID);
	}
	
	public void setMark(int ID, int mark) {
		database.get(ID).setMarks(mark);
	}
	
	public boolean removeStudent(int ID) {
		if(database.remove(ID) != null) {
			size++;
			return true;
		}
		size++;
		return false;
	}
	
	public int getHighestID() {
		return database.size() + size;
	}
	
	public boolean studentToFile(String fileName, int ID)
	{
		try {
			FileWriter fw = new FileWriter(fileName);
			BufferedWriter out = new BufferedWriter(fw);
			if(database.get(ID) != null) out.write(database.get(ID).getID() + " " + database.get(ID).getName() + " " + database.get(ID).getSurname() + " " + database.get(ID).getBirthday() + " " + database.get(ID).getAverage() + " " + database.get(ID).getCyber());
			else out.write("null");
			out.newLine();
			out.close();
			fw.close();
		}
		catch (IOException e) {
			System.out.println("Soubor nelze vytvorit");
			return false;
		}
		return true;
	}
	
	public boolean studentFromFile(String filename) {
		FileReader fr=null;
		BufferedReader in=null;
		try {
			fr = new FileReader(filename);
			in = new BufferedReader(fr);
			String line = in.readLine();
			String sep = "[ ]+";
			String[] segments = line.split(sep);
			if (segments.length == 6) {
				Student info = new Student(Integer.parseInt(segments[0]), segments[1], segments[2], segments[3], Integer.parseInt(segments[5]));
				info.setAverage(Double.parseDouble(segments[4]));
				database.put(Integer.parseInt(segments[0]), info);
			}
			
		}
		catch (IOException e) {
			System.out.println("Soubor  nelze otevřít");
			return false;
		} 
		catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			System.out.println("Chyba integrity dat v souboru");
			return false;
		}
		finally
		{
			try
			{	if (in!=null)
				{
					in.close();
					fr.close();
				}
			}
			catch (IOException e) {
				System.out.println("Soubor  nelze zavrit");
				return false;
			} 
		}
		
		return true;
	}

}
