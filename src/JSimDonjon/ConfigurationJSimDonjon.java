package JSimDonjon;

import SystemeDeConfiguration.Configurable;

public class ConfigurationJSimDonjon implements Configurable {
	private int densite;
	private int hauteur;
	private int largeur;
	private int nombreDEnfants;

	public ConfigurationJSimDonjon() {
		this.densite = 10;
		this.hauteur = 20;
		this.largeur = 20;
		this.nombreDEnfants = 20;
	}

	@Override
	public int getDensite() {
		return this.densite;
	}

	@Override
	public int getHauteur() {
		return this.hauteur;
	}

	@Override
	public int getLargeur() {
		return this.largeur;
	}

	@Override
	public int getNombreDePersonnage() {
		return this.nombreDEnfants;
	}

	@Override
	public void setDensite(final int densite) {
		this.densite = densite;

	}

	@Override
	public void setHauteur(final int hauteur) {
		this.hauteur = hauteur;
	}

	@Override
	public void setLargeur(final int largeur) {
		this.largeur = largeur;
	}

	@Override
	public void setNombreDePersonnage(final int nombreDEnfants) {
		this.nombreDEnfants = nombreDEnfants;
	}

}
