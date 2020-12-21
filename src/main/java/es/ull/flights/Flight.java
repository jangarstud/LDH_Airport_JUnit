/*
 * ========================================================================
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ========================================================================
 */
package es.ull.flights;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.ull.passengers.Passenger;

/*! Clase del vuelo */
public class Flight {

    private String flightNumber; /**< Una cadena. El número del vuelo. */
    private int seats; /**< Un entero. El número de asientos del vuelo. */
    private Set<Passenger> passengers = new HashSet<>(); /**< Una colección Set de pasajeros. Una colección de pasajeros del vuelo */

    private static String flightNumberRegex = "^[A-Z]{2}\\d{3,4}$"; /**< Una cadena. El regex para el número de vuelo. */
    private static Pattern pattern = Pattern.compile(flightNumberRegex); /**< Un objeto Pattern. El patrón del regex para flightNumberRegex. */

    /**
     * Constructor de la clase del vuelo.
     *
     * En este constructor se inicializa el objeto Flight siempre y cuando el número de vuelo se corresponda con el patrón
     * regex establecido. Esto evitará crear vuelos completamente inventados. En caso de no introducir un código de vuelo
     * correcto, se devolverá una excepción RuntimeException informando del mensaje "Invalid flight number".
     * @param flightNumber Una cadena. Valor que tomará el número de vuelo para el nuevo objeto vuelo.
     * @param seats Un entero. Valor que tomará el número de asientos para el nuevo objeto vuelo.
     */
    public Flight(String flightNumber, int seats) {
        Matcher matcher = pattern.matcher(flightNumber);
        if (!matcher.matches()) {
            throw new RuntimeException("Invalid flight number");
        }
        this.flightNumber = flightNumber;
        this.seats = seats;
    }

    /**
     * Getter del número de vuelo del vuelo.
     *
     * Este método devuelve el valor que tiene el objeto vuelo para el atributo 'flightNumber'
     * @return El número del vuelo
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * Getter del número de passajeros del vuelo.
     *
     * Este método devuelve el valor que tiene el objeto vuelo para el atributo 'passengers'
     * @return El número de pasajeros del vuelo
     */
    public int getNumberOfPassengers() {
        return passengers.size();
    }

    /**
     * Método que permite añadir al vuelo a un pasajero.
     *
     * En este método se añade a aquel pasajero que se le pase como parámetro. Es requisito imprescindible para poder añadirlo
     * el no exceder el cupo de asientos establecido para el vuelo. En ese caso se lanzará una excepción RuntimeException
     * con el mensaje "Not enough seats for flight" + "'flightNumber'". En otro caso, se añadirá al pasajero y se notificará
     * el añadido con un booleano.
     * @param passenger El pasajero que se desea añadir al vuelo
     * @return Un booleano informando del correcto funcionamiento añadiendo al pasajero
     */
    public boolean addPassenger(Passenger passenger) {
        if (getNumberOfPassengers() >= seats) {
            throw new RuntimeException("Not enough seats for flight " + getFlightNumber());
        }
        passenger.setFlight(this);
        return passengers.add(passenger);
    }

    /**
     * Método para eliminar a un pasajero del vuelo.
     *
     * En este método se elimina al pasajero que se haya pasado como parámetro.
     * Para ello se establece el valor del objeto Flight como 'null' para el pasajero específico.
     * Posteriormente se eliminará de la colección 'passengers' y se retornará un booleano informando del funcionamiento
     * de la operación.
     * @param passenger El pasajero que se desea eliminar del vuelo
     * @return Un booleano informando del correcto funcionamiento eliminado al pasajero
     */
    public boolean removePassenger(Passenger passenger) {
        passenger.setFlight(null);
        return passengers.remove(passenger);
    }
}
