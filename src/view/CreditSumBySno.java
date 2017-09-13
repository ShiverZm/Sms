package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.GradeDao;
import entity.Student;
import entity.User;
import util.DbUtil;

public class CreditSumBySno extends JInternalFrame {
	private JTable table;
	private JTextField s_snameTxt;
	
	private User user=null;
	private DbUtil dbUtil=new DbUtil();
	private GradeDao gradeDao=new GradeDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreditSumBySno frame = new CreditSumBySno();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CreditSumBySno() {
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("\u6839\u636E\u5B66\u53F7\u67E5\u5DF2\u4FEE\u5B66\u5206\u603B\u6570");
		setBounds(100, 100, 644, 506);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel label = new JLabel("\u6309\u59D3\u540D:");
		
		s_snameTxt = new JTextField();
		s_snameTxt.setColumns(10);
		
		JButton button = new JButton("\u67E5\u8BE2");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListStudentSumCreditActionPerformed(arg0);
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(112, Short.MAX_VALUE)
					.addComponent(label)
					.addGap(27)
					.addComponent(s_snameTxt, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
					.addGap(85)
					.addComponent(button)
					.addGap(94))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(86)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 454, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(88, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(79, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(button)
						.addComponent(s_snameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label))
					.addGap(60)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
					.addGap(109))
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u5B66\u53F7", "\u59D3\u540D", "\u603B\u5B66\u5206"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);
		this.fillTable(new Student());

	}
	/**
	 * 学生名字查询学分总数
	 * @param evt
	 */
	private void ListStudentSumCreditActionPerformed(ActionEvent evt){
		String sname=s_snameTxt.getText();
		Student stu=new Student();
		stu.setSname(sname);
		fillTable(stu);
	}
	/**
	 * 初始化表格
	 */
	private void fillTable(Student stu){
		DefaultTableModel dtm=(DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		Connection con=null;
		try {
			con=dbUtil.getCon();
			ResultSet rs=gradeDao.ListSumCredit(con,new Student());
			while(rs.next()){
				Vector v=new Vector<>();
				v.add(rs.getString("sno"));
				v.add(rs.getString("sname"));
				v.add(rs.getDouble("sum"));
				dtm.addRow(v);
			}
	 		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();			
			}
		}
	}
}
