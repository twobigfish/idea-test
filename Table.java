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

public class Table extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;// ������ģ�Ͷ���
	private JTable table;// ���������
	private JTextField tableText;
	private JTextField statusText;
	
private DatabaseConnection dbc=null;
	
	Connection conn=null;
	ResultSet rs=null;   //�����
	Statement stat=null;
	
	
	
	boolean selectdiningTable() //��ѯ���ױ����Ƿ��д��û�
	{
		try
		{
			stat=conn.createStatement(); //�����������
			String sql="SELECT status FROM dining_table where id=" + Integer.parseInt(tableText.getText().toString());
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
	
	void showdiningTable() //��ʾ���е��û�
	{
		try
		{
			stat=conn.createStatement(); //�����������
			String sql="SELECT * FROM dining_table";
			ResultSet rs=stat.executeQuery(sql);
			
			while(rs.next())
			{
				String[] rowValues = { rs.getString("id"),
						(rs.getString("status").equals("1")?"ռ��":"����") };// �������������
				tableModel.addRow(rowValues);// ����ģ�������һ��
				int rowCount = table.getRowCount() + 1;
			}
			
		}catch(Exception ex)
		{
			
		}
	}
	
	
	void adddiningTable(ActionEvent e)   //��˵��������ӵ�ĳһ����
	{
		try
		{
			stat=conn.createStatement(); //�����������
			String sql="insert into dining_table values(";
		    sql=sql + Integer.parseInt(tableText.getText()) + "," + Integer.parseInt(statusText.getText()) + ")";
		    
		    stat.executeUpdate(sql);
			
			
			
		}catch(Exception ex)
		{
			//ex.printStackTrace();
		}
		
	}
	
	
	void deletediningTable() //ɾ������Ա���е�ĳһ����
	{
		try
		{
			stat=conn.createStatement(); //�����������
			String sql="DELETE FROM dining_table WHERE id=";
		    sql=sql + Integer.parseInt(tableText.getText());
		    stat.executeUpdate(sql);
			
			
		}catch(Exception ex)
		{
			
		}
	}
	
	
	
	public Table() {
		super();
		
		this.dbc=new DatabaseConnection();
		this.conn=dbc.getConnection();
		
		setTitle("ά�����ģ��");
		
		
		//setBounds(100, 100, 510, 375);
		setBounds(100, 100, 450, 300);
		
		final JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		String[] columnNames = { "�������", "״̬" };// ��������������
		String[][] tableValues = {};// ��������������
		// ����ָ����������ͱ�����ݵı��ģ��
		tableModel = new DefaultTableModel(tableValues, columnNames);
		
		table = new JTable(tableModel);// ����ָ�����ģ�͵ı��
		table.setRowSorter(new TableRowSorter<>(tableModel));// ���ñ���������
		// ���ñ���ѡ��ģʽΪ��ѡ
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// Ϊ����������¼�������
		table.addMouseListener(new MouseAdapter() {
			// �����˵���¼�
			public void mouseClicked(MouseEvent e) {
				// ��ñ�ѡ���е�����
				int selectedRow = table.getSelectedRow();
				// �ӱ��ģ���л��ָ����Ԫ���ֵ
				Object oa = tableModel.getValueAt(selectedRow, 0);
				// �ӱ��ģ���л��ָ����Ԫ���ֵ
				Object ob = tableModel.getValueAt(selectedRow, 1);
				tableText.setText(oa.toString());// ��ֵ��ֵ���ı���
				statusText.setText(ob.toString());// ��ֵ��ֵ���ı���
			}
		});
		scrollPane.setViewportView(table);
		final JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.add(new JLabel("�������"));
		tableText = new JTextField("", 6);
		panel.add(tableText);
		panel.add(new JLabel("״̬"));
		statusText = new JTextField("", 2);
		panel.add(statusText);
		final JButton addButton = new JButton("���");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] rowValues = { tableText.getText(),
						(statusText.getText().equals("1")?"ռ��":"����") };// �������������
				tableModel.addRow(rowValues);// ����ģ�������һ��
				int rowCount = table.getRowCount() + 1;
				adddiningTable(e);
				tableText.setText("");
				statusText.setText("");
			}
		});
		
		JLabel lblNewLabel = new JLabel("                 ");
		panel.add(lblNewLabel);
		panel.add(addButton);
		final JButton delButton = new JButton("ɾ��");
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();// ��ñ�ѡ���е�����
				if (selectedRow != -1)// �ж��Ƿ���ڱ�ѡ����
				{
					// �ӱ��ģ�͵���ɾ��ָ����
					tableModel.removeRow(selectedRow);
					deletediningTable();
				}
			}
		});
		panel.add(delButton);
		
		showdiningTable();
	}
}