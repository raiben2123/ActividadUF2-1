package org.example;

import Model.Producto;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.TreeMap;

public class Servidor {
    public static void main(String[] args){
        TreeMap<String, Producto> productos = new TreeMap<String, Producto>();
        productos.put("PL",new Producto("Peras limoneras", 14, 5f));
        productos.put("PC",new Producto("Peras conferencia", 12, 7f));
        productos.put("PN",new Producto("Plátano canario", 5, 2.5f));
        productos.put("BN",new Producto("Bananas", 7, 1.3f));
        productos.put("TP",new Producto("Tomates tipo pera", 8, 1.7f));
        productos.put("TR",new Producto("Tomates Raf", 7, 5.3f));
        productos.put("UN",new Producto("Uvas negras", 8, 3.2f));
        productos.put("UB",new Producto("Uvas blancas", 5, 2.7f));
        productos.put("PT",new Producto("Picotas", 8, 4.3f));
        productos.put("CR",new Producto("Ciruelas rojas", 10, 2.8f));
        productos.put("MR",new Producto("Melocotones rojos", 3, 2.5f));
        productos.put("MA",new Producto("Melocotones amarillos", 4, 3.2f));
        String productoEncontrado;

        try {
            ServerSocket socket = new ServerSocket(25565);
            Socket socketCliente = new Socket();
            socketCliente = socket.accept();
            PrintWriter out = new PrintWriter(socketCliente.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));

            while (!socketCliente.isClosed()) {
                out.println("Bienvenido cliente, ¿que producto desea consultar?");
                String idProducto = in.readLine();
                productoEncontrado = "";
                for (var entry : productos.entrySet()) {
                    if (entry.getKey().equals(idProducto.toUpperCase())) {
                        productoEncontrado = (entry.getValue().getNombre() + " " + entry.getValue().getStock() + " " + entry.getValue().getPrecio());
                    }
                }
                if (productoEncontrado.isEmpty()) {
                    out.println("No se ha encontrado ningún producto con esa ID");
                } else {
                    out.println(productoEncontrado);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}