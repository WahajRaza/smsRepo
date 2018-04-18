package entity;

import java.io.Serializable;
import java.util.Date;

public class Period implements Serializable{
	//period master variables
	 int id;
	String name;
	int terms;
	int tests;
	Date from;
	Date to;
	int created_by;
	String status;
	String value;
	// period detail variables
	int dp_id;
	String month;
	String d_status;
	String d_value;
	private static final long serialVersionUID = 1L;
	
	public Period(int id, String name, int terms, int tests, Date from, Date to,int created_by, String status, String value) {
		super();
		this.id = id;
		this.name = name;
		this.terms = terms;
		this.tests = tests;
		this.from = from;
		this.to = to;
		this.setCreated_by(created_by);
		this.status = status;
		this.value = value;
	}
	
	public Period(int id, int dp_id, String month, int created_by, String d_status, String d_value) {
		super();
		this.id = id;
		this.dp_id = dp_id;
		this.month = month;
		this.setCreated_by(created_by);
		this.d_status = d_status;
		this.d_value = d_value;
	}

	public int getDp_id() {
		return dp_id;
	}

	public void setDp_id(int dp_id) {
		this.dp_id = dp_id;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
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
	public int getTerms() {
		return terms;
	}
	public void setTerms(int terms) {
		this.terms = terms;
	}
	public int getTests() {
		return tests;
	}
	public void setTests(int tests) {
		this.tests = tests;
	}
	public Date getFrom() {
		return from;
	}
	public void setFrom(Date from) {
		this.from = from;
	}
	public Date getTo() {
		return to;
	}
	public void setTo(Date to) {
		this.to = to;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public int getCreated_by() {
		return created_by;
	}

	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}
	
	

}
