package JSimDonjon;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

class Case implements AffichageDePlateau.PlateauCase {
	protected Image image = null;
	protected char dessin;
	protected Boolean vide;

	public Case(final char dessin, final String image, final Boolean vide) {
		this.dessin = dessin;
		this.vide = vide;
		try {
			this.image = ImageIO.read(new File("images/" + image));
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	public Boolean estVide() {
		return this.vide;
	}

	public char getDessin() {
		return this.dessin;
	}

	@Override
	public Image getImage() {
		return this.image;
	}
}
