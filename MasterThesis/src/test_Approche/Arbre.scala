package test_Approche

class Arbre {
  
  private var valeur:String=""
    private var père:Arbre=null;
	private var filsGauche:Arbre=null
	private var filsDroit:Arbre=null
	private var nbreDeFils:Int=0
	
	//Deuxième constructeur
	def this(a:String)={this(); valeur=a; filsGauche=null; filsDroit=null;}

	//Getter et Setter
	def getValeur():String={return valeur;}

	def setValeur(vald:String){valeur = vald;}
	
	def setPère(p:Arbre){père=p;}
	
	def getPère():Arbre={return père;}

	def setFilsGauche(fg:Arbre ){filsGauche = fg;}

	def getFilsGauche():Arbre={return filsGauche;}

	def setFilsDroit(fd:Arbre){filsDroit = fd;}

	def getFilsDroit():Arbre={return filsDroit;}
	
	def setNbreDeFils(nbre:Int){nbreDeFils=nbre;}
	
	def getNbreDeFils():Int={return nbreDeFils;}

}