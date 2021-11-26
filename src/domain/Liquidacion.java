package domain;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;

public class Liquidacion {

    private final int idLiquidacion;
    private Empleado[] empleados;
    private static int contadorLiquidaciones;

    private int tamanio;

    public Liquidacion() {
        this.idLiquidacion = ++Liquidacion.contadorLiquidaciones;
    }

    public int getTamanio() {
        return this.tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

    public Empleado getEmpleados(int pos) {
        return this.empleados[pos];
    }

    public void crearArreglo(int tamanio) {
        this.tamanio = tamanio;
        this.empleados = new Empleado[this.tamanio];
    }

    /**
     * Metodo para guardar los objetos de tipo empleado en el arreglo empleados
     */
    public void agregarEmpleado(Empleado empleado, int contador) {
        if (contador < this.empleados.length) {
            this.empleados[contador] = empleado;

        }
    }

    /**
     * Merodo para buscar un empleado por medio de la identificacion
     */
    public int buscarEmpleado(int idEmpleado) {
        int pos;
        for (int i = 0; i < this.empleados.length; i++) {
            if (this.empleados[i].getIdEmpleado() == idEmpleado) {
                pos = i;
                return pos;
            }
        }
        return -1;
    }

    /**
     * Metodo utilizado para almacenar la referencia del arreglo de empleados
     * entre instancias
     */
    public Empleado[] guardarEmpleados() {
        Empleado[] temporal = this.empleados;
        return temporal;
    }

    /**
     * Metodo para modificar el arreglo empleados entre instancias
     */
    public void modificarEmpleados(Empleado[] empleados) {
        this.empleados = empleados;
    }

    /**
     * Metodo usando para calcular los dias trabajados se resta la diferencia de
     * milesungos obtenidos de ambas fechas con el metodo getTime() de la clase
     * Date se restan y se divide entre la cantidad de milesungos de tiene un
     * dia 86400000 este valor se parsea a entero y devuelve in entero
     */
    public int diasTrabajados(Date fechaRetiro, int pos) {
        long tiempo;
        this.empleados[pos].setFechaRetiro(fechaRetiro);
        tiempo = fechaRetiro.getTime() - this.empleados[pos].getFechaIngreso().getTime();
        double resultado = tiempo / 86400000;
        int dias = (int) resultado;
        this.empleados[pos].setDiasLaborados(dias);
        return dias;
    }

    /**
     * Metodo para calcular la prima de servicio
     */
    public double calcularPrimaDeServicio(int pos) {
        double primaServicio = (this.empleados[pos].getSalario() * this.empleados[pos].getDiasLaborados()) / 360;
        return primaServicio;
    }

    /**
     * Metodo para calcular la cesantias
     */
    public double calcularCesantias(int pos) {
        double cesantias = (this.empleados[pos].getSalario() * this.empleados[pos].getDiasLaborados()) / 360;
        return cesantias;
    }

    /**
     * Metodo para calcular los intereses de cesantias
     */
    public double calcularInteresesCesantias(int pos) {
        double intereses = calcularCesantias(pos) * 0.12;
        return intereses;
    }

    /**
     * Metodo para calcular las vacaciones
     */
    public double calcularVacaciones(int pos) {
        double vacaciones = (this.empleados[pos].getSalario() * this.empleados[pos].getDiasLaborados()) / 720;
        return vacaciones;
    }

    /**
     * Metodo para el calculo total de la liquidacion
     */
    public double totalLiquidacion(int pos) {
        return calcularPrimaDeServicio(pos
        ) + calcularCesantias(pos
        ) + calcularInteresesCesantias(pos
        ) + calcularVacaciones(pos
        );
    }

    /**
     * Metodo para cargar cada valor de la liquidacio y el total en el arreglo
     * de empleados asi como tambine para establecer el estado de liquidacion en
     * true para diferenciarlos de los que aun no se les ha genrado la
     * liquidacion
     */
    public void generarLiquidacion(int pos) {
        this.empleados[pos].setPrimaServiciosEmpleado(calcularPrimaDeServicio(pos));
        this.empleados[pos].setCesantiasEmpleado(calcularCesantias(pos));
        this.empleados[pos].setIntereseCesantiaEmpleado(calcularInteresesCesantias(pos));
        this.empleados[pos].setVacacionesEmpleado(calcularVacaciones(pos));
        this.empleados[pos].setLiquidacionEmpleado(totalLiquidacion(pos));
        this.empleados[pos].setEstadoLiquidacion(true);
    }

    /**
     * Metodo para imprimir el informe de los empleados a los que les genero liquidacion
     */
    public String mostrarInforme() {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Locale locale = new Locale("es", "CO");
        NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
        int largo = this.empleados.length;
        String dato = "\n ***** INFORME EMPLEADOS A LOS QUE SE LES GENERO LIQUIDACION *****  \n" + "\n";
        for (int i = 0; i < largo; i++) {
            if (this.empleados[i].isEstadoLiquidacion() == true) {
                dato += "Identificacion: " + this.empleados[i].getIdEmpleado()
                        + " Nombre: " + this.empleados[i].getNombre()
                        + " Tipo de contarto: " + this.empleados[i].getTipoContrato()
                        + " Cargo: " + this.empleados[i].getCargo()
                        + " Salario: " + "$ " + numberFormat.format(this.empleados[i].getSalario())
                        + " Fecha de ingreso: " + df.format(this.empleados[i].getFechaIngreso())
                        + " Fecha de salida: " + df.format(this.empleados[i].getFechaRetiro())
                        + " Dias laborados: " + this.empleados[i].getDiasLaborados()
                        + " Valor liquidacion: " + "$ " + numberFormat.format(this.empleados[i].getLiquidacionEmpleado())
                        + "\n" + "----------------------------------------------------------------------------"
                        + "------------------------------------------------------------------------------------"
                        + "------------------------------------------------------------------------------------"
                        + "------------------------------------------------------------------------------------"
                        + "------------------------------------------------------------------------------------" + "\n";
            }

        }
        return dato;
    }


    /*
     * Metodo burbuja para ordenamiento del arreglo de empleados de forma alfabetica
     */
    public void sortBurbujaAlfabetico() {
        int total = this.empleados.length;
        /*
         * El metodo funciona como una matriz bidimensional, el primer for se posiciona sobre el primer elemento de la primerla fila y columnda
         * el segundo for compara el elemnto actual con el elemento superior osea el elemnto + 1
         * y va poscionando ese elemnto en la parte final del arreglo
         * el segundo for al iterar se le va restando la iteracion del primer for dado que los elementos de mitad del arreglo hacia atras se va ordenando
         * es por ello que el metodo solo itera hasta la mitada del arreglo 
         */
        for (int i = 0; i < total - 1; i++) {
            for (int j = 0; j < total - 1 - i; j++) {
                /*
                 * Ordena de manera decendente si quieras comparlo de manera acendente solo cambia el < por > 
                 * el metodo compareTo() devuelve siempre un numero de tipo int
                 * si es < que 0 es por que el objeto a comprar es menor
                 * si es > que 0 es por que el onjeto a comprar es mayor
                 * si es = que 0 es por que los objetos son iguales
                 * El algoritmo actual ordena de menor a mayor si quiren que sea al contrario cambien < por >
                 * Si quisieran orden con otro parametro ya sea la direccion, id o la edad
                 * tiene que cambiar donde dice .getNombre() por .getEdad(), .getDireccion() o .getId()
                 */
                if (((Comparable) this.empleados[j + 1].getNombre()).compareTo(this.empleados[j].getNombre()) < 0) {
                    /* 
                     * Si la condicion es verdadera entonces se establece como nuevo elemento menor el de una poscion superior
                     * por tal motivo se concatenan dentro del if la informacion referente a 
                     * la poscion actual con su respectivo dato con el nuevo valor menor y su posicion 
                     */
                    Empleado aux = this.empleados[j];
                    this.empleados[j] = this.empleados[j + 1];
                    this.empleados[j + 1] = aux;

                }
            }
        }
    }

    /**
     *Metodo burbuja para organizar el arreglo por la cantidad de dias laborados
     */
    public void sortBurbujaDias() {
        int total = this.empleados.length;
        /*
         * El metodo funciona como una matriz bidimensional, el primer for se posiciona sobre el primer elemento de la primerla fila y columnda
         * el segundo for compara el elemnto actual con el elemento superior osea el elemnto + 1
         * y va poscionando ese elemnto en la parte final del arreglo
         * el segundo for al iterar se le va restando la iteracion del primer for dado que los elementos de mitad del arreglo hacia atras se va ordenando
         * es por ello que el metodo solo itera hasta la mitada del arreglo 
         */
        for (int i = 0; i < total - 1; i++) {
            for (int j = 0; j < total - 1 - i; j++) {
                /*
                 * Ordena de manera decendente si quieras comparlo de manera acendente solo cambia el < por > 
                 * el metodo compareTo() devuelve siempre un numero de tipo int
                 * si es < que 0 es por que el objeto a comprar es menor
                 * si es > que 0 es por que el onjeto a comprar es mayor
                 * si es = que 0 es por que los objetos son iguales
                 * El algoritmo actual ordena de menor a mayor si quiren que sea al contrario cambien < por >
                 * Si quisieran orden con otro parametro ya sea la direccion, id o la edad
                 * tiene que cambiar donde dice .getNombre() por .getEdad(), .getDireccion() o .getId()
                 */
                if ((this.empleados[j + 1].getDiasLaborados() > this.empleados[j].getDiasLaborados())) {
                    /* 
                     * Si la condicion es verdadera entonces se establece como nuevo elemento menor el de una poscion superior
                     * por tal motivo se concatenan dentro del if la informacion referente a 
                     * la poscion actual con su respectivo dato con el nuevo valor menor y su posicion 
                     */
                    Empleado aux = this.empleados[j];
                    this.empleados[j] = this.empleados[j + 1];
                    this.empleados[j + 1] = aux;

                }
            }
        }
    }
}
