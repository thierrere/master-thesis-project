package test_Approche

import java.awt._
import java.util.ArrayList
import javax.swing._

class Editeur {

	var cheminPdf:String="";
	var T=new Arbre();
	var noeudCourant=new Arbre();
	var Dessin2=new AuteurTree();
	var aut:Auteur=null;
	var ea:EditeurAssoc=null;

	//Artefact
	var article:Article=null
	var accuse:Accuse=null
	var decisionA:Decision=null
	
	//JMenuBar pour l'interface
	var Menu:JMenuBar=new JMenuBar();
	var  menu:JMenu=new JMenu("Action");
	val ident:JMenuItem=new JMenuItem("Identifiaction"); 
	val r�init:JMenuItem=new JMenuItem("R�initialiser l'arborescence");
	menu.add(ident);menu.addSeparator();menu.add(r�init);
	Menu.add(menu);
	
	//ScrollPanes pour les JTextArea
	var scrpane,scrpane2:JScrollPane=null;

	//Boutons pour l'interface
	val valide=new JButton("Valide");
	val refuse=new JButton("Refuse");
	val delegation=new JButton("D�l�gation");
	val decision=new JButton("Envoyer d�cision");
	val modif=new JButton("Envoyer correction");
	val accepte=new JButton("Accepte");
	val rejete=new JButton("Rejete");
	val accsmodif=new JButton("Accept� Si Modif");
	val parcours=new JButton("Parcourir...");
	val ouvrir=new JButton("Ouvrir")

	//initialisation des �l�ments du formulaire 1 pour envoyer accus�
	val textAc=new JLabel("Message texte: ");val ttextAc=new JTextArea(5,15);ttextAc.setLineWrap(true);
	val form1=new JPanel();
	scrpane=new JScrollPane(ttextAc);
	form1.add(textAc);form1.add(scrpane);//form1.add(ttextAc);
	form1.add(valide);form1.add(refuse);
	//form1.setSize(200,200);

	//initialisation des �l�ments du formulaire 2 pour d�l�gation
	val namE2=new JLabel("Editeur: ");val tnamE2=new JTextField(10);
	val form2=new JPanel();
	form2.add(namE2);form2.add(tnamE2);form2.add(delegation);
	//form2.setSize(200,200);

	//initialisation des �l�ments du formulaire 3 pour envoy� d�cision
	val namE=new JLabel("Editeur: ");
	val suggest=new JLabel("Suggestions et observations: ");
	val tnamE=new JTextField(10);;val tsuggest=new JTextField(10);
	val form3=new JPanel(new GridLayout(6,2));
	form3.add(namE);form3.add(tnamE);form3.add(suggest);form3.add(tsuggest);
	form3.add(accepte);form3.add(rejete);form3.add(accsmodif);
	//form3.setSize(200,200);

	//initialisation des �l�ments du formulaire 4 pour envoy� correction
	val namE3=new JLabel("Message texte: ");val tnamE3=new JTextField(10);
	val form4=new JPanel();
	form4.add(namE3);form4.add(tnamE3);form4.add(modif);
	//form4.setSize(200,200);

	//Panneau des messages
	var pane2=new JPanel();
	pane2.setAutoscrolls(true);

	//Tabbed Pane
	val tpane=new JTabbedPane();
	tpane.add("Envoy� accus�",form1);
	tpane.add("D�l�gation",form2);
	tpane.add("Envoy� d�cision",form3);
	tpane.add("Envoy� correction",form4);
	tpane.add("Messages",pane2);
	//tpane.setSize(500,300);

	val pane1=new JPanel(new GridLayout(15,1));
	/*pane1.add(valide);pane1.add(refuse);pane1.add(delegation);//pane1.add(decision);
		pane1.add(modif);pane1.add(accepte);pane1.add(rejete);pane1.add(accsmodif);*/
	var pangen=new JPanel(new BorderLayout());
	//pangen.add(tpane,BorderLayout.WEST);pangen.add(Dessin2,BorderLayout.EAST);

	pangen.add(Dessin2,BorderLayout.WEST);

