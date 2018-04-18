package entity;

import java.io.Serializable;

public class Fee implements Serializable{
	private static final long serialVersionUID = 1L;
	//fee master
	private int id;
	private String fperiod;
	private String fclass;
	private String status;
	private int created_by;
	private String value;
	
	//fee details
	int dfid;
	String fee_type;
	String dstatus;
	String dvalue;
	
	//fee generation
	int fgdid;
	String fgft;
	String fgsm;
	int f_amount;
	int dis_amount;
	int rec_amount;
	String fgstatus;
	String fgvalue;
	
	public Fee(int id, String fperiod, String fclass, String status, int created_by, String value) {
		super();
		this.id = id;
		this.fperiod = fperiod;
		this.fclass = fclass;
		this.status = status;
		this.created_by = created_by;
		this.value = value;
	}
	
	public Fee(int id, int dfid, String fee_type, String dstatus,int created_by, String dvalue) {
		super();
		this.id = id;
		this.created_by = created_by;
		this.dfid = dfid;
		this.fee_type = fee_type;
		this.dstatus = dstatus;
		this.dvalue = dvalue;
	}

	public Fee(int id, int dfid, int fgdid,String fee_type, String fgsm, int f_amount, int dis_amount, int rec_amount,
			String fgstatus,int created_by, String fgvalue) {
		super();
		this.id = id;
		this.dfid = dfid;
		this.fgdid = fgdid;
		this.fgsm = fgsm;
		this.fee_type=fee_type;
		this.created_by=created_by;
		this.f_amount = f_amount;
		this.dis_amount = dis_amount;
		this.rec_amount = rec_amount;
		this.fgstatus = fgstatus;
		this.fgvalue = fgvalue;
	}
	
	public int getFgdid() {
		return fgdid;
	}

	public void setFgdid(int fgdid) {
		this.fgdid = fgdid;
	}

	public String getFgft() {
		return fgft;
	}

	public void setFgft(String fgft) {
		this.fgft = fgft;
	}

	public String getFgsm() {
		return fgsm;
	}

	public void setFgsm(String fgsm) {
		this.fgsm = fgsm;
	}

	public int getF_amount() {
		return f_amount;
	}

	public void setF_amount(int f_amount) {
		this.f_amount = f_amount;
	}

	public int getDis_amount() {
		return dis_amount;
	}

	public void setDis_amount(int dis_amount) {
		this.dis_amount = dis_amount;
	}

	public int getRec_amount() {
		return rec_amount;
	}

	public void setRec_amount(int rec_amount) {
		this.rec_amount = rec_amount;
	}

	public String getFgstatus() {
		return fgstatus;
	}

	public void setFgstatus(String fgstatus) {
		this.fgstatus = fgstatus;
	}

	public String getFgvalue() {
		return fgvalue;
	}

	public void setFgvalue(String fgvalue) {
		this.fgvalue = fgvalue;
	}



	public int getDfid() {
		return dfid;
	}

	public void setDfid(int dfid) {
		this.dfid = dfid;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public String getDstatus() {
		return dstatus;
	}

	public void setDstatus(String dstatus) {
		this.dstatus = dstatus;
	}

	public String getDvalue() {
		return dvalue;
	}

	public void setDvalue(String dvalue) {
		this.dvalue = dvalue;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFperiod() {
		return fperiod;
	}

	public void setFperiod(String fperiod) {
		this.fperiod = fperiod;
	}

	public String getFclass() {
		return fclass;
	}

	public void setFclass(String fclass) {
		this.fclass = fclass;
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
	
	
}
