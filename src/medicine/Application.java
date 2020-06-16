package medicine;

import java.util.ArrayList;
import java.util.List;

import medicine.Constants.MEDICAMENT_TYPE;
import medicine.gui.RegistrationManager;

public class Application
{
	public static void main(final String[] args)
	{
		final List<Medicament> medicaments = new ArrayList<Medicament>();
		// provide hard-coded list of data:
		medicaments.add(new Medicament("Strepsils", MEDICAMENT_TYPE.TYPE_V));
		medicaments.add(new Medicament("Nurofen", MEDICAMENT_TYPE.TYPE_V));
		medicaments.add(new Medicament("Nasivin", MEDICAMENT_TYPE.TYPE_V));
		medicaments.add(new Medicament("Paralen", MEDICAMENT_TYPE.TYPE_V));
		medicaments.add(new Medicament("Ascorutin", MEDICAMENT_TYPE.TYPE_V));
		medicaments.add(new Medicament("Voltaren", MEDICAMENT_TYPE.TYPE_V));
		medicaments.add(new Medicament("Ibalgin", MEDICAMENT_TYPE.TYPE_V));
		medicaments.add(new Medicament("Clacid", MEDICAMENT_TYPE.TYPE_P));
		medicaments.add(new Medicament("Bioparox", MEDICAMENT_TYPE.TYPE_P));
		medicaments.add(new Medicament("Sumammed", MEDICAMENT_TYPE.TYPE_P));
		medicaments.add(new Medicament("Roxithromycin", MEDICAMENT_TYPE.TYPE_P));
		new RegistrationManager(medicaments).setVisible(true);
	}
}
