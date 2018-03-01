package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class list {
		static JButton add= new JButton("Add");
		static JButton delete = new JButton("Delete");
		static JButton rewrite = new JButton("Modify");
		static JButton save = new JButton("Save File");
		JFrame frame = new JFrame("ProductList");
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel(); 
		JTable table;
		ProductList lists = new ProductList();
		public DefaultTableModel model;
	list(){
		}
	public void prepareframe() throws FileNotFoundException{ 
		frame.setSize(600,500);
		String header[] = {"제품명","제품ID","카테고리","가격","재고수","최소재고량","기타 메모"};
		String contents[][] = {
			};
		model = new DefaultTableModel(contents,header);
		Init(FIle.Filename);
		jtable();
		addtext();
		button();
		frame.add(p1,BorderLayout.EAST);
		frame.add(p2,BorderLayout.CENTER);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	public void Init(String productsListFileName) throws FileNotFoundException
	{
		lists.displayProductList(productsListFileName);
	}
	public void addtext()
	{
		int count = lists.count;
		//System.out.println(count);
		int row = 0;
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		for(row = 0;row<count;row++){
			if(lists.fi[row].memo == null)
				model.addRow(new Object[]{lists.fi[row].Jepom,lists.fi[row].ID,lists.fi[row].category,Integer.toString(lists.fi[row].price),Integer.toString(lists.fi[row].many),Integer.toString(lists.fi[row].max)});
			else
				model.addRow(new Object[]{lists.fi[row].Jepom,lists.fi[row].ID,lists.fi[row].category,Integer.toString(lists.fi[row].price),Integer.toString(lists.fi[row].many),Integer.toString(lists.fi[row].max),lists.fi[row].memo});
			}
	}
	public void jtable(){
		table = new JTable(model);
		JScrollPane scrollpane = new JScrollPane(table);
		p2.add(scrollpane);
	}
	public void button(){
		p1.setLayout(new GridLayout(4,1));
		p1.add(add);
		p1.add(delete);
		p1.add(rewrite);
		p1.add(save);
		add.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				record record = new record();
				record.frame();
			}
		});

		delete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				remove removerow = new remove();
				removerow.removerow();
			}
		});
		rewrite.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				modify change = new modify();
				change.frame();
			}
		});
		save.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				filesave fisave = new filesave();
				try {
					fisave.save();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	
	class record{
		JButton done = new JButton("Done");
		 Label a1 = new Label("제품명");
		 Label a2 = new Label("제품ID");
		 Label a3 = new Label("카테고리");	
		 Label a4 = new Label("가격");
		 Label a5 = new Label("재고수");
		 Label a6 = new Label("최소재고량");
		 Label a7 = new Label("기타메모");
		 JPanel e = new JPanel();
		 JPanel c = new JPanel();
		 TextField b1 = new TextField(8); 
		 TextField b2 = new TextField(8); 
		 TextField b3 = new TextField(8); 
		 TextField b4 = new TextField(8); 
		 TextField b5 = new TextField(8); 
		 TextField b6 = new TextField(8); 
		 TextField b7 = new TextField(8);
		record(){
		}
		void frame()
		{
			JFrame name = new JFrame("Add A New Product Line");
			name.setSize(700,100);
			e.setLayout(new GridLayout(1,1));
			e.add(done);
			text();
			button();
			name.add(e,BorderLayout.EAST);
			name.add(c,BorderLayout.CENTER);
			name.setLocationRelativeTo(null);
			name.setVisible(true);
			name.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
		void button()
		{
			done.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){	
					int count = lists.count;
					String name = b1.getText();
					String id = b2.getText();
					String price = b4.getText();
					String max = b5.getText();
					String min = b6.getText();
					String memo = b7.getText();
					DefaultTableModel model = (DefaultTableModel)table.getModel();
					try{
						lists.fi[count] = new ProductRecord();
						lists.fi[count].Jepom = name;
						lists.fi[count].ID = id;
						lists.fi[count].Category();
						lists.fi[count].price = Integer.parseInt(price);
						lists.fi[count].many = Integer.parseInt(max);
						lists.fi[count].max = Integer.parseInt(min);
						lists.fi[count].memo = memo;
					}
					catch(Exception e1)
					{
						System.out.println("wrong number");
						lists.fi[count] = new ProductRecord();
						return;
					}
					if(lists.fi[count].category == null)
					{
						System.out.println("Wrong ID");
						lists.fi[count] = new ProductRecord();
						return;
					}
					if(lists.fi[count].price<0)
					{
						lists.fi[count] = new ProductRecord();
						return;
					}
					if(lists.fi[count].max<0)
					{
						lists.fi[count] = new ProductRecord();
						return;
					}
					if(lists.fi[count].many<0){
						lists.fi[count] = new ProductRecord();
						return;
					}
					model.addRow(new Object[]{name,id,lists.fi[count].category,price,max,min,memo});
					lists.count = count+1;
				}	
			});
		}
		void text()
		{	
			c.setLayout(new GridLayout(2,7));
			c.add(a1);
			c.add(a2);
			c.add(a3);
			c.add(a4);
			c.add(a5);
			c.add(a6);
			c.add(a7);
			c.add(b1);
			c.add(b2);
			c.add(b3);
			c.add(b4);
			c.add(b5);
			c.add(b6);
			c.add(b7);
		}
	}
	
	
	class remove{
		void removerow(){
			int row = table.getSelectedRow();
			int i;
			if(row==-1)
				return;
//			DefaultTableModel removemodel = (DefaultTableModel)table.getModel();
			model.removeRow(row);
			for(i=row;i<lists.count-1;i++)
			{
				lists.fi[i] = lists.fi[i+1];
			}
			lists.fi[lists.count-1] = new ProductRecord();
			lists.count = lists.count-1;
			System.out.println(lists.count);
		}
	}
	
	class modify extends record{
		void button()
		{
			ProductRecord backup = new ProductRecord();
			
			int row = table.getSelectedRow();
			if(row == -1)
				return;
			b1.setText((String) table.getValueAt(row, 0));
			b2.setText((String) table.getValueAt(row, 1));
			b3.setText(lists.fi[row].category);
			b4.setText((String) table.getValueAt(row, 3));
			b5.setText((String) table.getValueAt(row, 4));
			b6.setText((String) table.getValueAt(row, 5));
			b7.setText((String) table.getValueAt(row, 6));
			done.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					try{
						backup.Jepom = b1.getText();
						backup.ID = b2.getText();
						backup.Category();
						backup.price = Integer.parseInt(b4.getText());
						backup.many = Integer.parseInt(b5.getText());
						backup.max = Integer.parseInt(b6.getText());
						backup.memo = b7.getText();
					}
					catch(Exception e1)
					{
						System.out.println("error");
						return;
					}
					if(backup.category == null)
					{
						System.out.println("wrong Id");
						return;
					}
					if(backup.price<0)
						return;
					if(backup.many<0)
						return;
					if(backup.max<0)
						return;
					lists.fi[row].Jepom = b1.getText();
					lists.fi[row].ID = b2.getText();
					lists.fi[row].Category();
					lists.fi[row].price = Integer.parseInt(b4.getText());
					lists.fi[row].many = Integer.parseInt(b5.getText());
					lists.fi[row].max = Integer.parseInt(b6.getText());
					lists.fi[row].memo = b7.getText();
					DefaultTableModel modifymodel = (DefaultTableModel)table.getModel();
					modifymodel.setValueAt(b1.getText(), row, 0);
					modifymodel.setValueAt(b2.getText(), row, 1);
					modifymodel.setValueAt(lists.fi[row].category, row, 2);
					modifymodel.setValueAt(b4.getText(), row, 3);
					modifymodel.setValueAt(b5.getText(), row, 4);
					modifymodel.setValueAt(b6.getText(), row, 5);
					modifymodel.setValueAt(b7.getText(), row, 6);
				}
			});

		}
	}
	class filesave{
		void save() throws IOException{
			FileWriter fw = new FileWriter(FIle.Filename);
			for(int i=0;i<lists.count;i++)
			{
				fw.write(lists.fi[i].Jepom);
				fw.write(":");
				fw.write(lists.fi[i].ID);
				fw.write(":");
				//fw.write(lists.fi[i].category);
				//fw.write(":");
				fw.write(Integer.toString(lists.fi[i].price));
				fw.write(":");
				fw.write(Integer.toString(lists.fi[i].many));
				fw.write(":");
				fw.write(Integer.toString(lists.fi[i].max));
				if(lists.fi[i].memo != null || lists.fi[i].memo == " "){
					fw.write(":");
					fw.write(lists.fi[i].memo);
				}
				fw.write("\r\n");
			}
			fw.flush();
			fw.close();
		}
	}
}
