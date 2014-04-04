import JSimDonjon.ConfigurationJSimDonjon;
import JSimDonjon.Donjon;
import SystemeDeConfiguration.FenetreDeConfiguration;


public class J_Sim_Donjon {
	public static final int DUREE_TOUR = 500;

	public static void main(final String[] args) {
		final ConfigurationJSimDonjon configuration = new ConfigurationJSimDonjon();
		try {
			final FenetreDeConfiguration fdc = new FenetreDeConfiguration(configuration);
			fdc.setVisible(true);
			if (fdc.getAnswer().equals("Ok")) {
				final Donjon maForet = new Donjon(configuration);
				for (;;) {
					maForet.afficher();
					maForet.jouer();
					try {
						Thread.sleep(DUREE_TOUR);
					} catch (final InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
}
