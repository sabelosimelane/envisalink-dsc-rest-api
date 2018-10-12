/**
 * 
 */
package za.co.juba.liquibase;

import java.sql.Connection;

import javax.inject.Inject;

import com.concept.dbtools.DBAccessor;
import com.concept.mvc.navigation.controller.Controller;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.CompositeResourceAccessor;
import liquibase.resource.FileSystemResourceAccessor;

/**
 * @author f3557790 <sabelo.simelane@fnb.co.za>
 */
public class LiquibaseExecutor {

	private @Inject DBAccessor dbAccessor;

	public void execute() throws Exception {
		Connection con = dbAccessor.connect();
		JdbcConnection jdbcCon = new JdbcConnection(con);

		Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(jdbcCon);
		Liquibase liquibase = new Liquibase("db.changelog.xml",
				new CompositeResourceAccessor(
						new ClassLoaderResourceAccessor(), 
						new FileSystemResourceAccessor(Controller.serverPath + "WEB-INF/dbchanges/"), 
						new ClassLoaderResourceAccessor(Thread.currentThread().getContextClassLoader())),
				database);

		liquibase.update("public");

	}
}
