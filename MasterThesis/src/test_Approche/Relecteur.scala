package test_Approche

import java.awt._
import java.util.ArrayList;
import javax.swing._

class Relecteur {

  var cheminPdf:String="";
  var T=new Arbre();
  var noeudCourant=new Arbre();
  var Dessin2=new AuteurTree();
   var ea:EditeurAssoc=null
   
  //Artefact
	var article:Article=null
	var accuse:Accuse=null
	var decisionA:Decision=null
	
	//JMenuBar pour l'interface
	var Menu:JMenuBar=new JMenuBar();
	var menu:JMenu=new JMenu("Action");
	val ident:JMenuItem=new JMenuItem("Identifiaction"); 
	val r�init:JMenuItem=new JMenuItem("R�initialiser l'arborescence");
  	menu.add(ident);menu.addSeparator();menu.add(r�init);
	Menu.add(menu);
	
	//ScrollPanes pour les JTextArea
	var scrpane,scrpane2:JScrollPane=null;
	
	//Boutons pour l'interface
	val valide=new JButton("Valide");
	val refuse=new JButton("Refuse");
	val decision=new JButton("Envoyer rapport de Lecture");
	val accepte=new JButton("Accepte");
	val rejete=new JButton("Rejete");
	val accsmodif=new JButton("Accept� Si Modif");
	val parcours=new JButton("Parcourir...");
	val ouvrir=new JButton("Ouvrir")
	
	//initialisation des �l�ments du formulaire 1 pour envoy� accus�
	val	textAc=new JLabel("Message texte: ");val ttextAc=new JTextArea(5,15);ttextAc.setLineWrap(true);
	val	form1=new JPanel();
		scrpane=new JScrollPane(ttextAc);
		form1.add(textAc);form1.add(scrpane);//form1.add(ttextAc);
		form1.add(valide);form1.add(refuse);

		//initialisation des �l�ments du formulaire 2 pour review/Les d�cisions
	val	namEA=new JLabel("Relecteur: ");
	val	suggest=new JLabel("Suggestions et observations: ");
	val	tnameEA=new JTextField(10);val tsuggest=new JTextField(10);
	val	form2=new JPanel(new GridLayout(6,2));
		form2.add(namEA);form2.add(tnameEA);form2.add(suggest);form2.add(tsuggest);
		form2.add(accepte);form2.add(rejete);form2.add(accsmodif);

		//Panneau des messages
	var	pane2=new JPanel();
		pane2.setAutoscrolls(true);
		
		//Tabbed Pane
	val	tpane=new JTabbedPane();
		tpane.add("Envoy� accus�",form1);
		tpane.add("Review",form2);
		tpane.add("Messages",pane2);

	//val	pane1=new JPanel(new GridLayout(20,1));
		/*pane1.add(valide);pane1.add(refuse); //pane1.add(decision);
		pane1.add(accepte);pane1.add(rejete);pane1.add(accsmodif);*/
	val	pangen=new JPanel(new BorderLayout());
		//pangen.add(tpane,BorderLayout.WEST);
		pangen.add(Dessin2,BorderLayout.WEST);

