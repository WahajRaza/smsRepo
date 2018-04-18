package interfaces;

import java.util.List;

import entity.Classes;

public interface ClassInterface {

	public String insertClass(int id, String name, String status,int userid,String value); 
	public List<Classes> getClasses();
	public void deleteClass(Classes cls);
}
