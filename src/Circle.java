/**
 * Classe cercle pour définir un cercle
 * 	calcul du périmètre
 * 	calcul de l'aire
 * 	calcul du centre
 * 	gestion de la couleur
 * 	méthode toString
 * 
 */
 
import java.awt.Color;

public class Circle extends Curve {

	// Les attributs
	private Point centre;
	private int rayon;
	
	/**
	 * Le constructeur
	 * @param le centre et le rayon
	 */ 
	public Circle(Point c, int r){
		super(); // Ici super() est facultatif car java ajoute cette instruction automatiquement
		centre = new Point(c.x,c.y);
		rayon = r;
	}

	/**
	 * Le constructeur
	 * @param c centre, le rayon et la couleur
	 */ 
	public Circle(Point c, int r, Color uneCouleur){
		super(uneCouleur);
		centre = new Point(c.x,c.y);
		rayon = r;   
	}
	
	/** 
	 * Pour calculer le périmètre d'un cercle
	 * @return le périmètre
	 */
	public double perimeter(){
		return 2.0*Math.PI*rayon;
	}
	
	/**
	 * Pour calculer l'aire d'un cercle
	 * @return l'area
	 */ 
	public double area(){
		return Math.PI*rayon*rayon;
	}
	
	/**
	 * Pour récupérer le centre d'un cercle
	 * @return le centre du cercle
	 */ 
	public Point barycenter(){
		return centre;
	}
  

	/**
	 * Pour afficher les données du cercle
	 * @return le centre et le rayon
	 */
    public String toString(){
		String res;
		res=("Cercle de centre ("+centre.x+","+centre.y+") et de rayon "+rayon+". ");
		res+=super.toString();
		return res;
	}
}


