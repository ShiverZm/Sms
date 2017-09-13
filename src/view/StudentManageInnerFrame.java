package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.StudentDao;
import entity.Student;
import entity.User;
import util.DbUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentManageInnerFrame extends JInternalFrame {
	private JTextField s_snoTxt;
	private JTable studentTable;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField snoTxt;
	private JTextField birthdayTxt;
	private JTextField snameTxt;
	private JTextField deptTxt;
	private JRadioButton manJrb;
	private JRadioButton womanJrb;
	
	private User user=null;
	private DbUtil dbUtil=new DbUtil();
	private StudentDao studentDao=new StudentDao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentManageInnerFrame frame = new StudentManageInnerFrame();
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
	public StudentManageInnerFrame() {
		setIconifiable(true);
		setClosable(true);
		setTitle("\u5B66\u751F\u4FE1\u606F\u7EF4\u62A4");
		setBounds(100, 100, 600, 652);
		
		JPanel panel = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel_1 = new JPanel();
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(54, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 494, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 492, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 492, GroupLayout.PREFERRED_SIZE))
					.addGap(36))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(48)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(26, Short.MAX_VALUE))
		);
		
		JLabel label_1 = new JLabel("\u5B66\u53F7:");
		
		JLabel label_2 = new JLabel("\u59D3\u540D:");
		
		JLabel label_3 = new JLabel("\u51FA\u751F\u65E5\u671F:");
		
		 manJrb = new JRadioButton("\u7537");
		buttonGroup.add(manJrb);
		
		 womanJrb = new JRadioButton("\u5973");
		buttonGroup.add(womanJrb);
		
		JLabel label_4 = new JLabel("\u6240\u5728\u7CFB:");
		
		snoTxt = new JTextField();
		snoTxt.setColumns(10);
		
		birthdayTxt = new JTextField();
		birthdayTxt.setColumns(10);
		
		snameTxt = new JTextField();
		snameTxt.setColumns(10);
		
		deptTxt = new JTextField();
		deptTxt.setColumns(10);
		
		JButton button_1 = new JButton("\u4FEE\u6539");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				studentModifyActionPerformed(arg0);
			}
		});
		button_1.setIcon(new ImageIcon(StudentManageInnerFrame.class.getResource("/images/modify.png")));
		
		JButton button_2 = new JButton("\u5220\u9664");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteStudentActionPerformed(arg0);
			}
		});
		button_2.setIcon(new ImageIcon(StudentManageInnerFrame.class.getResource("/images/delete.png")));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(28)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(manJrb)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(womanJrb))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel_1.createSequentialGroup()
										.addComponent(label_1)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(snoTxt, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_panel_1.createSequentialGroup()
										.addComponent(label_2)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(snameTxt, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)))
								.addComponent(button_1))
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(18)
									.addComponent(label_3)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(birthdayTxt, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(26)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addComponent(button_2)
										.addGroup(gl_panel_1.createSequentialGroup()
											.addComponent(label_4)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(deptTxt)))))))
					.addContainerGap(72, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(label_3)
						.addComponent(snoTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(birthdayTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(snameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_4)
						.addComponent(deptTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(manJrb)
						.addComponent(womanJrb))
					.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(button_2)
						.addComponent(button_1))
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		
		studentTable = new JTable();
		studentTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TableMouseClicked(arg0);
			}
		});
		studentTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u5B66\u53F7", "\u59D3\u540D", "\u6027\u522B", "\u51FA\u751F\u65E5\u671F", "\u6240\u5728\u7CFB"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(studentTable);
		
		JLabel label = new JLabel("\u6309\u5B66\u53F7:");
		
		s_snoTxt = new JTextField();
		s_snoTxt.setColumns(10);
		
		JButton button = new JButton("\u67E5\u8BE2");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				searcheActionPerformed(arg0);
			}
		});
		button.setIcon(new ImageIcon(StudentManageInnerFrame.class.getResource("/images/search.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(105)
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(s_snoTxt, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(button)
					.addContainerGap(56, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(31, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(s_snoTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button)
						.addComponent(label))
					.addGap(35))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
		this.fillTable(new Student());

	}
	
	/**
	 * 删除学生事件处理
	 * 
	 */
	private void deleteStudentActionPerformed(ActionEvent evt){
		String sno=snoTxt.getText();
		int n=JOptionPane.showConfirmDialog(null, "确实要删除这条数据");
		if(n==0){
			Connection con=null;
			try {
				con=dbUtil.getCon();
				if(studentDao.delete(con,sno)==1){
					JOptionPane.showMessageDialog(null, "已经成功删除");
					this.fillTable(new Student());
				}else{
					JOptionPane.showMessageDialog(null, "删除失败");
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "删除失败");
				e.printStackTrace();
			}finally {
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else{
			return;
		}
	}
	/**
	 * 按学号查询学生信息
	 * @param evt
	 */
	private void  searcheActionPerformed(ActionEvent evt){
		Student stu=new Student();
		stu.setSno(s_snoTxt.getText());
		this.fillTable(stu);
	}

	
	/**
	 * 修改学生信息事件处理
	 * @param evt
	 */
	private void studentModifyActionPerformed(ActionEvent evt){
				Student student =new Student();
				student.setSno(snoTxt.getText());
				student.setSname(snameTxt.getText());
				String sex=null;
				if(manJrb.isSelected()){
					sex="男";
				}else{
					sex="女";
				}
				student.setSex(sex);
				student.setBirthday(birthdayTxt.getText());
				student.setDept(deptTxt.getText());
				Connection con=null;
				try {
					con=dbUtil.getCon();
					int result=studentDao.update(con,student);
					if(result==1){
						JOptionPane.showMessageDialog(null, "修改成功");
						this.fillTable(new Student());
					}
					else{
						JOptionPane.showMessageDialog(null, "修改失败");
					}
				}catch (Exception e) {
					JOptionPane.showMessageDialog(null, "修改失败");
				} 
				finally {
					try {
						dbUtil.closeCon(con);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
		
	}
	/**
	 * 表格点击事件
	 * @param evt
	 */
	private void TableMouseClicked(MouseEvent evt){
		int row=studentTable.getSelectedRow();
		snoTxt.setText(""+studentTable.getValueAt(row, 0));
		snameTxt.setText((String) studentTable.getValueAt(row, 1));
		if("男".equals(studentTable.getValueAt(row, 2))){
			manJrb.setSelected(true);
		}else{
			womanJrb.setSelected(true);
		}
		birthdayTxt.setText((String) studentTable.getValueAt(row, 3));
		deptTxt.setText((String) studentTable.getValueAt(row, 4));
	}
	/**
	 *学生表格初始化
	 * @param student
	 */
	private void fillTable(Student student){
		DefaultTableModel dtm=(DefaultTableModel) studentTable.getModel();
		dtm.setRowCount(0); // 设置成0行
		Connection con=null;
		try{
			con=dbUtil.getCon();
			ResultSet rs=studentDao.list(con, student);
			while(rs.next()){
				Vector v=new Vector();
				v.add(rs.getInt("sno"));
				v.add(rs.getString("sname"));
				v.add(rs.getString("sex"));
				v.add(rs.getString("birthday"));
				v.add(rs.getString("dept"));
				dtm.addRow(v);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
