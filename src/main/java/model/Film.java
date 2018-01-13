package model;

import java.util.ArrayList;
import java.util.List;


public class Film {
	public String name;
	public List<String> categories;
	public String date;
	public String presseGrade;
	public String publicGrade;
	
	public Film(){
		categories = new ArrayList<String>();
		presseGrade="   ";
		publicGrade ="   ";
		date = "    ";
	}
	
	public String toString(){
		return "Name : "+ name + "\tCategories : " + categories.toString() + "\tdate : " + date + "\t presseGrade " + presseGrade + "\tpublicGrade :" +publicGrade +"\n";
	}
}
