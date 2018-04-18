package interfaces;

import java.util.List;
import entity.Sections;

public interface SectionInterface {
	public String insertSection(int id, String name, String status,int userid,String value); 
	public List<Sections> getSections();
	public void deleteSection(Sections cls);
}
