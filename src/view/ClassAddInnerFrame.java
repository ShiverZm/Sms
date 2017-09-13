package view;

import java.awt.EventQueue;
import entity.Class;
import entity.User;
import util.DbUtil;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import dao.ClassDao;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class ClassAddInnerFrame extends JInternalFrame {
	private JTextField cnoTxt;
	private JTextField cnameTxt;
	private JTextField creditTxt;
	
	private User user=null;
	private ClassDao classDao=new ClassDao();
	private DbUtil dbUtil=new DbUtil();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClassAddInnerFrame frame = new ClassAddInnerFrame();
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
	public ClassAddInnerFrame() {
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("\u8BFE\u7A0B\u6DFB\u52A0");
		setBounds(100, 100, 329, 410);
		
		JLabel label = new JLabel("\u8BFE\u7A0B\u53F7:");
		
		JLabel label_1 = new JLabel("\u8BFE\u7A0B\u540D:");
		
		JLabel lblNewLabel = new JLabel("\u8BFE\u7A0B\u5B66\u5206:");
		
		cnoTxt = new JTextField();
		cnoTxt.setColumns(10);
		
		cnameTxt = new JTextField();
		cnameTxt.setColumns(10);
		
		creditTxt = new JTextField();
		creditTxt.setColumns(10);
		
		JButton button = new JButton("\u6DFB\u52A0");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClassAddActionPerformed(arg0);
			}
		});
		button.setIcon(new ImageIcon(ClassAddInnerFrame.class.getResource("/images/add.png")));
		
		JButton button_1 = new JButton("\u91CD\u7F6E");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				resetValueActionPerformed(arg0);
			}
		});
		button_1.setIcon(new ImageIcon(ClassAddInnerFrame.class.getResource("/images/reset.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGap(41)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(label_1)
								.addComponent(label)
								.addComponent(lblNewLabel))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(creditTxt)
								.addComponent(cnameTxt)
								.addComponent(cnoTxt, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(button)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button_1)))
					.addContainerGap(72, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(85)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(cnoTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label)
							.addGap(28)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_1)
								.addComponent(cnameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(30)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(creditTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(button_1)
						.addComponent(button))
					.addGap(85))
		);
		getContentPane().setLayout(groupLayout);

	}
	private void ClassAddActionPerformed(ActionEvent evt){
		Class c=new Class();
		c.setCname(cnameTxt.getText());
		c.setCno(cnoTxt.getText());
		c.setCredit(Double.parseDouble(creditTxt.getText()));
		Connection con=null;
		try {
			con=dbUtil.getCon();
			int n=classDao.add(con,c);
			if(n==1){
				JOptionPane.showMessageDialog(null, "添加成功");
				this.dispose();
			}else{
				JOptionPane.showMessageDialog(null, "添加失败");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "添加失败");
		}finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	private void resetValueActionPerformed(ActionEvent evt){
		cnoTxt.setText("");
		cnameTxt.setText("");
		creditTxt.setText("");
	}
}
