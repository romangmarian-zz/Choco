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

public class Permutari implements Factorial {

    public void solve(int n) {

        Model model = new Model("Permutari");
        IntVar[] perms = new IntVar[n];
        for(int i = 0; i < n; i++)
            perms[i] = model.intVar("P" + i, 1, n);
        for(int i  = 0; i < n-1; i++)
            for(int j = i + 1; j < n; j++) {
                model.arithm(perms[i], "!=", perms[j]).post();
            }
        System.out.println(model.getName());
        Solver solver = model.getSolver();
        Criterion solcpt = new SolutionCounter(model, factorial(n));
        List<Solution> solutions = solver.findAllSolutions(solcpt);
        try {
            PrintWriter writer = new PrintWriter("permutari.txt", "UTF-8");
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
