package com.IES.MiCarreraPerfecta.Utils;

import java.lang.Math;

public class DistanceCoordinates {

    // Radio de la Tierra en kilómetros
    private static final double RADIO_TIERRA = 6371.0;

    // Método para convertir grados a radianes
    private static double aRadianes(double valor) {
        return Math.toRadians(valor);
    }

    // Método para calcular la distancia entre dos puntos geográficos usando la fórmula de Haversine
    public static double distanciaHaversine(double latitud1, double longitud1, double latitud2, double longitud2) {
        double dLat = aRadianes(latitud2 - latitud1);
        double dLon = aRadianes(longitud2 - longitud1);

        double a = Math.pow(Math.sin(dLat / 2), 2) +
                   Math.cos(aRadianes(latitud1)) * Math.cos(aRadianes(latitud2)) *
                   Math.pow(Math.sin(dLon / 2), 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return RADIO_TIERRA * c;
    }

    // Método para encontrar la ubicación más cercana a una ubicación dada
    public double[] ubicacionMasCercana(double miLatitud, double miLongitud, double[][] ubicaciones) {
        double distanciaMinima = Double.POSITIVE_INFINITY;
        double[] ubicacionMasCercana = null;

        for (double[] ubicacion : ubicaciones) {
            double latitud = ubicacion[0];
            double longitud = ubicacion[1];
            double distancia = distanciaHaversine(miLatitud, miLongitud, latitud, longitud);
            if (distancia < distanciaMinima) {
                distanciaMinima = distancia;
                ubicacionMasCercana = ubicacion;
            }
        }

        return ubicacionMasCercana;
    }
    
}