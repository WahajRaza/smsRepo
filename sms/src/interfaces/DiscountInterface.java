package interfaces;

import java.util.List;
import entity.Discount;

public interface DiscountInterface {
	public String insertDiscount(int id, String name,String desc, String status,int userid,String value); 
	public List<Discount> getDiscount();
	public void deleteDiscount(Discount disc);
}
