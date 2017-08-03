package test_Approche

import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JOptionPane
import java.io.IOException
import java.io.FileNotFoundException
import java.awt.Desktop
import java.io.File

class EcouteurEA extends ActionListener{
  
  var eassoc:EditeurAssoc=null;

	def this(ea:EditeurAssoc){this(); this.eassoc=ea;}

	def actionPerformed(e:ActionEvent) {
		// TODO Auto-generated method stub
		var source:Object=e.getSource();

		if(source.equals(eassoc.valide)){
			System.out.println("Bouton valide cliqué");
			eassoc.envoiAccuse("Valide");
		}

		if(source.equals(eassoc.refuse)){
			System.out.println("bouton accsmodif");
			eassoc.envoiAccuse("Refuse");
		}
		if(source.equals(eassoc.review)){
			System.out.println("bouton accsmodif");
			eassoc.reviewf();
		}
		if(source.equals(eassoc.accepte)){
			System.out.println("bouton accepté");
			eassoc.envoiDecision("Accepte");
		}
		if(source.equals(eassoc.accsmodif)){
			System.out.println("bouton accepté");
			eassoc.envoiDecision("AccSModif");
		}
		if(source.equals(eassoc.rejete)){
			System.out.println("bouton accepté");
			eassoc.envoiDecision("Rejete");
		}
		if(source.equals(eassoc.modif)){
			System.out.println("bouton accsmodif");
			eassoc.envoiModification();
		}
		if(source.equals(eassoc.réinit)){
		  eassoc.réinitAll();
		}
		if(source.equals(eassoc.ouvrir)){
		  try{
		  	val fileM=new File(eassoc.cheminPdf);
		  	Desktop.getDesktop().open(fileM);
		  }
		  catch{
		    case ex: FileNotFoundException => JOptionPane.showMessageDialog (
					eassoc.pangen, "Fichier non trouvé!!", 
					"Erreur Fichier", JOptionPane.ERROR_MESSAGE);
		    case ex: IOException => JOptionPane.showMessageDialog (
					eassoc.pangen, "Problème avec l'ouverture du fichier!!", 
					"Erreur Fichier", JOptionPane.ERROR_MESSAGE);
		  }

		}
	}
  

}