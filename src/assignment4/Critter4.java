package assignment4;

public class Critter4 extends Critter {

	@Override
	public String toString() { return "4"; }
	
	private static final int GENE_TOTAL = 28;
	private int[] genes = new int[4];
	private int dir;
	
	public Critter4() {
		for (int k = 0; k < 4; k += 1) {
			genes[k] = GENE_TOTAL / 4;
		}
		dir = Critter.getRandomInt(4);
	}

	@Override
	public void doTimeStep() {
		// Each time step Critter4 will walk 1 step in one of the first 4 directions only
		run(dir);
		// Discuss rest of method
		if (getEnergy() > 175) {
			Critter4 child = new Critter4();
			for (int k = 0; k < 4; k += 1) {
				child.genes[k] = this.genes[k];
			}
			int g = Critter.getRandomInt(4);
			while (child.genes[g] == 0) {
				g = Critter.getRandomInt(4);
			}
			child.genes[g] -= 1;
			g = Critter.getRandomInt(4);
			child.genes[g] += 1;
			reproduce(child, Critter.getRandomInt(4));
		}
		
		// Pick a new direction based on our genes
		int roll = Critter.getRandomInt(GENE_TOTAL);
		int turn = 0;
		while (genes[turn] <= roll) {
			roll = roll - genes[turn];
			turn = turn + 1;
		}
		assert(turn < 4);
		
		dir = (dir + turn) % 4;
	}

	@Override
	public boolean fight(String not_used) { 
		//Critter4 is unable to fight
		return true;
	}
	
	public static void runStats(java.util.List<Critter> critter4s) {
		int total_straight = 0;
		int total_left = 0;
		int total_right = 0;
		int total_back = 0;
		for (Object obj : critter4s) {
			Critter4 c4 = (Critter4) obj;
			total_straight += c4.genes[0];
			total_right += c4.genes[1] + c4.genes[2] + c4.genes[3];
			total_back += c4.genes[4];
			total_left += c4.genes[5] + c4.genes[6] + c4.genes[7];
		}
		System.out.print("" + critter4s.size() + " total Critter4s    ");
		System.out.print("" + total_straight / (GENE_TOTAL * 0.01 * critter4s.size()) + "% straight   ");
		System.out.print("" + total_back / (GENE_TOTAL * 0.01 * critter4s.size()) + "% back   ");
		System.out.print("" + total_right / (GENE_TOTAL * 0.01 * critter4s.size()) + "% right   ");
		System.out.print("" + total_left / (GENE_TOTAL * 0.01 * critter4s.size()) + "% left   ");
		System.out.println();
	}

}
