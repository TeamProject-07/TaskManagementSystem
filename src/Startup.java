import com.management.oop.project.core.TaskManagementSystemEngineImpl;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemEngine;
import com.management.oop.project.models.contracts.Bug;
import com.management.oop.project.models.contracts.Task;
import com.management.oop.project.models.tasks.FeedbackImpl;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Startup {

    public static void main(String[] args) {
        TaskManagementSystemEngineImpl engine = new TaskManagementSystemEngineImpl();
            engine.start();


    }
}
