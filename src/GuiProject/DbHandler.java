package GuiProject;

/*
 * Author: Trevor Evans
 * Date: 06-Nov-18
 * Time: 5:54 PM
 * Description: File that handles methods of connecting and reading from the embedded Derby database.
 */

import java.sql.*;

class DbHandler {

  private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
  private static final String dbURL = "jdbc:derby:OperatorData";

  private static Connection conn = null;
  private static Statement stmt = null;
  private static ResultSet rset = null;

  /**
   * Method to establish connection to embedded Derby database.
   *
   * @return conn - used in following methods to create a connection to the database and allow for
   *     querying.
   * @throws Exception - SQL exception should the database not be found/error.
   */
  static Connection createConnection() throws Exception {
    Class.forName(DRIVER);
    conn = DriverManager.getConnection(dbURL);
    return conn;
  }

  /**
   * Method to query embedded Derby database's table "ATTACKERS", returns a single random attacking
   * operator's information via String array.
   *
   * @return atkArr - String array holding information for selected attacker operator.
   * @throws Exception - SQL exception should the database not be found/error.
   */
  static String[] getAtkOperator() throws Exception {

    String[] atkArr = new String[2];

    try {
      conn = createConnection();
      stmt = conn.createStatement();
      rset =
          stmt.executeQuery(
              "SELECT * FROM ATTACKERS ORDER BY RANDOM() OFFSET 0 ROWS " + "FETCH NEXT 1 ROW ONLY");
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
   * Method to query embedded Derby database's table "ATTACKERS", returns a single random defending
   * operator's information via String array.
   *
   * @return atkArr - String array holding information for selected defender operator.
   * @throws Exception - SQL exception should the database not be found/error.
   */
  static String[] getDefOperator() throws Exception {

    String[] defArr = new String[2];

    try {
      conn = createConnection();
      stmt = conn.createStatement();
      rset =
          stmt.executeQuery(
              "SELECT * FROM DEFENDERS ORDER BY RANDOM() OFFSET 0 ROWS " + "FETCH NEXT 1 ROW ONLY");
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
