import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.search.limits.SolutionCounter;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.util.criteria.Criterion;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static java.sql.DriverManager.println;

public class Aranjamente implements Factorial {

    public void solve(int n, int k) {

        Model model = new Model("Aranjamente");
        IntVar[] aranjs = new IntVar[k];
        for(int i = 0; i < k; i++)
            aranjs[i] = model.intVar("P" + i, 1, n);
        model.allDifferent(aranjs).post();
        System.out.println(model.getName());
        Solver solver = model.getSolver();
        Criterion solcpt = new SolutionCounter(model, factorial(n)/factorial(n - k));
        List<Solution> solutions = solver.findAllSolutions(solcpt);
        try {
            PrintWriter writer = new PrintWriter("aranjamente.txt", "UTF-8");
            writer.println(solutions.size());
            for(Solution s: solutions)
                writer.println(s);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

}
