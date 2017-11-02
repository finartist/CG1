import java.util.ArrayList;
import java.util.List;

import Game.PlayerConstants;
import Game.Controller.FlowerInfo;
import Game.Controller.IPlayerController;
import Game.Controller.MapInfo;
import Game.Controller.PlayerInfo;
import Game.Logic.Vector;
import Game.Logic.AbstractFlower.EFlowerType;
import Game.Logic.Items.Capacity;

public class Agent833Junior implements IPlayerController{

	private Vector goal; // goal

	private int i = 0; // index for list
	List<FlowerInfo> curVisFlow;
	List<Float> curSmellFlow;
	private List<Vector> flowersSeen = new ArrayList<Vector>();

	private Vector hivePos;


	@Override
	public String getName() {
		return "Agent833 Jun.";
	}

	@Override
	public String getAuthor() {
		return "Lisa Piotrowski";
	}

	@Override
	public Vector think(MapInfo mapInfo, PlayerInfo ownPlayerInfo) {

		curVisFlow = ownPlayerInfo.lookAround();
		curSmellFlow = ownPlayerInfo.smell();

		// use 400 ticks to discover map and flowers
		if (mapInfo.getNumOfTicks() <= 100) {
			// adds new discovered flowers to list
			if(flowersSeen.size() < 5)
			addNewFlow();
			return discover(mapInfo, 100, 35f).sub(ownPlayerInfo.getPosition());	
		}
		else {

			// add some more flowers if new one are seen
			if(flowersSeen.size() < 5){
				addNewFlow();
			}

			// fly to hive if max capacity or only 80 Ticks left
			/** super ugly condition! how can I get factor from PlayerInfo ? **/
			if (mapInfo.getTotalNumOfTicks() - mapInfo.getNumOfTicks() < 80 || ownPlayerInfo.getPolls() >= PlayerConstants.POLL_CAPACITY * (ownPlayerInfo.getItem().getType().equals("CAPACITY")? new Capacity().getCapacity() : 1)) {

				goal = mapInfo.getHivePosition(); //.sub(ownPlayerInfo.getPosition());
			}
			else{
				// if not already been at goal flower position, flower stays goal
				if(!isHarvested(ownPlayerInfo) && ownPlayerInfo.getPosition().getX() != flowersSeen.get(i).getX() && ownPlayerInfo.getPosition().getY() != flowersSeen.get(i).getY()){
					goal = flowersSeen.get(i);
				}
				// if I am at my destination flower, check if harvested, if it is: next flower is new goal
				else{
					if(isHarvested(ownPlayerInfo)){
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
		return goal.sub(ownPlayerInfo.getPosition());//.add(correction);
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
		 * @return
		 */
		private Vector discover(MapInfo mapInfo, float discoverTicks, float radius) {
			// hive position is center of the circle
			hivePos = mapInfo.getHivePosition();

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
		
		/**
		 * Checks if destination flower has some pollen left.
		 * Only possible if flower can be seen.
		 * @param info PlayerInfo (needed for look around)
		 * @return true if flower has no pollen
		 */
		// look if the destination flower is already harvested
		private boolean isHarvested(PlayerInfo info){
			for(FlowerInfo el: curVisFlow){
				if(flowersSeen.get(i).getX() == el.getPosition().getX() && flowersSeen.get(i).getY() == el.getPosition().getY() ){
					if(el.getPollCount() > 1){
						return false;
					}
				}
			}
			
			float distance = flowersSeen.get(i).sub(info.getPosition()).length();
			for(Float f : curSmellFlow){
				if(distance <= (f.floatValue() + 0.001f) && distance >= (f.floatValue() - 0.001f)){
					return false;
				}
			}
			return true;
		}
}
