package medicine.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.EventListenerList;

import medicine.Constants;
import medicine.DataManager;
import medicine.Medicament;

public class RegulatoryManager extends CommonDialog implements ActionListener
{
	// serialization needs:
	private static final long serialVersionUID = -5576373104217438233L;
	
	// allows handle Events:
	private EventListenerList listenerList = new EventListenerList();
	
	public RegulatoryManager(final JFrame parent, final DataManager data)
	{
		super(parent, data);
		setTitle("Regulatory manager");
	}
	
	@Override
	protected JPanel createDesign()
	{
		final JPanel panel = new JPanel();
		final GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
		// Register button:
		final JButton btnRegister = new JButton("Register request");
		btnRegister.addActionListener(this);
		btnRegister.setActionCommand(Constants.REGULATORY_ACTION_REGISTER);
		// get selected medicament:
		final Medicament medicament = dataManager.getSelectedMedicament();
		// labels:
		final JLabel lblNameTitle = new JLabel("Medicament name:");
		final JLabel lblName = new JLabel(medicament.getName());
		final JLabel lblFreeTitle = new JLabel("Medicament availability:");
		final JLabel lblFree = new JLabel(medicament.getType().getMark());
		// horizontal layout:
		layout.setHorizontalGroup(layout.createParallelGroup().addGroup(layout.createSequentialGroup().addComponent(lblNameTitle).addGap(10).addComponent(lblName)).addGroup(layout.createSequentialGroup().addComponent(lblFreeTitle).addGap(10).addComponent(lblFree)).addComponent(btnRegister));
		// vertical layout:
		layout.setVerticalGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup().addComponent(lblNameTitle).addGap(10).addComponent(lblName)).addGap(10).addGroup(layout.createParallelGroup().addComponent(lblFreeTitle).addGap(10).addComponent(lblFree)).addGap(20).addComponent(btnRegister));
		return panel;
	}
	
	private void handleMedicament()
	{
		final List<Medicament> medicaments = dataManager.getMedicaments();
		// get selected medicament:
		final Medicament medicament = dataManager.getSelectedMedicament();
		// unique check:
		if (medicaments.contains(medicament))
			JOptionPane.showMessageDialog(this, "Medicament '" + medicament.getName() + ", " + medicament.getType() + "' already exists!", "Error", JOptionPane.ERROR_MESSAGE);
		else
		{
			medicaments.add(medicament);
			fireDataTransferred();
			JOptionPane.showMessageDialog(this, "Medicament '" + medicament.getName() + ", " + medicament.getType() + "' successfully registered.", "Success", JOptionPane.INFORMATION_MESSAGE);
			new ProductManager((JFrame) getParent(), dataManager).setVisible(true);
		}
	}
	
	public void addDataTransferListener(final DataTransferListener listener)
	{
		listenerList.add(DataTransferListener.class, listener);
	}
	
	public void removeDataTransferListener(final DataTransferListener listener)
	{
		listenerList.remove(DataTransferListener.class, listener);
	}
	
	private final void fireDataTransferred()
	{
		final Object[] listeners = listenerList.getListenerList();
		if (listeners != null)
		{
			for (int i = listeners.length - 2; i >= 0; i -= 2)
			{
				if (listeners[i] == DataTransferListener.class)
				{
					DataTransferListener listener = (DataTransferListener) listeners[i + 1];
					listener.medicamentAdded(dataManager.getSelectedMedicament());
				}
			}
		}
	}
	
	@Override
	public void actionPerformed(final ActionEvent event)
	{
		final String command = event.getActionCommand();
		if (command.equals(Constants.REGULATORY_ACTION_REGISTER))
		{
			handleMedicament();
			dispose();
		}
		else
			System.err.println("Unhandled action command: " + command);
	}
}
