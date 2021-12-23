package RestaurantManagement;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.*;

public class AllMenu extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;// ������ģ�Ͷ���
	private JTable table;// ���������
	
private DatabaseConnection dbc=null;
	
	Connection conn=null;
	ResultSet rs=null;   //�����
	Statement stat=null;
	
	
	void showdiningTable() //��ʾ���е��û�
	{
		try
		{
			stat=conn.createStatement(); //�����������
			String sql="SELECT * FROM allmenu";
			ResultSet rs=stat.executeQuery(sql);
			
			while(rs.next())
			{
				String[] rowValues = { rs.getString("id"),
						rs.getString("name") };// �������������
				tableModel.addRow(rowValues);// ����ģ�������һ��
				int rowCount = table.getRowCount() + 1;
			}
			
		}catch(Exception ex)
		{
			
		}
	}
	
	
	
	
	
	public AllMenu() {
		super();
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		this.dbc=new DatabaseConnection();
		this.conn=dbc.getConnection();
		
		setTitle("\u5168\u90E8\u8BA2\u5355");
		//setBounds(100, 100, 387, 375);
		setBounds(100, 100, 450, 300);
		
		final JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		String[] columnNames = { "���", "����" };// ��������������
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
		final JButton returnButton = new JButton("\u8FD4\u56DE");  //����staff����
		returnButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//����staff����
				Administrator Frame=new Administrator();
				Frame.setVisible(true);
				setVisible(false);
				
			}
		});
		
		panel.add(returnButton);
		
		showdiningTable();
	}
}