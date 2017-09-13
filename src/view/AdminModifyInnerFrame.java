package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.sql.Connection;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import entity.User;
import util.DbUtil;
import util.StringUtil;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import dao.UserDao;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;

public class AdminModifyInnerFrame extends JInternalFrame {
	private JTextField c_userNameTxt;
	private JPasswordField c_oldPasswordTxt;
	private JPasswordField c_newPasswordTxt;
	private JPasswordField c_repasswordTxt;
	
	private UserDao userDao=new UserDao();
	private DbUtil dbUtil=new DbUtil();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminModifyInnerFrame frame = new AdminModifyInnerFrame();
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
	public AdminModifyInnerFrame() {
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 450, 430);
		
		JLabel label = new JLabel("\u7528\u6237\u540D:");
		
		c_userNameTxt = new JTextField();
		c_userNameTxt.setColumns(10);
		
		JLabel label_1 = new JLabel("\u65E7\u5BC6\u7801:");
		
		JLabel label_2 = new JLabel("\u65B0\u5BC6\u7801:");
		
		JLabel label_3 = new JLabel("\u786E\u8BA4\u5BC6\u7801:");
		
		JButton button = new JButton("\u4FEE\u6539");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ModifyPasswordActionPerformed(arg0);
			}
		});
		button.setIcon(new ImageIcon(AdminModifyInnerFrame.class.getResource("/images/modify.png")));
		
		JButton button_1 = new JButton("\u91CD\u7F6E");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetActionPerformed(e);
			}
		});
		button_1.setIcon(new ImageIcon(AdminModifyInnerFrame.class.getResource("/images/reset.png")));
		
		c_oldPasswordTxt = new JPasswordField();
		
		c_newPasswordTxt = new JPasswordField();
		
		c_repasswordTxt = new JPasswordField();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(73)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(label_2)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(button)
									.addComponent(label_3)))
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(label_1)
								.addComponent(label))
							.addPreferredGap(ComponentPlacement.UNRELATED)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(c_userNameTxt))
						.addComponent(c_oldPasswordTxt)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addComponent(button_1)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(c_newPasswordTxt, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
								.addComponent(c_repasswordTxt))))
					.addContainerGap(150, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(72)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(c_userNameTxt, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(label))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(c_oldPasswordTxt, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(c_newPasswordTxt, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3)
						.addComponent(c_repasswordTxt, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(button)
						.addComponent(button_1))
					.addGap(59))
		);
		getContentPane().setLayout(groupLayout);

	}
	private void ModifyPasswordActionPerformed(ActionEvent evt){
		String userName=c_userNameTxt.getText();
		String password=c_oldPasswordTxt.getText();
		if(StringUtil.isEmpty(userName)){
			JOptionPane.showMessageDialog(null, "账号不能为空");
			return;
		}
		if(StringUtil.isEmpty(c_newPasswordTxt.getText())){
			JOptionPane.showMessageDialog(null, "密码不能为空");
			return;
		}
		if(StringUtil.isEmpty(c_repasswordTxt.getText())){
			JOptionPane.showMessageDialog(null, "重复密码不能为空");
			return;
		}
		if(!c_repasswordTxt.getText().equals(c_newPasswordTxt.getText())){
			JOptionPane.showMessageDialog(null, "两次密码不一致");
			return;
		}
		User user=new User();
		user.setUserName(userName);
		user.setPassword(password);
		String newPassword=c_newPasswordTxt.getText();
		Connection con=null;
		try {
			con=dbUtil.getCon();
			if(userDao.modifyPassword(con,user,newPassword)==1){
				JOptionPane.showMessageDialog(null, "修改密码成功");
				this.dispose();
			}else{
				JOptionPane.showMessageDialog(null, "原密码错误");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "修改密码失败");
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	private void resetActionPerformed(ActionEvent evt){
		c_oldPasswordTxt.setText("");
		c_newPasswordTxt.setText("");
		c_repasswordTxt.setText("");
		c_userNameTxt.setText("");
	}
}
