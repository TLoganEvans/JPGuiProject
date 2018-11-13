package GuiProject;

import java.sql.*;

/**
 * Author: Trevor Evans
 * Date: 06-Nov-18
 * Time: 5:54 PM
 * Description:
 */
class dbHandler {

    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String dbURL = "jdbc:derby:OperatorData";

    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rset = null;

    /**
     *
     * @return
     * @throws Exception
     */
    static Connection createConnection() throws Exception {
        Class.forName(DRIVER);
        conn = DriverManager.getConnection(dbURL);
        return conn;
    }

    /**
     *
     * @throws Exception
     */
    static String[] getAtkOperator() throws Exception {

        String[] atkArr = new String[2];

        try{
            conn = createConnection();
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
    static String[] getDefOperator() throws Exception {

        String[] defArr = new String[2];

        try{
            conn = createConnection();
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
