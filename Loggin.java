package RestaurantManagement;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.Color;

public class Loggin extends JFrame {

	private JPanel contentPane;
	private JTextField userText;
	private JPasswordField passwordText;
	
	private UserName userName;
    private DatabaseConnection dbc=null;
	
	Connection conn=null;
	ResultSet rs=null;   //�����
	Statement stat=null;
	private JRadioButton staffRadioButton;
	private JRadioButton adminRadioButton;
	private JButton deleteButton;
	private JButton addButton;
	private JLabel errorLabel;

	/**
	 * Launch the application.
	 */
	
	boolean selectAdminTable(MouseEvent e) //��ѯ����Ա�����Ƿ��д��û�
	{
		try
		{
			stat=conn.createStatement(); //�����������
			String sql="SELECT id FROM admin where name=";
			sql=sql + "'" + userText.getText() + "'";
			ResultSet rs=stat.executeQuery(sql);
			
			if(rs.next()==true)
			{
				return true;
			}
			else
			{
				return false;
			}
			
		}catch(Exception ex)
		{
			
		}
		return false;
	}
	
	boolean selectStaffTable(MouseEvent e)   //��ѯԱ�����Ƿ���ڴ��û�
	{
		try
		{
			String sql="SELECT id FROM staff where name=";
			sql=sql + "'" + userText.getText() + "'";
			//stat=conn.createStatement();  //�����������
			rs=stat.executeQuery(sql);
			
			
			if(rs.next()==true)
			{
				
				return true;
				
			}
			else
			{
				
				return false;
			}
			
		}catch(Exception ex)
		{
			//ex.printStackTrace();
		}
		
		return false;
	}
	
	void deleteAdminTable(MouseEvent e) //ɾ������Ա���е�ĳһ����
	{
		try
		{
			//stat=conn.createStatement(); //�����������
			String sql="DELETE FROM admin WHERE name='";
		    sql=sql + userText.getText() + "'";
		    stat.executeUpdate(sql);
			
			
		}catch(Exception ex)
		{
			
		}
	}
	
	void deleteStaffTable(MouseEvent e)   //ɾ���û����е�ĳһ����
	{
		try
		{
			//stat=conn.createStatement(); //�����������
			String sql="DELETE FROM staff WHERE name='";
		    sql=sql + userText.getText() + "'";
		    System.out.println(sql);
			
			
			
			
		}catch(Exception ex)
		{
			//ex.printStackTrace();
		}
		
	}
	
	
	void addAdminTable(MouseEvent e)   //ɾ���û����е�ĳһ����
	{
		try
		{
			//stat=conn.createStatement(); //�����������
			String sql="insert into admin(name,password) values('";
		    sql=sql + userText.getText() + "','" + passwordText.getText() + "')";
		    
		    stat.executeUpdate(sql);
			
			
			
		}catch(Exception ex)
		{
			//ex.printStackTrace();
		}
		
	}
	
	
	void addStaffTable(MouseEvent e)   //ɾ���û����е�ĳһ����
	{
		try
		{
			stat=conn.createStatement(); //�����������
			String sql="insert into staff(name,password) values('";
		    sql=sql + userText.getText() + "','" + passwordText.getText() + "')";
		    stat.executeUpdate(sql);
			
			
			
			
		}catch(Exception ex)
		{
			//ex.printStackTrace();
		}
		
	}
	
	

	/**
	 * Create the frame.
	 */
	public Loggin() {
		
		userName=new UserName();
		this.dbc=new DatabaseConnection();
		this.conn=dbc.getConnection();
		
		setTitle("\u4EBA\u5458\u7BA1\u7406");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel userLabel = new JLabel("\u7528\u6237\u540D");
		
		JLabel passwordLabel = new JLabel("\u5BC6\u7801");
		
		userText = new JTextField();
		userText.setColumns(10);
		
		passwordText = new JPasswordField();
		
		staffRadioButton = new JRadioButton("\u5458\u5DE5");
		staffRadioButton.setSelected(true);
		
		adminRadioButton = new JRadioButton("\u7BA1\u7406\u5458");
		
		ButtonGroup radioGroup=new ButtonGroup();
		radioGroup.add(staffRadioButton);
		radioGroup.add(adminRadioButton);
		
		deleteButton = new JButton("\u5220\u9664");  //ɾ������Ա��ֻҪroot��Ȩ�ޣ���Ա��
		deleteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(adminRadioButton.isSelected()) //��ѡ��ѡ�����Ա
				{
					if(userName.getUserName().equals("root"))
					{
					   if(selectAdminTable(arg0))   //�û�����
					   {
						   
				           
				           deleteAdminTable(arg0);
				           errorLabel.setText("ɾ���ɹ�");
				           userText.setText("");
				           passwordText.setText("");
					   }
					   else   //�û�������
					   {
						   errorLabel.setText("�����ڴ˹���Ա");
						   userText.setText("");
						   passwordText.setText("");
					   }
					}
					else
					{
						errorLabel.setText("��Ȩ��ɾ������Ա");
					}
				}
				else if(staffRadioButton.isSelected())
				{
					if(selectStaffTable(arg0))   //�û�����
					{
						
						deleteStaffTable(arg0);
						errorLabel.setText("ɾ���ɹ�");
						userText.setText("");
						passwordText.setText("");
						
						
					}
					else    //�û�������
					{
						errorLabel.setText("�����ڴ�Ա��");
					}
				}
			}
		});
		
		addButton = new JButton("\u6DFB\u52A0");   //��ӹ���Ա��ֻҪroot��Ȩ�ޣ���Ա��
		addButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(adminRadioButton.isSelected()) //��ѡ��ѡ�����Ա
				{
					if(userName.getUserName().equals("root"))
					{
					   if(!selectAdminTable(e))   //�û�������
					   {
						   
				           
				           addAdminTable(e);
				           errorLabel.setText("��ӳɹ�");
				           userText.setText("");
				           passwordText.setText("");
					   }
					   else   //�û�����
					   {
						   errorLabel.setText("�˹���Ա�Ѵ���");
						   userText.setText("");
						   passwordText.setText("");
					   }
					}
					else
					{
						errorLabel.setText("��Ȩ����ӹ���Ա");
					}
				}
				else if(staffRadioButton.isSelected())//��ѡ��ѡ��Ա��
				{
					if(!selectStaffTable(e))   //�û�������
					{
						
						addStaffTable(e);
						errorLabel.setText("��ӳɹ�");
						userText.setText("");
						passwordText.setText("");
						
						
					}
					else    //�û�����
					{
						errorLabel.setText("��Ա���Ѵ���");
					}
				}
			}
		});
		
		errorLabel = new JLabel("");
		errorLabel.setForeground(Color.RED);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(79)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(userLabel)
						.addComponent(passwordLabel))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(errorLabel)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(deleteButton)
									.addGap(75))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(staffRadioButton)
									.addGap(18)
									.addComponent(adminRadioButton)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(addButton))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(passwordText, Alignment.LEADING)
							.addComponent(userText, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)))
					.addContainerGap(92, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(errorLabel)
					.addGap(34)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(userLabel)
						.addComponent(userText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordLabel)
						.addComponent(passwordText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(staffRadioButton)
						.addComponent(adminRadioButton))
					.addPreferredGap(ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(deleteButton)
						.addComponent(addButton))
					.addGap(26))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
