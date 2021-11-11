
import edu.harvard.econcs.jopt.solver.IMIP;
import edu.harvard.econcs.jopt.solver.IMIPResult;
import edu.harvard.econcs.jopt.solver.client.SolverClient;
import edu.harvard.econcs.jopt.solver.mip.*;

public class LPExercise3c {

	private IMIP linearProgram;

	public LPExercise3c() {
		this.buildLinearProgram();
	}

	public IMIP getLP() {
		return linearProgram;
	}

	private void buildLinearProgram() {
		linearProgram = new MIP();

		// TODO: Implement linear program.
		Variable x1 = new Variable("x1", VarType.DOUBLE, -MIP.MAX_VALUE, MIP.MAX_VALUE);
		Variable x2 = new Variable("x2", VarType.DOUBLE, -MIP.MAX_VALUE, MIP.MAX_VALUE);

		linearProgram.add(x1);
		linearProgram.add(x2);
		linearProgram.setObjectiveMax(true);
		linearProgram.addObjectiveTerm(10, x1);
		linearProgram.addObjectiveTerm(6, x2);

		Constraint c1 = new Constraint(CompareType.LEQ, 2800);
		c1.addTerm(6, x1);
		c1.addTerm(2, x2);
		linearProgram.add(c1);

		Constraint c2 = new Constraint(CompareType.LEQ, 3000);
		c2.addTerm(6, x1);
		c2.addTerm(6, x2);
		linearProgram.add(c2);

		Constraint c3 = new Constraint(CompareType.LEQ, 1200);
		c3.addTerm(12, x1);
		c3.addTerm(8, x2);
		linearProgram.add(c3);

		Constraint c4 = new Constraint(CompareType.GEQ, 0);
		c4.addTerm(1, x1);
		linearProgram.add(c4);

		Constraint c5 = new Constraint(CompareType.GEQ, 0);
		c5.addTerm(1, x2);
		linearProgram.add(c5);

	}

	public IMIPResult solve() {
		SolverClient solverClient = new SolverClient();
		IMIPResult result = solverClient.solve(linearProgram);
		return result;
	}

	public static void main(String[] argv) {
		LPExercise3c exercise = new LPExercise3c();
		System.out.println(exercise.getLP());
		System.out.println(exercise.solve());
	}
}
