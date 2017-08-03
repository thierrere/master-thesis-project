package test_Approche

import java.awt.event._
import java.io.File
import javax.swing._
import javax.swing.filechooser.FileNameExtensionFilter

class PdfSelectAuteur extends JFrame with ActionListener{
      
      
        var filtre:FileNameExtensionFilter=new FileNameExtensionFilter("Document Pdf *.Pdf","Pdf");
    	var choose:JFileChooser=new JFileChooser(file);;
    	var i=0;
    	var file=new File("Mes Documents");
    	var cheminRetour="";
    	var aut:Auteur=null;
    	var fenParent:JFrame=null;
  
  def this(a:Auteur){
	  	this();		
		aut=a;
		fenParent=a.fen;
		choose=new JFileChooser(file);
		filtre=new FileNameExtensionFilter("Document Pdf *.Pdf", "Pdf");
		choose.setFileFilter(filtre);
		PdfSelectAuteur.this.setContentPane(choose);
		PdfSelectAuteur.this.setSize(600, 300);
		PdfSelectAuteur.this.setResizable(false);
		PdfSelectAuteur.this.setAlwaysOnTop(true);
		choose.addActionListener(PdfSelectAuteur.this);
		this.setVisible(true);
  }
  
  def actionPerformed(e:ActionEvent) {
	  var cmd:String = e.getActionCommand();
		if (cmd ==choose.cancelSelection()) {
			System.out.println("cancel");
			fenParent.setEnabled(true);
			PdfSelectAuteur.this.setVisible(false);
		}
		else if (cmd.equals(choose.approveSelection())) {
			System.out.println("ok"); 
			var fl:File=choose.getSelectedFile();
			if(!filtre.accept(fl)){
				JOptionPane.showConfirmDialog(PdfSelectAuteur.this, " Entrez l'extension '.pdf'  " ,"    Erreur  " ,JOptionPane.PLAIN_MESSAGE);;
			}
			else{
				if(!fl.canRead()){
					JOptionPane.showConfirmDialog(PdfSelectAuteur.this, " Entrez un autre nom de fichier '.pdf'  " ,"    Erreur  " ,JOptionPane.PLAIN_MESSAGE);;
				}
				else{
					System.out.println(" "+fl.getName()+" "+fl.getAbsolutePath());
					cheminRetour=fl.getAbsolutePath();
					System.out.println("Chemin retour: "+cheminRetour);
					/*aut.cheminRetour=cheminRetour;
					System.out.println("Chemin retour de aut: "+aut.cheminRetour);*/
					aut.tart.setText(cheminRetour);
					PdfSelectAuteur.this.setVisible(false);
					choose.setVisible(false);
					fenParent.setEnabled(true);
				}

			}

		}
    
  }

}