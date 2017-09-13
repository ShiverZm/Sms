package entity;

public class Student {
		private String sno;
		private String sname;
		private String sex;
		private String birthday;
		private String dept;
		
		public Student() {
			// TODO Auto-generated constructor stub
		}
		public Student(String sno, String sname, String sex, String birthday, String dept) {
			super();
			this.sno = sno;
			this.sname = sname;
			this.sex = sex;
			this.birthday = birthday;
			this.dept = dept;
		}
		public String getSno() {
			return sno;
		}
		public void setSno(String sno) {
			this.sno = sno;
		}
		public String getSname() {
			return sname;
		}
		public void setSname(String sname) {
			this.sname = sname;
		}
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		public String getBirthday() {
			return birthday;
		}
		public void setBirthday(String birthday) {
			this.birthday = birthday;
		}
		public String getDept() {
			return dept;
		}
		public void setDept(String dept) {
			this.dept = dept;
		}
		
		
}
