/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex7_productsstream;

import java.io.*;

/**
 *
 * @author oracle
 */
public class Ex7_productsStream {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        Product po1 = new Product("cod1", "parafusos", 3d);
        Product po2 = new Product("cod2", "cravos", 4d);
        Product po3 = new Product();

        //escritura
        FileOutputStream escribir = null;
        BufferedOutputStream escribirBuffer = null;
        DataOutputStream escribirData = null;
        //lectura
        FileInputStream leer = null;
        BufferedInputStream leerBuffer = null;
        DataInputStream leerData = null;

        escribir = new FileOutputStream("/home/oracle/Desktop/ex7/productos.txt");
        escribirBuffer = new BufferedOutputStream(escribir);
        escribirData = new DataOutputStream(escribirBuffer);

        escribirData.writeUTF(po1.getCodigo());
        escribirData.writeUTF(po1.getDescripcion());
        escribirData.writeDouble(po1.getPrecio());

        escribirData.writeUTF(po2.getCodigo());
        escribirData.writeUTF(po2.getDescripcion());
        escribirData.writeDouble(po2.getPrecio());

        escribirData.close();

        /*
         Para grabar en la instancia los valores de sus atributos, tenemos que 
         aprovechar que writeUTF deja en cada escritura los 2 bytes de margen
         Recordar que readUTF leerá hasta que se acabe la cadena UTF 
        
         */
        leer = new FileInputStream("/home/oracle/Desktop/ex7/productos.txt");
        leerBuffer = new BufferedInputStream(leer);
        leerData = new DataInputStream(leerBuffer);

        while (leerData.available() != 0) {
            po3.setCodigo(leerData.readUTF());
            po3.setDescripcion(leerData.readUTF());
            po3.setPrecio(leerData.readDouble());

            System.out.println(po3.getCodigo() + "\n" + po3.getDescripcion() + "\n" + po3.getPrecio());
        }

    }

}

class Product {

    private String codigo;
    private String descripcion;
    private Double precio;

    public Product() {

        codigo = "";
        descripcion = "";
        precio = 0d;

    }

    public Product(String codigo, String descripcion, Double precio) {

        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precio = precio;

    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

}
