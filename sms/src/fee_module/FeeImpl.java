package fee_module;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import entity.Fee;
import entity.var;

public class FeeImpl implements FeeInterface {

	// =================================================================================================================
	// fee Master
	@Override
	public int insertFee(int fee_id, String fperiod, String fclass, String ischeck, int userid, String value) {
		int i = 0;
		try {
			var.cst = var.conn.prepareCall("{? = call zk.add_fee(?,?,?,?,?,?)}");
			var.cst.registerOutParameter(++i, Types.NUMERIC);
			var.cst.setInt(++i, fee_id);
			var.cst.setString(++i, fperiod);
			var.cst.setString(++i, fclass);
			var.cst.setString(++i, ischeck);
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
	public void deleteFee(Fee fee) {
		try {
			var.stmt = var.conn.prepareStatement(
					"delete from zk.schx_fee_struc_master where schx_fee_struc_master_id=" + fee.getId());
			var.stmt.execute();
		} catch (Exception e) {
			System.out.println("wahaj exception " + e);
		}
	}

	@Override
	public List<Fee> getFee() {
		List<Fee> fee = new ArrayList<Fee>();
		try {

			var.stmt = var.conn.prepareStatement("select * from zk.get_fee()");
			var.rs = var.stmt.executeQuery();
			while (var.rs.next()) {
				fee.add(new Fee(var.rs.getInt(1), var.rs.getString(2), var.rs.getString(3), var.rs.getString(4),
						var.rs.getInt(5), var.rs.getString(6)));
			}
		} catch (Exception e) {
			System.out.println("wahaj exception " + e);
		}
		return fee;
	}

	@Override
	public List<String> getPeriods() {
		List<String> periods = new ArrayList<String>();
		try {

			var.stmt = var.conn.prepareStatement("select zk.get_fperiodname()");
			var.rs = var.stmt.executeQuery();
			while (var.rs.next()) {
				periods.add(var.rs.getString(1));
			}
		} catch (Exception e) {
			System.out.println("wahaj exception " + e);
		}
		return periods;
	}

	@Override
	public List<String> getClasses() {
		List<String> classes = new ArrayList<String>();
		try {

			var.stmt = var.conn.prepareStatement("select zk.get_fclassname()");
			var.rs = var.stmt.executeQuery();
			while (var.rs.next()) {
				classes.add(var.rs.getString(1));
			}
		} catch (Exception e) {
			System.out.println("wahaj exception " + e);
		}
		return classes;
	}
	
	// =================================================================================================================
	// fee detail

	@Override
	public int insertFeeDetail(int fdetail_id, int fid, String ft, String ischeck, int userid, String value) {
		int i = 0;
		try {
			var.cst = var.conn.prepareCall("{? = call zk.add_fee_detail(?,?,?,?,?,?)}");
			var.cst.registerOutParameter(++i, Types.NUMERIC);
			var.cst.setInt(++i, fdetail_id);
			var.cst.setInt(++i, fid);
			var.cst.setString(++i, ft);
			var.cst.setString(++i, ischeck);
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
	public List<String> getFeetypes(int fid) {
		List<String> feetypes = new ArrayList<String>();
		try {

			var.stmt = var.conn.prepareStatement("select zk.get_feetypes("+ fid + ")");
			var.rs = var.stmt.executeQuery();
			while (var.rs.next()) {
				feetypes.add(var.rs.getString(1));
			}
		} catch (Exception e) {
			System.out.println("wahaj exception " + e);
		}
		return feetypes;
	}

	@Override
	public void deleteFeeDetail(Fee fee) {
		try {
			var.stmt = var.conn.prepareStatement(
					"delete from zk.schx_fee_struc_detail where schx_fee_struc_detail_id=" + fee.getId());
			var.stmt.execute();
		} catch (Exception e) {
			System.out.println("wahaj exception " + e);
		}
	}

	@Override
	public List<Fee> getFeeDetail(int fid) {
		List<Fee> fee = new ArrayList<Fee>();
		try {

			var.stmt = var.conn.prepareStatement("select * from zk.get_fee_detail(" + fid + ")");
			var.rs = var.stmt.executeQuery();
			while (var.rs.next()) {
				fee.add(new Fee(var.rs.getInt(1), var.rs.getInt(2), var.rs.getString(3), var.rs.getString(4),
						var.rs.getInt(5), var.rs.getString(6)));
			}
		} catch (Exception e) {
			System.out.println("wahaj exception " + e);
		}
		return fee;
	}

	// =================================================================================================================
	// fee genertaion
	
	@Override
	public int insertFeeGen(int fgen_id, Integer fgmid, Integer fgdid, String feetype, String fgsm, Integer f_amount,
			Integer dis_amount, Integer rec_amount, String ischeck, int userid, String value) {
		int i = 0;
		try {
			var.cst = var.conn.prepareCall("{? = call zk.add_fee_gen(?,?,?,?,?,?,?,?,?,?,?)}");
			var.cst.registerOutParameter(++i, Types.NUMERIC);
			var.cst.setInt(++i, fgen_id);
			var.cst.setInt(++i, fgmid);
			var.cst.setInt(++i, fgdid);			
			var.cst.setString(++i, feetype);
			var.cst.setString(++i, fgsm);
			var.cst.setInt(++i, f_amount);
			var.cst.setInt(++i, dis_amount);
			var.cst.setInt(++i, rec_amount);
			var.cst.setString(++i, ischeck);
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
	public List<Fee> getFeeGen(Integer fgmid, Integer fgdid) {
		List<Fee> fee = new ArrayList<Fee>();
		try {

			var.stmt = var.conn.prepareStatement("select * from zk.get_fee_gen(" + fgmid + ","+ fgdid +")");
			var.rs = var.stmt.executeQuery();
			while (var.rs.next()) {
				fee.add(new Fee(var.rs.getInt(1), var.rs.getInt(2),var.rs.getInt(3), var.rs.getString(4),
						var.rs.getString(5),var.rs.getInt(6), var.rs.getInt(7),var.rs.getInt(8),var.rs.getString(9),
						var.rs.getInt(10), var.rs.getString(11)));
			}
		} catch (Exception e) {
			System.out.println("wahaj exception " + e);
		}
		return fee;
	}	

	@Override
	public List<String> getStudents() {
		List<String> students = new ArrayList<String>();
		try {

			var.stmt = var.conn.prepareStatement("select * from zk.get_students()");
			var.rs = var.stmt.executeQuery();
			while (var.rs.next()) {
				students.add(var.rs.getString(1));
			}
		} catch (Exception e) {
			System.out.println("wahaj exception " + e);
		}
		return students;
	}

	@Override
	public void deleteFeeGen(Fee fee) {
		try {
			var.stmt = var.conn.prepareStatement(
					"delete from zk.schx_fee_generate where schx_fee_generate_id=" + fee.getId());
			var.stmt.execute();
		} catch (Exception e) {
			System.out.println("wahaj exception " + e);
		}
		
	}

	

	

}
