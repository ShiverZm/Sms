package view;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import dao.ClassDao;
import dao.GradeDao;
import dao.StudentDao;
import entity.Class;
import entity.Grade;
import entity.Student;
import entity.User;
import util.DbUtil;
import util.StringUtil;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GradeAddInnerFrame extends JInternalFrame {
	private JTable studentTable;
	private JTable classTable;
	private JTextField snoTxt;
	private JTextField cnoTxt;
	private JTextField snameTxt;
	private JTextField cnameTxt;
	private JTextField gradeTxt;
	
	private User user=null;
	private DbUtil dbUtil=new DbUtil();
	private StudentDao studentDao=new StudentDao();
	private ClassDao classDao=new ClassDao();
	private GradeDao gradeDao=new GradeDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GradeAddInnerFrame frame = new GradeAddInnerFrame();
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
	public GradeAddInnerFrame() {
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("\u6210\u7EE9\u6DFB\u52A0");
		setBounds(100, 100, 858, 662);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel label = new JLabel("\u5B66\u751F\u5217\u8868");
		
		JLabel label_1 = new JLabel("\u8BFE\u7A0B\u5217\u8868");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JLabel label_2 = new JLabel("\u5B66\u53F7:");
		
		snoTxt = new JTextField();
		snoTxt.setEditable(false);
		snoTxt.setColumns(10);
		
		JLabel label_3 = new JLabel("\u8BFE\u53F7:");
		
		cnoTxt = new JTextField();
		cnoTxt.setEditable(false);
		cnoTxt.setColumns(10);
		
		JLabel label_4 = new JLabel("\u59D3\u540D:");
		
		JLabel label_5 = new JLabel("\u8BFE\u7A0B\u540D:");
		
		snameTxt = new JTextField();
		snameTxt.setEditable(false);
		snameTxt.setColumns(10);
		
		cnameTxt = new JTextField();
		cnameTxt.setEditable(false);
		cnameTxt.setColumns(10);
		
		JButton button = new JButton("\u6DFB\u52A0");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GradeAddActionPerformed(arg0);
			}
		});
		button.setIcon(new ImageIcon(GradeAddInnerFrame.class.getResource("/images/add.png")));
		
		JButton button_1 = new JButton("\u91CD\u7F6E");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				resetValueActionPerformed(arg0);
			}
		});
		button_1.setIcon(new ImageIcon(GradeAddInnerFrame.class.getResource("/images/reset.png")));
		
		JLabel label_6 = new JLabel("\u6210\u7EE9:");
		
		gradeTxt = new JTextField();
		gradeTxt.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(405, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(label_1)
						.addComponent(label))
					.addGap(422))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(206)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addComponent(label_2)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(snoTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(label_4)
									.addGap(10))
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addComponent(label_3)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(cnoTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(77)
											.addComponent(button)
											.addPreferredGap(ComponentPlacement.RELATED, 23, Short.MAX_VALUE))
										.addGroup(groupLayout.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(label_6)
												.addComponent(label_5))))))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(gradeTxt, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
								.addComponent(button_1, Alignment.TRAILING)
								.addComponent(snameTxt, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
								.addComponent(cnameTxt, Alignment.TRAILING)))
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE))
					.addGap(247))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_1)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(snoTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(snameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_4))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3)
						.addComponent(cnoTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cnameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_5))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_6)
						.addComponent(gradeTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(button)
						.addComponent(button_1))
					.addGap(61))
		);
		
		classTable = new JTable();
		classTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ClassTableMouseClicked(arg0);
			}
		});
		classTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u8BFE\u7A0B\u53F7", "\u8BFE\u7A0B\u540D"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_1.setViewportView(classTable);
		
		studentTable = new JTable();
		studentTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				StudentTableMouseClicked(arg0);
			}
		});
		studentTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u5B66\u53F7", "\u59D3\u540D", "\u6240\u5728\u7CFB"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(studentTable);
		getContentPane().setLayout(groupLayout);
		this.fillTable();
	}
	/**
	 * 初始化学生表和课程表
	 */
	private void fillTable(){
		DefaultTableModel stuDtm=(DefaultTableModel) studentTable.getModel();
		DefaultTableModel classDtm=(DefaultTableModel) classTable.getModel();
		stuDtm.setRowCount(0); 
		Connection con=null;
		try{
			con=dbUtil.getCon();
			ResultSet stuRs=studentDao.list(con, new Student());
			ResultSet classRs=classDao.list(con, new Class());
			while(stuRs.next()){
				Vector v=new Vector();
				v.add(stuRs.getString("sno"));
				v.add(stuRs.getString("sname"));
				v.add(stuRs.getString("dept"));
				stuDtm.addRow(v);
			}
			while(classRs.next()){
				Vector v=new Vector();
				v.add(classRs.getString("cno"));
				v.add(classRs.getString("cname"));
				classDtm.addRow(v);
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
	/**
	 * 学生表格选中事件处理
	 * @param me
	 */
	private void StudentTableMouseClicked(MouseEvent me){
		int srow=studentTable.getSelectedRow();
		snoTxt.setText((String) studentTable.getValueAt(srow, 0));
		snameTxt.setText((String) studentTable.getValueAt(srow, 1));
	}
	
	/**
	 * 课程表选中事件处理
	 * @param me
	 */
	private void ClassTableMouseClicked(MouseEvent me){
		int crow=classTable.getSelectedRow();
		cnoTxt.setText((String) classTable.getValueAt(crow, 0));
		cnameTxt.setText((String) classTable.getValueAt(crow, 1));
	}
	
	
	/**
	 * 重置学生成绩
	 * 
	 * @param evt
	 */
	private void resetValueActionPerformed(ActionEvent evt){
		snoTxt.setText("");
		snameTxt.setText("");
		cnoTxt.setText("");
		cnameTxt.setText("");
		gradeTxt.setText("");
	}
	
	private void GradeAddActionPerformed(ActionEvent arg0) {
		if(StringUtil.isEmpty(snoTxt.getText())||StringUtil.isEmpty(snameTxt.getText())){
			JOptionPane.showMessageDialog(null, "未选学生信息");
			return;
		}if(StringUtil.isEmpty(cnoTxt.getText())||StringUtil.isEmpty(cnameTxt.getText())){
			JOptionPane.showMessageDialog(null, "未选课程信息");
			return;
		}
		Grade grade=new Grade(snoTxt.getText(),cnoTxt.getText(),Integer.parseInt(gradeTxt.getText()));
		Connection con=null;
		try {
			con=dbUtil.getCon();
			if(gradeDao.add(con,grade)==1){
				JOptionPane.showMessageDialog(null, "添加成功");
			}else{
				JOptionPane.showMessageDialog(null, "添加失败");
			}
		} 
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "添加失败");
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
