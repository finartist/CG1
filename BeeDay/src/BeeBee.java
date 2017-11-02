import java.util.ArrayList;
import java.util.List;

import Game.Controller.IPlayerController;
import Game.Controller.MapInfo;
import Game.Controller.PlayerInfo;
import Game.Logic.Vector;

public class BeeBee implements IPlayerController {

	private class Circle {
		float range;
		Vector mid;

		public Circle(float r, Vector m) {
			range = r;
			mid = m;
		}

		public String toString() {
			return "r: " + range + "pos: " + mid.toString();
		}

		public Vector[] computeIntersection(Circle c){
			Vector[] result = new Vector[2];
			
			float distance = this.mid.sub(c.mid).length();
			System.out.println("distance " + distance);
			if(distance != 0){
				float x = (this.range*this.range + distance*distance - c.range*c.range)/2*distance;
				float y = (float) Math.sqrt(this.range*this.range - x*x);

				result[0] = new Vector(this.mid.getX() + x*(c.mid.getX()-this.mid.getX())/distance - y*(c.mid.getY()-this.mid.getY())/distance, this.mid.getY() + x*(c.mid.getY()-this.mid.getY())/distance + y*(c.mid.getX()-this.mid.getX())/distance);
				System.out.println(result[0]);
				result[1] = new Vector(this.mid.getX() + x*(c.mid.getX()-this.mid.getX())/distance + y*(c.mid.getY()-this.mid.getY())/distance, this.mid.getY() + x*(c.mid.getY()-this.mid.getY())/distance - y*(c.mid.getX()-this.mid.getX())/distance);
			}
			return result;
		}
	}

	public List<Circle> circles = new ArrayList<Circle>();
	public List<Vector> flowerPos = new ArrayList<Vector>();

	@Override
	public String getName() {
		return "BeeBee";
	}

	@Override
	public String getAuthor() {
		return "Lisa Piotrowski";
	}

	@Override
	public Vector think(MapInfo mapInfo, PlayerInfo ownPlayerInfo) {
		System.out.println(mapInfo.getNumOfTicks());
		List<Float> smell = ownPlayerInfo.smell();
		if (mapInfo.getNumOfTicks() < 4) {
			System.out.println(smell);
			for (float f : smell) {
				circles.add(new Circle(f, ownPlayerInfo.getPosition()));
			}
			return new Vector(0, 1);
		}
		System.out.println(circles.toString());

		if (mapInfo.getNumOfTicks() == 4) {
			determineFlowerPosition(mapInfo);
		}

		System.out.println(flowerPos.toString());

		return new Vector(0, 0);
	}

	public void determineFlowerPosition(MapInfo info) {
		System.out.println("Number of flowers" + info.getNumFlowers());
		for (int i = 0; i < info.getNumFlowers(); ++i) {
			flowerPos.add(trilaterate(circles.get(i), circles.get(i + info.getNumFlowers()), circles.get(i + 2*info.getNumFlowers())));
		}
	}

	public Vector trilaterate(Circle c1, Circle c2, Circle c3){
//		//ex = (P2 - P1) / ||P2 - P1||
//		Vector ex = c2.mid.sub(c1.mid)._normalize();
//		//i = ex(P3 - P1)
//		float i = ex.dot(c3.mid.sub(c1.mid));
//		//ey = (P3 - P1 - i · ex) / ||P3 - P1 - i · ex||
//		Vector ey = ((c3.mid.sub(c1.mid)).sub(ex.mult(i))._normalize());
//		//d = ||P2 - P1||
//		float d = c2.mid.sub(c1.mid).length();
//		//j = ey(P3 - P1)
//		float j = ey.dot(c3.mid.sub(c1.mid));
//		//x = (r12 - r22 + d2) / 2d
//		float x = (c1.range*c1.range - c2.range*c2.range + d*d) /(2*d);
//		//y = (r12 - r32 + i2 + j2) / 2j - ix / j
//		float y = (c1.range*c1.range - c3.range*c3.range + i*i + j*j)/2*j - i*x/j;
		
		Vector[] v1 = c1.computeIntersection(c2);
		Vector[] v2 = c2.computeIntersection(c3);
		
		if((v1[0]!=null && v2[0]!=null && v2[1] != null) && (v1[0].equals(v2[0]) || v1[0].equals(v2[1]))){
			return v1[0];
		}
		else if((v1[1]!=null && v2[0]!=null && v2[1] != null) && (v1[1].equals(v2[0]) || v1[1].equals(v2[1]))){
			return v1[1];
		}
		else{
			return null;
		}
	}

}
