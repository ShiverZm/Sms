package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import entity.Grade;
import entity.Student;
import util.DbUtil;
import util.StringUtil;

public class GradeDao {
	 
	public int add(Connection con,Grade grade) throws Exception{
		String sql="insert into t_grade values(?,?,?)";
		PreparedStatement pstm=con.prepareStatement(sql);
		pstm.setString(1, grade.getSno());
		pstm.setString(2, grade.getCno());
		pstm.setInt(3, grade.getGrade());
		return pstm.executeUpdate();
	}
	public ResultSet list(Connection con,Map<String, Object> map) throws Exception {
		StringBuffer sql=new StringBuffer("select c.cno,c.cname,s.sno,s.sname,g.grade "
				+ "from t_grade g "
				+ "left join t_student s on g.sno=s.sno "
				+ "left join t_class c on g.cno=c.cno ");
		if(map.get("sname")!=null){
			sql.append(" and s.sname like '%"+(String)map.get("sname")+"%'");
		}if(map.get("cname")!=null){
			sql.append(" and c.cname like  '%"+(String)map.get("cname")+"%'");
		}
		PreparedStatement pstm=con.prepareStatement(sql.toString().replaceFirst("and", "where"));
		return pstm.executeQuery();
	}
	public int modify(Connection con, Grade grade) throws Exception {
		String sql="update t_grade set grade=? where cno=? and sno=? ";
		PreparedStatement pstm=con.prepareStatement(sql);
		pstm.setInt(1, grade.getGrade());
		pstm.setString(2, grade.getCno());
		pstm.setString(3, grade.getSno());
		return pstm.executeUpdate();
	}
	
	public int delete(Connection con, Grade grade) throws Exception {
		String sql="delete from t_grade where sno=? and cno=?";
		PreparedStatement psmt=con.prepareStatement(sql);
		psmt.setString(1, grade.getSno());
		psmt.setString(2, grade.getCno());
		return psmt.executeUpdate();
	}
	
	public static void main(String[] args) throws Exception {
		Grade grade=new Grade("123456","B12345");
		System.out.println(""+new GradeDao().delete(new DbUtil().getCon(),grade));
		
	}
	public ResultSet ListSumCredit(Connection con, Student student) throws Exception {
		StringBuffer sql=new StringBuffer();
		sql.append("select s.sno,s.sname,sum(c.credit) sum "
				+ "from t_student s left join t_grade g on s.sno=g.sno"
								+ " left join t_class c on c.cno=g.cno"
								+ " where g.grade>=60 ");
		if(StringUtil.isNotEmpty(student.getSname())){
			sql.append(" and s.sname like '%"+student.getSname()+"'%");
		}
		sql.append(" group by s.sname ");
		return con.prepareStatement(sql.toString()).executeQuery();
	}
	public ResultSet listGrade(Connection con, String sno) throws Exception {
		String sql="select c.cname,g.grade from t_student s "
								+ " left join t_grade g on s.sno=g.sno"
								+ " left join t_class c on c.cno=g.cno"
								+ " where s.sno like '%"+sno+"%'";
		PreparedStatement psmt=con.prepareStatement(sql);
		return psmt.executeQuery();
	}
}
