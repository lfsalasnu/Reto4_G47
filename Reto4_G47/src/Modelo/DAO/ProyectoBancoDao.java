package Modelo.DAO;
import java.sql.*;
import Utilidades.JDBCUtilities;
public class ProyectoBancoDao {
    public static ResultSet consulta(String banco) {
        ResultSet rs=null;
        try {
            
            var conn=JDBCUtilities.getConnection();
            Statement stmt=null;
            String csql="SELECT ID_Proyecto as ID, Constructora, Ciudad, Proyecto.Clasificacion, Estrato, Nombre||' '||Primer_Apellido||' '||Segundo_Apellido as LIDER FROM Proyecto JOIN Tipo USING (ID_Tipo) JOIN Lider USING (ID_Lider) WHERE Banco_Vinculado='"+banco+"' ORDER BY Fecha_Inicio DESC, Ciudad ASC, Constructora";    
            stmt=conn.createStatement();
            rs=stmt.executeQuery(csql);
        } catch (Exception e) {
            //TODO: handle exception
        }
        return rs;
    }
}
