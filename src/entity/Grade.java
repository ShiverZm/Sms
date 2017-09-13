package entity;

public class Grade {
		private String sno;
		private String cno;
		private int grade;
		
		public Grade() {
			// TODO Auto-generated constructor stub
		}
		
		public Grade(String sno, String cno, int grade) {
			super();
			this.sno = sno;
			this.cno = cno;
			this.grade = grade;
		}
		

		public Grade(String sno, String cno) {
			super();
			this.sno = sno;
			this.cno = cno;
		}

		public String getSno() {
			return sno;
		}
		public void setSno(String sno) {
			this.sno = sno;
		}
		public String getCno() {
			return cno;
		}
		public void setCno(String cno) {
			this.cno = cno;
		}
		public int getGrade() {
			return grade;
		}
		public void setGrade(int grade) {
			this.grade = grade;
		}
		
}
