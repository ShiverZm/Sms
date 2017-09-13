package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import entity.Class;

import entity.Student;
import util.DbUtil;
import util.StringUtil;

public class StudentDao {
      
	
		public int add(Connection con,Student stu) throws Exception{
			String sql="insert into t_student values(?,?,?,?,?) ";
			PreparedStatement pstm=con.prepareStatement(sql);
			pstm.setString(1, stu.getSno());
			pstm.setString(2, stu.getSname());
			pstm.setString(3, stu.getSex());
			pstm.setString(4, stu.getBirthday());
			pstm.setString(5,stu.getDept());
			return pstm.executeUpdate();
		}
		
		public ResultSet list(Connection con, Student student) throws Exception {
			StringBuffer sql=new StringBuffer("select * from t_student ");
			if(StringUtil.isNotEmpty(student.getSno())){
				sql.append(" where sno like '%"+student.getSno()+"%'");
			}
			PreparedStatement pstm=con.prepareStatement(sql.toString());
			return pstm.executeQuery();
		}
		
		public int update(Connection con,Student student) throws Exception{
			String sql="update t_student set sname=?,sex=?,birthday=?,dept=? where sno=?";
			PreparedStatement pstm=con.prepareStatement(sql);
			pstm.setString(1, student.getSname());
			pstm.setString(2, student.getSex());
			pstm.setString(3, student.getBirthday());
			pstm.setString(4, student.getDept());
			pstm.setString(5, student.getSno());
			return pstm.executeUpdate();
		}
		
		public int delete(Connection con, String sno) throws Exception {
			String sql="delete from t_student where sno=?";
			PreparedStatement pstm=con.prepareStatement(sql);
			pstm.setString(1, sno);
			return pstm.executeUpdate();
		}
		
		public ResultSet listUnpassed(Connection con,Class c) throws Exception{
			StringBuffer sb=new StringBuffer();
			sb.append("select s.sno,s.sname,c.cname,g.grade"
					+ " from t_student s "
					+ " left join t_grade g on s.sno=g.sno"
					+ " left join t_class c on c.cno=g.cno"
					+" where g.grade <60 ");
			if(c.getCname()!=null){
				sb.append("and c.cname like '%"+c.getCname()+"%'");
			}
			PreparedStatement pstm=con.prepareStatement(sb.toString());
			return pstm.executeQuery();
		}
		public static void main(String[] args) throws Exception {
			
			int n=new StudentDao().delete(new DbUtil().getCon(), "1234");
			System.out.println(n);
		}


}
