package dao;

import entity.Car;
import entity.CarDto;

import java.util.ArrayList;

public interface CarDao {
    void addCar(Car car);
    ArrayList<CarDto> findAllCars(int page);
    int findCount() throws Exception;

    ArrayList<String> findAllCarsSalesmenName();

    void DeleteCarById(int id);

    CarDto findCarById(int id);

    void updateCar(Car car);

    ArrayList<Car> findStaffAllCars(Integer sid);

    ArrayList<CarDto> findAllCarsCanBuy(int currPage);

}