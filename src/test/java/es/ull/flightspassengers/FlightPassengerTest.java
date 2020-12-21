package es.ull.flightspassengers;

import es.ull.flights.Flight;
import es.ull.passengers.Passenger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*! Clase de test para el la interacción entre objetos de la clase Passenger y la clase Passenger */
public class FlightPassengerTest {

    /**
     * Test para comprobar la adición de pasajeros al vuelo.
     *
     * En este test se comprueba la correcta adición de nuevos pasajeros al vuelo.
     * Esto significa comprobar también que no se puede exceder el número de asientos disponible.
     * La última operación mencionada lanzará una RuntimeException que se controlará con la correspondiente aserción.
     */
    @Test
    public void testAddPassenger() {
        Flight flight = new Flight("RO3251", 3);
        Passenger passenger = new Passenger("4825", "Alberto", "ES");

        assertTrue(flight.addPassenger(passenger));

        Passenger passenger2 = new Passenger("4532", "Daniel", "ES");
        assertTrue(flight.addPassenger(passenger2));

        Passenger passenger3 = new Passenger("8925", "Laura", "ES");
        try {
            flight.addPassenger(passenger3);
        } catch (RuntimeException rE) {
            assertEquals("Not enough seats for flight", rE.getMessage());
        }
    }

    /**
     * Test para eliminar pasajeros del vuelo.
     *
     * En este test se comprueba el correcto funcionamiento de la eliminación de pasajeros.
     * Para ello se añaden 2 pasajeros de un total máximo de 3 posibles.
     * Se comprueba que cuando se desea eliminar un pasajero en concreto la operación devuelve true o false.
     * El resultado dependerá de si el pasajero se encuentra en el vuelo o no.
     */
    @Test
    public void testRemovePassenger() {
        Flight flight = new Flight("RO3251", 3);
        Passenger passenger = new Passenger("4825", "Alberto", "ES");
        flight.addPassenger(passenger);
        Passenger passenger2 = new Passenger("4532", "Daniel", "ES");
        flight.addPassenger(passenger2);

        Passenger passenger3 = new Passenger("8925", "Laura", "ES");

        assertTrue(flight.removePassenger(passenger));
        assertFalse(flight.removePassenger(passenger3));
        assertTrue(flight.removePassenger(passenger2));
    }

    /**
     * Test para la transición de vuelos para un mismo pasajero.
     *
     * En este test se comprobará que un pasajero tiene la posibilidad de unirse a un vuelo siempre y cuando
     * el número de asientos disponible sea el suficiente.
     * El cambio ha de poder efectuarse independientemente del vuelo en el que se encuentre el pasajero.
     * En caso de no haber sitios disponibles, se controlará la excepción que devuelve el método joinFlight().
     */
    @Test
    public void testJoinFlight() {
        Flight flight = new Flight("RO3251", 3);
        Passenger passenger = new Passenger("4825", "Alberto", "ES");

        passenger.joinFlight(flight);
        assertEquals(flight, passenger.getFlight());

        Flight flight1 = new Flight("TE2385", 2);
        passenger.joinFlight(flight1);
        assertEquals(flight1, passenger.getFlight());


        Passenger passenger2 = new Passenger("4532", "Daniel", "ES");
        passenger2.joinFlight(flight1);
        Passenger passenger3 = new Passenger("8925", "Laura", "ES");

        try {
            passenger3.joinFlight(flight1);

        } catch (RuntimeException rE) {
            assertEquals("Not enough seats for flight " + flight1.getFlightNumber(), rE.getMessage());
        }

    }

    /**
     * Test para comprobar que el método getFlight() de la clase Passenger devuelve el vuelo asignado.
     *
     * En este test se comprueba que el método getFlight() de la clase Passenger devuelve el vuelo asignado al pasajero.
     * Para ello se comprueba que la referencia del vuelo que tiene el pasajero contenga el mismo identifier que el
     * vuelo al que se ha deseado incorporar.
     * Dado que el identifier es único, solo podrá ser correcto si el identificador es exactamente tal y como el que se
     * creó al inicializar el objeto Flight.
     */
    @Test
    public void testGetFlight() {
        Flight flight = new Flight("RO3251", 3);
        Passenger passenger = new Passenger("4825", "Alberto", "ES");
        flight.addPassenger(passenger);

        assertEquals(flight.getFlightNumber(), passenger.getFlight().getFlightNumber());
    }
}
