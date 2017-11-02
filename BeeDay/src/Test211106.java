import java.util.ArrayList;
import java.util.List;

import Game.GameConstants;
import Game.PlayerConstants;
import Game.Controller.FlowerInfo;
import Game.Controller.IPlayerController;
import Game.Controller.MapInfo;
import Game.Controller.PlayerInfo;
import Game.Logic.AbstractFlower;
import Game.Logic.Vector;
import Game.Logic.Items.Capacity;

public class Test211106 implements IPlayerController {

	private int i = 0; // index for list
	private List<FlowerInfo> flowersSeen = new ArrayList<FlowerInfo>(); // all
																		// flowers
																		// i
																		// have
																		// seen
																		// already
	private Vector goal; // goal

	private int currTicks = 0;
	private float compTicks = 400f;
	private float trainTicks = 300f;

	// 
	private Vector hivePos;
	
	private String level;

	@Override
	public String getName() {
		// TODO Add bot name
		return "Billy";
	}

	@Override
	public String getAuthor() {
		// TODO Add my name
		return "Lisa";
	}

	@Override
	public Vector think(MapInfo mapInfo, PlayerInfo ownPlayerInfo) {
		
		long startTime = System.currentTimeMillis();

		currTicks = mapInfo.getNumOfTicks();
		level = mapInfo.getLevel(); /** can I take it outside think? **/

		// depending on mode, the time used to discover map is different
		if (level.equals("Sandbox") && currTicks <= compTicks) {
			goal = discover(mapInfo, compTicks, 50f);
			// adds new discovered flowers to list
			addNewFlow(ownPlayerInfo);
			
		} else if (level.equals("Training") && currTicks <= trainTicks) {
			goal = discover(mapInfo, trainTicks, 50f);
			addNewFlow(ownPlayerInfo);
		} else {

			// fly to hive if max capacity
			/** super ugly condition! how can I get factor from PlayerInfo ? **/
			if (ownPlayerInfo.getPolls() >= PlayerConstants.POLL_CAPACITY * (ownPlayerInfo.getItem().getType().equals("CAPACITY")? new Capacity().getCapacity() : 1)) {
				return mapInfo.getHivePosition().sub(ownPlayerInfo.getPosition());
			}

			// fly to flower if flower has polls left
			if (flowersSeen.get(i).getPollCount() > 0) {
				goal = flowersSeen.get(i).getPosition();
			}

			// if not going searching, try next flower on list
			else {
				if (i >= flowersSeen.size() - 1) {
					i = 0;
				} else {
					i++;
				}
				goal = flowersSeen.get(i).getPosition();
			}
		}
		
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println(totalTime);
		
		return goal.sub(ownPlayerInfo.getPosition());
		// return new Vector((float)Math.sin(angle) * radius,
		// (float)Math.cos(angle) * radius);
	}

	// method to add new flowers to list
	public void addNewFlow(PlayerInfo ownPlayerInfo) {
		List<FlowerInfo> curVisFlow = ownPlayerInfo.lookAround(); // currently
																	// visible
																	// flowers
		// if no flowers in list yet, add all visible
		if (this.flowersSeen.size() <= 0) {
			for (FlowerInfo f : curVisFlow) {
				this.flowersSeen.add(f);
			}
		}
		// else check for flowers not in the list yet
		else {
			for (FlowerInfo cur : curVisFlow) {
				if (!this.flowersSeen.contains(cur)) {
					this.flowersSeen.add(cur);
				}
			}
		}
	}

	// method for bee to fly a circle during the specified time(discoverTicks) 
	// so there are a lot of flowers discovered before iterated over list
	// radius is the radius of the circle
	public Vector discover(MapInfo mapInfo, float discoverTicks, float radius) {
		// hive position is center of the circle
		hivePos = mapInfo.getHivePosition();
		
		// the factor in front of PI depending on ticks
		double factor = currTicks/(discoverTicks/2);
		
		//x and y are coordinates of the vector, which should be returned
		float x = (float) Math.cos(factor*Math.PI)*radius;
		float y = (float) Math.sin(factor*Math.PI)*radius;
		
		return new Vector(x, y);

	}

}