package test_Approche

object MainClass {

  def main(args: Array[String]){
    val auteur:Auteur=new Auteur();
  	val editeur:Editeur=new Editeur();
	val ea:EditeurAssoc=new EditeurAssoc();
  	val review:Relecteur=new Relecteur();
		auteur.ed=editeur;
		editeur.aut=auteur; editeur.ea=ea;
		ea.ed=editeur; ea.rew=review;
		review.ea=ea;
  }

}