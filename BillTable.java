package RestaurantManagement;



import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.*;

public class BillTable extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;// ������ģ�Ͷ���
	private JTable table;// ���������
	
	private String strValue="";
	private UserName userName;
    private DatabaseConnection dbc=null;
	
	Connection conn=null;
	ResultSet rs=null;   //�����
	Statement stat=null;
	private JLabel lblNewLabel;
	
	
	
	void showdiningTable() //��ʾ���е��û�
	{
		try
		{
			stat=conn.createStatement(); //�����������
			String sql="SELECT * FROM bill";
			ResultSet rs=stat.executeQuery(sql);
			
			while(rs.next())
			{
				if(rs.getString("status").equals("0"))
				{
				String[] rowValues = { rs.getString("id"),"δ����" };// �������������
				tableModel.addRow(rowValues);// ����ģ�������һ��
				int rowCount = table.getRowCount() + 1;
				}
			}
			
		}catch(Exception ex)
		{
			
		}
	}
	
	
	
	public BillTable() {
		super();
		
		userName=new UserName();
		this.dbc=new DatabaseConnection();
		this.conn=dbc.getConnection();
		
		setTitle("���׹���");
		//setBounds(100, 100, 432, 335);
		setBounds(100, 100, 450, 300);
		final JPanel pane2 = new JPanel();
		getContentPane().add(pane2, BorderLayout.NORTH);
		
		lblNewLabel = new JLabel("\u8BF7\u9009\u62E9\u9910\u684C");
		pane2.add(lblNewLabel);
		
		final JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		String[] columnNames = { "�������", "״̬"};// ��������������
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
				//Object oa = tableModel.getValueAt(selectedRow, 0);
				// �ӱ��ģ���л��ָ����Ԫ���ֵ
				//Object ob = tableModel.getValueAt(selectedRow, 1);
				//idText.setText(oa.toString());// ��ֵ��ֵ���ı���
				//nameText.setText(ob.toString());// ��ֵ��ֵ���ı���
			}
		});
		scrollPane.setViewportView(table);
		final JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		final JButton okButton = new JButton("\u786E\u5B9A");  //ȷ��
		okButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();// ��ñ�ѡ���е�����
				userName.settableNum(table.getValueAt(selectedRow, 0).toString());
				
				//System.out.println(userName.gettableNum());
				//��ת�����˽���
				Bill billFrame=new Bill();
				billFrame.setVisible(true);
				setVisible(false);
			}
		});
		
		
		JButton cancelButton = new JButton("\u53D6\u6D88");  //ȡ��
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setVisible(false);
			}
		});
		panel.add(cancelButton);
		
		JLabel lblNewLabel_1 = new JLabel("     ");
		panel.add(lblNewLabel_1);
		panel.add(okButton);
		
		showdiningTable();
		
	}
}