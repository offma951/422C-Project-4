package assignment4;

public class Critter2 extends Critter {
	
	@Override
	public String toString() { return "2"; }
	
	private static final int GENE_TOTAL = 32;
	private int[] genes = new int[8];
	private int dir;
	
	public Critter2() {
		for (int k = 0; k < 8; k += 1) {
			genes[k] = GENE_TOTAL / 8;
		}
		dir = Critter.getRandomInt(8);
	}

	@Override
	public void doTimeStep() {
		// Each time step Critter2 will run 2 steps in any of the 8 directions
		run(dir);
		// Discuss rest of method
		if (getEnergy() > 110) {
			Critter2 child = new Critter2();
			for (int k = 0; k < 8; k += 1) {
				child.genes[k] = this.genes[k];
			}
			int g = Critter.getRandomInt(8);
			while (child.genes[g] == 0) {
				g = Critter.getRandomInt(8);
			}
			child.genes[g] -= 1;
			g = Critter.getRandomInt(8);
			child.genes[g] += 1;
			reproduce(child, Critter.getRandomInt(8));
		}
		
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
	
	public static void runStats(java.util.List<Critter> critter2s) {
		int total_straight = 0;
		int total_left = 0;
		int total_right = 0;
		int total_back = 0;
		for (Object obj : critter2s) {
			Critter2 c2 = (Critter2) obj;
			total_straight += c2.genes[0];
			total_right += c2.genes[1] + c2.genes[2] + c2.genes[3];
			total_back += c2.genes[4];
			total_left += c2.genes[5] + c2.genes[6] + c2.genes[7];
		}
		System.out.print("" + critter2s.size() + " total Critter2s    ");
		System.out.print("" + total_straight / (GENE_TOTAL * 0.01 * critter2s.size()) + "% straight   ");
		System.out.print("" + total_back / (GENE_TOTAL * 0.01 * critter2s.size()) + "% back   ");
		System.out.print("" + total_right / (GENE_TOTAL * 0.01 * critter2s.size()) + "% right   ");
		System.out.print("" + total_left / (GENE_TOTAL * 0.01 * critter2s.size()) + "% left   ");
		System.out.println();
	}

}
