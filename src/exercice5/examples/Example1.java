package exercice5.examples;

//import exercice5.Exercice5;

public class Example1 {
	
	/* 
	 * Ajoute un rectangle robi avec ses propriétés par défaut
	 * On doit voir un rectangle bleu en (0,0)
	 * 
	 */
	String script = "(space add robi (Rect new))"
				+   "(robi add pif (Image new alien.gif))"
				+   "(robi setColor yellow)"
				+   "(robi translate 100 0)"
				+   "(space sleep 1000)"
				+   "(robi translate 0 50)"
				+   "(space sleep 1000)"
				+   "(robi translate 100 50)";
	
	
	public  void launch() {
		Exercice5 exo = new Exercice5();
		exo.oneShot(script);
	}
	
	public static void main(String[] args) {
		new Example1().launch();
	}
}
  