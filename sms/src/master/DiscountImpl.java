package master;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import entity.Discount;
import entity.var;
import interfaces.DiscountInterface;

public class DiscountImpl implements DiscountInterface{

	@Override
	public String insertDiscount(int id, String name, String desc, String status, int userid, String value) {
int i=0;
		try {
			var.cst = var.conn.prepareCall("{? =call zk.add_discount(?,?,?,?,?,?)}");
			var.cst.registerOutParameter(++i, Types.VARCHAR);
			var.cst.setInt(++i, id);
			var.cst.setString(++i, name);
			var.cst.setString(++i, desc);
			var.cst.setString(++i, status);
			var.cst.setInt(++i, userid);
			var.cst.setString(++i, value);
			var.cst.execute();
			return var.cst.getObject(1).toString();

			// Clients.showNotification(cst.getObject(1).toString());
		} catch (Exception e) {
			return "false arha hai" + e;
		}
	}

	@Override
	public List<Discount> getDiscount() {
		List<Discount> discount = new ArrayList<Discount>();
		try {

			var.stmt = var.conn.prepareStatement("select * from zk.get_discount()");
			var.rs = var.stmt.executeQuery();
			while (var.rs.next()) {
				discount.add(new Discount(var.rs.getInt(1),var.rs.getString(2), var.rs.getString(3),var.rs.getString(6), var.rs.getInt(8), var.rs.getString(11)));
			}
		} catch (Exception e) {
			System.out.println("wahaj exception " + e);
		}

		return discount;
	}

	@Override
	public void deleteDiscount(Discount disc) {
		try {
			var.stmt = var.conn.prepareStatement("delete from zk.schx_fee_discount where schx_fee_discount_id="+disc.getId());
			var.stmt.execute();
		} catch (Exception e) {
			System.out.println("wahaj exception " + e);
		}
		
	}

}
