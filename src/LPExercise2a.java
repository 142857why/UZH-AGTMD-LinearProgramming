

import edu.harvard.econcs.jopt.solver.IMIP;
import edu.harvard.econcs.jopt.solver.IMIPResult;
import edu.harvard.econcs.jopt.solver.client.SolverClient;
import edu.harvard.econcs.jopt.solver.mip.*;

public class LPExercise2a {

	private IMIP linearProgram;

	public LPExercise2a() {
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
		linearProgram.addObjectiveTerm(0.63, x1);
		linearProgram.addObjectiveTerm(0.37, x2);

		Constraint c1 = new Constraint(CompareType.GEQ, 2);
		c1.addTerm(1, x1);
		c1.addTerm(-1, x2);
		linearProgram.add(c1);

		Constraint c2 = new Constraint(CompareType.EQ, 3);
		c2.addTerm(1, x1);
		c2.addTerm(0.25, x2);
		linearProgram.add(c2);

		Constraint c3 = new Constraint(CompareType.GEQ, 0);
		c3.addTerm(1, x1);
		linearProgram.add(c3);

		Constraint c4 = new Constraint(CompareType.GEQ, 0);
		c4.addTerm(1, x2);
		linearProgram.add(c4);

	}

	public IMIPResult solve() {
		SolverClient solverClient = new SolverClient();
		IMIPResult result = solverClient.solve(linearProgram);
		return result;
	}

	public static void main(String[] argv) {
		LPExercise2a exercise = new LPExercise2a();
		System.out.println(exercise.getLP());
		System.out.println(exercise.solve());
	}
}
