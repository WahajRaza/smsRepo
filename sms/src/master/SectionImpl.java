package master;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import entity.Sections;
import entity.var;
import interfaces.SectionInterface;

public class SectionImpl implements SectionInterface{
	
	static List<Sections> sectionList = new ArrayList<Sections>();
	@Override
	public String insertSection(int id, String name, String status, int userid, String value) {
		try {
			var.cst = var.conn.prepareCall("{? =call zk.add_section(?,?,?,?,?)}");
			var.cst.registerOutParameter(1, Types.VARCHAR);
			var.cst.setInt(2, id);
			var.cst.setString(3, name);
			var.cst.setString(4, status);
			var.cst.setInt(5, userid);
			var.cst.setString(6, value);
			var.cst.execute();
			return var.cst.getObject(1).toString();

			// Clients.showNotification(cst.getObject(1).toString());
		} catch (Exception e) {
			return "false arha hai" + e;
		}
	}

	@Override
	public List<Sections> getSections() {
		List<Sections> sections = new ArrayList<Sections>();
		try {

			var.stmt = var.conn.prepareStatement("select * from zk.get_section()");
			var.rs = var.stmt.executeQuery();
			while (var.rs.next()) {
				sections.add(new Sections(var.rs.getInt(1),var.rs.getString(2), var.rs.getString(5), var.rs.getInt(7), var.rs.getString(10)));
			}
		} catch (Exception e) {
			System.out.println("wahaj exception " + e);
		}

		return sections;
	}

	@Override
	public void deleteSection(Sections sec) {
		try {
			System.out.println(sec.getId());
			var.stmt = var.conn.prepareStatement("delete from zk.schx_section where schx_section_id="+sec.getId());
			var.stmt.execute();
		} catch (Exception e) {
			System.out.println("wahaj exception " + e);
		}
	}

}
