package Project;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Student implements ClenVUT{
	private int ID;
	private double average = 0;
	private int cyber;
	private String name;
	private String surname;
	private String birthday;
	private List<Integer> marks = new ArrayList<Integer>();
	private Map<String, String> dictionary = new HashMap<String, String>();
	
	
	Student(int ID, String name, String surname, String birthday, int cyber) {
		this.ID = ID;
		this.name = name;
		this.surname = surname;
		this.birthday = birthday;
		this.cyber = cyber;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getBirthday() {
		return birthday;
	}
	
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	public void setMarks(int mark) {
		marks.add(mark);
	}
	
	public double getAverage() {
		average = 0;
		if(marks.size() == 0) return 1.0;
		for(int i = 0; i < marks.size(); i++) {
			average += marks.get(i);
		}
		average /= marks.size();
		return average;
	}
	
	public void setAverage(double average) {
		this.average = average;
	}
	
	public void setCyber(int cyber) {
		this.cyber = cyber;
	}
	
	public int getID() {
		return this.ID;
	}
	
	public void setDictionary() {
		dictionary.put("a", ".-");
		dictionary.put("b", "-...");
		dictionary.put("c", "-.-.");
		dictionary.put("d", "-..");
		dictionary.put("e", ".");
		dictionary.put("f", "..-.");
		dictionary.put("g", "--.");
		dictionary.put("h", "....");
		dictionary.put("i", "..");
		dictionary.put("j", ".---");
		dictionary.put("k", "-.-");
		dictionary.put("l", ".-..");
		dictionary.put("m", "--");
		dictionary.put("n", "-.");
		dictionary.put("o", "---");
		dictionary.put("p", ".--.");
		dictionary.put("q", "--.-");
		dictionary.put("r", ".-.");
		dictionary.put("s", "...");
		dictionary.put("t", "-");
		dictionary.put("u", "..-");
		dictionary.put("v", "...-");
		dictionary.put("w", ".--");
		dictionary.put("x", "-..-");
		dictionary.put("y", "-.--");
		dictionary.put("z", "--..");
	}
	
	public String skill() {
		if(this.cyber == 1) {
			String input = this.name + " " + this.surname;
			try {
				MessageDigest digest = MessageDigest.getInstance("SHA-256");
				byte[] encodedHash = digest.digest(input.getBytes());
				StringBuilder hexString = new StringBuilder();
	            for (byte b : encodedHash) {
	                String hex = Integer.toHexString(0xff & b);
	                if (hex.length() == 1) {
	                    hexString.append('0');
	                }
	                hexString.append(hex);
	            }
	            return hexString.toString();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		else {
			String morse = this.name + " " + this.surname;
			String result = "";
			morse = morse.toLowerCase();
			this.setDictionary();
			for(int i = 0; i < morse.length(); i++) {
				if(morse.charAt(i) == ' ') continue;
				char a = morse.charAt(i);
				result += dictionary.get(String.valueOf(a)) + " ";
			}
			
			return result;
		}
	}
	
	public int getCyber() {
		return this.cyber;
	}
}
