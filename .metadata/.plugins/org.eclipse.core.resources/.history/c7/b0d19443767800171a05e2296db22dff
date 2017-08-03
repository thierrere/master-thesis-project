package test_Approche

import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JOptionPane
import java.io.IOException
import java.io.FileNotFoundException
import java.awt.Desktop
import java.io.File

class EcouteurAuteur extends ActionListener {
  
  var auteur:Auteur=null;
	
	def this(auteur:Auteur){ this(); this.auteur=auteur;}
	
	def actionPerformed(e:ActionEvent) {
		// TODO Auto-generated method stub
		var source:Object=e.getSource();

		//Réaction du bouton soumission
		if(source.equals(auteur.sous)){
			System.out.println("Bouton soumission cliqué");
			auteur.soumission();
			//Dessin1.parcoursPrefixe(test.T, 0, 0, " ",Component.g:Graphics2D)
		}

		if(source.equals(auteur.accsmodif)){
			System.out.println("bouton accsmodif");
			auteur.envoiCorrection();
		}
		/*if(source.equals(auteur.parcours)){
		  println("Bouton ouvrir...")
		  auteur.fen.setEnabled(false);
		  new PdfSelectAuteur(auteur);
		}*/
		if(source.equals(auteur.réinit)){
		  auteur.réinitAll();
		}
		if(source.equals(auteur.ouvrir)){
		  try{
		  	val fileM=new File(auteur.cheminPdf);
		  	Desktop.getDesktop().open(fileM);
		  }
		  catch{
		    case ex: FileNotFoundException => JOptionPane.showMessageDialog (
					auteur.pangen, "Fichier non trouvé!!", 
					"Erreur Fichier", JOptionPane.ERROR_MESSAGE);
		    case ex: IOException => JOptionPane.showMessageDialog (
					auteur.pangen, "Problème avec l'ouverture du fichier!!", 
					"Erreur Fichier", JOptionPane.ERROR_MESSAGE);
		  }
		}
	}


}