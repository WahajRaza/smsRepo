package sm;
import java.io.Serializable;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import entity.User;
import entity.var;
import services.UserInfoService;

public class UserInfoServiceImpl implements UserInfoService,Serializable{
	private static final long serialVersionUID = 1L;
	static protected List<User> userList = new ArrayList<User>();
	public static int userid;
	
	public UserInfoServiceImpl() {	  
		try {
			Class.forName("org.postgresql.Driver");
			var.conn = DriverManager.getConnection(
					"jdbc:postgresql://ZK/postgres", "postgres",
					"postgres");
		} catch (Exception e) {
			System.out.println("Connection Failed because " + e);
		}
	}
	@Override
	public synchronized User findUser(String email){
		try {
			// load driver and get a database connection
			var.stmt = var.conn.prepareStatement("select * from zk.schx_users where email=?");
			var.stmt.setString(1, email);
			var.rs = var.stmt.executeQuery();
			if (var.rs.next()) {
				userid=var.rs.getInt(1);
				User u = new User(var.rs.getInt(1),var.rs.getString(2),var.rs.getString(3),var.rs.getString(4));
				if(email.equals(u.getEmail())){
					return User.clone(u);				
			}
			}
			} catch (Exception e) {
			System.out.println("wahaj exception " + e);
		} 			
		return null;
	}	
}
