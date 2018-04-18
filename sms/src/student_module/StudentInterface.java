package student_module;

import java.util.Date;
import java.util.List;

import entity.Period;
import entity.Student;

public interface StudentInterface {
	//student master
	List<Student> getStudent();
	int insertStudent(int id, String name, String reg_no,String address,String town,String city, String landline,String cell_no,String jclass, Date jdate, String status,
			int userid,String value);
	void deleteStudent(Student student);
	
	//student detail
	List<Student> getStudentDetail(int sid);
	int insertStudentDetail(int sdetail_id, int sid, int roll_no,String class_id,String section_id,String fee_id,String status, int userid,  String key);
	void deleteStudentDetail(Student student);	
	List<String> getClasses(int sid);
	List<String> getSections(int sid);
	List<String> getFees(int sid);
	
	//student discount
	List<Student> getStudentDiscount(Integer sid);
	int insertStudentDiscount(int sdiscount_id, int sid , int year, String discount, String ischeck, int userid,
			String value);
	void deleteStudentDiscount(Student student);
	List<String> getDiscounts(Integer sid);
}
