package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.GradeDao;
import entity.Grade;
import entity.User;
import util.DbUtil;
import util.StringUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GradeManageInnerFrame extends JInternalFrame {
	private JTable table;
	private JTextField s_snameTxt;
	private JTextField s_cnameTxt;
	private JTextField snoTxt;
	private JTextField snameTxt;
	private JTextField cnameTxt;
	private JTextField gradeTxt;

	private User user=null;
	private GradeDao gradeDao=new GradeDao();
	private DbUtil dbUtil=new DbUtil();
	private JTextField cnoTxt;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GradeManageInnerFrame frame = new GradeManageInnerFrame();
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
	public GradeManageInnerFrame() {
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setTitle("\u6210\u7EE9\u7BA1\u7406");
		setBounds(100, 100, 684, 553);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel label = new JLabel("\u6309\u59D3\u540D:");
		
		s_snameTxt = new JTextField();
		s_snameTxt.setColumns(10);
		
		JLabel label_1 = new JLabel("\u6309\u8BFE\u540D:");
		
		s_cnameTxt = new JTextField();
		s_cnameTxt.setColumns(10);
		
		JButton button = new JButton("\u67E5\u8BE2");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GradeSearchActionPerformed(arg0);
			}
		});
		button.setIcon(new ImageIcon(GradeManageInnerFrame.class.getResource("/images/search.png")));
		
		JLabel label_2 = new JLabel("\u5B66\u53F7:");
		
		snoTxt = new JTextField();
		snoTxt.setEditable(false);
		snoTxt.setColumns(10);
		
		JLabel label_3 = new JLabel("\u59D3\u540D:");
		
		snameTxt = new JTextField();
		snameTxt.setEditable(false);
		snameTxt.setColumns(10);
		
		JLabel label_5 = new JLabel("\u8BFE\u540D:");
		
		cnameTxt = new JTextField();
		cnameTxt.setEditable(false);
		cnameTxt.setColumns(10);
		
		JLabel label_6 = new JLabel("\u6210\u7EE9:");
		
		gradeTxt = new JTextField();
		gradeTxt.setColumns(10);
		
		JButton button_1 = new JButton("\u4FEE\u6539");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GradeModifyActionPerformed(arg0);
			}
		});
		button_1.setIcon(new ImageIcon(GradeManageInnerFrame.class.getResource("/images/modify.png")));
		
		JButton button_2 = new JButton("\u5220\u9664");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GradeDeleteActionPerformed(arg0);
			}
		});
		button_2.setIcon(new ImageIcon(GradeManageInnerFrame.class.getResource("/images/delete.png")));
		
		JLabel label_4 = new JLabel("\u8BFE\u53F7:");
		
		cnoTxt = new JTextField();
		cnoTxt.setEditable(false);
		cnoTxt.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(69, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_6)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(gradeTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(469))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(label)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(s_snameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGap(37)
										.addComponent(label_1)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(s_cnameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
										.addComponent(button))
									.addGroup(groupLayout.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 552, GroupLayout.PREFERRED_SIZE))
									.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addGroup(groupLayout.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(label_2)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(snoTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(label_3)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(snameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
										.addGap(76)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(label_5)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(cnameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
											.addComponent(button_2)
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(label_4)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(cnoTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
										.addGap(208)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(44)
									.addComponent(button_1)
									.addGap(3)))
							.addGap(47))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(label)
							.addComponent(s_snameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(s_cnameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(label_1))
						.addComponent(button))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(snoTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_4)
						.addComponent(cnoTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(33)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3)
						.addComponent(snameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_5)
						.addComponent(cnameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_6)
						.addComponent(gradeTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(button_1)
						.addComponent(button_2))
					.addContainerGap(62, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TableMouseClicked(arg0);
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u5B66\u53F7", "\u59D3\u540D", "\u8BFE\u53F7", "\u8BFE\u540D", "\u6210\u7EE9"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);
		this.fillTable(new HashMap<String,Object>());
	}
	
	/**
	 * 初始化成绩表
	 */
	private void fillTable(Map<String, Object> map){
		DefaultTableModel dtm=(DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		Connection con=null;
		try {
			con=dbUtil.getCon();
			ResultSet rs=gradeDao.list(con,map);
			while(rs.next()){
				Vector v=new Vector<>();
				v.add(rs.getString("sno"));
				v.add(rs.getString("sname"));
				v.add(rs.getString("cno"));
				v.add(rs.getString("cname"));
				v.add(rs.getInt("grade"));
				dtm.addRow(v);
			}
		} catch (Exception e) {
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
	/**
	 * 成绩查询事件处理
	 * @param evt
	 */
	private void GradeSearchActionPerformed(ActionEvent evt){
		String s_sname=s_snameTxt.getText();
		String c_cname=s_cnameTxt.getText();
		Map map=new HashMap<String, Object>();
		map.put("sname", s_sname);
		map.put("cname", c_cname);
		this.fillTable(map);
	}
	/**
	 * 表格点击事件处理
	 * @param me
	 */
	private void TableMouseClicked(MouseEvent me){
		int row=table.getSelectedRow();
		snoTxt.setText((String) table.getValueAt(row, 0));
		snameTxt.setText((String) table.getValueAt(row, 1));
		cnoTxt.setText((String) table.getValueAt(row, 2));
		cnameTxt.setText((String) table.getValueAt(row,3));
		gradeTxt.setText(""+table.getValueAt(row, 4));
	}
	/**
	 * 成绩修改事件处理
	 * 
	 * @param evt
	 */
	private void GradeModifyActionPerformed(ActionEvent evt){
		Grade grade=new Grade(snoTxt.getText(),cnoTxt.getText(),Integer.parseInt(gradeTxt.getText()));
		if((Integer)grade.getGrade()==null){
			JOptionPane.showMessageDialog(null, "成绩不能为空");
			return;
		}
		Connection con=null;
		try {
			con=dbUtil.getCon();
			if(gradeDao.modify(con,grade)==1){
				JOptionPane.showMessageDialog(null, "修改成功");
				this.fillTable(new HashMap<>());
			}else{
				JOptionPane.showMessageDialog(null, "修改失败");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "修改失败");
			e.printStackTrace();
		}finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private void GradeDeleteActionPerformed(ActionEvent evt){
		Grade grade =new Grade(snoTxt.getText(), cnoTxt.getText());
		int n=JOptionPane.showConfirmDialog(null, "是否要删除这条数据");
		if(n==0){
			Connection con=null;
			try {
				con=dbUtil.getCon();
				if(gradeDao.delete(con,grade)==1){
					JOptionPane.showMessageDialog(null, "删除成功");
					fillTable(new HashMap<>());
				}else{
					JOptionPane.showInternalMessageDialog(null, "删除失败");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return;
	}
}
