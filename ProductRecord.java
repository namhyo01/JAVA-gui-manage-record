package gui;

public class ProductRecord{
	public String Jepom;
	public String ID;
	public String category;
	public int price;
	public int max;
	public int many;
	public String memo;
	ProductRecord(){
		Jepom = null;
		ID = null;
		category = null;
		price =0;
		max =0;
		many =0;
		memo = null;};
	public void print()
	{
		if(memo != null)
			System.out.printf("%-20s%-15s%-15s%d\t%d\t%-15d%s\n",this.Jepom, this.ID, this.category, this.price, this.many, this.max,this.memo);
		else
			System.out.printf("%-20s%-15s%-15s%d\t%d\t%d\n",this.Jepom, this.ID, this.category, this.price, this.many, this.max);
	}
	public void Category()
	{
		if(this.ID == null)
		{
			System.out.println("error");
			this.category = null;
		}
		else
		{
			if(this.ID.indexOf('1') == 0)
			{
				this.category = "Food";
			}
			else if(this.ID.indexOf('2')==0)
			{
				this.category = "Office";
			}
			else if(this.ID.indexOf('3')==0)
			{
				this.category = "Misc.";
			}
			else if(this.ID.indexOf('4')==0)
			{
				this.category = "Health";
			}
			else if(this.ID.indexOf('5')==0)
			{
				this.category = "Clothing";
			}
			else
			{
				this.category = null;
			}
		}
	}
}
