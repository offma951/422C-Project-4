package assignment4;

public class Critter3 extends Critter {

	@Override
	public String toString() { return "3"; }
	
	private static final int GENE_TOTAL = 40;
	private int[] genes = new int[8];
	private int dir;
	
	public Critter3() {
		for (int k = 0; k < 8; k += 1) {
			genes[k] = GENE_TOTAL / 8;
		}
		dir = Critter.getRandomInt(8);
	}

	@Override
	public void doTimeStep() {
		// Each time step Critter1 will walk one step in one of the first 4 directions only
		walk(dir);
		// Critter3 is unable to reproduce
		
		// Pick a new direction based on our genes
		int roll = Critter.getRandomInt(GENE_TOTAL);
		int turn = 0;
		while (genes[turn] <= roll) {
			roll = roll - genes[turn];
			turn = turn + 1;
		}
		assert(turn < 8);
		
		dir = (dir + turn) % 8;
	}

	@Override
	public boolean fight(String oponent) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public static void runStats(java.util.List<Critter> critter3s) {
		int total_straight = 0;
		int total_left = 0;
		int total_right = 0;
		int total_back = 0;
		for (Object obj : critter3s) {
			Critter3 c3 = (Critter3) obj;
			total_straight += c3.genes[0];
			total_right += c3.genes[1] + c3.genes[2] + c3.genes[3];
			total_back += c3.genes[4];
			total_left += c3.genes[5] + c3.genes[6] + c3.genes[7];
		}
		System.out.print("" + critter3s.size() + " total Critter3s    ");
		System.out.print("" + total_straight / (GENE_TOTAL * 0.01 * critter3s.size()) + "% straight   ");
		System.out.print("" + total_back / (GENE_TOTAL * 0.01 * critter3s.size()) + "% back   ");
		System.out.print("" + total_right / (GENE_TOTAL * 0.01 * critter3s.size()) + "% right   ");
		System.out.print("" + total_left / (GENE_TOTAL * 0.01 * critter3s.size()) + "% left   ");
		System.out.println();
	}
	
}
