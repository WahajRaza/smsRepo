package master;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import entity.Classes;
import entity.var;
import interfaces.ClassInterface;

public class ClassImpl implements ClassInterface {
	static List<Classes> classList = new ArrayList<Classes>();
	
	public String insertClass(int id, String name, String status, int userid, String value) {

		try {
			var.cst = var.conn.prepareCall("{? =call zk.add_class(?,?,?,?,?)}");
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

	public List<Classes> getClasses() {
		List<Classes> classes = new ArrayList<Classes>();
		try {

			var.stmt = var.conn.prepareStatement("select * from zk.get_class()");
			var.rs = var.stmt.executeQuery();
			while (var.rs.next()) {
				classes.add(new Classes(var.rs.getInt(1),var.rs.getString(2), var.rs.getString(5), var.rs.getInt(7), var.rs.getString(10)));
			}
		} catch (Exception e) {
			System.out.println("wahaj exception " + e);
		}

		return classes;
	}

	@Override
	public void deleteClass(Classes cls) {
		try {
			System.out.println(cls.getId());
			var.stmt = var.conn.prepareStatement("delete from zk.schx_class where schx_class_id="+cls.getId());
			var.stmt.execute();
		} catch (Exception e) {
			System.out.println("wahaj exception " + e);
		}
		
	}
}
