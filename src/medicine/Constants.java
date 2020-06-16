package medicine;

public class Constants
{
	// keeping all possible VOLUME(s) for desired Medicament
	public enum VOLUME
	{
		VOLUME_100MG(100, 15), VOLUME_250MG(250, 25), VOLUME_500MG(500, 32), VOLUME_600MG(600, 35);
		
		private int volume; // volume value
		private double price; // price value
		private String display; // GUI representation
		
		VOLUME(final int volume, final double price)
		{
			this.volume = volume;
			this.price = price;
			this.display = volume + "mg";
		}
		
		public int getVolume()
		{
			return volume;
		}
		
		public double getPrice()
		{
			return price;
		}
		
		@Override
		public String toString()
		{
			return display;
		}
	}
	
	// allowed TYPE(s) for desired Medicament
	public enum MEDICAMENT_TYPE
	{
		TYPE_V("V", true), TYPE_P("P", false);
		
		private String mark; // GUI representation
		private boolean isFree; // flag
		
		MEDICAMENT_TYPE(final String mark, final boolean isFree)
		{
			this.isFree = isFree;
			this.mark = mark;
		}
		
		public boolean isFree()
		{
			return isFree;
		}
		
		public String getMark()
		{
			return mark;
		}
		
		@Override
		public String toString()
		{
			return getMark();
		}
	}
	
	// constant strings (support Action handler):
	public static final String REGISTRATION_ACTION_ADD = "med.add";
	public static final String REGISTRATION_ACTION_REGISTER = "med.register";
	public static final String REGISTRATION_ACTION_MEDICAMENT = "med.medicament";
	public static final String REGISTRATION_ACTION_VOLUME = "med.volume";
	
	public static final String REGULATORY_ACTION_REGISTER = "regulatory.register";
	
	public static final String PRODUCT_ACTION_ACCEPT = "product.accept";
	
	public static final String REPRESENTATIVE_ACTION_1M = "representative.1month";
	public static final String REPRESENTATIVE_ACTION_3M = "representative.3months";;
	public static final String REPRESENTATIVE_ACTION_6M = "representative.6months";;
	public static final String REPRESENTATIVE_ACTION_OK = "representative.ok";;
	
	// common actions:
	public static final String ACTION_OK = "OK";
	public static final String ACTION_CANCEL = "CANCEL";
	
	// DESIGN constants:
	public static final int DEFAULT_WIDTH = 400;
	public static final int DEFAULT_HEIGHT = 300;
}
