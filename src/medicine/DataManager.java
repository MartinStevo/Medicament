package medicine;

import java.util.ArrayList;
import java.util.List;

import medicine.Constants.VOLUME;

public class DataManager
{
	// related data to be accessed/modified:
	private List<Medicament> medicaments = new ArrayList<Medicament>();
	private double finalPrice;
	private int pieces;
	private Medicament selectedMedicament;
	private VOLUME volume;
	
	public DataManager()
	{
	}
	
	public List<Medicament> getMedicaments()
	{
		return medicaments;
	}
	
	public void setMedicaments(List<Medicament> medicaments)
	{
		this.medicaments = medicaments;
	}
	
	public double getFinalPrice()
	{
		return finalPrice;
	}
	
	public void setFinalPrice(final double finalPrice)
	{
		this.finalPrice = finalPrice;
	}
	
	public int getPieces()
	{
		return pieces;
	}
	
	public void setPieces(final int pieces)
	{
		this.pieces = pieces;
	}
	
	public VOLUME getVolume()
	{
		return volume;
	}
	
	public void setVolume(final VOLUME volume)
	{
		this.volume = volume;
	}
	
	public Medicament getSelectedMedicament()
	{
		return selectedMedicament;
	}
	
	public void setSelectedMedicament(final Medicament selectedMedicament)
	{
		this.selectedMedicament = selectedMedicament;
	}
}
