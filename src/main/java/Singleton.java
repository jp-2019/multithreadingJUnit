import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class Singleton {
    private static Singleton instance = null;
    private static Logger logger = Logger.getLogger("Test application");
    private static boolean firstThread = true;
    protected Singleton() {

    }
    public static Singleton getInstance() {
        if(instance == null) {
            simulateRandomActivity();
            instance = new Singleton();
        }
        return instance;
    }
    private static void simulateRandomActivity(){
        try {
            if (firstThread) {
                firstThread = false;
                // logs give you greater details about method that's failing
                // customize Level.
                logger.log(Level.INFO, "the thread is sleeping");
                logger.log(Level.WARNING, "for 50 seconds");
                // this map should give the second thread enough time
                // to get by the first thread.
                Thread.currentThread().sleep(50);
            }
        }
        catch (InterruptedException ex) {
            logger.log(new LogRecord(Level.SEVERE, ex.getMessage()));
        }
    }
}
