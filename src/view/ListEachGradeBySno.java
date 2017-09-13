package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import dao.GradeDao;
import entity.User;
import util.DbUtil;
import util.StringUtil;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

public class ListEachGradeBySno extends JInternalFrame {
	private JTable table;
	private JTextField s_snoTxt;
	
	
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
					ListEachGradeBySno frame = new ListEachGradeBySno();
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
	public ListEachGradeBySno() {
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("\u5B66\u53F7\u67E5\u8BE2\u5404\u79D1\u6210\u7EE9");
		setBounds(100, 100, 647, 535);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel label = new JLabel("\u5B66\u53F7\uFF1A");
		
		s_snoTxt = new JTextField();
		s_snoTxt.setColumns(10);
		
		JButton button = new JButton("\u67E5\u8BE2");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListGradeActionPerformed(arg0);
			}
		});
		button.setIcon(new ImageIcon(ListEachGradeBySno.class.getResource("/images/search.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(53, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(label)
							.addGap(27)
							.addComponent(s_snoTxt, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
							.addGap(47)
							.addComponent(button)
							.addGap(85))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 543, GroupLayout.PREFERRED_SIZE)
							.addGap(35))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(98, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(button)
						.addComponent(s_snoTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label))
					.addGap(27)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE)
					.addGap(117))
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u8BFE\u7A0B\u540D", "\u6210\u7EE9"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);

	}
	
	private void ListGradeActionPerformed(ActionEvent evt){
		String sno=s_snoTxt.getText();
		if(StringUtil.isEmpty(sno)){
			JOptionPane.showMessageDialog(null, "请输入学号");
			return;
		}
		DefaultTableModel dtm=(DefaultTableModel) table.getModel();
		Connection con=null;
		try {
			con=dbUtil.getCon();
			ResultSet rs=gradeDao.listGrade(con,sno);
			if(rs!=null){
				while(rs.next()){
					Vector v =new Vector<>();
					v.add(rs.getString("cname"));
					v.add(rs.getString("grade"));
					dtm.addRow(v);
				}
			}else{
				JOptionPane.showMessageDialog(null, "请检验是否存在该学生");
				return;
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
