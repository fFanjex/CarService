package org.example.carservice.service;

import org.example.carservice.model.Car;
import org.example.carservice.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CarService {
    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> findAllCars() {
        return carRepository.findAll();
    }

    public Car createCar(Car car) {
        return carRepository.save(car);
    }

    public Car getByCarId(Long id) {
        return carRepository.findById(id).orElse(null);
    }

    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    public Car findByVinNumber(String vin) {
        return carRepository.findByVinNumber(vin);
    }

    public Car updateCar(Long id, Car carDetails) {
        return carRepository.findById(id)
                .map(car -> {
                    car.setVinNumber(carDetails.getVinNumber());
                    car.setBrand(car.getBrand());
                    car.setReleaseDate(carDetails.getReleaseDate());
                    car.setOwner(carDetails.getOwner());
                    return carRepository.save(car);
                })
                .orElseThrow(() -> new IllegalArgumentException("Car nor found"));
    }

    public List<Object[]> getCarStatistics() {
        return carRepository.getCarStatistics();
    }
}
