package medicine.gui;

import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import medicine.Constants;
import medicine.DataManager;

public abstract class CommonDialog extends JDialog
{
	// serialization needs:
	private static final long serialVersionUID = 2799956588540155342L;
	
	// data holder:
	protected DataManager dataManager;
	
	public CommonDialog(final JFrame parent, final DataManager data)
	{
		super(parent, true);
		dataManager = data;
		add(createDesign());
		setSize(new Dimension(Constants.DEFAULT_WIDTH, Constants.DEFAULT_HEIGHT));
		setLocationRelativeTo(parent);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	// each of child class will implement it by OWN needs
	protected abstract JPanel createDesign();
}
