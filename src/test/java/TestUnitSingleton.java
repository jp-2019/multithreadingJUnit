import java.util.logging.Logger;
import org.junit.Assert;
import junit.framework.TestCase;

public class TestUnitSingleton  extends TestCase{
    private static Singleton s = null;
    private static Logger logger = Logger.getAnonymousLogger();

    public TestUnitSingleton(String name) {
        super(name);
    }

    public void setUp() {
        logger.info("getting singleton...");
        s = null;

    }
    public void testUnique() throws InterruptedException {
        // Both threads call Singleton.getInstance()
        // Both threads are allocated difference spaces in memory
        // Each thread gets a different reference number
        Thread threadOne = new Thread(new SingletonTestRunnable()),
                threadTwo = new Thread(new SingletonTestRunnable());
        threadOne.start();
        threadTwo.start();
        threadOne.join();
        threadTwo.join();
    }
    private static class SingletonTestRunnable implements Runnable {
        public void run(){
            //Get a reference to the singleton
            Singleton singleton = Singleton.getInstance();
            //Protect singleton member variable from
            // multithreaded access.
            synchronized (TestUnitSingleton.class){
                if(s == null) //If local reference is null..
                    s = singleton; //..set it to the singleton
            }
            //Local reference must be equal to the one and
            //only instance of Singleton; otherwise, we have two
            //Singleton instances.
            // Test fails if we assertEquals true because local
            // and static variables are not the same objects.
            Assert.assertEquals(true, singleton == s);
        }
    }
}