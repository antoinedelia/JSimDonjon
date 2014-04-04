package JSimDonjon;

import java.util.ArrayList;
import java.util.Random;

import JSimDonjon.Bordure;
import JSimDonjon.Case;
import JSimDonjon.ConfigurationJSimDonjon;
import JSimDonjon.ElementMobile;
import JSimDonjon.Monstre;
import JSimDonjon.Obstacle;
import JSimDonjon.Personnage;
import JSimDonjon.Sol;
import AffichageDePlateau.Plateau;

public class Donjon {
	private final ConfigurationJSimDonjon configuration;

	private final Case cases[][];
	private final Random hasard;
	private final ArrayList<ElementMobile> mobiles;
	private final Boolean affichageConsole = false;	//Définir mode console/graphique
	private final Plateau plateau;

	public Donjon(final ConfigurationJSimDonjon configuration) {
		this.configuration = configuration;

		this.cases = new Case[this.configuration.getLargeur()][this.configuration.getHauteur()];
		this.hasard = new Random();
		this.mobiles = new ArrayList<>();

		Case uneCase;
		for (int y = 0; y < this.configuration.getHauteur(); y++) {
			for (int x = 0; x < this.configuration.getLargeur(); x++) {
				if ((x == 0) || (x == (this.configuration.getLargeur() - 1)) || (y == 0)
						|| (y == (this.configuration.getHauteur() - 1))) {
					uneCase = new Bordure();
				} else if (this.hasard.nextInt(this.configuration.getDensite()) == 0) {
					uneCase = this.construireObstacle();
				} else {
					uneCase = this.construireSol();
				}
				this.setXY(x, y, uneCase);
			}
		}
		this.plateau = new Plateau(this.configuration.getLargeur(),
				this.configuration.getHauteur(), this.cases);

		this.placerLOgre();
		this.placerLesEnfants();
	}

	public void afficher() {
		if (this.affichageConsole) {
			this.afficherConsole();
		} else {
			this.afficherGraphique();
		}
	}

	private void afficherConsole() {
		for (int y = 0; y < this.configuration.getHauteur(); y++) {
			for (int x = 0; x < this.configuration.getLargeur(); x++) {
				final ElementMobile e = this.getElementMobile(x, y);
				if (e == null) {
					System.out.print(this.getXY(x, y).getDessin());
				} else {
					System.out.print(e.getDessin());
				}
			}
			System.out.println();
		}
	}

	private void afficherGraphique() {
		this.plateau.rafraichir();
	}

	private Case construireObstacle() {
		return new Obstacle();

	}

	private Case construireSol() {
		return new Sol();
	}

	public ConfigurationJSimDonjon getConfiguration() {
		return this.configuration;
	}

	public ElementMobile getElementMobile(final int x, final int y) {
		for (final ElementMobile e : this.mobiles) {
			if (e != null) {
				if ((e.getX() == x) && (e.getY() == y)) {
					return e;
				}
			}
		}
		return null;
	}

	public Case getXY(final int x, final int y) {
		return this.cases[x][y];
	}

	public void jouer() {
		for (final ElementMobile e : this.mobiles) {
			if(e.espece=="Monstre"){	//Si c'est un ogre, il va se déplacer différemment
				e.deplacerMonstre();
			}
			else
			{
				e.deplacerPersonnages();
			}
		}
	}

	
	private void placerLOgre() {
		final Monstre ogre = new Monstre(this);
		this.mobiles.add(ogre);
		this.placerUnElementMobileAuHasard(ogre);
		this.plateau.placerPiece(ogre);
	}
	
	
	private void placerLesEnfants() {
		Personnage e;
		for (int i = 0; i < this.configuration.getNombreDePersonnage(); i++) {
			e = new Personnage(this);
			this.mobiles.add(e);
			this.placerUnElementMobileAuHasard(e);
			this.plateau.placerPiece(e);
		}
	}


	private void placerUnElementMobileAuHasard(final ElementMobile e) {
		int x = this.hasard.nextInt(this.configuration.getLargeur());
		int y = this.hasard.nextInt(this.configuration.getHauteur());
		while ((!this.getXY(x, y).estVide()) && (this.getElementMobile(x, y) == null)) {
			x = this.hasard.nextInt(this.configuration.getLargeur());
			y = this.hasard.nextInt(this.configuration.getHauteur());
		}
		e.setX(x);
		e.setY(y);
	}

	public void setXY(final int x, final int y, final Case c) {
		this.cases[x][y] = c;
	}
}
