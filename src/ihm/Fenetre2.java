package ihm;

import java.awt.Checkbox;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import corps.TypeImage;
import ihm.fenetre1.commandesSup.Slide;
import ihm.fenetreImage.VisuImage;
import ihm.util.MenuSauverOuvrir;

public class Fenetre2 extends VisuImage{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6073674098267913730L;
	Programme p;
	Slide slideInt;
	Checkbox niveauInt;

	MenuSauverOuvrir sauvOuv;


	public Fenetre2(Programmable prog) {
		super();
		changerImage(prog.imageFinale());
		

		setLocation(150, 100);
		format=prog.getFormatImage();

		//Boutons
		niveauInt = new Checkbox("Niveau Intensité");
		niveauInt.addItemListener(this);

		commandes.add(niveauInt);

		//Slide
		slideInt=new Slide();
		slideInt.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent event){

				modifBlanc(((JSlider)event.getSource()).getValue(), slideInt.getMaximum());

			}});
		slideInt.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent w) {
				// masque la feêntre externe :
				slideInt.setVisible(false);
				niveauInt.setState(false);

			}
		});

		//=============================================
		//Enregistrement et sauvegarde de scènes

		sauvOuv=new MenuSauverOuvrir();
		sauvOuv.ajouterA(menuFichier);
		menuFichier.remove(quitter);
		menuFichier.add(quitter);

		setVisible(true);
	}




	@Override
	public void itemStateChanged(ItemEvent evt) {
		if (evt.getSource() == niveauInt) {
			if (evt.getStateChange() == ItemEvent.SELECTED) {
				slideInt.setVisible(true);
			}
			else slideInt.setVisible(false);
		}
		else super.itemStateChanged(evt);    
	}




	public void modifBlanc(double d, double dMax) {
		/*	  switch (p.mode) {
		case Raytracing :
			changerImage(p.r.getParam().getPic(p.r.imageBase, p.r.intBlanc*(1+d/dMax)));;
			break;
		case Miroirs:
			changerImage(p.mec.getParam().getPic(p.r.imageBase, p.r.intBlanc*(1+d/dMax)));;
			break;
		}*/
	}


}
