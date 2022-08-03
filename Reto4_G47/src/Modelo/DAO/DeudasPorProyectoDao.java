package Modelo.DAO;
import java.sql.*;
import Utilidades.*;

public class DeudasPorProyectoDao {
    public static ResultSet consulta(double limiteInferior) {
        Statement stmt=null;
        ResultSet rs=null;
        try {
            var conn=JDBCUtilities.getConnection();
            
            String csql="SELECT ID_Proyecto, SUM(Cantidad*Precio_Unidad) as VALOR FROM Compra JOIN MaterialConstruccion USING(ID_MaterialConstruccion) WHERE Pagado='No' GROUP BY ID_Proyecto HAVING VALOR>"+limiteInferior+" ORDER BY VALOR DESC;";
            
            stmt=conn.createStatement();
            rs=stmt.executeQuery(csql);

            
        } catch (Exception e) {
            //TODO: handle exception
        }
        return rs;        
    }
}
