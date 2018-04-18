package master;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import entity.FeeTypes;
import entity.var;
import interfaces.FeeTypeInterface;

public class FeeTypeImpl implements FeeTypeInterface{
	static List<FeeTypes> feetypeList = new ArrayList<FeeTypes>();
	@Override
	public String insertFeeType(int id, String name, String rec, String status, int userid, String value) {
		int i=0;
		try {
			var.cst = var.conn.prepareCall("{? =call zk.add_fee_type(?,?,?,?,?,?)}");
			var.cst.registerOutParameter(++i, Types.VARCHAR);
			var.cst.setInt(++i, id);
			var.cst.setString(++i, name);
			var.cst.setString(++i, rec);
			var.cst.setString(++i, status);
			var.cst.setInt(++i, userid);
			var.cst.setString(++i, value);
			var.cst.execute();
			return var.cst.getObject(1).toString();
		} catch (Exception e) {
			return "false arha hai" + e;
		}
	}

	@Override
	public List<FeeTypes> getFeeTypes() {
		List<FeeTypes> feetypes = new ArrayList<FeeTypes>();
		try {

			var.stmt = var.conn.prepareStatement("select * from zk.get_feetype()");
			var.rs = var.stmt.executeQuery();
			while (var.rs.next()) {
				feetypes.add(new FeeTypes(var.rs.getInt(1),var.rs.getString(2), var.rs.getString(3), var.rs.getString(6), var.rs.getInt(8), var.rs.getString(11)));
			}
		} catch (Exception e) {
			System.out.println("wahaj exception " + e);
		}

		return feetypes;
	}

	@Override
	public void deleteFeeType(FeeTypes ft) {
		try {
			var.stmt = var.conn.prepareStatement("delete from zk.schx_fee_type where schx_fee_type_id="+ft.getId());
			var.stmt.execute();
		} catch (Exception e) {
			System.out.println("wahaj exception " + e);
		}
		
	}

}
