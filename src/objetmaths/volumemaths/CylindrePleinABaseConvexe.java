package objetmaths.volumemaths;

import algLin.Point3;
import algLin.R3;
import objetmaths.surfacemaths.SurfacePlaneConvexe;

public class CylindrePleinABaseConvexe implements VolumeMath{

	SurfacePlaneConvexe base;
	R3 directrice;
	double d1;	// hauteur du tron�on qui part de la base et avance dans le sens de directrice
	double d2;	// hauteur du tron�on qui part de la base et avance dans le sens oppos� de directrice
	
	
	public CylindrePleinABaseConvexe(SurfacePlaneConvexe b, R3 dir) {
		base = b;
		directrice = dir;
		d1=d2=Double.POSITIVE_INFINITY;
	}
	
	
	public boolean estDedans(Point3 p) {
		double dist1 = base.dist(p, directrice.opp());
		double dist2 = base.dist(p, directrice);
		return dist1<d1 || dist2 <d2;
	}

}