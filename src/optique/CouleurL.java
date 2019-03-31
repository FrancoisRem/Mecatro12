package optique;

import java.awt.Color;
import java.io.Serializable;

public class CouleurL implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3224752714424626019L;
	double i;
	double r,g,b;
	double maxCoul;
	public static final CouleurL noir = new CouleurL(1,1,1,0);


	//Constructeurs
	public CouleurL(double r1, double g1, double b1, double i) {
		this.i=i;
		this.r=r1/(r1+g1+b1);
		this.g=g1/(r1+g1+b1);
		this.b=b1/(r1+g1+b1);
		maxCoul=r;
		if (g>r)
			maxCoul=g;
		if (b>maxCoul)
			maxCoul=b;
	}

	public CouleurL(Color c, double i) {
		this(c.getRed(),c.getGreen(),c.getBlue(),i);
	}

	private CouleurL(CouleurL lux) {
		i=lux.i;
		r=lux.r;
		g=lux.g;
		b=lux.b;
	}
	
	

	//Getters
	public double getIntensite() {
		return i;
	}
	public double getR() {
		return r;
	}
	public double getG() {
		return g;
	} 
	public double getB() {
		return b;
	}

	public Color getColor() {
		return appliquerIntGlobale(i);
	}

	
	//Methodes de luminosite
	/**Renvoie d ssi compris entre 0 et 1, 0 si moins, 1 si plus
	 * 
	 * @param d
	 * @return
	 */
	public float cut(double d) {
		return (float) Math.min(1, Math.max(0, d));
	}


	public void multiplierIntensite(double k) {
		i=k*i;
	}

	public CouleurL multiplieIntensite(double k) {
		CouleurL result = new CouleurL(this);
		result.multiplierIntensite(k);
		return result;
	}


	public Color appliquerIntGlobale(double val) {
		try {
			return new Color( (int)(255*(r/maxCoul) * (i/val)), (int)(255*(g/maxCoul) * (i/val)), (int)(255*(b/maxCoul) * (i/val)));
		} catch (IllegalArgumentException e) {
			return new Color( (int)(255* cut((r/maxCoul) * (i/val) )), (int)(255* cut((g/maxCoul) * (i/val) )), (int)(255* cut((b/maxCoul) * (i/val) )));
		}
	}



	
	//Melange de CouleurL

	public CouleurL plus(CouleurL lux) {
		return new CouleurL(i*r + lux.i*lux.r , i*g + lux.i*lux.g , i*b + lux.b*lux.i, i + lux.i);
	}
	
	

	@Override
	public int hashCode() {
		return (int)(255*(r+g+b+i));
	}

	@Override
	public boolean equals(Object o2) {
		if (o2 instanceof CouleurL) {
			return r== ((CouleurL)o2).r && g== ((CouleurL)o2).g && b== ((CouleurL)o2).b && i== ((CouleurL)o2).i;
		}
		else return false;
	}

	//Autres
	

	@Override
	public String toString() {
		return String.format("( Intensite: %.3g  |  Couleur: (%.3g ; %.3g ; %.3g) )",i,r,g,b);
	}


	public static void main(String[] args) {
		CouleurL l1 = new CouleurL(1,0,0,4);
		CouleurL l2 = new CouleurL(0.333,0.333,0.333,0.06);
		CouleurL l3 = l1.plus(l2);
		Color c= new Color(1,1,(float)(0.5));
		System.out.println(l2.appliquerIntGlobale(200000));
		

	}





}
