package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.User;
import util.DbUtil;
import util.StringUtil;

public class UserDao {
		
	public User login(Connection con,User user) throws Exception{
		User user1=null;
		String sql="select * from t_user where userName=? and password=?";
		PreparedStatement pstm=con.prepareStatement(sql);
		pstm.setString(1, user.getUserName());
		pstm.setString(2, user.getPassword());
		ResultSet rs=pstm.executeQuery();
		while (rs.next()) {
			user1=new User();
			user1.setUserName(rs.getString("userName"));
			user1.setPassword(rs.getString("password"));
			user1.setId(rs.getInt("id"));
			user1.setRealName(rs.getString("realName"));
			user1.setRole(rs.getInt("role"));
		}
		return user1;
	}

	public ResultSet list(Connection con, User user) throws Exception {
		StringBuffer sql=new StringBuffer("select * from t_user u");
		if(StringUtil.isNotEmpty(user.getUserName())){
		    sql.append(" where u.userName like '%"+user.getUserName()+"%'");	
		}
		PreparedStatement pstm=con.prepareStatement(sql.toString());
		return pstm.executeQuery();
	}
	

	public int modifyPassword(Connection con, User user,String newPassword) throws Exception {
	     String sql="update t_user set password=? where userName=? and password=?";
	     PreparedStatement psmt=con.prepareStatement(sql);
	     psmt.setString(1, newPassword);
	     psmt.setString(2, user.getUserName());
	     psmt.setString(3, user.getPassword());
	     return psmt.executeUpdate();
	}
	
	public static void main(String[] args) throws Exception {
		User user=new User();
		user.setUserName("zsw");
		user.setPassword("123456");
		System.out.println(""+new UserDao().login(new DbUtil().getCon(), user));
			
	}
}
