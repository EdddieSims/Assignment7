package com.edevstudios.driverstandings.domain;

import com.edevstudios.driverstandings.interfaces.ICar;

/**
 * Created by Edmund.Simons on 2016/03/29.
 */
public class Car implements ICar
{
    private Long id;
    private String make;
    private String model;
    private int year;

    public Car(Builder builder)
    {
        this.id = builder.id;
        this.make = builder.make;
        this.model = builder.model;
        this.year = builder.year;
    }

    public static class Builder
    {
        private Long id;
        private String make;
        private String model;
        private int year;

        public Builder(String make)
        {
            this.make = make;
        }

        public Builder id(Long id)
        {
            this.id = id;
            return this;
        }

        public Builder model(String model)
        {
            this.model = model;
            return this;
        }

        public Builder year(int year)
        {
            this.year = year;
            return this;
        }

        public Builder copy(Car value)
        {
            this.id = value.id;
            this.make = value.make;
            this.model = value.model;
            this.year = value.year;
            return this;
        }

        public Car build()
        {
            return new Car(this);
        }
    }

    public Long getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (year != car.year) return false;
        if (!id.equals(car.id)) return false;
        if (make != null ? !make.equals(car.make) : car.make != null) return false;
        return model != null ? model.equals(car.model) : car.model == null;

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (make != null ? make.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + year;
        return result;
    }

    @Override
    public String toString() {
        return "Car{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                '}';
    }
}
