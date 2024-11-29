package utilz;

public class Constant {
	
	public static class Directions{
		public static final int LEFT = 0;
		public static final int UP = 1;
		public static final int RIGHT = 2;
		public static final int DOWN = 3;
	}
	
	public static class PlayerConstants{
		public static final int RUNNING = 7;
		public static final int IDLE = 1;
		public static final int JUMP = 2;
		public static final int FALLING = 3;
		public static final int GROUND = 5;
		public static final int HIT = 5;
		public static final int ATTACK = 0;
		public static final int BLOCK = 8;
		public static final int PUGAY = 9;
		
		public static int GetSpriteAmount(int player_action) {
			
			switch(player_action) {
			case RUNNING:
			case IDLE:
				return 6;
			case JUMP:
			case FALLING:
//			case GROUND:
//			case HIT:
			case ATTACK:
				return 7;
			case BLOCK:
			case PUGAY:
				return 8;
			default:
				return 1;
			}
		}

	}
}
