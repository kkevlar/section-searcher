import java.util.List;
import java.util.ArrayList;

public class PlanFactory {

	public static Plan makePlan(String name, int id, ArrayList<Category> categories) {
		Plan plan = new Plan(name, id, categories); //need a way to determine id's
		return plan;
	}
}
