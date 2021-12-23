package RestaurantManagement;



import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.*;

public class Menu extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;// ������ģ�Ͷ���
	private JTable table;// ���������
	private JTextField idText;
	private JTextField nameText;
	private JTextField numText;
	private JTextField costText;
	private JTextField sellText;
	
	private String strValue="";
	
    private DatabaseConnection dbc=null;
	
	Connection conn=null;
	ResultSet rs=null;   //�����
	Statement stat=null;
	
	
	boolean selectmenuTable() //��ѯ���ױ����Ƿ��д��û�
	{
		try
		{
			stat=conn.createStatement(); //�����������
			String sql="SELECT name FROM menu where id="+Integer.parseInt(idText.toString());
			ResultSet rs=stat.executeQuery(sql);
			System.out.println(sql);
			
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
	
	void showmenuTable() //��ʾ���ױ��е��û�
	{
		try
		{
			stat=conn.createStatement(); //�����������
			String sql="SELECT * FROM menu";
			ResultSet rs=stat.executeQuery(sql);
			
			while(rs.next())
			{
				String[] rowValues = { rs.getString("id"),
						rs.getString("name"),rs.getString("num"), rs.getString("cost"), rs.getString("sell") };// �������������
				tableModel.addRow(rowValues);// ����ģ�������һ��
				int rowCount = table.getRowCount() + 1;
			}
			
		}catch(Exception ex)
		{
			
		}
	}
	
	
	void updatemenuTable(ActionEvent e) //�޸Ĳ˵�
	{
		try
		{
			stat=conn.createStatement(); //�����������
			String sql="UPDATE menu SET id=";
		    sql=sql + Integer.parseInt(idText.getText()) + ",name='";
		    sql=sql + nameText.getText() + "',num=";
		    sql=sql + Integer.parseInt(numText.getText()) + ",cost=";
		    sql=sql + Integer.parseInt(costText.getText()) + ",sell=";
		    sql=sql + Integer.parseInt(sellText.getText()) + " WHERE id=";
		    sql=sql + Integer.parseInt(strValue);
		    stat.executeUpdate(sql);
			
			
			
		}catch(Exception ex)
		{
			
		}
	}
	
	
	void addmenuTable(ActionEvent e)   //��˵��������ӵ�ĳһ����
	{
		try
		{
			stat=conn.createStatement(); //�����������
			String sql="insert into menu values(";
		    sql=sql + Integer.parseInt(idText.getText()) + ",'" + nameText.getText() + "',";
		    sql=sql + Integer.parseInt(numText.getText()) + "," + Integer.parseInt(costText.getText()) + ",";
		    sql=sql + Integer.parseInt(sellText.getText()) + ")";
		    
		    stat.executeUpdate(sql);
			
			
			
		}catch(Exception ex)
		{
			//ex.printStackTrace();
		}
		
	}
	
	
	void deletemenuTable() //ɾ������Ա���е�ĳһ����
	{
		try
		{
			stat=conn.createStatement(); //�����������
			String sql="DELETE FROM menu WHERE id=";
		    sql=sql + Integer.parseInt(idText.getText());
		    stat.executeUpdate(sql);
			
			
		}catch(Exception ex)
		{
			
		}
	}
	
	
	
	
	public Menu() {
		super();
		
		this.dbc=new DatabaseConnection();
		this.conn=dbc.getConnection();
		
		setTitle("���׹���");
		setBounds(100, 100, 838, 376);
		final JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		String[] columnNames = { "���", "����","���", "�ɱ�","�ۼ�"};// ��������������
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
				Object oc = tableModel.getValueAt(selectedRow, 2);
				Object od = tableModel.getValueAt(selectedRow, 3);
				Object oe = tableModel.getValueAt(selectedRow, 4);
				
				idText.setText(oa.toString());// ��ֵ��ֵ���ı���
				nameText.setText(ob.toString());// ��ֵ��ֵ���ı���
				numText.setText(oc.toString());
				costText.setText(od.toString());
				sellText.setText(oe.toString());
				
			}
		});
		scrollPane.setViewportView(table);
		final JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.add(new JLabel("���"));
		idText = new JTextField("", 4);
		panel.add(idText);
		panel.add(new JLabel("����"));
		nameText = new JTextField("", 15);
		panel.add(nameText);
		panel.add(new JLabel("���"));
		numText = new JTextField("", 4);
		panel.add(numText);
		panel.add(new JLabel("�ɱ�"));
		costText = new JTextField("", 4);
		panel.add(costText);
		panel.add(new JLabel("�ۼ�"));
		sellText = new JTextField("", 4);
		panel.add(sellText);
		final JButton addButton = new JButton("���");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectmenuTable()==false)
				{
				   String[] rowValues = { idText.getText(),
						   nameText.getText(),numText.getText(), costText.getText(), sellText.getText() };// �������������
				   tableModel.addRow(rowValues);// ����ģ�������һ��
				   int rowCount = table.getRowCount() + 1;
				   addmenuTable(e);
				   idText.setText("");
				   nameText.setText("");
				   numText.setText("");
				   costText.setText("");
				   sellText.setText("");
				}
				else
				{
					//JOptionPane.showmessageDialog(Menu.this,"","",1);
				}
				
				
			}
		});
		panel.add(addButton);
		final JButton updateButton = new JButton("�޸�");
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();// ��ñ�ѡ���е�����
				strValue=table.getValueAt(selectedRow, 0).toString();
				if (selectedRow != -1) {// �ж��Ƿ���ڱ�ѡ����
					tableModel.setValueAt(idText.getText(),
							selectedRow, 0);// �޸ı��ģ�͵��е�ָ��ֵ
					tableModel.setValueAt(nameText.getText(),
							selectedRow, 1);// �޸ı��ģ�͵��е�ָ��ֵ
					tableModel.setValueAt(numText.getText(),
							selectedRow, 2);
					tableModel.setValueAt(costText.getText(),
							selectedRow, 3);
					tableModel.setValueAt(sellText.getText(),
							selectedRow, 4);
					updatemenuTable(e);
				}
			}
		});
		panel.add(updateButton);
		final JButton deleteButton = new JButton("ɾ��");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();// ��ñ�ѡ���е�����
				strValue=table.getValueAt(selectedRow, 0).toString();
				if (selectedRow != -1)// �ж��Ƿ���ڱ�ѡ����
				{
					// �ӱ��ģ�͵���ɾ��ָ����
					tableModel.removeRow(selectedRow);
					deletemenuTable();
				}
			}
		});
		panel.add(deleteButton);
		
		showmenuTable();
		
	}
}