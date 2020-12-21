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
package es.ull.passengers;

import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.ull.flights.Flight;

/*! Clase del pasajero */
public class Passenger {

    private String identifier; /**< Una cadena. El identificador del pasajero. */
    private String name; /**< Una cadena. El nombre del pasajero. */
    private String countryCode; /**< Una cadena. El código de país del pasajero. */
    private Flight flight; /**< Una objeto Flight. La referencia al vuelo del pasajero. */

    /**
     * Constructor de la clase del pasajero
     *
     * En este constructor se inicializa el objeto de la clase Passenger siempre y cuando se desee crear un nuevo
     * pasajero. Durante su creación se hace una validación del tercer parámetro, donde se comprobará que el código
     * de país introducido se corresponde con el de la máquina local.
     * @param identifier Una cadena. Valor que tomará el identificador para el nuevo objeto pasajero.
     * @param name Una cadena. Valor que tomará el nombre para el nuevo objeto pasajero.
     * @param countryCode Una cadena. Valor que tomará el código de país para el nuevo objeto pasajero.
     */
    public Passenger(String identifier, String name, String countryCode) {
        if (!Arrays.asList(Locale.getISOCountries()).contains(countryCode)) {
            throw new RuntimeException("Invalid country code");
        }

        this.identifier = identifier;
        this.name = name;
        this.countryCode = countryCode;
    }

    /**
     * Getter del identificador del pasajero.
     *
     * Este método devuelve el valor que tiene el objeto pasajero para el atributo 'identifier'
     * @return El identificador del pasajero
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Getter del nombre del pasajero.
     *
     * Este método devuelve el valor que tiene el objeto pasajero para el atributo 'nombre'
     * @return El nombre del pasajero
     */
    public String getName() {
        return name;
    }

    /**
     * Getter del código de país del pasajero.
     *
     * Este método devuelve el valor que tiene el objeto pasajero para el atributo 'countryCode'
     * @return El código de país del pasajero
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Getter del vuelo del pasajero.
     *
     * Este método devuelve el valor que tiene el objeto pasajero para el atributo 'flight'
     * @return La referencia al objeto vuelo del pasajero
     */
    public Flight getFlight() {
        return flight;
    }

    /**
     * Método que permite al pasajero incorporarse a un vuelo.
     *
     * En este método se incorpora al pasajero al vuelo deseado. No obstante, se han de tener en cuenta ciertos casos.
     * El vuelo no puede no existir (referencia 'null'). Del mismo modo, si el pasajero ya se encontraba en otro vuelo,
     * se le ha de eliminar de éste, por lo que también se ha de comprobar que el vuelo actual sea distinto de 'null'.
     * Finalmente, se añade al pasajero al vuelo. En caso de que no sea posible se lanza una RuntimeException.
     * Lo mismo para eliminar al pasajero.
     * @param flight El vuelo al que se desea incoroporar el pasajero
     */
    public void joinFlight(Flight flight) {
        Flight previousFlight = this.flight;
        if (null != previousFlight) {
            if (!previousFlight.removePassenger(this)) {
                throw new RuntimeException("Cannot remove passenger");
            }
        }
        setFlight(flight);
        if (null != flight) {
            if (!flight.addPassenger(this)) {
                throw new RuntimeException("Cannot add passenger");
            }
        }
    }

    /**
     * Setter del vuelo para el pasajero.
     *
     * Este método establece el nuevo valor que tomará el atributo 'flight' del pasajero.
     * @param flight El nuevo valor del vuelo del pasajero
     */
    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    /**
     * Refactorización del método toString del pasajero.
     *
     * En este método se especifica cómo se desea que actúe el toString del objeto Passenger. De esta forma se devolverá
     * una cadena con los atributos identificativos de la clase del pasajero: 'name', 'identifier' y 'countryCode'.
     * @return Una cadena con los atributos de nombre, identificador y código de país del pasajero
     */
    @Override
    public String toString() {
        return "Passenger " + getName() + " with identifier: " + getIdentifier() + " from " + getCountryCode();
    }
}
