package UTN;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class DBPalabrasMysql {

    private Connection con;
    private Statement st;

    private static DBPalabrasMysql ourInstance;

    public static DBPalabrasMysql getInstance() {
        if(Objects.isNull(ourInstance))
            ourInstance = new DBPalabrasMysql();

        return ourInstance;
    }

    private DBPalabrasMysql() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            st = con.createStatement();
            st.execute("CREATE DATABASE IF NOT EXISTS ahorcado;");
            st.execute(""
                    + "CREATE TABLE IF NOT EXISTS `ahorcado`.`palabras` ("
                    + "  `palabra` varchar(50) DEFAULT '',"
                    + "  PRIMARY KEY (`palabra`)"
                    + ")");
            st.execute(""
                    + "CREATE TABLE IF NOT EXISTS `ahorcado`.`jugador` ("
                    + "  `id` bigint(20) AUTO_INCREMENT,"
                    + "  `nombre` varchar(50) DEFAULT NULL,"
                    + "  `palabra` varchar(50) DEFAULT NULL,"
                    + "  `fecha` datetime DEFAULT NOW(),"
                    + "  PRIMARY KEY (`id`)"
                    + ")");

            st.execute("INSERT IGNORE INTO `ahorcado`.`palabras` "
                    +"(palabra)"
                    +" VALUES "
                    +" ('Lobo')"
                    +",('Hija')"
                    +",('Vaca')"
                    +",('Cerezas')"
                    +",('Bocadillo')"
                    +",('Cinco')"
                    +",('Pintor')"
                    +",('Cuatro')"
                    +",('Arriba');");
            con.close();
            con = DriverManager.getConnection("jdbc:mysql://localhost/ahorcado?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
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
        try {
            String sql = "INSERT INTO `ahorcado`.`jugador`(nombre,palabra) VALUES (?,?) ";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, jugador.getNombre());
            st.setString(2, palabra);
            st.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
