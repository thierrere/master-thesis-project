package test_Approche

import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JOptionPane
import java.io.FileNotFoundException
import java.io.IOException
import java.awt.Desktop
import java.io.File

class EcouteurReview extends ActionListener{
  
  var review:Relecteur=null;
	
	def this(rew:Relecteur){ this(); this.review=rew;}
	
	def actionPerformed(e:ActionEvent) {
		// TODO Auto-generated method stub
		var source:Object=e.getSource();
		
		if(source.equals(review.valide)){
			System.out.println("Bouton valide cliqué");
			review.envoiAccuse("Valide");
		}

		if(source.equals(review.refuse)){
			System.out.println("bouton accsmodif");
			review.envoiAccuse("Refuse");
		}
		if(source.equals(review.accepte)){
			System.out.println("bouton accepté");
			review.etat("Accepte");
		}
		if(source.equals(review.accsmodif)){
			System.out.println("bouton accepté");
			review.etat("AccSModif");
		}
		if(source.equals(review.rejete)){
			System.out.println("bouton accepté");
			review.etat("Rejete");
		}
		if(source.equals(review.réinit)){
		  review.réinitAll();
		}
		if(source.equals(review.ouvrir)){
		  try{
		  	val fileM=new File(review.cheminPdf);
		  	Desktop.getDesktop().open(fileM);
		  }
		  catch{
		    case ex: FileNotFoundException => JOptionPane.showMessageDialog (
					review.pangen, "Fichier non trouvé!!", 
					"Erreur Fichier", JOptionPane.ERROR_MESSAGE);
		    case ex: IOException => JOptionPane.showMessageDialog (
					review.pangen, "Problème avec l'ouverture du fichier!!", 
					"Erreur Fichier", JOptionPane.ERROR_MESSAGE);
		  }
		}
	}

}