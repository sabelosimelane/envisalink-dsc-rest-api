package za.co.juba;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Paths;
import java.util.Properties;

import javax.inject.Inject;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.concept.dbtools.DBAccessor;
import com.concept.dbtools.jdbc.JdbcStringHostCtxConfig;
import com.concept.mvc.navigation.controller.Controller;

/**
 * 
 * @author Sabelo Simelane <sabside@gmail.com>
 *
 */
@RunWith(Suite.class)
@SuiteClasses({	})
public class AllTests {
	
	public static String PROJECT_ROOT = DirectoryUtil.currentDir();
	private static @Inject DBAccessor dbAccessor;
	
	@Before
	public static void setup(DBAccessor dbAccessor) {
		try {
			Properties props = new Properties();
			props.load(new FileInputStream(new File("./src/test/za/co/juba/settings.properties")));
			
			System.out.println(Paths.get(".").toAbsolutePath());
			
			Controller.serverPath = props.getProperty("path");
			
			dbAccessor.setup(new JdbcStringHostCtxConfig("localhost", Integer.parseInt(props.getProperty("db.port")), props.getProperty("db.user"), 
					props.getProperty("db.password"), props.getProperty("db.test"), false, null));
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	@AfterClass
	public static void cleanup() {
		/*System.out.println("AFTER THE FACT!!!");
		try {
			Connection conn = dbAccessor.connect();
			dbAccessor.runUpdate(conn, "SELECT 'drop table if exists ' || tablename || ' cascade;' as pg_drop FROM pg_tables");
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
	}
}
