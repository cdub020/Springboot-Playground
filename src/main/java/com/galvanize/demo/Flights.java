package com.galvanize.demo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/")
public class Flights {

    @GetMapping("flights/flight")
    public Flight getFlight() {
        Flight flight1 = new Flight();
        Calendar cal = Calendar.getInstance();
        cal.set(2017, 03, 21, 07, 34);
        flight1.departs = cal;

        Flight.NewClass.Names newname = new Flight.NewClass.Names();
        newname.setFirstName("Some name");
        newname.setLastName("Some other name");

        List<Flight.NewClass> newflight = new ArrayList<>();
        newflight.add(new Flight.NewClass());
        newflight.get(0).setPrice(200);
        newflight.get(0).setPassenger(newname);
        flight1.setTickets(newflight);

        return flight1;
    }

    @GetMapping("flights")
        public List<Flight> getTheFlights(){
            Flight flight1 = new Flight();
            Calendar cal = Calendar.getInstance();
            cal.set(2017, 03, 21, 07, 34);
            flight1.departs = cal;
            Flight.NewClass.Names newname = new Flight.NewClass.Names();
            newname.setFirstName("Some name");
            newname.setLastName(null);

            List<Flight.NewClass> newflight = new ArrayList<>();
            newflight.add(new Flight.NewClass());
            newflight.get(0).setPrice(200);
            newflight.get(0).setPassenger(newname);
            flight1.setTickets(newflight);

            return Arrays.asList(flight1);
        }

    public static class Flight{
        private Calendar departs;
        private List<NewClass> tickets;

        @JsonProperty("Tickets")
        public List<NewClass> getTickets() {
            return tickets;
        }

        public void setTickets(List<NewClass> tickets) {
            this.tickets = tickets;
        }

        @JsonProperty("Departs")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        public Calendar getDeparts() {
            return departs;
        }

        public void setDeparts(Calendar departs) {
            this.departs = departs;
        }

        public static class NewClass{
            private Names passenger;
            private int price;

            @JsonProperty("Passenger")
            public Names getPassenger() {
                return passenger;
            }

            public void setPassenger(Names passenger) {
                this.passenger = passenger;
            }

            @JsonProperty("Price")
            public int getPrice() {
                return price;
            }
            public void setPrice(int price) {
                this.price = price;
            }
            public static class Names{
                private String firstName;
                private String lastName;

                @JsonProperty("FirstName")
                @JsonInclude(JsonInclude.Include.NON_NULL)
                public String getFirstName() {
                    return firstName;
                }

                public void setFirstName(String firstName) {
                    this.firstName = firstName;
                }
                @JsonProperty("LastName")
                @JsonInclude(JsonInclude.Include.NON_NULL)
                public String getLastName() {
                    return lastName;
                }

                public void setLastName(String lastName) {
                    this.lastName = lastName;
                }
            }
        }
    }
}
