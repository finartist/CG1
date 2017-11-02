import java.util.ArrayList;
import java.util.List;

import Game.Controller.FlowerInfo;
import Game.Controller.IPlayerController;
import Game.Controller.MapInfo;
import Game.Controller.PlayerInfo;
import Game.Logic.AbstractFlower.EFlowerType;
import Game.Logic.Vector;

public class Agent833 implements IPlayerController {
	
	private Vector goal; // goal
	
	private int i = 0; // index for list
	private List<FlowerInfo> curVisFlow; // currently visible
	private List<Float> curSmellFlow; // currently smellable
	private List<Vector> flowersSeen = new ArrayList<Vector>(); //flowers already seen

	@Override
	public String getName() {
		return "Agent833";
	}

	@Override
	public String getAuthor() {
		return "Lisa Piotrowski";
	}

	@Override
	public Vector think(MapInfo mapInfo, PlayerInfo ownPlayerInfo) {
		// getting flower information, basically to make the code more readable
		curVisFlow = ownPlayerInfo.lookAround();
		curSmellFlow = ownPlayerInfo.smell();
	    
		// use 250 ticks to discover map and flowers
		if (mapInfo.getNumOfTicks() <= 250) {
			// adds new discovered flowers to list
			addNewFlow();
			return discover(mapInfo, 250, 50f).sub(ownPlayerInfo.getPosition())._normalize();	
		}
		else {
			
			// add some more flowers if new ones are seen
			if(flowersSeen.size() < mapInfo.getNumFlowers() - 7){
				addNewFlow();
			}
			// fly to hive if max capacity or only 80 ticks left
			if (mapInfo.getTotalNumOfTicks() - mapInfo.getNumOfTicks() < 80 || ownPlayerInfo.getPolls() >= ownPlayerInfo.getMyPollenCapacity()) {
				goal = mapInfo.getHivePosition();
			}
			else{
				// if not already been at goal flower position, flower stays goal if it is not already harvested
				if(!isHarvested(ownPlayerInfo) && ownPlayerInfo.getPosition().getX() != flowersSeen.get(i).getX() && ownPlayerInfo.getPosition().getY() != flowersSeen.get(i).getY()){
					goal = flowersSeen.get(i);
				}
				// if at destination flower, check if harvested, if it is: next flower is new goal
				else{
					if(isHarvested(ownPlayerInfo)){
						// no index out of bound
						if(i == flowersSeen.size()-1){
							i = 0;
						}
						else{
							++i;
						}
					}
					goal = flowersSeen.get(i);
				}
			}
		}
		
		// check if evil flower is in the way and if it is: avoid it
		Vector correction = avoidEvil(ownPlayerInfo);
		if(correction.getX() != 0 && correction.getY() != 0){
			// check if i can go around the evil flower, otherwise go the other way around
			if(!mapInfo.getWalkable(correction.mult(15).add(ownPlayerInfo.getPosition())._normalize())){
				correction=correction.negate();
			}
			//for debugging
			//System.out.println("correction " + correction);
			return correction._normalize();
		}
		//for debugging
		//System.out.println("Goal: " + goal.sub(ownPlayerInfo.getPosition()));
		return goal.sub(ownPlayerInfo.getPosition())._normalize();
	}
	
	// method to add new flowers to list
	/**
	 * Adds yet unseen flowers to list of seen flowers
	 */
	private void addNewFlow() {

		// if no flowers in list yet, add all visible
		if (this.flowersSeen.size() == 0) {
			for (FlowerInfo f : curVisFlow) {
				//good flowers
				if(f.getFlowerType() != EFlowerType.EVIL && f.getFlowerType() != EFlowerType.TULIP){
					this.flowersSeen.add(f.getPosition());
				}
			}
		}
		// else check for flowers not in the list yet
		else {
			for (FlowerInfo cur : curVisFlow) {
				//good flowers
				if (!containsVector(flowersSeen, cur.getPosition()) && cur.getFlowerType() != EFlowerType.EVIL && cur.getFlowerType() != EFlowerType.TULIP) {
					//System.out.println("Flower " + cur.getPosition());
					this.flowersSeen.add(cur.getPosition());
				}
			}
		}
	}

	// method for bee to fly a circle during the specified time(discoverTicks) 
	// so there are a lot of flowers discovered before iterated over list
	// radius is the radius of the circle
	/**
	 * Discovers map through flying a circle
	 * @param mapInfo Map info of game
	 * @param discoverTicks Ticks used to discover
	 * @param radius Radius of the Circle around the hive
	 * @return Vector of the point on the circle around hive 
	 */
	private Vector discover(MapInfo mapInfo, float discoverTicks, float radius) {

		// the factor in front of PI depending on ticks
		double factor = mapInfo.getNumOfTicks()/(discoverTicks/2);

		//x and y are coordinates of the vector, which should be returned
		float x = (float) Math.cos(factor*Math.PI)*radius;
		float y = (float) Math.sin(factor*Math.PI)*radius;

		return new Vector(x, y);

	}

