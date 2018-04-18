package fee_module;

import java.util.List;

import entity.Fee;

public interface FeeInterface {
	// fee master
	int insertFee(int fee_id, String fperiod, String fclass, String ischeck, int userid, String value);
	void deleteFee(Fee fee);
	List<Fee> getFee();
	List<String> getPeriods();
	List<String> getClasses();

	//fee details
	int insertFeeDetail(int fdetail_id, int fid, String ft, String ischeck, int userid, String value);
	List<String> getFeetypes(int fid);
	void deleteFeeDetail(Fee fee);
	List<Fee> getFeeDetail(int fid);
	
	//fee generation
	int insertFeeGen(int fgen_id, Integer fgmid, Integer fgdid, String feetype, String fgsm, Integer f_amount,
			Integer dis_amount, Integer rec_amount, String ischeck, int userid, String value);
	List<String> getStudents();
	void deleteFeeGen(Fee fee);
	List<Fee> getFeeGen(Integer fgmid, Integer fgdid);

}
