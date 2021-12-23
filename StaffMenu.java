package RestaurantManagement;



import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.*;

public class StaffMenu extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;// ������ģ�Ͷ���
	private JTable table;// ���������
	
	private String strValue="";
	
    private DatabaseConnection dbc=null;
	
	Connection conn=null;
	ResultSet rs=null;   //�����
	Statement stat=null;
	
	
	
	void showmenuTable() //��ʾ���ױ��е��û�
	{
		try
		{
			stat=conn.createStatement(); //�����������
			String sql="SELECT * FROM menu";
			ResultSet rs=stat.executeQuery(sql);
			
			while(rs.next())
			{
				String[] rowValues = {rs.getString("name"),rs.getString("num"),rs.getString("sell") };// �������������
				tableModel.addRow(rowValues);// ����ģ�������һ��
				int rowCount = table.getRowCount() + 1;
			}
			
		}catch(Exception ex)
		{
			
		}
	}
	
	
	
	
	public StaffMenu() {
		super();
		
		this.dbc=new DatabaseConnection();
		this.conn=dbc.getConnection();
		
		setTitle("���׹���");
		//setBounds(100, 100, 472, 375);
		setBounds(100, 100, 450, 300);
		final JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		String[] columnNames = { "����","���","�ۼ�"};// ��������������
		String[][] tableValues = {};// ��������������
		// ����ָ����������ͱ�����ݵı��ģ��
		tableModel = new DefaultTableModel(tableValues, columnNames);
		
		table = new JTable(tableModel);// ����ָ�����ģ�͵ı��
		table.setRowSorter(new TableRowSorter<>(tableModel));// ���ñ���������
		// ���ñ���ѡ��ģʽΪ��ѡ
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		scrollPane.setViewportView(table);
		final JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		final JButton reservationButton = new JButton("\u70B9\u83DC");  //���
		reservationButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//��ת����˽���
				ReservationTable reservationFrame=new ReservationTable();
				reservationFrame.setVisible(true);
				setVisible(false);
			}
		});
		
		
		panel.add(reservationButton);
		
		JLabel lblNewLabel = new JLabel("           ");
		panel.add(lblNewLabel);
		final JButton tableButton = new JButton("\u9910\u684C");  //�鿴����
		tableButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//��ת����������
				StaffTable TableFrame=new StaffTable();
				TableFrame.setVisible(true);
				setVisible(false);
			}
		});
		
		
		panel.add(tableButton);
		
		showmenuTable();
		
	}
}