import java.sql.*;

public class usuarios {
    static final String DB_URL="jdbc:mysql://localhost/estudiantes";
    static final String USER="root";
    static final String PASS="";
    static final String QUERY="SELECT * FROM registro2";

    public static void main(String[] args) {
        try(
                Connection conn= DriverManager.getConnection(DB_URL,USER,PASS);
                Statement stmt= conn.createStatement();
                ResultSet rs= stmt.executeQuery(QUERY);
        ){
            while(rs.next()){
                System.out.println("Codigo: "+rs.getInt("Codigo"));
                System.out.println("Cedula: "+rs.getInt("Cedula"));
                System.out.println("Nombre: "+rs.getString("Nombre"));
                System.out.println("Fechanac: "+rs.getDate("Fechanac"));
                System.out.println("Signozodiacal: "+rs.getString("Signozodiacal"));

            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}

