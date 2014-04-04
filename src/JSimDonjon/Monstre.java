package JSimDonjon;

import javax.swing.JOptionPane;

import JSimDonjon.Donjon;
import JSimDonjon.ElementMobile;
import JSimDonjon.Personnage;

public class Monstre extends ElementMobile {

	protected int vie=100;	//Point de vie du monstre
	protected int pas=0;	//Nombre de pas du monstre
	protected int tourne=0;	//Le nombre de fois que le monstre a tourné
	protected int stop=0;	//Si le monstre doit se stopper après avoir manger un personnage
	protected boolean demiTour=false;	//Si le monstre doit faire demi-tour

	public Monstre(final Donjon donjon) {
		super(donjon, 'M', "monstre.gif", "Monstre");	//On définit une image et le nom du monstre
	}

	@Override
	protected void changerDirection() {

		if(vie!=0){	//Si le monstre n'est pas mort
			if(stop==0){	//S'il ne doit pas s'arréter
				if(pas==0){	//Si c'est son premier pas, on définit aléatoirement la direction du monstre
					int test=(int)(Math.random() * (100-1)) + 1;
					if(test<25)
						this.direction=NORD;
					else if(test<50)
						this.direction=SUD;
					else if(test<75)
						this.direction=EST;
					else
						this.direction=OUEST;
				}
				if(demiTour)	//S'il doit faire demi-tour, cela lui prendra un tour de plus
				{
					demiTour=false;
				}
				else if(this.direction==NORD){	//S'il se dirige vers le nord

					final ElementMobile nord1 = this.donjon.getElementMobile(this.getX(), this.getY()-1);	//On définit trois éléments mobiles dans le voisinnage du monstre
					final ElementMobile nord2 = this.donjon.getElementMobile(this.getX()+1, this.getY());
					final ElementMobile nord3 = this.donjon.getElementMobile(this.getX()-1, this.getY());
					if (nord1 != null && !nord1.getEspece().equals("Personnage Mort")) {	//Si la case devant lui n'est pas vide et que ce n'est pas un personnage mort
						if (nord1.getEspece().equals("Personnage")) {	//Si c'est un personnage
							final Personnage personnage = (Personnage) nord1;
							personnage.mourir();	//On fait mourir le personnage
							y--;	//On place le monstre à l'endroit où se situait le personnage
							stop=-3;	//Il s'arrète 3 tours
							vie=vie+20;	//Il gagne 20 points de vie
						}
					}
					else if(nord2!=null && !nord2.getEspece().equals("Personnage Mort")){	//On fait de même pour les cases à gauche et à drite du monstre
						if (nord2.getEspece().equals("Personnage")) {
							final Personnage personnage = (Personnage) nord2;
							personnage.mourir();
							x++;
							stop=-3;
							vie=vie+20;
						}
					}
					else if(nord3 != null && !nord3.getEspece().equals("Personnage Mort")){
						if (nord3.getEspece().equals("Personnage")) {
							final Personnage personnage = (Personnage) nord3;
							personnage.mourir();
							x--;
							stop=-3;
							vie=vie+20;
						}
					}
					else if (!this.donjon.getXY(x, y-1).estVide()){	//S'il y a un obstacle devant le monstre
						if(tourne%3==0 && tourne!=0){	//Si c'est la troisième fois qu'il tourne
							this.direction=SUD;	//Il fait demi-tour
							demiTour=true;
						}
						else{
							this.direction=EST;	//Sinon il tourne à droite
							tourne++;	//On compte le nombre de fois que le monstre à tourné
						}
					}
					else
						y--;	//On fait avancer le monstre
				}
				else if(this.direction==EST){

					final ElementMobile est1 = this.donjon.getElementMobile(this.getX()+1, this.getY());
					final ElementMobile est2 = this.donjon.getElementMobile(this.getX(), this.getY()+1);
					final ElementMobile est3 = this.donjon.getElementMobile(this.getX(), this.getY()-1);
					if (est1 != null && !est1.getEspece().equals("Personnage Mort")) {
						if (est1.getEspece().equals("Personnage")) {
							final Personnage personnage = (Personnage) est1;
							personnage.mourir();
							x++;
							stop=-3;
							vie=vie+20;
						}
					}
					else if(est2 != null && !est2.getEspece().equals("Personnage Mort")){
						if (est2.getEspece().equals("Personnage")) {
							final Personnage personnage = (Personnage) est2;
							personnage.mourir();
							y++;
							stop=-3;
							vie=vie+20;
						}
					}
					else if(est3 != null && !est3.getEspece().equals("Personnage Mort")){
						if (est3.getEspece().equals("Personnage")) {
							final Personnage personnage = (Personnage) est3;
							personnage.mourir();
							y--;
							stop=-3;
							vie=vie+20;
						}
					}
					else if(!this.donjon.getXY(x+1, y).estVide()){
						if(tourne%3==0 && tourne!=0){
							this.direction=OUEST;
							demiTour=true;
						}
						else{
							this.direction=SUD;
							tourne++;
						}
					}
					else
						x++;
				}
				else if(this.direction==SUD){


					final ElementMobile sud1 = this.donjon.getElementMobile(this.getX(), this.getY()+1);
					final ElementMobile sud2 = this.donjon.getElementMobile(this.getX()+1, this.getY());
					final ElementMobile sud3 = this.donjon.getElementMobile(this.getX()-1, this.getY());
					if (sud1 != null && !sud1.getEspece().equals("Personnage Mort")) {
						if (sud1.getEspece().equals("Personnage")) {
							final Personnage personnage = (Personnage) sud1;
							personnage.mourir();
							y++;
							stop=-3;
							vie=vie+20;
						}
					}
					else if(sud2!=null && !sud2.getEspece().equals("Personnage Mort")){
						if (sud2.getEspece().equals("Personnage")) {
							final Personnage personnage = (Personnage) sud2;
							personnage.mourir();
							x++;
							stop=-3;
							vie=vie+20;
						}
					}
					else if(sud3!=null && !sud3.getEspece().equals("Personnage Mort")){
						if (sud3.getEspece().equals("Personnage")) {
							final Personnage personnage = (Personnage) sud3;
							personnage.mourir();
							x--;
							stop=-3;
							vie=vie+20;
						}
					}
					else if(!this.donjon.getXY(x, y+1).estVide()){
						if(tourne%3==0 && tourne!=0){
							this.direction=NORD;
							demiTour=true;
						}
						else{
							this.direction=OUEST;
							tourne++;
						}
					}
					else
						y++;
				}
				else if(this.direction==OUEST){


					final ElementMobile ouest1 = this.donjon.getElementMobile(this.getX()-1, this.getY());
					final ElementMobile ouest2 = this.donjon.getElementMobile(this.getX(), this.getY()+1);
					final ElementMobile ouest3 = this.donjon.getElementMobile(this.getX(), this.getY()-1);
					if (ouest1 != null && !ouest1.getEspece().equals("Personnage Mort")) {
						if (ouest1.getEspece().equals("Personnage")) {
							final Personnage personnage = (Personnage) ouest1;
							personnage.mourir();
							x--;
							stop=-3;
							vie=vie+20;
						}
					}
					else if(ouest2!=null && !ouest2.getEspece().equals("Personnage Mort")){
						if (ouest2.getEspece().equals("Personnage")) {
							final Personnage personnage = (Personnage) ouest2;
							personnage.mourir();
							y++;
							stop=-3;
							vie=vie+20;
						}
					}
					else if(ouest3!=null && !ouest3.getEspece().equals("Personnage Mort")){
						if (ouest3.getEspece().equals("Personnage")) {
							final Personnage personnage = (Personnage) ouest3;
							personnage.mourir();
							y--;
							stop=-3;
							vie=vie+20;
						}

					}
					else if(!this.donjon.getXY(x-1, y).estVide()){
						if(tourne%3==0 && tourne!=0){
							this.direction=EST;
							demiTour=true;
						}
						else
						{
							this.direction=NORD;
							tourne++;
						}
					}
					else
						x--;
				}
			}
			else{	//Si le monstre est à l'arrêt, 
				stop++;	//On lui dit qu'il lui reste un tour de moins à patienter
				pas--;	//On décrémente les pas car ils sont ajoutés automatiquement
				vie++;	//On incrémente la vie car elle diminue automatiquement
			}
		}
		else{	//Si le monstre n'a plus de vie
			JOptionPane.showMessageDialog(null, "Le monstre est mort !");	//On affiche qu'il est mort
			System.exit(0);	//On quitte le jeu
		}
		pas++;	//On ajoute un pas au monstre
		vie--;	//On lui enlève un point de vie
	}


	@Override
	public void deplacerMonstre() {
		super.deplacerMonstre();
	}
}
