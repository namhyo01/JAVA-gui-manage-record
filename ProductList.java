package gui;

import java.io.*;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class ProductList {
		public ProductRecord[] fi; 
		private String name;
		private String id;
		private String Category;
		private String Price;
		private String Current;
		private String stock;
		private String Memo;
		public int count = 0;
		private int j;
		ProductList(){
			fi = new ProductRecord[100];
			name = "Product Name";
			id = "Product ID";
			Category = "Category";
			Price = "Price";
			Current = "Current";
			stock = "StockSafe";
			Memo = "Level";
		}
		
 	public void displayProductList(String productsListFileName) throws FileNotFoundException
	{
 		Scanner file;
 		File fil = new File(productsListFileName);
 		try{
 			file = new Scanner(fil);
 		}
 		catch(Exception e)
 		{
 			System.out.println("Unknown File");
 			return;
 		}
 		while(file.hasNext())
 		{
 			String line = file.nextLine();
 			if(line.contains("//") || line.contains("  .*"))
 			{
 				continue;
 			}
 			else
 			{
 				fi[count] = new ProductRecord();
 			    String arr[] = line.split(":");
 			   	if(arr.length < 5 || arr.length > 6)
 				{
 					System.out.println("Irregular product line    "+"Skip the input line : "+line);
 					continue;
 				}
 			   	else{
				int i=0;
 				while(i<arr.length)
 				{
 						try{
 							if(i==0)
 							{
 								fi[count].Jepom = arr[i].trim();
 							}
							else if(i==1)
 							{
 								fi[count].ID = arr[i].trim();
 							}
 							else if(i==2)
 							{
 								fi[count].Category();
 								try{
 									fi[count].price = Integer.parseInt(arr[i].trim());
 								}
 								catch(NumberFormatException e)
 								{
 									System.out.println("Illegal value --"+arr[i]+"Skip the input line : "+ line);
 									continue;
 								}
 							}
 							else if(i==3)
 							{
 								try{
 									fi[count].many = Integer.parseInt(arr[i].trim());		
 								}
 								catch(NumberFormatException e)
 								{
 									System.out.println("Illegal current --"+arr[i]+"Skip the input line : "+ line);
 									continue;
 								}
 							}
 							else if(i==4)
 							{
 								try{
 									fi[count].max = Integer.parseInt(arr[i].trim());
 								}
 								catch(NumberFormatException e)
 								{
 									System.out.println("Illegal stocksafe --"+arr[i]+"Skip the input line : "+ line);
 									continue;
 								}
 							}
 							else{
 								fi[count].memo = arr[i].trim();
 							}

 						}
 						catch(Exception e){
 							break;
 						}
 						i++;
 				}		
					if (fi[count].price<0)
					{
						System.out.println("Illegal price -- 0    "+"Skip the input line : "+line);
						continue;
					}
					if(fi[count].many<0)
					{
						System.out.println("Illegal current stock -- 0    "+"Skip the input line : "+line);
						continue;
					}
					if(fi[count].max<0)
					{
						System.out.println("Illegal StockSafe -- 0    "+"Skip the input line : "+line);
						continue;
					}
 				}
 			}
 			count++;
 		}	
 		file.close();
 		for(int i=0;i<count-1;i++)
 		{
 			for(int k=i+1;k<count;k++)
 			{
 				if(fi[i].ID.equals(fi[k].ID))
 				{
 					System.out.println("ID Conflict");
 					conflict(k);
 					return;
 				}
 			}
 		}
	}
 	void conflict(int i)
 	{
 		for(int d = i;d<count-1;d++)
 			fi[d] = fi[d+1];
 		fi[count-1] = new ProductRecord();
 		count = count-1;
 	}
 	void display()
 	{
		System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\n",this.name, this.id, this.Category, this.Price, this.Current, this.stock, this.Memo);
		System.out.println("----------------------------------------------------------------------------------------------");
		while(j<count)
		{
				fi[j].print();
				j++;
		}
		System.out.println("20163158 kimnamhyo made");
 	}
}