	//Split Pane
	var jpane=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,tpane,pangen);
	//initialisation de l'�couteur
	val ecouteur=new EcouteurEditeur(this);
	valide.addActionListener(ecouteur);
	refuse.addActionListener(ecouteur);
	delegation.addActionListener(ecouteur);
	decision.addActionListener(ecouteur);
	modif.addActionListener(ecouteur);
	accepte.addActionListener(ecouteur);
	accsmodif.addActionListener(ecouteur);
	rejete.addActionListener(ecouteur);
	r�init.addActionListener(ecouteur);
	ouvrir.addActionListener(ecouteur);

	val fen=new JFrame("Espace de travail �diteur");
	fen.setContentPane(jpane);
	//fen.pack();
	fen.setJMenuBar(Menu);
	fen.setSize(650,300);
	fen.setVisible(true);
	
	def parseur(operateur:String, artefact:Artefact){
		// TODO Auto-generated method stub		
		if(operateur.equals("soumission")){
			
			if(noeudCourant.getValeur.equals("Refuse")||(noeudCourant.getValeur.equals(""))){
			
			article=artefact.asInstanceOf[Article];
			cheminPdf=article.getPath();
			System.out.println("Noeud courant: "+noeudCourant.getValeur());
			//Construction de l'interface message
			tpane.remove(pane2);
			pane2=new JPanel();
			val t1:JLabel=new JLabel("Nouvel article re�u");
			val t11:JLabel=new JLabel("    ");
			val t2:JLabel=new JLabel("Date: "+article.getDate());
			val t3=new JLabel("                   ");
			//val t31:JLabel=new JLabel("Titre: "+article.getTitle());
			val t4:JLabel=new JLabel("R�sum�: ");
			val t5:JTextArea=new JTextArea(5,20);
			t5.setAutoscrolls(true);t5.setLineWrap(true);
			scrpane2=new JScrollPane(t5);
			t5.setText(" "+article.getResume());
			pane2.add(t1);pane2.add(t11);pane2.add(t2);pane2.add(t3);//pane2.add(t31);
			pane2.add(t4);pane2.add(scrpane2);pane2.add(ouvrir);	
			tpane.add("Messages",pane2);
			this.soumission();
			}
			else{
			  return;
			}
		}
		if(operateur.equals("valide")){
		  if (!noeudCourant.getValeur.equals("Delegation")){
			   return;
			}
			accuse=artefact.asInstanceOf[Accuse];
			System.out.println("Noeud courant: "+noeudCourant.getValeur());
			//Construction de l'interface message
			tpane.remove(pane2);
			pane2=new JPanel();
			val t1:JLabel=new JLabel("Accus� re�u");
			val t11:JLabel=new JLabel("                 ");
			val t2:JLabel=new JLabel("Date: ");
			val t3:JLabel=new JLabel(accuse.getDate());
			val t4:JLabel=new JLabel("Texte: ");
			val t5:JTextArea=new JTextArea(5,20);
			scrpane2=new JScrollPane(t5);
			t5.setText(" "+accuse.getText());t5.setLineWrap(true);
			pane2.add(t1);pane2.add(t11);pane2.add(t2);pane2.add(t3);pane2.add(t4);pane2.add(scrpane2)//pane2.add(t5);	
			tpane.add("Messages",pane2);
			this.accuse("Valide");
		}
		if(operateur.equals("refuse")){
		   if (!noeudCourant.getValeur.equals("Delegation")){
			   return;
			}
			accuse=artefact.asInstanceOf[Accuse];
			System.out.println("Noeud courant: "+noeudCourant.getValeur());
			//Construction de l'interface message
			tpane.remove(pane2);
			pane2=new JPanel();
			val t1:JLabel=new JLabel("Accus� re�u");
			val t11:JLabel=new JLabel("                 ");
			val t2:JLabel=new JLabel("Date: ");
			val t3:JLabel=new JLabel(accuse.getDate());
			val t4:JLabel=new JLabel("Texte: ");
			val t5:JTextArea=new JTextArea(5,20);		
			scrpane2=new JScrollPane(t5);
			t5.setText(" "+accuse.getText());t5.setLineWrap(true);
			pane2.add(t1);pane2.add(t11);pane2.add(t2);pane2.add(t3);pane2.add(t4);pane2.add(scrpane2)//pane2.add(t5);	
			tpane.add("Messages",pane2);
			this.accuse("Refuse");
		}
		if(operateur.equals("rejete")){
		  if (!noeudCourant.getValeur.equals("Accuse")){
			   return;
			}
			val dec=artefact.asInstanceOf[Decision];
			decisionA=new Decision();
			if(dec!=null){
			  decisionA.setRelecteur(dec.getRelecteur);
			  decisionA.setDecision(dec.getDecision);
			}
			System.out.println("Noeud courant: "+noeudCourant.getValeur());
			JOptionPane.showMessageDialog (
					this.pangen, "Reception du rapport de l'�diteur associ�!!", 
					"Traitement �diteur associ�", JOptionPane.OK_OPTION);
			tpane.remove(pane2);
			pane2=new JPanel();
			val t1:JLabel=new JLabel("D�cision re�ue");
			val t11:JLabel=new JLabel("     ");
			val t2:JLabel=new JLabel("Date: ");
			val t3:JLabel=new JLabel(" "+decisionA.getDate());
			val t4:JLabel=new JLabel("Article "+decisionA.getDecision+" pour publication");
			//val t5:JTextArea=new JTextArea(5,10);
			pane2.add(t1);pane2.add(t11);pane2.add(t2);pane2.add(t3);pane2.add(t4);
			tpane.add("Messages",pane2);
			this.etat("Rejete");
		}
		if(operateur.equals("accepte")){
			val dec=artefact.asInstanceOf[Decision];
			decisionA=new Decision();
			if(dec!=null){
			  decisionA.setRelecteur(dec.getRelecteur);
			  decisionA.setDecision(dec.getDecision);
			}
			System.out.println("Noeud courant: "+noeudCourant.getValeur());	
			JOptionPane.showMessageDialog (
					this.pangen, "Reception du rapport de l'�diteur associ�!!", 
					"Traitement �diteur associ�", JOptionPane.OK_OPTION);
			tpane.remove(pane2);
			pane2=new JPanel();
			val t1:JLabel=new JLabel("D�cision re�ue");
			val t11:JLabel=new JLabel("     ");
			val t2:JLabel=new JLabel("Date: ");
			val t3:JLabel=new JLabel(" "+decisionA.getDate());
			val t4:JLabel=new JLabel("Article "+decisionA.getDecision+" pour publication");
			//val t5:JTextArea=new JTextArea(5,10);
			pane2.add(t1);pane2.add(t11);pane2.add(t2);pane2.add(t3);pane2.add(t4);
			tpane.add("Messages",pane2);
			if(noeudCourant.getValeur().equals("Accuse"))this.etat("Accepte");
			else {
			  if (!noeudCourant.getValeur.equals("AccSModif")){
				  return;
			   }
			  this.correction("Accepte");
			}
		}
	 if(operateur.equals("accsmodif")){
			System.out.println("Noeud courant: "+noeudCourant.getValeur());
			if(noeudCourant.getValeur().equals("Accuse")){
				val dec=artefact.asInstanceOf[Decision];
			decisionA=new Decision();
			if(dec!=null){
			  decisionA.setRelecteur(dec.getRelecteur);
			  decisionA.setDecision(dec.getDecision);
			}
				tpane.remove(pane2);
			pane2=new JPanel();
			val t1:JLabel=new JLabel("D�cision re�ue");
			val t11:JLabel=new JLabel("     ");
			val t2:JLabel=new JLabel("Date: ");
			val t3:JLabel=new JLabel(" "+decisionA.getDate());
			val t4:JLabel=new JLabel("Article "+decisionA.getDecision+" pour publication");
			//val t5:JTextArea=new JTextArea(5,10);
			pane2.add(t1);pane2.add(t11);pane2.add(t2);pane2.add(t3);pane2.add(t4);
			tpane.add("Messages",pane2);
				this.etat("AccSModif");
				JOptionPane.showMessageDialog (
						this.pangen, "Reception du rapport de l'�diteur associ�!!", 
						"Traitement �diteur associ�", JOptionPane.OK_OPTION);
			}
			else if(noeudCourant.getValeur().equals("AccSModif")) {
			  article=artefact.asInstanceOf[Article];
			  cheminPdf=article.getPath();
				this.modification();
				JOptionPane.showMessageDialog (
						this.pangen, "Reception des corrections de l'auteur!!", 
						"Traitement �diteur associ�", JOptionPane.OK_OPTION);
				tpane.remove(pane2);
			pane2=new JPanel();
			val t1:JLabel=new JLabel("Modification article re�u");
			val t11:JLabel=new JLabel("    ");
			val t2:JLabel=new JLabel("Date: ");
			val t3:JLabel=new JLabel(" "+article.getDate());
			val t4:JLabel=new JLabel("R�sum�: ");
			val t5:JTextArea=new JTextArea(5,20);
			scrpane2=new JScrollPane(t5);
			t5.setText(" "+article.getResume());t5.setLineWrap(true);
			pane2.add(t1);pane2.add(t11);pane2.add(t2);pane2.add(t3);pane2.add(t4);pane2.add(scrpane2);pane2.add(ouvrir);	
			tpane.add("Messages",pane2);
			}
			else {
			  if (!noeudCourant.getValeur.equals("AccSModif")){
				  return;
			   }
				val dec=artefact.asInstanceOf[Decision];
			decisionA=new Decision();
			if(dec!=null){
			  decisionA.setRelecteur(dec.getRelecteur);
			  decisionA.setDecision(dec.getDecision);
			}
				tpane.remove(pane2);
			pane2=new JPanel();
			val t1:JLabel=new JLabel("D�cision re�ue");
			val t11:JLabel=new JLabel("     ");
			val t2:JLabel=new JLabel("Date: ");
			val t3:JLabel=new JLabel(" "+decisionA.getDate());
			val t4:JLabel=new JLabel("Article "+decisionA.getDecision+" pour publication");
			//val t5:JTextArea=new JTextArea(5,10);
			pane2.add(t1);pane2.add(t11);pane2.add(t2);pane2.add(t3);pane2.add(t4);
			tpane.add("Messages",pane2);
				this.correction("AccSModif");
				JOptionPane.showMessageDialog (
						this.pangen, "Reception du rapport de l'�diteur associ�!!", 
						"Traitement �diteur associ�", JOptionPane.OK_OPTION);
			}
		}
		/*if(operateur==""){
			System.out.println("Noeud courant: "+noeudCourant.getValeur());*/

	}

	def soumission(){
	  if(!noeudCourant.getValeur.equals("")){
		if (noeudCourant.getValeur.equals("Refuse")){
			   T=new Arbre();
			}
	  }
		T.setValeur("Soumission");
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
				"Soumission", JOptionPane.OK_OPTION);
		tpane.setSelectedComponent(pane2);
	}
	def envoiAccuse(decision:String){
		if (!noeudCourant.getValeur.equals("Soumission")){
			JOptionPane.showMessageDialog (
				this.pangen, "Accus� re�eption d�j� envoy� ou aucune soumission re�ue!!", 
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
			aut.parseur("valide", accuse);
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
			aut.parseur("refuse",accuse);
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

	def delegationf(){
	  if((noeudCourant.getValeur.equals("Soumission"))&&(noeudCourant.getFilsGauche==null)){
	     JOptionPane.showMessageDialog (
				this.pangen, "Envoy� d'abord un accus� � l'auteur!!", 
				"Erreur d'envoi", JOptionPane.ERROR_MESSAGE);
					return;
		}
	  if((!noeudCourant.getValeur.equals("Soumission"))||(!noeudCourant.getValeur.equals("Non"))){
		//Noeud Courant en Soumission
		var deleg:Arbre=new Arbre("Delegation");
		deleg.setP�re(noeudCourant);
		deleg.setNbreDeFils(1);
		noeudCourant.setFilsDroit(deleg);
		noeudCourant=deleg;
		fen.setVisible(false);
		pangen.remove(Dessin2);
		Dessin2=new AuteurTree();
		Dessin2.parcoursPrefixeTreeNode(this.T,null);
		//fen.add(Dessin2);
		pangen.add(Dessin2,BorderLayout.EAST);
		fen.setVisible(true);
		ea.parseur("delegation", article);
		System.out.println("Editeur: D�l�gation effectu�e");
	  }
	  else{
		  JOptionPane.showMessageDialog (
				this.pangen, "Aucun Article re�u ou � d�l�guer!!", 
				"Erreur d'envoi", JOptionPane.ERROR_MESSAGE);
					return;
		}
	  
	}

	def accuse(decision:String){
		//Noeud Courant en d�l�gation
		var accu:Arbre=new Arbre("Accuse");
		accu.setP�re(noeudCourant);
		if(decision.equals("Valide")){
			accu.setNbreDeFils(2);
			//System.out.println("Article accept� de traitement");
			var a:Arbre=new Arbre("Oui");
			a.setP�re(accu);
			a.setNbreDeFils(0);
			accu.setFilsGauche(a);
			noeudCourant.setFilsGauche(accu);
			noeudCourant=accu;
			fen.setVisible(false);
			pangen.remove(Dessin2);
			Dessin2=new AuteurTree();
			Dessin2.parcoursPrefixeTreeNode(this.T,null);
			//fen.add(Dessin2);
			pangen.add(Dessin2,BorderLayout.EAST);
			fen.setVisible(true);
			JOptionPane.showMessageDialog (
					this.pangen, "Reception de l'accus� de l'�diteur associ�!!", 
					"Accus� reception", JOptionPane.OK_OPTION);
			System.out.println("Editeur: Accus� recu");
			tpane.setSelectedComponent(pane2);
		}
		else{
			//article Refuse de traitement
			accu.setNbreDeFils(1);
			System.out.println("Article refus� de traitement");
			var a:Arbre=new Arbre("Non");
			a.setP�re(accu);
			a.setNbreDeFils(1);
			accu.setFilsGauche(a);
			noeudCourant.setFilsGauche(accu);
			noeudCourant=a;
			fen.setVisible(false);
			pangen.remove(Dessin2);
			Dessin2=new AuteurTree();
			Dessin2.parcoursPrefixeTreeNode(this.T,null);
			//fen.add(Dessin2);
			pangen.add(Dessin2,BorderLayout.EAST);
			fen.setVisible(true);
			this.parcoursPrefixe(T);
			JOptionPane.showMessageDialog (
					this.pangen, "Reception de l'accus� de l'�diteur associ�!!", 
					"Accus� reception", JOptionPane.OK_OPTION);
			System.out.println("Accus� recu");
			tpane.setSelectedComponent(pane2);
			return;
		}
	}

	def etat(decision:String){
		var etat:Arbre=new Arbre("Etat");
		etat.setNbreDeFils(1);
		etat.setP�re(noeudCourant);
		noeudCourant.setFilsDroit(etat);
		noeudCourant=etat;
		if(decision.equals("Accepte")){
			System.out.println("Article accept�");
			var a:Arbre=new Arbre("Accepte");
			a.setP�re(etat);
			etat.setFilsGauche(a);
			a.setNbreDeFils(0);
			fen.setVisible(false);
			pangen.remove(Dessin2);
			Dessin2=new AuteurTree();
			Dessin2.parcoursPrefixeTreeNode(this.T,null);
			//fen.add(Dessin2);
			pangen.add(Dessin2,BorderLayout.EAST);
			fen.setVisible(true);
			this.parcoursPrefixe(T);
			tpane.setSelectedComponent(pane2);
			return;
		}
		else if(decision.equals("Rejete")){
			System.out.println("Article rejet�");
			var a:Arbre=new Arbre("Rejete");
			a.setP�re(etat);
			etat.setFilsGauche(a);
			a.setNbreDeFils(0);
			fen.setVisible(false);
			pangen.remove(Dessin2);
			Dessin2=new AuteurTree();
			Dessin2.parcoursPrefixeTreeNode(this.T,null);
			//fen.add(Dessin2);
			pangen.add(Dessin2,BorderLayout.EAST);
			fen.setVisible(true);
			this.parcoursPrefixe(T);
			tpane.setSelectedComponent(pane2);
			return;
		}
		else{
			//Article Accept� Si Modifications apport�es
			System.out.println("Article accept� si modifi�");
			var a:Arbre=new Arbre("AccSModif");
			a.setP�re(etat);
			etat.setFilsGauche(a);
			a.setNbreDeFils(2);
			noeudCourant=a;
			fen.setVisible(false);
			pangen.remove(Dessin2);
			Dessin2=new AuteurTree();
			Dessin2.parcoursPrefixeTreeNode(this.T,null);
			//fen.add(Dessin2);
			pangen.add(Dessin2,BorderLayout.EAST);
			fen.setVisible(true);
			System.out.println("Premi�re D�cision recue");
			tpane.setSelectedComponent(pane2);
			return;

		}
	}
	
	def envoiDecision(decision:String){
	  println("noeudcourant: "+noeudCourant.getValeur);
	    if((noeudCourant.getValeur.equals("Etat"))||((noeudCourant.getValeur.equals("AccSModif")))){
		decisionA.setEditeur(tnamE.getText());
		if(decision.equals("Accepte")){
			System.out.println("Article accept�");
			this.aut.parseur("accepte",decisionA);
			return;
		}
		else if(decision.equals("Rejete")){
			System.out.println("Article rejet�");
			this.aut.parseur("rejete",decisionA);
			return;
		}
		else{
			//Article Accept� Si Modifications apport�es
			System.out.println("Article accept� si modifi�");
			this.aut.parseur("accsmodif",decisionA);
			return;

		}
	    }
	    else{
	     JOptionPane.showMessageDialog (
				this.pangen, "Aucun article trait�e/Attente de la d�cision de l'�diteur associ�!!", 
				"Erreur d'envoi", JOptionPane.ERROR_MESSAGE);
					return;
		}
	}

	def modification(){
		var a:Arbre=new Arbre("Modification");
		a.setP�re(noeudCourant);
		a.setNbreDeFils(0);
		noeudCourant.setFilsGauche(a);
		fen.setVisible(false);
		pangen.remove(Dessin2);
		Dessin2=new AuteurTree();
		Dessin2.parcoursPrefixeTreeNode(this.T,null);
		//fen.add(Dessin2);
		pangen.add(Dessin2,BorderLayout.EAST);
		fen.setVisible(true);
		fen.setVisible(true);
		/*JOptionPane.showMessageDialog (
				this.pangen, "Reception des modifications de l'article en cours de taitement!!", 
				"Modification", JOptionPane.OK_OPTION);*/
		tpane.setSelectedComponent(pane2);
	}

	def envoiModification(){
	   if(!noeudCourant.getValeur.equals("AccSModif")){
	     JOptionPane.showMessageDialog (
				this.pangen, "Aucune modification re�ue!!", 
				"Erreur d'envoi", JOptionPane.ERROR_MESSAGE);
					return;
		}
		this.ea.parseur("modification",article);
		
	}

	def correction(decision:String){
		var corr:Arbre=new Arbre("Correction");
		corr.setNbreDeFils(1);
		corr.setP�re(noeudCourant);
		noeudCourant.setFilsDroit(corr);
		if(decision.equals("Accepte")){
			System.out.println("Article accept�");
			var a:Arbre=new Arbre("Accepte");
			a.setP�re(corr);
			corr.setFilsGauche(a);
			a.setNbreDeFils(0);
			fen.setVisible(false);
			pangen.remove(Dessin2);
			Dessin2=new AuteurTree();
			Dessin2.parcoursPrefixeTreeNode(this.T,null);
			//fen.add(Dessin2);
			pangen.add(Dessin2,BorderLayout.EAST);
			fen.setVisible(true);
			this.parcoursPrefixe(T);
			tpane.setSelectedComponent(pane2);
			return;
		}
		else{
			//Article Accept� Si Modifications apport�es
			System.out.println("Article accept� si modifi�");
			var a:Arbre=new Arbre("AccSModif");
			a.setP�re(corr);
			corr.setFilsGauche(a);
			a.setNbreDeFils(2);
			noeudCourant=a;
			fen.setVisible(false);
			pangen.remove(Dessin2);
			Dessin2=new AuteurTree();
			Dessin2.parcoursPrefixeTreeNode(this.T,null);
			//fen.add(Dessin2);
			pangen.add(Dessin2,BorderLayout.EAST);
			fen.setVisible(true);
			System.out.println("Autre D�cision recue");
			tpane.setSelectedComponent(pane2);
			return;
		}
	}

	def parcoursPrefixe(a:Arbre){
		if (a == null) 	return;
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