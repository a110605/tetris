package GameData;

public class GameData {

	private static Score score;
	private static Speed speed;
	private static Level level;
	private static boolean changedScore;

	public GameData() {
		score = new Score();
		speed = new Speed();
		level = new Level();

		changedScore = false;
	}

	public void addPoints() {
		score.addPoints();
		changedScore = false;
	}

	public int getPoints() {
		return score.getScore();
	}
	
	// points & previous levels
	public void dataRefresh() {
		upLevel(30, 1); // level 2
		upLevel(50, 2); // level 3
		upLevel(80, 3); // level 4
		upLevel(130, 4); // level 5
		upLevel(200, 5); // level 6
		upLevel(210, 6); // level 7
		upLevel(250, 7); // level 8
		upLevel(300, 8); // level 9
	}
	
	public static void upLevel(int points, int previousLevel){

		if (score.getScore() >= points && level.getLevelNr() == previousLevel) {
			if (!changedScore) {
				level.levelUp();
				speed.levelUp();
				changedScore = true;
			}
		}
	}
	
	public long getMiliseconds(){
		return speed.getSpeed();
	}
	
	public boolean changedLevel(){
		return changedScore;
	}
	
	public void resetData(){
		speed.resetSpeed();
		score.resetScore();
		level.resetLevel();
	}
	
	public String getLevel(){
		return level.getLevel();
	}
}
