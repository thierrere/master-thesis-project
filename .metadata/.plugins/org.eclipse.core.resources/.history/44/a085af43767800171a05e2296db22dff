package test_Approche

import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.io.File
import java.awt.Desktop
import java.io.IOException
import java.io.FileNotFoundException
import javax.swing.JOptionPane


class EcouteurEditeur extends ActionListener{
  
  var editeur:Editeur=null;
	
	def this(editeur:Editeur){ this(); this.editeur=editeur;}
	
	def actionPerformed(e:ActionEvent) {
		// TODO Auto-generated method stub		
		var source:Object=e.getSource();
		
		if(source.equals(editeur.valide)){
			System.out.println("Bouton valide cliqué");
			editeur.envoiAccuse("Valide");
		}

		if(source.equals(editeur.refuse)){
			System.out.println("bouton accsmodif");
			editeur.envoiAccuse("Refuse");
		}
		if(source.equals(editeur.delegation)){
			System.out.println("bouton accsmodif");
			editeur.delegationf();
		}
		if(source.equals(editeur.accepte)){
			System.out.println("bouton accepté");
			editeur.envoiDecision("Accepte");
		}
		if(source.equals(editeur.accsmodif)){
			System.out.println("bouton accepté");
			editeur.envoiDecision("AccSModif");
		}
		if(source.equals(editeur.rejete)){
			System.out.println("bouton accepté");
			editeur.envoiDecision("Rejete");
		}
		if(source.equals(editeur.modif)){
			System.out.println("bouton modif");
			editeur.envoiModification();
			//auteur.ed.modification();
		}
		if(source.equals(editeur.réinit)){
		  editeur.réinitAll();
		}
		if(source.equals(editeur.ouvrir)){
		  try{
		  	val fileM=new File(editeur.cheminPdf);
		  	Desktop.getDesktop().open(fileM);
		  }
		  catch{
		    case ex: FileNotFoundException => JOptionPane.showMessageDialog (
					editeur.pangen, "Fichier non trouvé!!", 
					"Erreur Fichier", JOptionPane.ERROR_MESSAGE);
		    case ex: IOException => JOptionPane.showMessageDialog (
					editeur.pangen, "Problème avec l'ouverture du fichier!!", 
					"Erreur Fichier", JOptionPane.ERROR_MESSAGE);
		  }
									  
		}

	}

}