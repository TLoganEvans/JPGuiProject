package GuiProject;

import java.sql.*;
import java.util.ArrayList;

/**
 * Author: Trevor Evans
 * Date: 06-Nov-18
 * Time: 5:54 PM
 * Description:
 */
public class dbHandler {

    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String dbURL = "jdbc:derby:OperatorData";

    private static Statement stmt = null;
    private static ResultSet rset = null;

    /**
     *
     * @return
     * @throws Exception
     */
    public static Connection createConnection() throws Exception {
        Class.forName(DRIVER);
        Connection conn = DriverManager.getConnection(dbURL);
        return conn;
    }

    /**
     *
     * @throws Exception
     */
    public static String[] getAtkOperator() throws Exception {

        String[] atkArr = new String[2];

        try{
            Connection conn = createConnection();
            stmt = conn.createStatement();
            rset = stmt.executeQuery("SELECT * FROM ATTACKERS ORDER BY RANDOM() OFFSET 0 ROWS " +
                                    "FETCH NEXT 1 ROW ONLY");
            while (rset.next()) {
                String name = rset.getString("NAME");
                String image = rset.getString("IMAGE");
                atkArr[0] = name;
                atkArr[1] = image;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return atkArr;
    }

    /**
     *
     * @throws Exception
     */
    public static String[] getDefOperator() throws Exception {

        String[] defArr = new String[2];

        try{
            Connection conn = createConnection();
            stmt = conn.createStatement();
            rset = stmt.executeQuery("SELECT * FROM DEFENDERS ORDER BY RANDOM() OFFSET 0 ROWS " +
                                    "FETCH NEXT 1 ROW ONLY");
            while (rset.next()) {
                String name = rset.getString("NAME");
                String image = rset.getString("IMAGE");
                defArr[0] = name;
                defArr[1] = image;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return defArr;
    }

}
