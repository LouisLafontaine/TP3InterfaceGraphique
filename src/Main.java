/*
  Classe principale pour gérer différents types de courbe et leur couleur

  Permet de créer une fenêtre pour gérer la liste de courbes
 */ 
 
import java.awt.Color;
 
public class Main {
  
	public static void main(String[] args){
		
		// Création d'un tableau de courbes
		Curve[] curveArray =new Curve[5];
		
		// création de 3 cercles
		Curve c1 = new Circle(new Point(100,100), 50);
		Curve c2 = new Circle(new Point(300,300), 80);
		Curve c3 = new Circle(new Point(200,150), 30, Color.blue);
		
		// Création de 2 polygones
		Point[] p1 = new Point[4];
		p1[0] = new Point(50, 200);
		p1[1] = new Point(200, 200);
		p1[2] = new Point(150, 300);
		p1[3] = new Point(100, 300);
		Curve poly1 = new Polygon(p1, Color.magenta);
		
		Point[] p2 = new Point[3];
		p2[0] = new Point(250, 50);
		p2[1] = new Point(350, 120);
		p2[2] = new Point(260, 200);
		Curve poly2 = new Polygon(p2, Color.pink);

		// Remplissage du tableau par les courbes créées auparavant
		curveArray[0]=c1;
		curveArray[1]=c2;
		curveArray[2]=c3;
		curveArray[3]=poly1;
		curveArray[4]=poly2;
		
		// Création de la fenêtre pour l'IHM
		new CurveSelectionWindow(curveArray);
   }
   
}

