package domain;

import java.util.Date;

public class Empleado extends Persona {

    private String cargo;
    private String email;
    private long telefono;
    private String tipoContrato;
    private double salario;
    private Date fechaIngreso;
    private int diasLaborados;
    private Date fechaRetiro;
    private final int idEmpleado;
    private boolean estadoLiquidacion;
    private double liquidacionEmpleado;
    private double primaServiciosEmpleado;
    private double cesantiasEmpleado;
    private double intereseCesantiaEmpleado;
    private double vacacionesEmpleado;
    private static int contadorEmpleado;

    public Empleado(String nombre, String direccion, Date fechaNacimiento, String tipoContrato, String cargo,
            String email, long telefono, double salario, Date fechaIngreso) {
        super(nombre, direccion, fechaNacimiento);
        this.idEmpleado = ++Empleado.contadorEmpleado;
        this.tipoContrato = tipoContrato;
        this.cargo = cargo;
        this.email = email;
        this.telefono = telefono;
        this.salario = salario;
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaRetiro() {
        return fechaRetiro;
    }

    public void setFechaRetiro(Date fechaRetiro) {
        this.fechaRetiro = fechaRetiro;
    }
    
    public int getDiasLaborados() {
        return this.diasLaborados;
    }

    public void setDiasLaborados(int diasLaborados) {
        this.diasLaborados = diasLaborados;
    }

    public String getCargo() {
        return this.cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getTelefono() {
        return this.telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public String getTipoContrato() {
        return this.tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public double getSalario() {
        return this.salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Date getFechaIngreso() {
        return this.fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public boolean isEstadoLiquidacion() {
        return this.estadoLiquidacion;
    }

    public void setEstadoLiquidacion(boolean estadoLiquidacion) {
        this.estadoLiquidacion = estadoLiquidacion;
    }

    public double getLiquidacionEmpleado() {
        return this.liquidacionEmpleado;
    }

    public void setLiquidacionEmpleado(double liquidacionEmpleado) {
        this.liquidacionEmpleado = liquidacionEmpleado;
    }

    public double getPrimaServiciosEmpleado() {
        return this.primaServiciosEmpleado;
    }

    public void setPrimaServiciosEmpleado(double primaServiciosEmpleado) {
        this.primaServiciosEmpleado = primaServiciosEmpleado;
    }

    public double getCesantiasEmpleado() {
        return this.cesantiasEmpleado;
    }

    public void setCesantiasEmpleado(double cesantiasEmpleado) {
        this.cesantiasEmpleado = cesantiasEmpleado;
    }

    public double getIntereseCesantiaEmpleado() {
        return this.intereseCesantiaEmpleado;
    }

    public void setIntereseCesantiaEmpleado(double intereseCesantiaEmpleado) {
        this.intereseCesantiaEmpleado = intereseCesantiaEmpleado;
    }

    public double getVacacionesEmpleado() {
        return this.vacacionesEmpleado;
    }

    public void setVacacionesEmpleado(double vacacionesEmpleado) {
        this.vacacionesEmpleado = vacacionesEmpleado;
    }

    public int getIdEmpleado() {
        return this.idEmpleado;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Empleado{cargo=").append(cargo);
        sb.append(", email=").append(email);
        sb.append(", telefono=").append(telefono);
        sb.append(", tipoContrato=").append(tipoContrato);
        sb.append(", salario=").append(salario);
        sb.append(", fechaIngreso=").append(fechaIngreso);
        sb.append(", idEmpleado=").append(idEmpleado);
        sb.append(", estadoLiquidacion=").append(estadoLiquidacion);
        sb.append('}');
        return sb.toString();
    }

    

}
