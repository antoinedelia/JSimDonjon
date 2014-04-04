package JSimDonjon;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import JSimDonjon.Donjon;

abstract class ElementMobile implements AffichageDePlateau.PlateauPiece {
	public final static int NORD = 0;
	public final static int EST = 1;
	public final static int SUD = 2;
	public final static int OUEST = 3;

	protected int x;
	protected int y;
	protected int i=0;
	protected int direction;
	protected char dessin;
	protected Image image = null;
	protected Donjon donjon;
	protected String espece;

	public ElementMobile(final Donjon donjon, final char dessin, final String image, final int x,
			final int y, final int direction, final String espece) {
		this.donjon = donjon;
		this.dessin = dessin;
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.espece = espece;
		this.setImage(image);
	}

	public ElementMobile(final Donjon donjon, final char dessin, final String image,
			final String espece) {
		this(donjon, dessin, image, 0, 0, NORD, espece);
	}

	protected int calculerNouveauX() {
		switch (this.direction) {
		case EST:
			if (this.x < (this.donjon.getConfiguration().getLargeur() - 1)) {
				return this.x + 1;
			}
		case OUEST:
			if (this.x > 0) {
				return this.x - 1;
			}
		default:
			return this.x;
		}
	}

	protected int calculerNouveauY() {
		switch (this.direction) {
		case NORD:
			if (this.y > 0) {
				return this.y - 1;
			}
		case SUD:
			if (this.y < (this.donjon.getConfiguration().getHauteur() - 1)) {
				return this.y + 1;
			}
		default:
			return this.y;
		}
	}

	protected abstract void changerDirection();

	public char getDessin() {
		return this.dessin;
	}

	public String getEspece() {
		return this.espece;
	}

	@Override
	public Image getImage() {
		return this.image;
	}

	@Override
	public int getPositionHorizontale() {
		return this.getX();
	}

	@Override
	public int getPositionVerticale() {
		return this.getY();
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}


	public void deplacerMonstre(){	//Le déplacement du monstre

		this.changerDirection();
	}

	public void deplacerPersonnages() {	//Le déplacement des personnages

		this.changerDirection();

		final int x = this.calculerNouveauX();
		final int y = this.calculerNouveauY();

		if (this.donjon.getXY(x, y).estVide()) {
			this.x = x;
			this.y = y;
		}
	}




	public void setImage(final String image) {
		try {
			this.image = ImageIO.read(new File("images/" + image));
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	public void setX(final int x) {
		this.x = x;
	}

	public void setY(final int y) {
		this.y = y;
	}

}
