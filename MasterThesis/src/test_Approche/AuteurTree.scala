package test_Approche

import javax.swing.tree.DefaultMutableTreeNode
import javax.swing.JTree
import javax.swing.JPanel
import javax.swing.JLabel;

class AuteurTree extends JPanel {

		var tree:JTree=null;
		val es=new JLabel("                                                           ");
		add(es);
		setSize(400,400)

		def parcoursPrefixeTreeNode(a:Arbre,pere:DefaultMutableTreeNode){
			if(a==null)return;

			if(pere==null){
			  //Noeud Root
				System.out.print(a.getValeur()+" ");
				val top:DefaultMutableTreeNode=new DefaultMutableTreeNode(a.getValeur());
				parcoursPrefixeTreeNode(a.getFilsGauche(),top);
				parcoursPrefixeTreeNode(a.getFilsDroit(),top);
				tree=new JTree(top);
				add(tree);	
			}
			else{
				//Noeud possédant un père
				System.out.print(a.getValeur()+" ");
				val top1:DefaultMutableTreeNode=new DefaultMutableTreeNode(a.getValeur());
				pere.add(top1);
				parcoursPrefixeTreeNode(a.getFilsGauche(),top1);
				parcoursPrefixeTreeNode(a.getFilsDroit(),top1);
			}
		}


}