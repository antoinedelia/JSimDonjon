package JSimDonjon;

import java.util.Random;

import JSimDonjon.Donjon;
import JSimDonjon.ElementMobile;

class Personnage extends ElementMobile {
	private Boolean vivant;

	public Personnage(final Donjon donjon) {
		super(donjon, 'P', "perso.gif", "Personnage");
		this.vivant = true;
	}

	@Override
	protected void changerDirection() {

		this.direction = new Random().nextInt(4);
	}

	public void mourir() {
		this.vivant = false;
		this.dessin = '±';
		this.setImage("mort.png");
		this.espece="Personnage Mort";
	}

	@Override
	public void deplacerPersonnages() {
		if (!this.vivant) {
			return;
		}
		super.deplacerPersonnages();
	}
}
