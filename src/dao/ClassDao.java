package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Class;
import util.DbUtil;
import util.StringUtil;

public class ClassDao {
	public int add(Connection con,Class c) throws Exception{
		String sql="insert into t_class values(?,?,?)";
		PreparedStatement psmt=con.prepareStatement(sql);
		psmt.setString(1, c.getCno());
		psmt.setString(2, c.getCname());
		psmt.setDouble(3, c.getCredit());
		return psmt.executeUpdate();
	}
	
	
	public ResultSet list(Connection con, Class c) throws Exception {
		StringBuffer sql=new StringBuffer("select * from t_class ");
		if(StringUtil.isNotEmpty(c.getCname())){
			sql.append(" where cname like '%"+c.getCname()+"%'");
		}
		PreparedStatement pstm=con.prepareStatement(sql.toString());
		return pstm.executeQuery();
	}
	
	public int update(Connection con,Class c) throws Exception{
		String sql="update t_class set cname=?,credit=? where cno=?";
		PreparedStatement pstm=con.prepareStatement(sql);
		pstm.setString(1, c.getCname());
		pstm.setDouble(2, c.getCredit());
		pstm.setString(3, c.getCno());
		return pstm.executeUpdate();
	}
	
	public int delete(Connection con, String cno) throws Exception {
		String sql="delete from t_class where cno=?";
		PreparedStatement pstm=con.prepareStatement(sql);
		pstm.setString(1, cno);
		return pstm.executeUpdate();
	}
	
	
	


	public ResultSet listUnpassCount(Connection con, String cno) throws Exception {
		String sql="SELECT c.cname,COUNT(g.sno) count "
				+ " FROM t_class c LEFT JOIN t_grade g ON c.cno=g.cno  "
				+ " WHERE g.grade<60 ";
		StringBuffer sb=new StringBuffer(sql);
		if(cno!=null){
			sb.append(" and c.cno like '%"+cno+"%'");
		}
		sb.append(" group by c.cno ");
		PreparedStatement pstm=con.prepareStatement(sb.toString());
		return pstm.executeQuery();
	}



	public ResultSet listHeighestAndLowest(Connection con, String cno) throws Exception {
		String sql="SELECT c.cname,max(g.grade) heightest,min(g.grade) lowest "
				+ " FROM t_class c LEFT JOIN t_grade g ON c.cno=g.cno  ";
		StringBuffer sb=new StringBuffer(sql);
		if(cno!=null){
			sb.append("where c.cno like '%"+cno+"%'");
		}
		sb.append(" group by c.cno ");
		PreparedStatement pstm=con.prepareStatement(sb.toString());
		return pstm.executeQuery();
	}
	
	public static void main(String[] args) throws Exception {
		
		ResultSet rs=new ClassDao().listUnpassCount(new DbUtil().getCon(),"B12345");
		while(rs.next()){
			System.out.println(rs.getString("cname"));
			System.out.println(rs.getInt("count"));
		}
		
	}

}
