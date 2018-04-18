package interfaces;

import java.util.List;
import entity.FeeTypes;

public interface FeeTypeInterface {
	public String insertFeeType(int id, String name,String rec, String status,int userid,String value); 
	public List<FeeTypes> getFeeTypes();
	public void deleteFeeType(FeeTypes ft);
}
