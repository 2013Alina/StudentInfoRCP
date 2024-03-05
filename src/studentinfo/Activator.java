package studentinfo;

import java.sql.SQLException;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.h2.tools.Server;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

    // The plug-in ID
    public static final String PLUGIN_ID = "StudentInfoRCP"; 

    // The shared instance
    private static Activator plugin;

    /**
     * The constructor
     */
    public Activator() {
    }

    @Override
    public void start(BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
        startH2Server();
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        plugin = null;
        stopH2Server();
        super.stop(context);
    }

    /**
     * Returns the shared instance
     *
     * @return the shared instance
     */
    public static Activator getDefault() {
        return plugin;
    }

    private void startH2Server() {
        try {
            Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stopH2Server() {
        // Остановить сервер H2
        try {
            Server.shutdownTcpServer("tcp://localhost:8082", "1234", true, true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
