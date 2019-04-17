package UTN;


import java.sql.*;

public class DBPalabrasMysql {

    private Connection con;
    private Statement st;

    private static DBPalabrasMysql ourInstance = new DBPalabrasMysql();

    public static DBPalabrasMysql getInstance() {
        return ourInstance;
    }

    private DBPalabrasMysql() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "");
            st = con.createStatement();
            st.execute("CREATE DATABASE IF NOT EXISTS ahoracado;");
            st.execute(""
                    + "CREATE TABLE IF NOT EXISTS `ahorcado`.`palabras` ("
                    + "  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT,"
                    + "  `palabra` varchar(50) DEFAULT NULL,"
                    + "  PRIMARY KEY (`id`)"
                    + ")");
            st.execute(""
                    + "CREATE TABLE IF NOT EXISTS `ahorcado`.`jugador` ("
                    + "  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT,"
                    + "  `nombre` varchar(50) DEFAULT NULL,"
                    + "  `palabra` varchar(50) DEFAULT NULL,"
                    + "  `fecha` datetime DEFAULT NULL,"
                    + "  PRIMARY KEY (`id`)"
                    + ")");

            st.execute("INSERT IGNORE INTO palabras "
                    +"(palabra)"
                    +"VALUES"
                    +"(Lobo)"
                    +"(Hija)"
                    +"(Vaca)"
                    +"(Cerezas)"
                    +"(Bocadillo)"
                    +"(Cinco)"
                    +"(Pintor)"
                    +"(Cuatro)"
                    +"(Arriba);");
            con.close();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/save", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String getPalabra(){

        String palabra = null;
        try {
            String sql = ("Select palabra from palabras ORDER BY RAND() LIMIT 1;");
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet resultSet = st.executeQuery();
            while(resultSet.next())
            {
                palabra = resultSet.getString("palabra");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return palabra;
    }

    public void guardarJugador(Jugador jugador, String palabra) {

    }
}
