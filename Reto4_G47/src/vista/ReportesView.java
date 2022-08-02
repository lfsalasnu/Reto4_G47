package vista;

import Utilidades.JDBCUtilities;
import java.sql.*;
//import Modelo.DAO.*;
import Modelo.VO.ProyectoBancoVo;

//import Modelo.DAO.ProyectoBancoDao;
public class ReportesView {
    private String repitaCaracter(Character caracter, Integer veces) {
        String respuesta = "";
        for (int i = 0; i < veces; i++) {
            respuesta += caracter;
        }
        return respuesta;
    }
        
    public void proyectosFinanciadosPorBanco(String banco) {
        System.out.println(repitaCaracter('=', 36) + " LISTADO DE PROYECTOS POR BANCO "
                            + repitaCaracter('=', 37));
        if (banco != null && !banco.isBlank()) {
            System.out.println(String.format("%3s %-25s %-20s %-15s %-7s %-30s", "ID", "CONSTRUCTORA", "CIUDAD", "CLASIFICACION", "ESTRATO", "LIDER"));
            System.out.println(repitaCaracter('-', 105));
            // TODO Imprimir en pantalla la información del proyecto
            ProyectoBancoVo.valores(banco);
            
        }
    }
        
    public void totalAdeudadoPorProyectosSuperioresALimite(Double limiteInferior) {
        System.out.println(repitaCaracter('=', 1) + " TOTAL DEUDAS POR PROYECTO "
                            + repitaCaracter('=', 1));
        if (limiteInferior != null) {
            System.out.println(String.format("%3s %14s", "ID", "VALOR "));
            System.out.println(repitaCaracter('-', 29));
            // TODO Imprimir en pantalla la información del total adeudado
        
            try {
                var conn=JDBCUtilities.getConnection();
                Statement stmt=null;
                ResultSet rs=null;
                String csql="SELECT ID_Proyecto, SUM(Cantidad*Precio_Unidad) as VALOR FROM Compra JOIN MaterialConstruccion USING(ID_MaterialConstruccion) WHERE Pagado='No' GROUP BY ID_Proyecto HAVING VALOR>"+limiteInferior+" ORDER BY VALOR DESC;";
                
                stmt=conn.createStatement();
                rs=stmt.executeQuery(csql);

                while(rs.next()){
                    int id=rs.getInt("ID_Proyecto");
                    Float valor=rs.getFloat("VALOR");
                    System.out.println(String.format("%3d %,15.1f", id, valor));
                }
                rs.close();
                stmt.close();
                conn.close();
            } catch (Exception e) {
                //TODO: handle exception
            }
        }
    }
        
    public void lideresQueMasGastan() {
        System.out.println(repitaCaracter('=', 6) + " 10 LIDERES MAS COMPRADORES "
                            + repitaCaracter('=', 7));
        System.out.println(String.format("%-25s %14s", "LIDER", "VALOR "));
        System.out.println(repitaCaracter('-', 41));
        // TODO Imprimir en pantalla la información de los líderes
        try {
            var conn=JDBCUtilities.getConnection();
            Statement stmt=null;
            ResultSet rs=null;
            String csql="SELECT l.Nombre ||' '||l.Primer_Apellido ||' '||l.Segundo_Apellido as LIDER, SUM(c.Cantidad*mc.Precio_Unidad) as VALOR FROM Proyecto p JOIN Lider l ON(p.ID_Lider=l.ID_Lider) JOIN Compra c ON(c.ID_Proyecto=p.ID_Proyecto) JOIN MaterialConstruccion mc ON(mc.ID_MaterialConstruccion=c.ID_MaterialConstruccion) GROUP BY LIDER ORDER BY VALOR DESC LIMIT 10;";
            
            stmt=conn.createStatement();
            rs=stmt.executeQuery(csql);

            while(rs.next()){
                String lider=rs.getString("LIDER");
                Float valor=rs.getFloat("VALOR");
                System.out.println(String.format("%-25s %,15.1f", lider, valor));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            //TODO: handle exception
        }
    
    }
}
