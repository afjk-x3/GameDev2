package utilz;

public class Constant {
	
	public static class Directions{
		public static final int LEFT = 0;
		public static final int UP = 1;
		public static final int RIGHT = 2;
		public static final int DOWN = 3;
	}
	
	public static class PlayerConstants{
		public static final int RUNNING = 0;
		public static final int IDLE = 1;
		public static final int JUMP = 2;
		public static final int CROUCH = 3;
		public static final int GROUND = 5;
		public static final int HIT = 5;
		public static final int ATTACK = 7;
		public static final int BLOCK = 8;
		public static final int PUGAY = 9;
		public static final int SUNGKIT = 10;
		
		public static int GetSpriteAmount(int player_action) {
			
			switch(player_action) {
			case RUNNING:
				return 12;
			case IDLE:
				return 6;
			case JUMP:
				return 12;
			case CROUCH:
				return 1;
//			case GROUND:
//			case HIT:
			case ATTACK:
				return 7;
			case SUNGKIT:
				return 12;
			case BLOCK:
			case PUGAY:
				return 8;
			default:
				return 1;
			}
		}

	}
}