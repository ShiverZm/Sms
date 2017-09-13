package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.StudentDao;
import entity.Class;
import entity.User;
import util.DbUtil;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class ListUnpassedStudentListInnerFrame extends JInternalFrame {
	private JTable table;
	private JTextField s_cnameTxt;
	
	private User user=null;
	private StudentDao studentDao=new StudentDao();
	private DbUtil dbUtil=new DbUtil();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListUnpassedStudentListInnerFrame frame = new ListUnpassedStudentListInnerFrame();
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
	public ListUnpassedStudentListInnerFrame() {
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setTitle("\u67E5\u8BE2\u4E0D\u53CA\u683C\u5B66\u751F\u4FE1\u606F");
		setBounds(100, 100, 630, 530);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel label = new JLabel("\u6309\u8BFE\u7A0B\u540D:");
		
		s_cnameTxt = new JTextField();
		s_cnameTxt.setColumns(10);
		
		JButton button = new JButton("\u67E5\u8BE2");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListByClassActionPerformed(arg0);
			}
		});
		button.setIcon(new ImageIcon(ListUnpassedStudentListInnerFrame.class.getResource("/images/search.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(122)
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(s_cnameTxt, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(button)
					.addContainerGap(36, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(56, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 519, GroupLayout.PREFERRED_SIZE)
					.addGap(39))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(52, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(s_cnameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button))
					.addGap(27)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
					.addGap(47))
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u59D3\u540D", "\u6210\u7EE9", "\u8BFE\u7A0B\u540D"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);
		fillTable(new Class());

	}
	
	/**
	 * 查询课程不及格学生信息
	 * @param evt
	 */
	private void ListByClassActionPerformed(ActionEvent evt){
		Class c=new Class();
		c.setCname(s_cnameTxt.getText());
		fillTable(c);
		
	}
	/**
	 * 初始化表格
	 */
	private void fillTable(Class c){
		DefaultTableModel dtm=(DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		Connection con=null;
		try {
			con=dbUtil.getCon();
			ResultSet rs=studentDao.listUnpassed(con, c);
			while(rs.next()){
				Vector v=new Vector<>();
				v.add(rs.getString("sno"));
				v.add(rs.getString("sname"));
				v.add(rs.getString("grade"));
				v.add(rs.getString("cname"));
				dtm.addRow(v);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}