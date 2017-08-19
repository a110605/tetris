package GameData;

public class Speed {

	private long[] gears;
	private int level;
	
	public Speed(){
		gears = new long[10];
		level = 1;
		
		gears[1] = 1000L;
		gears[2] = 800L;
		gears[3] = 600L;
		gears[4] = 500L;
		gears[5] = 400L;
		gears[6] = 300L;
		gears[7] = 200L;
		gears[8] = 100L;
		gears[9] = 50L;
	}
	
	public long getSpeed(){
		return gears[this.level];
	}
	
	public void resetSpeed(){
		this.level = 1;
	}
	
	public void levelUp(){
		this.level++;
	}
	
}
