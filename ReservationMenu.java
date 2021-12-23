package RestaurantManagement;



import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.*;

public class ReservationMenu extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;// ������ģ�Ͷ���
	private JTable table;// ���������
	
	private int[] selectedRow;
	private String strValue="";
	private UserName userName;
    private DatabaseConnection dbc=null;
    
    
	
	Connection conn=null;
	ResultSet rs=null;   //�����
	Statement stat=null;
	private JLabel lblNewLabel;
	
	
	
	
	void addTable()   //����
	{
		try
		{
			stat=conn.createStatement(); //�����������
			String sql="create table t" + userName.gettableNum() + " (id int(4) primary key, name char(20), cost int, sell int)";
			stat.execute(sql);
			
			
		}catch(Exception ex)
		{
			
		}
	}
	
	
	void showmenuTable() //��ʾ���ױ��е��û�
	{
		try
		{
			stat=conn.createStatement(); //�����������
			String sql="SELECT * FROM menu";
			ResultSet rs=stat.executeQuery(sql);
			
			while(rs.next())
			{
				String[] rowValues = {rs.getString("id"),rs.getString("name"),rs.getString("num"),rs.getString("sell") };// �������������
				tableModel.addRow(rowValues);// ����ģ�������һ��
				int rowCount = table.getRowCount() + 1;
			}
			
		}catch(Exception ex)
		{
			
		}
	}
	
	
	void updatediningTable() //�޸Ĳ���
	{
		try
		{
			stat=conn.createStatement(); //�����������
			String sql="UPDATE dining_table SET status=1 WHERE id=";
		    sql=sql + userName.gettableNum();
		    stat.executeUpdate(sql);
			
			
			
		}catch(Exception ex)
		{
			
		}
	}
	
	
	
	
	void addmenuTable(MouseEvent e)   //��˵��������ӵ�ĳһ����
	{
		int sumSell=0; //�ܼ�
		int sumCost=0; //�ܳɱ�
		
		try
		{
			
			for(int i=0;i<selectedRow.length;i++)   //��˸���
			{
				//�ڲ��ױ��в�ѯ��Ĳ�
				stat=conn.createStatement(); //�����������
				String sql="SELECT * FROM menu where id="+table.getValueAt(selectedRow[i], 0);
				ResultSet rs=stat.executeQuery(sql);
				rs.next();
				
				//����Ĳ˲����
				stat=conn.createStatement(); //�����������
				String sql1="insert into t" + userName.gettableNum() + " values(";
			    sql1=sql1 + rs.getInt("id") + ",'" + rs.getString("name") + "',";
			    sql1=sql1 + rs.getInt("cost") + "," + rs.getInt("sell") + ")";
			    stat.executeUpdate(sql1);
			    
			    
			    //�ڲ��ױ��еĽ�����Ĳ˵Ŀ���һ
			    stat=conn.createStatement(); //�����������
				String sql2="UPDATE menu SET num=" + (rs.getInt("num")-1) + " WHERE id=";
			    sql2=sql2 + rs.getInt("id");
			    stat.executeUpdate(sql2);
			    
			    //�����������ܶ���
			    stat=conn.createStatement(); //�����������
				String sql4="insert into allmenu(name) values('";
			    sql4=sql4 + rs.getString("name") + "')";
			    stat.executeUpdate(sql4);
			    
			    
			    sumSell=sumSell+rs.getInt("sell");
			    sumCost=sumCost+rs.getInt("cost");
				
			}
			updatediningTable();  //�ò���״̬��ʾ��ռ��
			
			
			//���������������˹����
			stat=conn.createStatement(); //�����������
			String sql3="insert into bill values(now(),";
		    sql3=sql3 + userName.gettableNum() + "," + sumSell + ",";
		    sql3=sql3 + sumCost + "," + (sumSell-sumCost) + ", 0)";
		    stat.executeUpdate(sql3);
			
			
			
		}catch(Exception ex)
		{
			//ex.printStackTrace();
		}
		
	}
	
	
	
	public ReservationMenu() {
		super();
		
		userName=new UserName();
		this.dbc=new DatabaseConnection();
		this.conn=dbc.getConnection();
		
		setTitle("���׹���");
		//setBounds(100, 100, 432, 335);
		setBounds(100, 100, 450, 300);
		final JPanel pane2 = new JPanel();
		getContentPane().add(pane2, BorderLayout.NORTH);
		
		lblNewLabel = new JLabel("\u8BF7\u9009\u62E9\u83DC");
		pane2.add(lblNewLabel);
		
		final JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		String[] columnNames = { "���", "����","���","�۸�"};// ��������������
		String[][] tableValues = {};// ��������������
		// ����ָ����������ͱ�����ݵı��ģ��
		tableModel = new DefaultTableModel(tableValues, columnNames);
		
		table = new JTable(tableModel);// ����ָ�����ģ�͵ı��
		table.setRowSorter(new TableRowSorter<>(tableModel));// ���ñ���������
		// ���ñ���ѡ��ģʽΪ��ѡ
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		// Ϊ����������¼�������
		table.addMouseListener(new MouseAdapter() {
			// �����˵���¼�
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		scrollPane.setViewportView(table);
		final JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		final JButton okButton = new JButton("\u786E\u5B9A");  //ȷ��
		okButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				selectedRow= table.getSelectedRows();// ��ñ�ѡ���е�����
				//strValue=table.getValueAt(selectedRow, 0).toString();
				addTable();
				addmenuTable(e);
				
				//����ȷ�Ͻ���
				ReservationSure sureFrame=new ReservationSure();
				sureFrame.setVisible(true);
				setVisible(false);
			}
		});
		
		JButton cancelButton = new JButton("\u53D6\u6D88");  //ȡ��
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				ReservationTable resFrame=new ReservationTable();
				resFrame.setVisible(true);
				setVisible(false);
			}
		});
		panel.add(cancelButton);
		
		JLabel lblNewLabel_1 = new JLabel("         ");
		panel.add(lblNewLabel_1);
		
		
		
		panel.add(okButton);
		showmenuTable();
		
	}
}