	// check if we have the flower in the list already
	// list.contains does not! work because references change every game loop
	/**
	 * Checks if given list contains certain vector
	 * @param list List the vector should be in
	 * @param vec Vector to be found
	 * @return true, if list contains Vector
	 */
	private boolean containsVector(List<Vector> list, Vector vec){
		for(Vector v : list){
			if(v.getX() == vec.getX() && v.getY() == vec.getY()){
				return true;
			}
		}
		return false;
	}

	// returns a vector to avoid evil flower influence
	/**
	 * Returns a Vector to avoid evil flower.
	 * @param info Player Info 
	 * @return Vector
	 */
	private Vector avoidEvil(PlayerInfo info){
		Vector correction = new Vector(0, 0);
		
		// looping through visible flowers, because there are less than if evil flowers are saved and iterated over
		for(FlowerInfo flow : curVisFlow){
			// if we see an evil flower, we want to fly around it's influence
			if(flow.getFlowerType() == EFlowerType.EVIL){
				//compute intersection, if there is one, of the evil flower influence and flight route
				Vector inter = intersection(flow.getPosition(), 16f, info.getPosition(), goal.sub(info.getPosition()));

				// correcting vector is tangent of circle intersection point
				if(inter != null){
					// first set it intersection point
					correction = inter.sub(flow.getPosition());
					
					//then compute a perpendicular Vector -> tangent
					float tempx = correction.getX();
					correction.setX(-correction.getY());
					correction.setY(tempx);
					
					// now have a look for the shortest way around
					double angle = Math.acos(goal.sub(info.getPosition()).dot(correction)/(goal.sub(info.getPosition()).length()*correction.length()));
					if(angle < 90 || angle > 270){
						correction = correction.negate();
					}
					correction._normalize();
				}
				// return statement here, so if multiple evil flowers are seen the bee looks at the first
				return correction;
			}

		}
		return correction;
	}

	// look if the destination flower is already harvested
	/**
	 * Checks if destination flower has some pollen left.
	 * Only possible if flower can be seen.
	 * @param info PlayerInfo (needed for look around)
	 * @return true if flower has no pollen
	 */
	private boolean isHarvested(PlayerInfo info){
		// first have a look, if destination flower is already in sight and use this as prior method to check
		for(FlowerInfo el: curVisFlow){
			if(flowersSeen.get(i).getX() == el.getPosition().getX() && flowersSeen.get(i).getY() == el.getPosition().getY() ){
				if(el.getPollCount() > 1){
					return false;
				}
			}
		}

		// only if destination flower is not seen OR has no more pollen, check smell radius
		// cause flowers without full pollen do not smell
		float distance = flowersSeen.get(i).sub(info.getPosition()).length();
		for(Float f : curSmellFlow){
			// check distance and epsilon surrounding for smellable flowers
			if(distance <= (f.floatValue() + 0.001f) && distance >= (f.floatValue() - 0.001f)){
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks if Bee flies inside circle of influence and gives back
	 * the intersection position.
	 * @param circleMid Evil flower position/middle of circle.
	 * @param r radius of the circle
	 * @param rayStart Own position/start point of ray
	 * @param rayDir Flight direction/direction of ray
	 * @return Intersection Position
	 */
	private Vector intersection(Vector circleMid, float r, Vector rayStart, Vector rayDir){
		//(o+t*d - c)^2 - r^2= 0

		Vector d = rayDir;
		Vector o = rayStart;
		
		//setting o = o - c (c is circle mid)
		o = o.sub(circleMid);

		double t1 = Double.NaN;
		double t2 = Double.NaN;

		// Coefficients of the square equation deriving from (o+t*d)^2 - r^2
		float[] sqrEqu = {d.getX()*d.getX() + d.getY()*d.getY(), 2 * o.getX()*d.getX() + 2 * o.getY()*d.getY(),  o.getX()*o.getX() + o.getY()*o.getY() - r*r};

		if (sqrEqu[0] != 0) {
			// divide every part of the sqr. equation by the coefficient in front of t^2, so we can use pq-formula
			sqrEqu[1] /= sqrEqu[0];
			sqrEqu[2] /= sqrEqu[0];
			sqrEqu[0] /= sqrEqu[0];

			double radicant = (sqrEqu[1] / 2)*(sqrEqu[1] / 2) - sqrEqu[2];
			if (radicant < 0) {
				return null;
			}
			// 2 solutions for t
			t1 = -(sqrEqu[1] / 2) + Math.sqrt(radicant);
			t2 = -(sqrEqu[1] / 2) - Math.sqrt(radicant);
		}

		else {
			return null;
		}

		//System.out.println("t1: " + t1);
		//System.out.println("t2: " + t2);

		// if there are 2 valid t, use the smaller, because it's closer to the ray's origin
		// t should not be greater than 1, because then the goal is in front of the circle
		// t is greater than 0, because if negative, the bee is inside the circle
		if ((!(t1 == Double.NaN) && !(t1 < 0) && !(t1>1)) && (!(t2 == Double.NaN) && !(t2 < 0) && !(t2>1))) {
			float lambda;
			lambda = (float) Math.min(t1, t2);
			
			//System.out.println("lambda: " + lambda);
			return new Vector(rayStart.add(rayDir.mult(lambda)));
		}

		// although the function should return false beforehand if there is no valid t, 
		// this assures it will definitely return something if I made a mistake
		return null;
	}

}
