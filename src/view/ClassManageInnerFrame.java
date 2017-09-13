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

import dao.ClassDao;
import entity.Class;
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

public class ClassManageInnerFrame extends JInternalFrame {
	private JTextField s_cnameTxt;
	private JTable classTable;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField cnoTxt;
	private JTextField cnameTxt;
	private JTextField creditTxt;
	
	private User user=null;
	private DbUtil dbUtil=new DbUtil();
	private ClassDao ClassDao=new ClassDao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClassManageInnerFrame frame = new ClassManageInnerFrame();
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
	public ClassManageInnerFrame() {
		setIconifiable(true);
		setClosable(true);
		setTitle("\u8BFE\u7A0B\u4FE1\u606F\u7EF4\u62A4");
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
		
		JLabel label_1 = new JLabel("\u8BFE\u7A0B\u53F7:");
		
		JLabel label_2 = new JLabel("\u5B66\u5206:");
		
		JLabel label_3 = new JLabel("\u8BFE\u7A0B\u540D:");
		
		cnoTxt = new JTextField();
		cnoTxt.setColumns(10);
		
		cnameTxt = new JTextField();
		cnameTxt.setColumns(10);
		
		creditTxt = new JTextField();
		creditTxt.setColumns(10);
		
		JButton button_1 = new JButton("\u4FEE\u6539");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClassModifyActionPerformed(arg0);
			}
		});
		button_1.setIcon(new ImageIcon(ClassManageInnerFrame.class.getResource("/images/modify.png")));
		
		JButton button_2 = new JButton("\u5220\u9664");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteClassActionPerformed(arg0);
			}
		});
		button_2.setIcon(new ImageIcon(ClassManageInnerFrame.class.getResource("/images/delete.png")));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(28)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(label_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cnoTxt, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
						.addComponent(button_1))
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(26)
							.addComponent(button_2))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addComponent(label_2)
								.addComponent(label_3))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(creditTxt, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
								.addComponent(cnameTxt, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(76, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(cnoTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_3)
						.addComponent(cnameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(creditTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(button_2)
						.addComponent(button_1))
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		
		classTable = new JTable();
		classTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TableMouseClicked(arg0);
			}
		});
		classTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u8BFE\u7A0B\u53F7", "\u8BFE\u7A0B\u540D", "\u5B66\u5206"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(classTable);
		
		JLabel label = new JLabel("\u6309\u8BFE\u7A0B\u540D:");
		
		s_cnameTxt = new JTextField();
		s_cnameTxt.setColumns(10);
		
		JButton button = new JButton("\u67E5\u8BE2");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				searcheActionPerformed(arg0);
			}
		});
		button.setIcon(new ImageIcon(ClassManageInnerFrame.class.getResource("/images/search.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(105)
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(s_cnameTxt, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(button)
					.addContainerGap(56, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(31, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(s_cnameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button)
						.addComponent(label))
					.addGap(35))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
		this.fillTable(new Class());

	}
	
	/**
	 * 删除课程事件处理
	 * 
	 */
	private void deleteClassActionPerformed(ActionEvent evt){
		String cno=cnoTxt.getText();
		int n=JOptionPane.showConfirmDialog(null, "确实要删除这条数据");
		if(n==0){
			Connection con=null;
			try {
				con=dbUtil.getCon();
				if(ClassDao.delete(con,cno)==1){
					JOptionPane.showMessageDialog(null, "已经成功删除");
					this.fillTable(new Class());
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
	 * 按课程号查询信息
	 * @param evt
	 */
	private void  searcheActionPerformed(ActionEvent evt){
		Class c=new Class();
		c.setCname(s_cnameTxt.getText());
		this.fillTable(c);
	}

	
	/**
	 * 修改课程信息事件处理
	 * @param evt
	 */
	private void ClassModifyActionPerformed(ActionEvent evt){
				Class c =new Class();
				c.setCno(cnoTxt.getText());
				c.setCname(cnameTxt.getText());
				c.setCredit(Double.parseDouble(creditTxt.getText()));
				Connection con=null;
				try {
					con=dbUtil.getCon();
					int result=ClassDao.update(con,c);
					if(result==1){
						JOptionPane.showMessageDialog(null, "修改成功");
						this.fillTable(new Class());
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
		int row=classTable.getSelectedRow();
		cnoTxt.setText(""+classTable.getValueAt(row, 0));
		cnameTxt.setText((String) classTable.getValueAt(row, 1));
		creditTxt.setText((String) classTable.getValueAt(row, 2));
		
	}
	/**
	 *课程表格初始化
	 * @param c
	 */
	private void fillTable(Class c){
		DefaultTableModel dtm=(DefaultTableModel) classTable.getModel();
		dtm.setRowCount(0); // 设置成0行
		Connection con=null;
		try{
			con=dbUtil.getCon();
			ResultSet rs=ClassDao.list(con, c);
			while(rs.next()){
				Vector v=new Vector();
				v.add(rs.getString("cno"));
				v.add(rs.getString("cname"));
				v.add(rs.getString("credit"));
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
