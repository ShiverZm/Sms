package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import entity.User;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.SystemColor;
import javax.swing.BoxLayout;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JDesktopPane table;
	private User user=null;
	private JLabel userName; 
	private JLabel urole;
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public MainFrame() throws HeadlessException {
		fillLayout();
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Create the frame.
	 */
	
	public MainFrame(User user) {
		this.user=user;
		fillLayout();
	}
	
	private void fillLayout(){
		setBackground(SystemColor.activeCaption);
		setAutoRequestFocus(false);
		setTitle("\u5B66\u751F\u7BA1\u7406\u754C\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 301);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		table = new JDesktopPane();
		table.setBackground(SystemColor.inactiveCaption);
		contentPane.add(table);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1904, 26);
		table.add(menuBar);
		
		JMenu menu_3 = new JMenu("\u7528\u6237\u7BA1\u7406");
		menu_3.setIcon(new ImageIcon(MainFrame.class.getResource("/images/admin.png")));
		menuBar.add(menu_3);
		
		JMenuItem menuItem_1 = new JMenuItem("\u4FEE\u6539\u5BC6\u7801");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdminModifyInnerFrame amif=new AdminModifyInnerFrame();
				amif.setVisible(true);
				table.add(amif);
			}
		});
		menuItem_1.setIcon(new ImageIcon(MainFrame.class.getResource("/images/edit.png")));
		menu_3.add(menuItem_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("\u7528\u6237\u540D\u5355");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				AdminManageInnerFrame amif=new AdminManageInnerFrame();
				amif.setVisible(true);
				table.add(amif);
			}
		});
		mntmNewMenuItem.setIcon(new ImageIcon(MainFrame.class.getResource("/images/bookManager.png")));
		menu_3.add(mntmNewMenuItem);
		
		JMenuItem menuItem_2 = new JMenuItem("\u5B89\u5168\u9000\u51FA");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int r=JOptionPane.showConfirmDialog(null, "是否退出");
				if(r==0){
					dispose();
				}
				
			}
		});
		menuItem_2.setIcon(new ImageIcon(MainFrame.class.getResource("/images/exit.png")));
		menu_3.add(menuItem_2);
		
		JMenu menu = new JMenu("\u5B66\u751F\u7BA1\u7406");
		menu.setIcon(new ImageIcon(MainFrame.class.getResource("/images/student.png")));
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("\u5B66\u751F\u6DFB\u52A0");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(user.getRole()<=1&&user!=null){
				StudentAddInnerFrame sdif=new StudentAddInnerFrame();
				sdif.setVisible(true);
				table.add(sdif);
				}else{
					JOptionPane.showInternalMessageDialog(null, "您没有该权限");
				}
			}
		});
		menuItem.setIcon(new ImageIcon(MainFrame.class.getResource("/images/add.png")));
		menu.add(menuItem);
		
		JMenuItem menuItem_3 = new JMenuItem("\u5B66\u751F\u7EF4\u62A4");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(user.getRole()<=1&&user!=null){
					StudentManageInnerFrame smif=new StudentManageInnerFrame();
					smif.setVisible(true);
					table.add(smif);
				}else{
					JOptionPane.showMessageDialog(null, "抱歉 您没有该权限");
				}
			}
		});
		menuItem_3.setIcon(new ImageIcon(MainFrame.class.getResource("/images/edit.png")));
		menu.add(menuItem_3);
		
		JMenu menu_4 = new JMenu("\u8BFE\u7A0B\u7BA1\u7406");
		menu_4.setIcon(new ImageIcon(MainFrame.class.getResource("/images/class.png")));
		menuBar.add(menu_4);
		
		JMenuItem menuItem_4 = new JMenuItem("\u8BFE\u7A0B\u6DFB\u52A0");
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(user.getRole()<=1&&user!=null){
					ClassAddInnerFrame caif=new ClassAddInnerFrame();
					caif.setVisible(true);
					table.add(caif);
				}else{
					JOptionPane.showMessageDialog(null, "抱歉 您没有该权限");
				}
			}
		});
		menuItem_4.setIcon(new ImageIcon(MainFrame.class.getResource("/images/add.png")));
		menu_4.add(menuItem_4);
		
		JMenuItem menuItem_5 = new JMenuItem("\u8BFE\u7A0B\u7EF4\u62A4");
		menuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(user.getRole()<=1&&user!=null){
					ClassManageInnerFrame cmif=new ClassManageInnerFrame();
					cmif.setVisible(true);
					table.add(cmif);
				}else{
					JOptionPane.showMessageDialog(null, "抱歉 您没有该权限");
				}
			}
		});
		menuItem_5.setIcon(new ImageIcon(MainFrame.class.getResource("/images/edit.png")));
		menu_4.add(menuItem_5);
		
		JMenu menu_1 = new JMenu("\u6210\u7EE9\u7BA1\u7406");
		menu_1.setIcon(new ImageIcon(MainFrame.class.getResource("/images/grade.png")));
		menuBar.add(menu_1);
		
		JMenuItem menuItem_6 = new JMenuItem("\u6210\u7EE9\u6DFB\u52A0");
		menuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(user.getRole()<=1&&user!=null){
					GradeAddInnerFrame gaif=new GradeAddInnerFrame();
					gaif.setVisible(true);
					table.add(gaif);
				}else{
						JOptionPane.showMessageDialog(null, "抱歉 您没有该权限");
				}
			}
		});
		menuItem_6.setIcon(new ImageIcon(MainFrame.class.getResource("/images/add.png")));
		menu_1.add(menuItem_6);
		
		JMenuItem menuItem_7 = new JMenuItem("\u6210\u7EE9\u7EF4\u62A4");
		menuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(user.getRole()<=1&&user!=null){
					GradeManageInnerFrame gmif=new GradeManageInnerFrame();
					gmif.setVisible(true);
					table.add(gmif);
				}else{
					JOptionPane.showMessageDialog(null, "抱歉 您没有该权限");
			}
			}
		});
		menuItem_7.setIcon(new ImageIcon(MainFrame.class.getResource("/images/edit.png")));
		menu_1.add(menuItem_7);
		
		JMenu menu_5 = new JMenu("\u67E5\u8BE2");
		menu_5.setIcon(new ImageIcon(MainFrame.class.getResource("/images/search.png")));
		menuBar.add(menu_5);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("\u4E0D\u53CA\u683C\u7684\u5B66\u751F\u540D\u5355");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListUnpassedStudentListInnerFrame suslif=new ListUnpassedStudentListInnerFrame();
				suslif.setVisible(true);
				table.add(suslif);
			}
		});
		mntmNewMenuItem_1.setIcon(new ImageIcon(MainFrame.class.getResource("/images/delete.png")));
		menu_5.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("\u5B66\u53F7\u67E5\u5DF2\u4FEE\u5B66\u5206\u603B\u6570");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreditSumBySno csbs=new CreditSumBySno();
				csbs.setVisible(true);
				table.add(csbs);
			}
		});
		mntmNewMenuItem_2.setIcon(new ImageIcon(MainFrame.class.getResource("/images/search.png")));
		menu_5.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("\u5B66\u53F7\u67E5\u5404\u79D1\u6210\u7EE9");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListEachGradeBySno legbs=new ListEachGradeBySno();
				legbs.setVisible(true);
				table.add(legbs);
			}
		});
		mntmNewMenuItem_3.setIcon(new ImageIcon(MainFrame.class.getResource("/images/search.png")));
		menu_5.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu = new JMenu("\u7EDF\u8BA1");
		mnNewMenu.setIcon(new ImageIcon(MainFrame.class.getResource("/images/base.png")));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("\u8BFE\u53F7\u7EDF\u8BA1\u4E0D\u53CA\u683C\u4EBA\u6570");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaticUnpassCountByCno sucbc=new StaticUnpassCountByCno();
				sucbc.setVisible(true);
				table.add(sucbc);
			}
		});
		mntmNewMenuItem_4.setIcon(new ImageIcon(MainFrame.class.getResource("/images/delete.png")));
		mnNewMenu.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("\u8BFE\u540D\u7EDF\u8BA1\u5404\u8BFE\u7A0B\u7684\u6700\u9AD8\u5206\u6700\u4F4E\u5206\u548C\u5E73\u5747\u5206");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HeightestAndLowestScoreByCno halsbc=new HeightestAndLowestScoreByCno();
				halsbc.setVisible(true);
				table.add(halsbc);
			}
		});
		mntmNewMenuItem_5.setIcon(new ImageIcon(MainFrame.class.getResource("/images/bookTypeManager.png")));
		mnNewMenu.add(mntmNewMenuItem_5);
		
		JMenu menu_2 = new JMenu("\u5173\u4E8E\u6211\u4EEC");
		menu_2.setIcon(new ImageIcon(MainFrame.class.getResource("/images/about.png")));
		menuBar.add(menu_2);
		
		JMenuItem menuItem_8 = new JMenuItem("\u5173\u4E8E\u6211");
		menuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AboutMeInnerFrame amif=new AboutMeInnerFrame();
				amif.setVisible(true);
				table.add(amif);
			}
		});
		menuItem_8.setIcon(new ImageIcon(MainFrame.class.getResource("/images/me.png")));
		menu_2.add(menuItem_8);
		
		JPanel panel = new JPanel();
		panel.setBounds(811, 166, 471, 210);
		table.add(panel);
		
		 userName = new JLabel();
		 userName.setFont(new Font("宋体", Font.PLAIN, 20));
		 userName.setText(user.getRealName());
		
		urole = new JLabel();
		int roleId=user.getRole();
		String str=null;
			if(roleId==0){
				str="管理员";
			}else if(roleId==1){
				str="老师";
			}else{
				str="学生";
			}
		urole.setText(str);
		
		JLabel lblNewLabel = new JLabel("\u6B22\u8FCE\uFF1A");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 32));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(152, Short.MAX_VALUE)
					.addComponent(userName)
					.addGap(33)
					.addComponent(urole)
					.addGap(142))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(39)
					.addComponent(lblNewLabel)
					.addContainerGap(360, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(48)
					.addComponent(lblNewLabel)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(urole)
						.addComponent(userName))
					.addContainerGap(108, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
}
