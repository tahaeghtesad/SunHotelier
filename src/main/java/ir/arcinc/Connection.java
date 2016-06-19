package ir.arcinc;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import org.apache.log4j.Logger;

/**
 * Created by tahae on 6/18/2016.
 */
public class Connection {
    private Cluster cluster;
    private Session session;
    private Logger logger = Logger.getLogger(Connection.class);
    public Connection(){
        cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
        session = cluster.connect("sunhotelier");
    }

    public ResultSet execute(String cql, Object... params){
        logger.info(cql);
        return session.execute(cql,params);
    }
}
