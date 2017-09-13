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
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

import dao.ClassDao;
import entity.User;
import util.DbUtil;
import util.StringUtil;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class HeightestAndLowestScoreByCno extends JInternalFrame {
	private JTable table;
	private JTextField s_cnoTxt;
	
	private User user=null;
	private DbUtil dbUtil=new DbUtil();
	private ClassDao classDao=new ClassDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HeightestAndLowestScoreByCno frame = new HeightestAndLowestScoreByCno();
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
	public HeightestAndLowestScoreByCno() {
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 642, 497);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel label = new JLabel("\u8BFE\u7A0B\u53F7:");
		
		s_cnoTxt = new JTextField();
		s_cnoTxt.setColumns(10);
		
		JButton button = new JButton("\u67E5\u8BE2");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SearchHeightestAndLowest(arg0);
			}
		});
		button.setIcon(new ImageIcon(HeightestAndLowestScoreByCno.class.getResource("/images/search.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(65, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 498, GroupLayout.PREFERRED_SIZE)
					.addGap(63))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(179)
					.addComponent(label)
					.addGap(18)
					.addComponent(s_cnoTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addComponent(button)
					.addContainerGap(201, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(91, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(button)
						.addComponent(s_cnoTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label))
					.addGap(43)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
					.addGap(89))
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u8BFE\u7A0B\u540D", "\u6700\u9AD8\u5206", "\u6700\u4F4E\u5206"
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
		fillTable(new String());
	}
	
	private void SearchHeightestAndLowest(ActionEvent evt){
		String cno=s_cnoTxt.getText();
		if(StringUtil.isEmpty(cno)){
			JOptionPane.showMessageDialog(null, "请输入查询课号");
			return;
		}
		fillTable(cno);
	}
	
	private void fillTable(String cno){
		DefaultTableModel dtm=(DefaultTableModel) table.getModel();
		dtm.setRowCount(0); 
		Connection con=null;
		try {
			con=dbUtil.getCon();
			ResultSet rs=classDao.listHeighestAndLowest(con,cno);
			if(rs!=null){
				while(rs.next()){
					Vector v =new Vector<>();
					v.add(rs.getString("cname"));
					v.add(rs.getInt("heightest"));
					v.add(rs.getInt("lowest"));
					dtm.addRow(v);
				}
			}else{
				JOptionPane.showMessageDialog(null, "课程号不存在 请查实");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
