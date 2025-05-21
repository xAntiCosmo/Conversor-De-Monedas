package com.aluracursos.conversor.principal;
import java.io.FileWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.aluracursos.conversor.modelos.Conversor;
import com.aluracursos.conversor.modelos.Moneda;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner Lectura = new Scanner(System.in);
        Integer opcion = 0;
        String Moneda1 = null;
        String Moneda2 = null;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Moneda> historial = new ArrayList<>();

        while (opcion != 7) {
            System.out.println("\tConversor de monedas\n");
            System.out.println("Escribe la opción de conversión que deseas: ");
            System.out.println("1) Dolar -> Peso Argentino");
            System.out.println("2) Peso Argentino -> Dolar");
            System.out.println("3) Dolar -> Real Brasileño");
            System.out.println("4) Real Brasileño -> Dolar");
            System.out.println("5) Dolar -> Peso Colombiano");
            System.out.println("6) Peso Colombiano -> Dolar");
            System.out.println("7) Salir");
            System.out.print("Elige tu opción: ");
            opcion = Lectura.nextInt();

            if (opcion == 7) {
                break;
            }

            switch (opcion) {
                case 1:
                    Moneda1 = "USD";
                    Moneda2 = "ARS";
                    break;
                case 2:
                    Moneda1 = "ARS";
                    Moneda2 = "USD";
                    break;
                case 3:
                    Moneda1 = "USD";
                    Moneda2 = "BRL";
                    break;
                case 4:
                    Moneda1 = "BRL";
                    Moneda2 = "USD";
                    break;
                case 5:
                    Moneda1 = "USD";
                    Moneda2 = "COP";
                    break;
                case 6:
                    Moneda1 = "COP";
                    Moneda2 = "USD";
                    break;
                default:
                    System.out.println("\nIngresa un valor válido!\n");
                    break;
            }
            
            String Direccion = "https://v6.exchangerate-api.com/v6/f09966c50abcf80cc5ed78da/pair/" + Moneda1 + "/" + Moneda2;

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(Direccion))
                .build();
            
            HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();
            Conversor miConversor = gson.fromJson(json, Conversor.class);
            Moneda miMoneda = new Moneda(miConversor);
            System.out.println(miMoneda);
            historial.add(miMoneda);
        }
        FileWriter historialTxt = new FileWriter("HistorialDeCambios.json");
        historialTxt.write(gson.toJson(historial));
        historialTxt.close();
        System.out.println("Finalizando....");
    }
}
