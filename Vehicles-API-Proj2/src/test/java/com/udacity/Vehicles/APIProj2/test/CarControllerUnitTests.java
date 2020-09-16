package com.udacity.Vehicles.APIProj2.test;

import com.udacity.Vehicles.APIProj2.api.CarController;
import com.udacity.Vehicles.APIProj2.domain.Condition;
import com.udacity.Vehicles.APIProj2.domain.Location;
import com.udacity.Vehicles.APIProj2.domain.car.Car;
import com.udacity.Vehicles.APIProj2.domain.car.Details;
import com.udacity.Vehicles.APIProj2.domain.manufacturer.Manufacturer;
import com.udacity.Vehicles.APIProj2.service.CarService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Implements testing of the CarController class.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class CarControllerUnitTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<Car> json;

    @MockBean
    private CarService carService;

    /**
     * Creates example car for testing.
     */
    @Before
    public void setup() {
        Car car = getCar();
        given(carService.save(any())).willReturn(car);
        given(carService.findById(any())).willReturn(car);
        given(carService.list()).willReturn(Collections.singletonList(car));
    }

    /**
     * Creation of car in the system check
     *
     * @throws Exception when car creation fails
     */
    @Test
    public void createCar() throws Exception {
        Car car = getCar();
        mvc.perform(
                post("/cars")
                        .content(json.write(car).getJson())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated());
    }


    @Test
    public void listCars() throws Exception {
        mvc.perform(
                get("/cars")
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }


    @Test
    public void findCar() throws Exception {
        Car car = getCar();
        mvc.perform(
                get("/cars/{id}", car.getId())
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }


    @Test
    public void deleteCar() throws Exception {
        Car car = getCar();
        mvc.perform(delete("/cars/{id}", car.getId()))
                .andExpect(status().isNoContent());
    }


    private Car getCar() {
        Car car = new Car();
        car.setId(1L);
        car.setLocation(new Location(56.045850, -37.935312));
        Details details = new Details();
        Manufacturer manufacturer = new Manufacturer(104, "Dodge");
        details.setManufacturer(manufacturer);
        details.setModel("Impala");
        details.setMileage(34087);
        details.setExternalColor("black");
        details.setBody("sedan");
        details.setEngine("4.0L V8");
        details.setFuelType("Petrol");
        details.setModelYear(2011);
        details.setProductionYear(2011);
        details.setNumberOfDoors(4);
        car.setDetails(details);
        car.setCondition(Condition.USED);
        return car;
    }
}