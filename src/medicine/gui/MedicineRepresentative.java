package medicine.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import medicine.Constants;
import medicine.DataManager;

public class MedicineRepresentative extends CommonDialog implements ActionListener
{
	private static final long serialVersionUID = 2038569374951951939L;
	private double price;
	private double finalPrice;
	
	private JLabel lblFinalPriceValue;
	
	public MedicineRepresentative(final JFrame parent, final DataManager data)
	{
		super(parent, data);
		setTitle("Medicine representative manager");
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
		// price of our interest:
		price = dataManager.getFinalPrice();
		// labels:
		final JLabel lblPrice = new JLabel("Price:");
		final JLabel lblPriceValue = new JLabel(String.format("%.2f€", price));
		final JLabel lblFinalPrice = new JLabel("Final price:");
		lblFinalPriceValue = new JLabel();
		updatePriceLabel(1.2); // default selected radio button
		final JLabel lblAdvert = new JLabel("Advertisement duration:");
		// radio buttons:
		final JRadioButton radio1m = new JRadioButton("1month");
		radio1m.addActionListener(this);
		radio1m.setActionCommand(Constants.REPRESENTATIVE_ACTION_1M);
		final JRadioButton radio3m = new JRadioButton("3months");
		radio3m.addActionListener(this);
		radio3m.setActionCommand(Constants.REPRESENTATIVE_ACTION_3M);
		final JRadioButton radio6m = new JRadioButton("6months");
		radio6m.addActionListener(this);
		radio6m.setActionCommand(Constants.REPRESENTATIVE_ACTION_6M);
		// radio group:
		final ButtonGroup radios = new ButtonGroup();
		radios.add(radio1m);
		radios.add(radio3m);
		radios.add(radio6m);
		radio1m.setSelected(true);
		// OK button:
		final JButton btnOk = new JButton("OK");
		btnOk.setActionCommand(Constants.ACTION_OK);
		btnOk.addActionListener(this);
		// horizontal layout:
		layout.setHorizontalGroup(layout.createParallelGroup().addGroup(layout.createSequentialGroup().addComponent(lblPrice).addGap(10).addComponent(lblPriceValue)).addGroup(layout.createParallelGroup().addComponent(lblAdvert).addComponent(radio1m).addComponent(radio3m).addComponent(radio6m)).addGroup(layout.createSequentialGroup().addComponent(lblFinalPrice).addGap(10).addComponent(lblFinalPriceValue)).addGroup(layout.createSequentialGroup().addComponent(btnOk)));
		// vertical layout:
		layout.setVerticalGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup().addComponent(lblPrice).addComponent(lblPriceValue)).addGap(20).addGroup(layout.createSequentialGroup().addComponent(lblAdvert).addComponent(radio1m).addComponent(radio3m).addComponent(radio6m)).addGap(20).addGroup(layout.createParallelGroup().addComponent(lblFinalPrice).addComponent(lblFinalPriceValue)).addGroup(layout.createParallelGroup().addComponent(btnOk)));
		return panel;
	}
	
	private void updatePriceLabel(final double ratio)
	{
		finalPrice = price * ratio;
		lblFinalPriceValue.setText(String.format("%.2f€", finalPrice));
		dataManager.setFinalPrice(finalPrice);
	}
	
	@Override
	public void actionPerformed(final ActionEvent event)
	{
		final String command = event.getActionCommand();
		if (command.equals(Constants.REPRESENTATIVE_ACTION_1M))
			updatePriceLabel(1.2d);
		else if (command.equals(Constants.REPRESENTATIVE_ACTION_3M))
			updatePriceLabel(1.8d);
		else if (command.equals(Constants.REPRESENTATIVE_ACTION_6M))
			updatePriceLabel(2.0d);
		else if (command.equals(Constants.ACTION_OK))
		{
			final String message = String.format("Order in final price %.2f€ successfully completed.", finalPrice);
			JOptionPane.showMessageDialog(this, message, "Request complete", JOptionPane.INFORMATION_MESSAGE);
			dispose();
		}
		else
			System.err.println("Unsupported action command: " + command);
	}
}
