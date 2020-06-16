package medicine.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;

import medicine.Constants;
import medicine.DataManager;
import medicine.Medicament;
import medicine.Constants.MEDICAMENT_TYPE;
import medicine.Constants.VOLUME;

public class RegistrationManager extends JFrame implements ActionListener, DataTransferListener
{
	// serialization needs:
	private static final long serialVersionUID = 8108275106343107271L;
	
	private DataManager dataTransferManager = new DataManager();
	private JComboBox cbMeds;
	private JComboBox cbVolumes;
	
	public RegistrationManager(final List<Medicament> medicaments)
	{
		dataTransferManager.setMedicaments(medicaments);
		// design window:
		add(createDesign());
		setTitle("Registration manager");
		setSize(new Dimension(Constants.DEFAULT_WIDTH, Constants.DEFAULT_HEIGHT));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// centering window:
		final Toolkit toolkit = Toolkit.getDefaultToolkit();
		final Dimension screenSize = toolkit.getScreenSize();
		final int x = (screenSize.width - getWidth()) / 2;
		final int y = (screenSize.height - getHeight()) / 2;
		setLocation(x, y);
		setResizable(false);
	}
	
	private JPanel createDesign()
	{
		// DESIGN:
		final JPanel panel = new JPanel();
		final GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		// first checkbox:
		cbMeds = new JComboBox(dataTransferManager.getMedicaments().toArray(new Medicament[0]));
		cbMeds.addActionListener(this);
		cbMeds.setActionCommand(Constants.REGISTRATION_ACTION_MEDICAMENT);
		// second checkbox:
		cbVolumes = new JComboBox(VOLUME.values());
		cbVolumes.addActionListener(this);
		cbVolumes.setActionCommand(Constants.REGISTRATION_ACTION_VOLUME);
		// initial setting (select first one):
		dataTransferManager.setSelectedMedicament((Medicament) cbMeds.getSelectedItem());
		dataTransferManager.setVolume((VOLUME) cbVolumes.getSelectedItem());
		// first label & button:
		final JLabel lblTitle = new JLabel("Medicament list:");
		final JButton btnAdd = new JButton("Add");
		btnAdd.setActionCommand(Constants.REGISTRATION_ACTION_ADD);
		btnAdd.addActionListener(this);
		// second label & button:
		final JButton btnEdit = new JButton("Register");
		btnEdit.setActionCommand(Constants.REGISTRATION_ACTION_REGISTER);
		btnEdit.addActionListener(this);
		// style layout:
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
		layout.linkSize(btnAdd, btnEdit);
		// horizontal layout:
		layout.setHorizontalGroup(layout.createParallelGroup().addComponent(lblTitle).addGroup(layout.createSequentialGroup().addComponent(cbMeds).addComponent(cbVolumes)).addGroup(layout.createSequentialGroup().addComponent(btnAdd).addComponent(btnEdit)));
		// vertical layout:
		layout.setVerticalGroup(layout.createSequentialGroup().addComponent(lblTitle).addGap(20).addGroup(layout.createParallelGroup().addComponent(cbMeds, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addComponent(cbVolumes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(20).addGroup(layout.createParallelGroup().addComponent(btnAdd).addComponent(btnEdit)));
		return panel;
	}
	
	@Override
	public void actionPerformed(final ActionEvent event)
	{
		final String command = event.getActionCommand();
		if (command.equals(Constants.REGISTRATION_ACTION_ADD))
			new AddMedicamentDialog(this, dataTransferManager).setVisible(true);
		else if (command.equals(Constants.REGISTRATION_ACTION_REGISTER))
			new ProductManager(this, dataTransferManager).setVisible(true);
		else if (command.equals(Constants.REGISTRATION_ACTION_MEDICAMENT))
			dataTransferManager.setSelectedMedicament((Medicament) cbMeds.getSelectedItem());
		else if (command.equals(Constants.REGISTRATION_ACTION_VOLUME))
			dataTransferManager.setVolume((VOLUME) cbVolumes.getSelectedItem());
		else
			System.err.println("Unhandled action command: " + command);
	}
	
	class AddMedicamentDialog extends CommonDialog implements ActionListener, KeyListener
	{
		// serialization needs:
		private static final long serialVersionUID = 7732739985853657458L;
		
		private JTextField medName;
		private JComboBox cbMedIsFree;
		
		public AddMedicamentDialog(final JFrame parent, final DataManager data)
		{
			super(parent, data);
			setBounds(parent.getBounds().x, parent.getBounds().y, 320, 160);
			setTitle("Add new medicament");
		}
		
		@Override
		protected JPanel createDesign()
		{
			// DESIGN:
			final JPanel panel = new JPanel();
			final GroupLayout layout = new GroupLayout(panel);
			layout.setAutoCreateGaps(true);
			layout.setAutoCreateContainerGaps(true);
			panel.setLayout(layout);
			// first label & field:
			final JLabel lblMedName = new JLabel("Name: ");
			medName = new JTextField();
			medName.addKeyListener(this);
			// second label & field:
			final JLabel lblMedIsFree = new JLabel("Free: ");
			cbMedIsFree = new JComboBox();
			cbMedIsFree.addItem(MEDICAMENT_TYPE.TYPE_V);
			cbMedIsFree.addItem(MEDICAMENT_TYPE.TYPE_P);
			// OK button:
			final JButton btnOk = new JButton("OK");
			btnOk.setActionCommand(Constants.ACTION_OK);
			btnOk.addActionListener(this);
			// Cancel button:
			final JButton btnCancel = new JButton("Cancel");
			btnCancel.setActionCommand(Constants.ACTION_CANCEL);
			btnCancel.addActionListener(this);
			layout.linkSize(btnOk, btnCancel);
			// horizontal layout:
			layout.setHorizontalGroup(layout.createParallelGroup().addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup().addComponent(lblMedName).addComponent(lblMedIsFree)).addGroup(layout.createParallelGroup().addComponent(medName, 50, 200, Short.MAX_VALUE).addComponent(cbMedIsFree, 50, 200, Short.MAX_VALUE))).addGroup(layout.createSequentialGroup().addGap(0, 10, Short.MAX_VALUE).addComponent(btnOk).addComponent(btnCancel)));
			// vertical layout:
			layout.setVerticalGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.CENTER).addComponent(lblMedName).addComponent(medName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGroup(layout.createParallelGroup(Alignment.CENTER).addComponent(lblMedIsFree).addComponent(cbMedIsFree, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(20).addGroup(
			layout.createParallelGroup().addComponent(btnOk).addComponent(btnCancel)));
			return panel;
		}
		
		@Override
		public void actionPerformed(final ActionEvent event)
		{
			final String command = event.getActionCommand();
			if (command.equals(Constants.ACTION_OK))
				actionOK();
			dispose();
		}
		
		private void actionOK()
		{
			final String name = medName.getText();
			if (name.isEmpty())
			{
				JOptionPane.showMessageDialog(this, "Please enter a name", "Error", JOptionPane.ERROR_MESSAGE);
				medName.requestFocus();
				return;
			}
			final MEDICAMENT_TYPE availability = (MEDICAMENT_TYPE) cbMedIsFree.getSelectedItem();
			final Medicament medicament = new Medicament(name, availability);
			this.dataManager.setSelectedMedicament(medicament);
			// dialog window:
			final RegulatoryManager dialog = new RegulatoryManager(RegistrationManager.this, this.dataManager);
			dialog.addDataTransferListener(RegistrationManager.this);
			dialog.setVisible(true);
			dialog.addWindowListener(new WindowAdapter()
			{
				@Override
				public void windowClosing(WindowEvent e)
				{
					dialog.removeDataTransferListener(RegistrationManager.this);
				}
			});
		}
		
		@Override
		public void keyTyped(KeyEvent e)
		{
		}
		
		@Override
		public void keyPressed(KeyEvent e)
		{
			// handle 'Enter' key:
			if ((e.getSource() instanceof JTextField) && (e.getKeyCode() == KeyEvent.VK_ENTER))
				actionOK();
		}
		
		@Override
		public void keyReleased(KeyEvent e)
		{
		}
	}
	
	@Override
	public void medicamentAdded(final Medicament medicament)
	{
		cbMeds.addItem(medicament);
		cbMeds.setSelectedItem(medicament);
	}
}