		//Split Pane
	val	jpane=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,tpane,pangen);
	
	val ecouteur=new EcouteurReview(this);
		valide.addActionListener(ecouteur);
		refuse.addActionListener(ecouteur);
		decision.addActionListener(ecouteur);
		accepte.addActionListener(ecouteur);
		accsmodif.addActionListener(ecouteur);
		rejete.addActionListener(ecouteur);
		r�init.addActionListener(ecouteur);
		ouvrir.addActionListener(ecouteur);

	val	fen=new JFrame("Espace de travail relecteur");
		fen.setContentPane(jpane);
		//fen.pack();
		fen.setJMenuBar(Menu);
		fen.setSize(650,300);
		fen.setVisible(true);
		
		def parseur(operateur:String, artefact:Artefact){
		// TODO Auto-generated method stub		
		if(operateur.equals("review")){
		  if(noeudCourant.getValeur.equals("Refuse")||(noeudCourant.getValeur.equals(""))){
			System.out.println("Noeud courant: "+noeudCourant.getValeur());
			article=artefact.asInstanceOf[Article];
			cheminPdf=article.getPath();
			tpane.remove(pane2);
			pane2=new JPanel(); pane2.setAutoscrolls(true);
			val t1:JLabel=new JLabel("Nouvel article re�u");
			val t11:JLabel=new JLabel("    ");
			val t2:JLabel=new JLabel("Date: "+article.getDate());
			val t3:JLabel=new JLabel("                        ");
			//val t31:JLabel=new JLabel("Titre: "+article.getTitle());
			val t4:JLabel=new JLabel("R�sum�: ");
			val t5:JTextArea=new JTextArea(5,20);
			t5.setAutoscrolls(true);t5.setLineWrap(true);
			scrpane2=new JScrollPane(t5);
			t5.setText(" "+article.getResume());
			pane2.add(t1);pane2.add(t11);pane2.add(t2);pane2.add(t3);//pane2.add(t31);
			pane2.add(t4);pane2.add(scrpane2);pane2.add(ouvrir);	
			tpane.add("Messages",pane2);
			this.review();
			//this.delegation();
			}
		  else{
		    return;
		  }
		}
		if(operateur.equals("modification")){
		   if (!noeudCourant.getValeur.equals("AccSModif")){
			   return;
			}
			System.out.println("Noeud courant: "+noeudCourant.getValeur());
			article=artefact.asInstanceOf[Article];
			cheminPdf=article.getPath();
			tpane.remove(pane2);
			pane2=new JPanel();pane2.setAutoscrolls(true);
			val t1:JLabel=new JLabel("Modification de l'article re�u");
			val t11:JLabel=new JLabel("    ");
			val t2:JLabel=new JLabel("Date: "+article.getDate());
			val t3:JLabel=new JLabel("                        ");
			val t4:JLabel=new JLabel("R�sum�: ");
			val t5:JTextArea=new JTextArea(5,20);t5.setLineWrap(true);	
			scrpane2=new JScrollPane(t5);
			t5.setText(" "+article.getResume());
			pane2.add(t1);pane2.add(t11);pane2.add(t2);pane2.add(t3);pane2.add(t4);pane2.add(scrpane2);pane2.add(ouvrir);	
			tpane.add("Messages",pane2);
			this.accsmodiff();
		}
		/*if(operateur==""){
			System.out.println("Noeud courant: "+noeudCourant.getValeur());*/
	}

	def review(){
	  if(!noeudCourant.getValeur.equals("")){
		  if (noeudCourant.getValeur.equals("Refuse")){
		   T=new Arbre();
	   	}
	  }
		T.setValeur("Review");
		T.setP�re(null);
		T.setNbreDeFils(2);
		noeudCourant=T;
		fen.setVisible(false);
		pangen.remove(Dessin2);
		Dessin2=new AuteurTree();
		Dessin2.parcoursPrefixeTreeNode(this.T,null);
		//fen.add(Dessin2);
		pangen.add(Dessin2,BorderLayout.EAST);
		fen.setVisible(true);
		JOptionPane.showMessageDialog (
				this.pangen, "Nouvel article re�u!!", 
				"Relecture", JOptionPane.OK_OPTION);
		tpane.setSelectedComponent(pane2);
	}
	def envoiAccuse(decision:String){
	  if (!noeudCourant.getValeur.equals("Review")){
			JOptionPane.showMessageDialog (
				this.pangen, "Aucun article recue!!", 
				"Erreur d'envoi", JOptionPane.ERROR_MESSAGE);
			return;
	   	}
		//Construction de l'artefact accuse
		accuse=new Accuse();
		accuse.setText(ttextAc.getText());
		//Noeud Courant en soumission
		if(decision.equals("Valide")){
			var a:Arbre=new Arbre(decision);
			a.setP�re(noeudCourant);
			a.setNbreDeFils(0);
			noeudCourant.setFilsGauche(a);
			ea.parseur("valide",accuse);
			fen.setVisible(false);
			pangen.remove(Dessin2);
			Dessin2=new AuteurTree();
			Dessin2.parcoursPrefixeTreeNode(this.T,null);
			//fen.add(Dessin2);
			pangen.add(Dessin2,BorderLayout.EAST);
			fen.setVisible(true);
			this.parcoursPrefixe(T);
		}
		else{
			//article Refuse de traitement
			var a:Arbre=new Arbre(decision);
			a.setP�re(noeudCourant);
			a.setNbreDeFils(0);
			noeudCourant.setFilsGauche(a);
			noeudCourant=a;
			ea.parseur("refuse",accuse);
			fen.setVisible(false);
			pangen.remove(Dessin2);
			Dessin2=new AuteurTree();
			Dessin2.parcoursPrefixeTreeNode(this.T,null);
			//fen.add(Dessin2);
			pangen.add(Dessin2,BorderLayout.EAST);
			fen.setVisible(true);
			this.parcoursPrefixe(T);
			return;
		}		
	}

	def etat(decision:String){
		if(noeudCourant.getValeur().equals("AccSModif")){
			this.correction(decision);
			return;
		}
		if((noeudCourant.getValeur.equals("Review"))&&(noeudCourant.getFilsGauche==null)){
	     JOptionPane.showMessageDialog (
				this.pangen, "Envoy� d'abord un accus� � l'�diteur associ�!!", 
				"Erreur d'envoi", JOptionPane.ERROR_MESSAGE);
					return;
		}
		if(!noeudCourant.getValeur.equals("Review")){
	     JOptionPane.showMessageDialog (
				this.pangen, "Aucun article en traitement!!", 
				"Erreur d'envoi", JOptionPane.ERROR_MESSAGE);
					return;
		}
		//Construction de l'artefact d�cision
		decisionA=new Decision();
		decisionA.setRelecteur(tnameEA.getText());
		decisionA.setObservaSuggest(tsuggest.getText());
		
		var etat:Arbre=new Arbre("etat");
		etat.setNbreDeFils(1);
		etat.setP�re(noeudCourant);
		noeudCourant.setFilsDroit(etat);
		//noeudCourant=etat;
		if(decision.equals("Accepte")){
			decisionA.setDecision("Accepte");
			System.out.println("Relecteur: Article accept�");
			var a:Arbre=new Arbre("Accepte");
			a.setP�re(etat);
			etat.setFilsGauche(a);
			a.setNbreDeFils(0);
			decisionA.setDecision("Accepte")
			ea.parseur("etat",decisionA);
			fen.setVisible(false);
			pangen.remove(Dessin2);
			Dessin2=new AuteurTree();
			Dessin2.parcoursPrefixeTreeNode(this.T,null);
			//fen.add(Dessin2);
			pangen.add(Dessin2,BorderLayout.EAST);
			fen.setVisible(true);
			this.parcoursPrefixe(T);
			return;
		}
		else if(decision.equals("Rejete")){
			decisionA.setDecision("Rejete");
			System.out.println("Article rejet�");
			var a:Arbre=new Arbre("Rejete");
			a.setP�re(etat);
			etat.setFilsGauche(a);
			a.setNbreDeFils(0);
			decisionA.setDecision("Rejete")
			ea.parseur("etat",decisionA);
			fen.setVisible(false);
			pangen.remove(Dessin2);
			Dessin2=new AuteurTree();
			Dessin2.parcoursPrefixeTreeNode(this.T,null);
			//fen.add(Dessin2);
			pangen.add(Dessin2,BorderLayout.EAST);
			fen.setVisible(true);
			this.parcoursPrefixe(T);
			return;
		}
		else{
			//Article Accept� Si Modifications apport�es
			decisionA.setDecision("AccSModif");
			System.out.println("Article accept� si modifi�");
			var a:Arbre=new Arbre("AccSModif");
			a.setP�re(etat);
			etat.setFilsGauche(a);
			a.setNbreDeFils(1);
			decisionA.setDecision("AccSModif")
			ea.parseur("etat",decisionA);
			noeudCourant=a;
			fen.setVisible(false);
			pangen.remove(Dessin2);
			Dessin2=new AuteurTree();
			Dessin2.parcoursPrefixeTreeNode(this.T,null);
			//fen.add(Dessin2);
			pangen.add(Dessin2,BorderLayout.EAST);
			fen.setVisible(true);
			return;
		}
	}
	def accsmodiff(){
		JOptionPane.showMessageDialog (
				this.pangen, "Reception des modifications de l'article en cours de taitement!!", 
				"Modification", JOptionPane.OK_OPTION);
		System.out.println("Premi�re modification recue");
		tpane.setSelectedComponent(pane2);
		return;
	}

	def correction(decision:String){
	  if(!noeudCourant.getValeur.equals("AccSModif")){
	     JOptionPane.showMessageDialog (
				this.pangen, "Aucun article en traitement!!", 
				"Erreur d'envoi", JOptionPane.ERROR_MESSAGE);
					return;
		}
		//Construction de l'artefact decisionA
		decisionA=new Decision();
		decisionA.setRelecteur("");
		decisionA.setObservaSuggest(tsuggest.getText());
		
		var corr:Arbre=new Arbre("Correction");
		corr.setNbreDeFils(1);
		corr.setP�re(noeudCourant);
		noeudCourant.setFilsGauche(corr);
		if(decision.equals("Accepte")){
			decisionA.setDecision("Accepte");
			System.out.println("Article accept�");
			var a:Arbre=new Arbre("Accepte");
			a.setP�re(corr);
			corr.setFilsGauche(a);
			a.setNbreDeFils(0);
			decisionA.setDecision("Accepte")
			ea.parseur("correction",decisionA);
			fen.setVisible(false);
			pangen.remove(Dessin2);
			Dessin2=new AuteurTree();
			Dessin2.parcoursPrefixeTreeNode(this.T,null);
			//fen.add(Dessin2);
			pangen.add(Dessin2,BorderLayout.EAST);
			fen.setVisible(true);
			this.parcoursPrefixe(T);
			return;
		}
		else{
			//Article Accept� Si Modifications apport�es
			decisionA.setDecision("AccSModif");
			System.out.println("Article accept� si modifi�");
			var a:Arbre=new Arbre("AccSModif");
			a.setP�re(corr);
			corr.setFilsGauche(a);
			a.setNbreDeFils(1);
			noeudCourant=a;
			ea.parseur("correction",decisionA);
			fen.setVisible(false);
			pangen.remove(Dessin2);
			Dessin2=new AuteurTree();
			Dessin2.parcoursPrefixeTreeNode(this.T,null);
			//fen.add(Dessin2);
			pangen.add(Dessin2,BorderLayout.EAST);
			fen.setVisible(true);
			return;
		}
	}

	def parcoursPrefixe(a:Arbre){
		if (a == null) return;
		System.out.print(a.getValeur()+" ");
		parcoursPrefixe(a.getFilsGauche());
		parcoursPrefixe(a.getFilsDroit());
	}
	//Fonction pour r�initialiser l'arbre T ou l'arborescence
	def r�initAll (){
		T=new Arbre()
		noeudCourant=new Arbre()
		fen.setVisible(false);
			pangen.remove(Dessin2);
			Dessin2=new AuteurTree();
			Dessin2.parcoursPrefixeTreeNode(this.T,null);
			//fen.add(Dessin2);
			pangen.add(Dessin2,BorderLayout.EAST);
			fen.setVisible(true);
	}

}