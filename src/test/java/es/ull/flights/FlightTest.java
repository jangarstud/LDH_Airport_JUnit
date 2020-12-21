package es.ull.flights;

import es.ull.passengers.Passenger;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/*! Clase de test para el scope de la clase Flight */
public class FlightTest {

    /**
     * Test para comprobar el regex de la clase Flight.
     *
     * En este test se comprueba cómo actúa el constructor de la clase Flight a la hora de crear un nuevo objeto.
     * Primeramente se crea un objeto erróneo controlando la excepción que debería lanzar el constructor.
     * Después se comprueba que efectivamente se ha podido crear el objeto y se ha podido añadir al vuelo.
     */
    @Test
    public void testFlightNumberRegex(){
        try{
            new Flight("CF\3542", 40);
        } catch (Exception e){
            assertEquals("Invalid flight number", e.getMessage());
        }

        assertEquals("CF234", new Flight("CF234", 25).getFlightNumber());

        Flight validFlight = new Flight("JI9832", 10);
        Passenger validPassenger = new Passenger("3325", "Pablo", "ES");

        validFlight.addPassenger(validPassenger);
        assertEquals(1, validFlight.getNumberOfPassengers());

    }

}
