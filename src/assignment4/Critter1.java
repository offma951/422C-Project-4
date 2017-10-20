package assignment4;

public class Critter1 extends Critter {
	
	@Override
	public String toString() { return "1"; }
	
	private static final int GENE_TOTAL = 24;
	private int[] genes = new int[4];
	private int dir;
	
	public Critter1() {
		for (int k = 0; k < 4; k += 1) {
			genes[k] = GENE_TOTAL / 4;
		}
		dir = Critter.getRandomInt(4);
	}

	@Override
	public void doTimeStep() {
		// Each time step Critter1 will run 2 steps in one of the first 4 directions only
		run(dir);
	}

	@Override
	public boolean fight(String oponent) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public static void runStats(java.util.List<Critter> critter1s) {
		int total_straight = 0;
		int total_left = 0;
		int total_right = 0;
		int total_back = 0;
		for (Object obj : critter1s) {
			Critter1 c1 = (Critter1) obj;
			total_straight += c1.genes[0];
			total_right += c1.genes[1] + c1.genes[2] + c1.genes[3];
			total_back += c1.genes[4];
			total_left += c1.genes[5] + c1.genes[6] + c1.genes[7];
		}
		System.out.print("" + critter1s.size() + " total Critter1s    ");
		System.out.print("" + total_straight / (GENE_TOTAL * 0.01 * critter1s.size()) + "% straight   ");
		System.out.print("" + total_back / (GENE_TOTAL * 0.01 * critter1s.size()) + "% back   ");
		System.out.print("" + total_right / (GENE_TOTAL * 0.01 * critter1s.size()) + "% right   ");
		System.out.print("" + total_left / (GENE_TOTAL * 0.01 * critter1s.size()) + "% left   ");
		System.out.println();
	}
	
}
