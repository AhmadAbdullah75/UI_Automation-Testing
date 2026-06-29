package runner;

import org.junit.Test;
import java.net.URL;
import java.util.Enumeration;

public class DebugClasspath {
    @Test
    public void printResources() throws Exception {
        System.out.println("DEBUG: Scanning classpath for feature files...");
        Enumeration<URL> resources = getClass().getClassLoader().getResources("features");
        while (resources.hasMoreElements()) {
            System.out.println("Found 'features': " + resources.nextElement());
        }
        resources = getClass().getClassLoader().getResources("Features");
        while (resources.hasMoreElements()) {
            System.out.println("Found 'Features': " + resources.nextElement());
        }
        
        // specific file
        System.out.println("Looking for 'product.feature': " + getClass().getClassLoader().getResource("features/product.feature"));
        System.out.println("Looking for 'Features/product.feature': " + getClass().getClassLoader().getResource("Features/product.feature"));
    }
}
