/*
  Classe Polygone pour gérer un polygone quelconque
  	calcul du périmètre
  	calcul de l'aire
  	calcul du centre
  	gestion de la couleur
  	méthode toString

 */
 
import java.awt.Color;
import java.awt.Graphics;

public class Polygon extends Curve {
	
	// Les attributs
	private final Point[] iniVertices;
	private final Point[] vertices;

	/**
	 * Le constructeur
	 * @param p tableau de points décrivant chaque sommet
	 */ 
	public Polygon(Point[] p){
		super(); // Ici super() est facultatif car java ajoute cette instruction automatiquement
		iniVertices = new Point[p.length];
		vertices = new Point[p.length];
		for(int i=0; i<p.length; i++) {
			iniVertices[i] = new Point(p[i].x,p[i].y);
			vertices[i] = new Point(p[i].x,p[i].y);
		}
	}
	
	/**
	 * Le constructeur
	 * @param p tableau de points décrivant chaque sommet et une couleur
	 */ 
	public Polygon(Point[] p, Color uneCouleur){
		// Pour appeler le constructeur de l'ancêtre et affecter au polygone la couleur par défaut
       super(uneCouleur);
		iniVertices = new Point[p.length];
		vertices = new Point[p.length];
		for(int i=0; i<p.length; i++) {
			iniVertices[i] = new Point(p[i].x,p[i].y);
			vertices[i] = new Point(p[i].x,p[i].y);
		}
	}
	
	/**
	 * Pour calculer le périmètre d'un polygone
	 * @return le périmètre
	 */ 
	public double perimeter(){
		double res =0;
		for(int i = 1; i< vertices.length; i++){
			res += vertices[i-1].distance(vertices[i]);
		}
		// ajout de la distance entre dernier et premier point
		res += vertices[vertices.length-1].distance(vertices[0]);
		return res;
	}
	
	/**
	 * Pour calculer l'aire d'un polygone
	 * @return l'area
	 */ 
	public double area(){
		double a=0;
		int i;
		for(i=0; i< vertices.length-1; i++)
			a += vertices[i].x* vertices[i+1].y - vertices[i+1].x* vertices[i].y;
		// "i" a la valeur points.length-1 maintenant
		a += vertices[i].x* vertices[0].y - vertices[0].x* vertices[i].y;
		return 0.5*a;
	}
	
	/**
	 * Pour calculer le barycentre d'un polygone
	 * @return le barycentre sous forme d'un APoint
	 */ 
	public Point barycenter(){
		Point res = new Point(0,0);
		int i;
		for(i=0; i< vertices.length-1; i++){
			res.x += (vertices[i].x+ vertices[i+1].x) * (vertices[i].x* vertices[i+1].y - vertices[i+1].x* vertices[i].y);
			res.y += (vertices[i].y+ vertices[i+1].y) * (vertices[i].x* vertices[i+1].y - vertices[i+1].x* vertices[i].y);
		}
		// "i" a la valeur points.length-1 maintenant
		res.x += (vertices[i].x+ vertices[0].x) * (vertices[i].x* vertices[0].y - vertices[0].x* vertices[i].y);
		res.y += (vertices[i].y+ vertices[0].y) * (vertices[i].x* vertices[0].y - vertices[0].x* vertices[i].y);
		double a = area();
		res.x /= 6*a;
		res.y /= 6*a;
		return res;
  }


	/**
	 * Pour afficher les données du polygone
	 * @return les coordonnées des sommets
	 */
	public String toString(){
		StringBuilder res;
		res = new StringBuilder("Polygone avec " + vertices.length + " sommets :");
		for (Point vertex : vertices) res.append(" (").append(vertex.x).append(",").append(vertex.y).append(")");
		res.append(". ");
		res.append(super.toString());
		return res.toString();
	}

	public void draw(Graphics g){
		g.setColor(this.color);
		int[] xPoints = new int[vertices.length];
		int[] yPoints = new int[vertices.length];
		for(int i=0; i<vertices.length; i++) {
			xPoints[i] = (int) vertices[i].x;
			yPoints[i] = (int) vertices[i].y;
		}
		g.fillPolygon(xPoints,yPoints,vertices.length);
	}

	@Override
	public void displaceY(int dy) {
		for(Point v : vertices){
			v.y += dy;
		}
	}

	public void reset(){
		System.arraycopy(iniVertices, 0, vertices, 0, iniVertices.length);
	}

}

