import com.management.oop.project.core.TaskManagementSystemEngineImpl;

public class Startup {

    public static void main(String[] args) {
        TaskManagementSystemEngineImpl engine = new TaskManagementSystemEngineImpl();
            engine.start();
    }
}
