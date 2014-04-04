package SystemeDeConfiguration;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import SystemeDeConfiguration.Configurable;
import SystemeDeConfiguration.FenetreDeConfiguration;

public class FenetreDeConfiguration extends JDialog {

	private static final long serialVersionUID = 1823220579238313073L;
	private final JPanel contentPanel = new JPanel();
	private JTextField largeur;
	private JTextField hauteur;
	private JTextField nbreObstacle;
	private JTextField nombreDePersonnage;
	private JButton okButton;
	private JButton cancelButton;
	private String answer = "Cancel";

	public FenetreDeConfiguration(final Configurable configuration) {
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		this.setResizable(false);
		this.setTitle("Configuration");
		this.setBounds(100, 100, 200, 200);
		this.getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.getContentPane().add(this.contentPanel, BorderLayout.CENTER);
		final GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 120, 80 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 1.0 };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		this.contentPanel.setLayout(gbl_contentPanel);
		{
			final JLabel lblNewLabel = new JLabel("Largeur");
			final GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 0;
			this.contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			this.largeur = new JTextField();
			this.largeur.setText(Integer.toString(configuration.getLargeur()));
			final GridBagConstraints gbc_largeur = new GridBagConstraints();
			gbc_largeur.insets = new Insets(0, 0, 5, 5);
			gbc_largeur.fill = GridBagConstraints.HORIZONTAL;
			gbc_largeur.gridx = 1;
			gbc_largeur.gridy = 0;
			this.contentPanel.add(this.largeur, gbc_largeur);
			this.largeur.setColumns(10);
		}
		{
			final JLabel lblNewLabel_1 = new JLabel("Hauteur");
			final GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_1.gridx = 0;
			gbc_lblNewLabel_1.gridy = 1;
			this.contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		}
		{
			this.hauteur = new JTextField();
			this.hauteur.setText(Integer.toString(configuration.getHauteur()));
			final GridBagConstraints gbc_hauteur = new GridBagConstraints();
			gbc_hauteur.insets = new Insets(0, 0, 5, 5);
			gbc_hauteur.fill = GridBagConstraints.HORIZONTAL;
			gbc_hauteur.gridx = 1;
			gbc_hauteur.gridy = 1;
			this.contentPanel.add(this.hauteur, gbc_hauteur);
			this.hauteur.setColumns(10);
		}
		{
			final JLabel lblNewLabel_2 = new JLabel("Pourcentage de vide");
			final GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
			gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_2.gridx = 0;
			gbc_lblNewLabel_2.gridy = 2;
			this.contentPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		}
		{
			this.nbreObstacle = new JTextField();
			this.nbreObstacle.setText(Integer.toString(configuration.getDensite()));
			final GridBagConstraints gbc_densite = new GridBagConstraints();
			gbc_densite.insets = new Insets(0, 0, 5, 5);
			gbc_densite.fill = GridBagConstraints.HORIZONTAL;
			gbc_densite.gridx = 1;
			gbc_densite.gridy = 2;
			this.contentPanel.add(this.nbreObstacle, gbc_densite);
			this.nbreObstacle.setColumns(10);
		}
		{
			final JLabel lblNombreDenfants = new JLabel("Nombre de personnages");
			final GridBagConstraints gbc_lblNombreDenfants = new GridBagConstraints();
			gbc_lblNombreDenfants.anchor = GridBagConstraints.EAST;
			gbc_lblNombreDenfants.insets = new Insets(0, 0, 0, 5);
			gbc_lblNombreDenfants.gridx = 0;
			gbc_lblNombreDenfants.gridy = 3;
			this.contentPanel.add(lblNombreDenfants, gbc_lblNombreDenfants);
		}
		{
			this.nombreDePersonnage = new JTextField();
			this.nombreDePersonnage.setText(Integer.toString(configuration.getNombreDePersonnage()));
			final GridBagConstraints gbc_nombreDEnfants = new GridBagConstraints();
			gbc_nombreDEnfants.insets = new Insets(0, 0, 0, 5);
			gbc_nombreDEnfants.fill = GridBagConstraints.HORIZONTAL;
			gbc_nombreDEnfants.gridx = 1;
			gbc_nombreDEnfants.gridy = 3;
			this.contentPanel.add(this.nombreDePersonnage, gbc_nombreDEnfants);
			this.nombreDePersonnage.setColumns(10);
		}
		{
			final JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			this.getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				this.okButton = new JButton("OK");
				this.okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(final ActionEvent e) {
						configuration.setDensite(Integer
								.parseInt(FenetreDeConfiguration.this.nbreObstacle.getText()));
						configuration.setHauteur(Integer
								.parseInt(FenetreDeConfiguration.this.hauteur.getText()));
						configuration.setLargeur(Integer
								.parseInt(FenetreDeConfiguration.this.largeur.getText()));
						configuration.setNombreDePersonnage(Integer
								.parseInt(FenetreDeConfiguration.this.nombreDePersonnage.getText()));
						FenetreDeConfiguration.this.answer = "Ok";
						FenetreDeConfiguration.this.setVisible(false);
					}
				});
				this.okButton.setActionCommand("OK");
				buttonPane.add(this.okButton);
				this.getRootPane().setDefaultButton(this.okButton);
			}
			{
				this.cancelButton = new JButton("Cancel");
				this.cancelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(final ActionEvent e) {
						FenetreDeConfiguration.this.answer = "Cancel";
						FenetreDeConfiguration.this.setVisible(false);
					}
				});
				this.cancelButton.setActionCommand("Cancel");
				buttonPane.add(this.cancelButton);
			}
		}
		this.setLocationRelativeTo(null);

	}

	public String getAnswer() {
		return this.answer;
	}
}