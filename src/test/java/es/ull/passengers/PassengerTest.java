package es.ull.passengers;

import es.ull.flights.Flight;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*! Clase de test para el scope de la clase Passenger */
public class PassengerTest {

    /**
     * Test para comprobar la validación del código de país en el constructor.
     *
     * En este test se comprobará el algoritmo de validación del código ISO de país correspondiente a la máquina local.
     * De este modo, aquel pasajero creado con un código de país distinto a España (en este caso) deberá ocasionar
     * el lanzamiento de una excepción con el mensaje de "Invalid country code". En otro caso, el pasajero ha de poder
     * crearse sin ningún problema.
     */
    @Test
    public void testPassengerCountryCode(){
        try{
            new Passenger("5562", "Julian", "POR");
        } catch (Exception e){
            assertEquals("Invalid country code", e.getMessage());
        }

        Passenger juan = new Passenger("5534", "Juan", "ES");
        assertEquals("5534", juan.getIdentifier());
        assertEquals("Juan", juan.getName());
        assertEquals("ES", juan.getCountryCode());
    }

    /**
     * Test para la comprobación del método toString del pasajero.
     *
     * En este test se comprobará que todos los toString invocados para cualquier objeto passenger, devuelvan siempre
     * las propiedades 'name', 'identifier' y 'countryCode'.
     */
    @Test
    public void testToString(){
        Passenger passenger = new Passenger("4825", "Alberto", "ES");
        assertEquals("Passenger " + passenger.getName() + " with identifier: " + passenger.getIdentifier() + " from " + passenger.getCountryCode(), passenger.toString());
    }
}
