package entity;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class var {
	static public Connection conn = null;
	static public PreparedStatement stmt = null;
	static public CallableStatement cst = null;
	static public ResultSet rs = null;
	static public int i=0;
}
