package medicine;

import java.io.Serializable;

import medicine.Constants.MEDICAMENT_TYPE;

public class Medicament implements Comparable<Medicament>, Serializable
{
	// serialization needs:
	private static final long serialVersionUID = -1331252343515099134L;
	
	private String name; // name of Medicament
	private MEDICAMENT_TYPE type; // type of Medicament
	
	public Medicament(final String name, final MEDICAMENT_TYPE type)
	{
		this.name = name;
		this.type = type;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(final String name)
	{
		this.name = name;
	}
	
	public MEDICAMENT_TYPE getType()
	{
		return type;
	}
	
	public void setFree(final MEDICAMENT_TYPE type)
	{
		this.type = type;
	}
	
	@Override
	public String toString()
	{
		return name;
	}
	
	@Override
	public int hashCode()
	{
		final int hashCode = name.hashCode();
		// switch 'sign' based on type of Medicament
		if (type.equals(MEDICAMENT_TYPE.TYPE_V))
			return hashCode;
		else
			return -hashCode;
	}
	
	@Override
	public boolean equals(final Object object)
	{
		// check for equality:
		if (object instanceof Medicament)
		{
			final Medicament medicament = (Medicament) object;
			return name.equals(medicament.name) && type.equals(medicament.type);
		}
		return false;
	}
	
	@Override
	public int compareTo(final Medicament medicament)
	{
		int result = name.compareTo(medicament.name);
		// case of equals 'name'
		if (result == 0)
		{
			if (!type.equals(medicament.type))
				result = type.equals(MEDICAMENT_TYPE.TYPE_V) ? -1 : 1; // prefer TYPE_V
		}
		return result;
	}
}