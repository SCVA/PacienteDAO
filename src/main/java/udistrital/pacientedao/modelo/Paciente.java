/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udistrital.pacientedao.modelo;

import java.time.OffsetDateTime;

/**
 *
 * @author Sebastian
 */
public class Paciente {
    private Long cedula;                     // PK: numeric(11,0)
    private OffsetDateTime fechaNac;         // timestamp with time zone
    private String primerNombre;             // primern
    private String segundoNombre;            // segundon (puede ser null)
    private String primerApellido;           // primera
    private String segundoApellido;          // segundoa (puede ser null)

    public Paciente(Long cedula, OffsetDateTime fechaNac, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido) {
        this.cedula = cedula;
        this.fechaNac = fechaNac;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
    }

    public Long getCedula() {
        return cedula;
    }

    public void setCedula(Long cedula) {
        this.cedula = cedula;
    }

    public OffsetDateTime getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(OffsetDateTime fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }
    
    @Override
    public String toString() {
        return "Paciente{" +
               "cedula=" + cedula +
               ", fechaNac=" + fechaNac +
               ", primerNombre='" + primerNombre + '\'' +
               ", segundoNombre='" + segundoNombre + '\'' +
               ", primerApellido='" + primerApellido + '\'' +
               ", segundoApellido='" + segundoApellido + '\'' +
               '}';
    }
}