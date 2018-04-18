package student_module;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.Period;
import entity.Student;
import entity.var;

public class StudentImpl implements StudentInterface{

	//=================================================================================================================
	//Student Master
	@Override
	public int insertStudent(int id, String name, String reg_no, String address, String town, String city, String landline,
			String cell_no,String jclass, Date jdate, String status, int userid, String value) {
		int i = 0;
		try {
			var.cst = var.conn.prepareCall("{? = call zk.add_student(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			var.cst.registerOutParameter(++i, Types.NUMERIC);
			var.cst.setInt(++i, id);
			var.cst.setString(++i, name);
			var.cst.setString(++i, reg_no);
			var.cst.setString(++i, address);
			var.cst.setString(++i, town);
			var.cst.setString(++i, city);
			var.cst.setString(++i, landline);
			var.cst.setString(++i, cell_no);
			var.cst.setString(++i, jclass);
			var.cst.setDate(++i, new java.sql.Date(jdate.getTime()));
			var.cst.setString(++i, status);
			var.cst.setInt(++i, userid);
			var.cst.setString(++i, value);
			var.cst.execute();
			return Integer.parseInt(var.cst.getObject(1).toString());

			// Clients.showNotification(cst.getObject(1).toString());
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		}
	}
	@Override
	public List<Student> getStudent() {
		List<Student> student = new ArrayList<Student>();
		try {

			var.stmt = var.conn.prepareStatement("select * from zk.get_student()");
			var.rs = var.stmt.executeQuery();
			while (var.rs.next()) {
				student.add(new Student(var.rs.getInt(1), var.rs.getString(2), var.rs.getString(3), var.rs.getString(4),
						var.rs.getString(5), var.rs.getString(6), var.rs.getString(7), var.rs.getString(8), var.rs.getString(9), 
						var.rs.getDate(10), var.rs.getString(13),var.rs.getInt(15),
						var.rs.getString(18)));
			}
		} catch (Exception e) {
			System.out.println("wahaj exception " + e);
		}

		return student;
	}
	@Override
	public void deleteStudent(Student student) {
		try {
			var.stmt = var.conn.prepareStatement("delete from zk.schx_student_master where schx_student_master_id=" + student.getId());
			var.stmt.execute();
		} catch (Exception e) {
			System.out.println("wahaj exception " + e);
		}
		
	}
	
	//=================================================================================================================
	//Student Details
	@Override
	public List<Student> getStudentDetail(int sid) {
		List<Student> student = new ArrayList<Student>();
		try {

			var.stmt = var.conn.prepareStatement("select * from zk.get_studentdetail(" + sid + ") ");
			var.rs = var.stmt.executeQuery();
			while (var.rs.next()) {
				student.add(new Student(var.rs.getInt(1), var.rs.getInt(2),var.rs.getInt(3),var.rs.getString(4),var.rs.getString(5),
						var.rs.getString(6),var.rs.getString(7), var.rs.getInt(8),
						var.rs.getString(9)));
			}
		} catch (Exception e) {
			System.out.println("wahaj exception " + e);
		}

		return student;
	}

	@Override
	public int insertStudentDetail(int sdetail_id, int sid, int roll_no, String class_id, String section_id, String fee_id,
			String status, int userid, String key) {
		int i = 0;
		try {
			System.out.println(sdetail_id);
			var.cst = var.conn.prepareCall("{? = call zk.add_student_detail(?,?,?,?,?,?,?,?,?)}");
			var.cst.registerOutParameter(++i, Types.NUMERIC);
			var.cst.setInt(++i, sdetail_id);
			var.cst.setInt(++i, sid);
			var.cst.setInt(++i, roll_no);
			var.cst.setString(++i, class_id);
			var.cst.setString(++i,section_id);
			var.cst.setString(++i, fee_id);
			var.cst.setString(++i, status);
			var.cst.setInt(++i, userid);
			var.cst.setString(++i, key);
			var.cst.execute();
			return Integer.parseInt(var.cst.getObject(1).toString());

			// Clients.showNotification(cst.getObject(1).toString());
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		}
	}

	@Override
	public void deleteStudentDetail(Student student) {
		try {
			var.stmt = var.conn.prepareStatement(
					"delete from zk.schx_student_detail where schx_student_detail_id=" + student.getId());
			var.stmt.execute();
		} catch (Exception e) {
			System.out.println("wahaj exception " + e);
		}	
	}

	@Override
	public List<String> getClasses(int sid) {
		List<String> classes = new ArrayList<String>();
		try {

			var.stmt = var.conn.prepareStatement("select zk.get_classname("+ sid + ")");
			var.rs = var.stmt.executeQuery();
			while (var.rs.next()) {
				classes.add(var.rs.getString(1));
			}
		} catch (Exception e) {
			System.out.println("wahaj exception " + e);
		}

		return classes;
	}
	
	@Override
	public List<String> getSections(int sid) {
		List<String> sections = new ArrayList<String>();
		try {

			var.stmt = var.conn.prepareStatement("select zk.get_sectionname("+ sid + ")");
			var.rs = var.stmt.executeQuery();
			while (var.rs.next()) {
				sections.add(var.rs.getString(1));
			}
		} catch (Exception e) {
			System.out.println("wahaj exception " + e);
		}

		return sections;
	}
	@Override
	public List<String> getFees(int sid) {
		List<String> fees = new ArrayList<String>();
		try {

			var.stmt = var.conn.prepareStatement("select zk.get_feename("+ sid + ")");
			var.rs = var.stmt.executeQuery();
			while (var.rs.next()) {
				fees.add(var.rs.getString(1));
			}
		} catch (Exception e) {
			System.out.println("wahaj exception " + e);
		}

		return fees;
	}
	
	//=================================================================================================================
	//Student Discount
	@Override
	public List<Student> getStudentDiscount(Integer sid) {
		List<Student> student = new ArrayList<Student>();
		try {

			var.stmt = var.conn.prepareStatement("select * from zk.get_student_discount(" + sid + ") ");
			var.rs = var.stmt.executeQuery();
			while (var.rs.next()) {
				student.add(new Student(var.rs.getInt(1), var.rs.getInt(2),var.rs.getInt(3),
						var.rs.getString(4),var.rs.getInt(5), var.rs.getString(6),
						var.rs.getString(7)));
			}
		} catch (Exception e) {
			System.out.println("wahaj exception " + e);
		}

		return student;
	}
	@Override
	public int insertStudentDiscount(int sdiscount_id, int sid, int year, String discount, String status,
			int userid, String value) {
		int i = 0;
		try {
			var.cst = var.conn.prepareCall("{? = call zk.add_student_discount(?,?,?,?,?,?,?)}");
			var.cst.registerOutParameter(++i, Types.NUMERIC);
			var.cst.setInt(++i, sdiscount_id);
			var.cst.setInt(++i, sid);
			var.cst.setInt(++i, year);
			var.cst.setString(++i,discount);
			var.cst.setString(++i, status);
			var.cst.setInt(++i, userid);
			var.cst.setString(++i, value);
			var.cst.execute();
			return Integer.parseInt(var.cst.getObject(1).toString());
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		}
	}
	@Override
	public void deleteStudentDiscount(Student student) {
		try {
			var.stmt = var.conn.prepareStatement(
					"delete from zk.schx_student_discount where schx_student_discount_id=" + student.getId());
			var.stmt.execute();
		} catch (Exception e) {
			System.out.println("wahaj exception " + e);
		}	
	}
	@Override
	public List<String> getDiscounts(Integer sid) {
		List<String> discount = new ArrayList<String>();
		try {

			var.stmt = var.conn.prepareStatement("select zk.get_discountname("+ sid + ")");
			var.rs = var.stmt.executeQuery();
			while (var.rs.next()) {
				discount.add(var.rs.getString(1));
			}
		} catch (Exception e) {
			System.out.println("wahaj exception " + e);
		}
		return discount;
	}

}
