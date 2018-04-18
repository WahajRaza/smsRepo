package entity;

import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable {
	private static final long serialVersionUID = 1L;
	// Student master variables
	int id;
	String name;
	String reg_no;
	String address;
	String town;
	String city;
	String landline;
	String cell_no;
	String jclass;
	Date admission;
	String status;
	int created_by;
	String value;
	// student detail variables
	int ds_id;
	int roll_no;
	String class_id;
	String section_id;
	String fee_id;
	String d_status;
	String d_value;
	//student discount
	int dis_id;
	int year;
	String discount;
	String dis_status;
	String dis_value;
	
	public Student(int id, String name, String reg_no, String address, String town, String city, String landline, String cell_no,
			String jclass, Date admission, String status, int created_by, String value) {
		super();
		this.id = id;
		this.name = name;
		this.reg_no = reg_no;
		this.address = address;
		this.town = town;
		this.city = city;
		this.landline = landline;
		this.cell_no = cell_no;
		this.jclass = jclass;
		this.admission = admission;
		this.status = status;
		this.created_by = created_by;
		this.value = value;
	}

	public Student(int id, int ds_id, int roll_no, String class_id, String section_id, String fee_id, String d_status,int created_by,
			String d_value) {
		super();
		this.id = id;
		this.ds_id = ds_id;
		this.roll_no = roll_no;
		this.class_id = class_id;
		this.section_id = section_id;
		this.fee_id = fee_id;
		this.d_status = d_status;
		this.created_by=created_by;
		this.d_value = d_value;
	}
	
	public Student(int id, int dis_id, int year, String dis_status,int created_by, String dis_value, String discount) {
		super();
		this.id = id;
		this.dis_id = dis_id;
		this.year = year;
		this.discount = discount;
		this.dis_status = dis_status;
		this.created_by=created_by;
		this.dis_value = dis_value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReg_no() {
		return reg_no;
	}

	public void setReg_no(String reg_no) {
		this.reg_no = reg_no;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLandline() {
		return landline;
	}

	public void setLandline(String landline) {
		this.landline = landline;
	}

	public String getCell_no() {
		return cell_no;
	}

	public void setCell_no(String cell_no) {
		this.cell_no = cell_no;
	}

	public String getJclass() {
		return jclass;
	}

	public void setJclass(String jclass) {
		this.jclass = jclass;
	}

	public Date getAdmission() {
		return admission;
	}

	public void setAdmission(Date admission) {
		this.admission = admission;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCreated_by() {
		return created_by;
	}

	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getDs_id() {
		return ds_id;
	}

	public void setDs_id(int ds_id) {
		this.ds_id = ds_id;
	}

	public int getRoll_no() {
		return roll_no;
	}

	public void setRoll_no(int roll_no) {
		this.roll_no = roll_no;
	}

	public String getClass_id() {
		return class_id;
	}

	public void setClass_id(String class_id) {
		this.class_id = class_id;
	}

	public String getSection_id() {
		return section_id;
	}

	public void setSection_id(String section_id) {
		this.section_id = section_id;
	}

	public String getFee_id() {
		return fee_id;
	}

	public void setFee_id(String fee_id) {
		this.fee_id = fee_id;
	}

	public String getD_status() {
		return d_status;
	}

	public void setD_status(String d_status) {
		this.d_status = d_status;
	}

	public String getD_value() {
		return d_value;
	}

	public void setD_value(String d_value) {
		this.d_value = d_value;
	}

	public int getDis_id() {
		return dis_id;
	}

	public void setDis_id(int dis_id) {
		this.dis_id = dis_id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getDis_status() {
		return dis_status;
	}

	public void setDis_status(String dis_status) {
		this.dis_status = dis_status;
	}

	public String getDis_value() {
		return dis_value;
	}

	public void setDis_value(String dis_value) {
		this.dis_value = dis_value;
	}

	
	
}
