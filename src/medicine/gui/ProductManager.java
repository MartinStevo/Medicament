package medicine.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import medicine.Constants;
import medicine.DataManager;
import medicine.Medicament;
import medicine.Constants.MEDICAMENT_TYPE;
import medicine.Constants.VOLUME;

public class ProductManager extends CommonDialog implements ActionListener
{
	// serialization needs:
	private static final long serialVersionUID = 2436658205288199612L;
	
	private JLabel lblPriceValue;
	private JLabel lbl10PercentPriceValue;
	private JTextField tfPackages;
	
	private double price; // price for single package
	private double totalPrice; // price for all packages
	
	public ProductManager(final JFrame parent, final DataManager data)
	{
		super(parent, data);
		setTitle("Product manager");
	}
	
	@Override
	protected JPanel createDesign()
	{
		// DESIGN:
		final JPanel panel = new JPanel();
		final GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
		// Accept button:
		final JButton btnRegister = new JButton("Accept request");
		btnRegister.addActionListener(this);
		btnRegister.setActionCommand(Constants.PRODUCT_ACTION_ACCEPT);
		// get selected Medicament:
		final Medicament medicament = dataManager.getSelectedMedicament();
		final VOLUME volume = dataManager.getVolume();
		// labels:
		final JLabel lblName = new JLabel(medicament.getName());
		final JLabel lblFree = new JLabel(medicament.getType().toString());
		final JLabel lblVolume = new JLabel(volume.toString());
		final JLabel lblPrice = new JLabel("Price: ");
		lblPriceValue = new JLabel();
		final JLabel lbl10PercentPrice = new JLabel("10% price to pay: ");
		lbl10PercentPriceValue = new JLabel();
		final JLabel lblPieces = new JLabel("Packages: ");
		tfPackages = new JTextField("1");
		tfPackages.setHorizontalAlignment(JTextField.RIGHT);
		tfPackages.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyReleased(KeyEvent e)
			{
				final int key = e.getKeyCode();
				switch (key)
				{
					case KeyEvent.VK_0:
					case KeyEvent.VK_1:
					case KeyEvent.VK_2:
					case KeyEvent.VK_3:
					case KeyEvent.VK_4:
					case KeyEvent.VK_5:
					case KeyEvent.VK_6:
					case KeyEvent.VK_7:
					case KeyEvent.VK_8:
					case KeyEvent.VK_9:
					case KeyEvent.VK_BACK_SPACE:
					case KeyEvent.VK_DELETE:
					case KeyEvent.VK_ENTER:
						handlePrice();
						break;
					default:
						// ignore rest
						break;
				}
			}
		});
		// initialization:
		calculatePrice(medicament, volume);
		// horizontal layout:
		layout.setHorizontalGroup(layout.createParallelGroup().addGroup(layout.createSequentialGroup().addComponent(lblName).addGap(20).addComponent(lblFree).addGap(20).addComponent(lblVolume).addGap(20)).addGroup(layout.createSequentialGroup().addComponent(lblPieces).addComponent(tfPackages, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)).addGroup(layout.createSequentialGroup().addComponent(lblPrice).addComponent(lblPriceValue)).addGroup(
		layout.createSequentialGroup().addComponent(lbl10PercentPrice).addComponent(lbl10PercentPriceValue)).addComponent(btnRegister));
		// vertical layout:
		layout.setVerticalGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup().addComponent(lblName).addComponent(lblFree).addComponent(lblVolume)).addGap(20).addGroup(layout.createParallelGroup().addComponent(lblPieces).addComponent(tfPackages, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(50).addGroup(layout.createParallelGroup().addComponent(lblPrice).addComponent(lblPriceValue)).addGroup(
		layout.createParallelGroup().addComponent(lbl10PercentPrice).addComponent(lbl10PercentPriceValue)).addComponent(lbl10PercentPrice).addComponent(btnRegister));
		return panel;
	}
	
	private void calculatePrice(final Medicament medicament, final VOLUME volume)
	{
		final double basicPrice = volume.getPrice();
		if (!medicament.getType().isFree())
			price = basicPrice;
		else
			price = basicPrice * 1.2d; // multiply penalty
		totalPrice = price;
		updatePriceLabel();
	}
	
	private void updatePriceLabel()
	{
		lblPriceValue.setText(String.format("%.2f€", totalPrice));
		lbl10PercentPriceValue.setText(String.format("%.2f€", totalPrice * 0.1d));
	}
	
	private void handlePrice()
	{
		final String text = tfPackages.getText();
		int packages = 0;
		try
		{
			// parse number of packages:
			packages = Integer.parseInt(text);
		}
		catch (final NumberFormatException e)
		{
			showErrorMessage();
			tfPackages.setText("1");
			return;
		}
		if (packages <= 0)
		{
			showErrorMessage();
			tfPackages.setText("1");
			return;
		}
		// recalculate total:
		totalPrice = packages * price;
		updatePriceLabel();
	}
	
	private void showErrorMessage()
	{
		// display ERROR:
		JOptionPane.showMessageDialog(this, "Invalid package count!", "Error", JOptionPane.ERROR_MESSAGE);
		tfPackages.requestFocus();
	}
	
	@Override
	public void actionPerformed(final ActionEvent event)
	{
		final String command = event.getActionCommand();
		if (command.equals(Constants.PRODUCT_ACTION_ACCEPT))
		{
			handlePrice();
			dataManager.setFinalPrice(totalPrice);
			final MEDICAMENT_TYPE type = dataManager.getSelectedMedicament().getType();
			// check type:
			if (type.equals(MEDICAMENT_TYPE.TYPE_V))
				new MedicineRepresentative((JFrame) getParent(), dataManager).setVisible(true);
			else
			{
				final String message = String.format("Order in total price %.2f€ successfully completed.", totalPrice);
				JOptionPane.showMessageDialog(this, message, "Request complete", JOptionPane.INFORMATION_MESSAGE);
			}
			dispose();
		}
		else
			System.err.println("Unsupported action command: " + command);
	}
}
