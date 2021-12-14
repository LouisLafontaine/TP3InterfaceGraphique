/*
  Classe cercle pour définir un cercle
  	calcul du périmètre
  	calcul de l'aire
  	calcul du centre
  	gestion de la couleur
  	méthode toString

 */
 
import java.awt.Color;
import java.awt.Graphics;

public class Circle extends Curve {

	// Les attributs
	private final Point iniCenter;
	private final Point center;
	private final int radius;
	
	/**
	 * Le constructeur
	 * @param c centre et le rayon
	 */ 
	public Circle(Point c, int r){
		super(); // Ici super() est facultatif car java ajoute cette instruction automatiquement
		iniCenter =  new Point(c.x,c.y);
		center = new Point(c.x,c.y);
		radius = r;
	}

	/**
	 * Le constructeur
	 * @param c centre, le rayon et la couleur
	 */ 
	public Circle(Point c, int r, Color uneCouleur){
		super(uneCouleur);
		iniCenter = new Point(c.x, c.y);
		center = new Point(c.x,c.y);
		radius = r;
	}
	
	/** 
	 * Pour calculer le périmètre d'un cercle
	 * @return le périmètre
	 */
	public double perimeter(){
		return 2.0*Math.PI* radius;
	}
	
	/**
	 * Pour calculer l'aire d'un cercle
	 * @return l'area
	 */ 
	public double area(){
		return Math.PI* radius * radius;
	}
	
	/**
	 * Pour récupérer le centre d'un cercle
	 * @return le centre du cercle
	 */ 
	public Point barycenter(){
		return center;
	}
  

	/**
	 * Pour afficher les données du cercle
	 * @return le centre et le rayon
	 */
    public String toString(){
		String res;
		res=("Cercle de centre ("+ center.x+","+ center.y+") et de rayon "+ radius +". ");
		res+=super.toString();
		return res;
	}

	public void draw(Graphics g){
    	g.setColor(this.color);
    	g.fillOval((int)center.x-radius, (int)center.y-radius, 2*this.radius, 2*this.radius);
	}

	public void move(int dx, int dy) {
    	this.center.x += dx;
		this.center.y += dy;
	}

	public void reset(){
    	center.x = iniCenter.x;
    	center.y = iniCenter.y;
	}

	public boolean isTouching(Point p){
    	return (center.distance(p) < radius);
	}
}


