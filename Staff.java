package RestaurantManagement;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Staff extends JFrame {

	private JPanel contentPane;
	private JLabel userLabel;
	private JButton modifyButton;
	private JButton menuButton;
	private JButton reservationButton;
	private JButton tableButton;
	private JButton billButton;
	
	private UserName userName;

	/**
	 * Launch the application.
	 */
	
	

	/**
	 * Create the frame.
	 */
	public Staff() {
		setTitle("\u9910\u996E\u7BA1\u7406\u7CFB\u7EDF");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		userName=new UserName();
		JLabel userLabel = new JLabel("��ӭ����" + userName.getUserName());
		
		modifyButton = new JButton("\u4FEE\u6539\u5BC6\u7801");
		modifyButton.addMouseListener(new MouseAdapter() {     //�޸�����
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//��ת���޸��������
				StaffModifyPassword ModifyFrame=new StaffModifyPassword();
			    ModifyFrame.setVisible(true);
				
			}
		});
		
		menuButton = new JButton("\u83DC\u5355");  //�˵�
		menuButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//��ת���˵�����
				StaffMenu MenuFrame=new StaffMenu();
				MenuFrame.setVisible(true);
			}
		});
		
		reservationButton = new JButton("\u70B9\u83DC");   //���
		reservationButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//��ת����˽���
				ReservationTable reservationFrame=new ReservationTable();
				reservationFrame.setVisible(true);
			}
		});
		
		tableButton = new JButton("\u9910\u684C");   //����
		tableButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//��ת����������
				StaffTable TableFrame=new StaffTable();
				TableFrame.setVisible(true);
				setVisible(false);
			}
		});
		
		billButton = new JButton("\u7ED3\u8D26");    //����
		billButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//��ת�����˽��� BillTable
				BillTable TableFrame=new BillTable();
				TableFrame.setVisible(true);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(userLabel)
							.addPreferredGap(ComponentPlacement.RELATED, 257, Short.MAX_VALUE)
							.addComponent(modifyButton))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(120)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(menuButton)
								.addComponent(tableButton))
							.addPreferredGap(ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(reservationButton)
								.addComponent(billButton))
							.addGap(112)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(userLabel)
								.addComponent(modifyButton))
							.addGap(150)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(tableButton)
								.addComponent(billButton)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(109)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(menuButton)
								.addComponent(reservationButton))))
					.addContainerGap(55, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
