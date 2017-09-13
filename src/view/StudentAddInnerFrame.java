package view;

import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import dao.StudentDao;
import entity.Student;
import entity.User;
import util.DbUtil;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class StudentAddInnerFrame extends JInternalFrame {
	private JTextField snoTxt;
	private JTextField snameTxt;
	private JTextField birthdayTxt;
	private JTextField deptTxt;;
	private JRadioButton manTxt;
	private JRadioButton womanTxt;
	
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
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
					StudentAddInnerFrame frame = new StudentAddInnerFrame();
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
	public StudentAddInnerFrame() {
		setTitle("\u5B66\u751F\u6DFB\u52A0");
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 469, 465);
		
		JLabel label = new JLabel("\u5B66\u53F7:");
		
		JLabel lblNewLabel = new JLabel("\u5B66\u751F\u59D3\u540D:");
		
		JLabel label_1 = new JLabel("\u6027\u522B:");
		
		JLabel label_2 = new JLabel("\u51FA\u751F\u65E5\u671F:");
		
		JLabel lblNewLabel_1 = new JLabel("\u6240\u5728\u7CFB:");
		
		snoTxt = new JTextField();
		snoTxt.setColumns(10);
		
		snameTxt = new JTextField();
		snameTxt.setColumns(10);
		
		manTxt = new JRadioButton("\u7537");
		buttonGroup.add(manTxt);
		
		womanTxt = new JRadioButton("\u5973");
		buttonGroup.add(womanTxt);
		
		birthdayTxt = new JTextField();
		birthdayTxt.setColumns(10);
		
		deptTxt = new JTextField();
		deptTxt.setColumns(10);
		
		JButton button = new JButton("\u6DFB\u52A0");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StudentAddActionPerformed(arg0);
			}
		});
		
		JButton button_1 = new JButton("\u91CD\u7F6E");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				resetValue(arg0);
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(68)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel)
						.addComponent(label_1)
						.addComponent(label)
						.addComponent(label_2))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(deptTxt)
						.addComponent(birthdayTxt)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(button)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(button_1))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(manTxt)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(womanTxt))
							.addComponent(snameTxt, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
							.addComponent(snoTxt)))
					.addContainerGap(130, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(81)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(snoTxt, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(snameTxt, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(manTxt)
						.addComponent(womanTxt))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(birthdayTxt, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2))
					.addGap(22)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(deptTxt, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addGap(55)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(button)
						.addComponent(button_1))
					.addContainerGap(55, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}
	/**
	 * 添加学生事件处理
	 * 
	 * @param evt
	 */
	private void StudentAddActionPerformed(ActionEvent evt){
		Student s=new Student();
		s.setSno(snoTxt.getText());
		s.setSname(snameTxt.getText());
		String sex=null;
		if(manTxt.isSelected()){
			sex="男";
		}if(womanTxt.isSelected()){
			sex="女";
		}
		s.setSex(sex);
		s.setDept(deptTxt.getText());
		s.setBirthday(birthdayTxt.getText());
		Connection con=null;
		try {
			con=dbUtil.getCon();
			if(studentDao.add(con, s)==1){
				JOptionPane.showMessageDialog(null, "添加成功");	
				this.dispose();
			}else{
				JOptionPane.showMessageDialog(null, "添加失败");
			}
			
		} catch (MySQLIntegrityConstraintViolationException e) {
			JOptionPane.showMessageDialog(null, "该学生已存在");
			e.printStackTrace();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "添加失败");
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	private void resetValue(ActionEvent evt){
		 snoTxt.setText("");;
		snameTxt.setText("");;
		birthdayTxt.setText("");;
		 deptTxt.setText("");;
		 manTxt.setSelected(false);;
		womanTxt.setSelected(false);;
	}
}
