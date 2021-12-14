import java.awt.Color;
import java.awt.Graphics;

public abstract class Curve{

	// Attribut
	protected Color iniColor;
	protected Color color;
	protected final Color DEFAULT_COLOR = Color.black;


	/**
     * Le constructeur par défaut qui affecte la couleur noire
     */
    public Curve(){
		// La couleur par défaut est défini comme une constante
		iniColor = DEFAULT_COLOR;
		color = DEFAULT_COLOR;
    }
    
    /**
     * Le constructeur avec une couleur
     * @param aColor couleur de la courbe
     */ 
    public Curve(Color aColor){
        iniColor = aColor;
    	color = aColor;
    }
	
	/**
	 * Pour afficher la couleur de la courbe
	 */
	public String toString(){
		return ("Ma longueur est "+ perimeter()+ " et mon area est "+ area()+ " et ma couleur est "+ color);
	}
  
	// Les méthodes abstraites
	/** 
	 * Pour calculer la longueur d'une courbe (quel que soit son type)
	 * @return la longueur
	 */
	public abstract double perimeter();
	
	/** 
	 * Pour calculer l'aire d'une courbe (quel que soit son type)
	 * @return l'area
	 */
	public abstract double area();
	
	/** 
	 * Pour calculer le barycentre d'une courbe (quel que soit son type)
	 * @return le barycentre
	 */
	public abstract Point barycenter();

	public abstract void draw(Graphics g);

	public abstract void move(int dx, int dy);

	public abstract void reset();

	public abstract boolean isTouching(Point p);
}

