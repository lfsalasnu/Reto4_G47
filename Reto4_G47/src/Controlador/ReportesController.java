package Controlador;
import vista.ReportesView;

public class ReportesController {
    // TODO Implementar la clase
    public static void main(String[] args) {
        var reportesView = new ReportesView();
        var banco = "Conavi"; 
        reportesView.proyectosFinanciadosPorBanco(banco);
        //var reportesView = new ReportesView(); 
        //var limiteInferior = 80_000d;
        //reportesView.totalAdeudadoPorProyectosSuperioresALimite(limiteInferior);
        //var reportesView = new ReportesView(); 
        //reportesView.lideresQueMasGastan();
    }
}