package medicine.gui;

import java.util.EventListener;

import medicine.Medicament;

public interface DataTransferListener extends EventListener
{
	public void medicamentAdded(final Medicament medicament);
}
