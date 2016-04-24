package com.edevstudios.driverstandings.domain;

import com.edevstudios.driverstandings.interfaces.IDriver;

/**
 * Created by Edmund.Simons on 2016/03/29.
 */
public class Driver implements IDriver
{
    private Long id;
    private String name;
    private String surname;
    private String country;
    private String team;
    private int points;
    private int behind;
    private int numOfWins;

    public Driver(Builder build)
    {
        this.id = build.id;
        this.name = build.name;
        this.surname = build.surname;
        this.country = build.country;
        this.team = build.team;
        this.points = build.points;
        this.behind = build.behind;
        this.numOfWins = build.numOfWins;
    }

    public static class Builder
    {
        private Long id;
        private String name;
        private String surname;
        private String country;
        private String team;

        private int points;
        private int behind;
        private int numOfWins;

        public Builder(String name)
        {
            this.name = name;
        }

        public Builder id(Long value)
        {
            this.id = value;
            return this;
        }

        public Builder surname(String value)
        {
            this.surname = value;
            return this;
        }

        public Builder country(String value)
        {
            this.country = value;
            return this;
        }

        public Builder team(String value)
        {
            this.team = value;
            return this;
        }

        public Builder points(int value)
        {
            this.points = value;
            return this;
        }

        public Builder behind(int value)
        {
            this.behind = value;
            return this;
        }

        public Builder numOfWins(int value)
        {
            this.numOfWins = value;
            return this;
        }

        public Builder copy(Driver driver)
        {
            this.id = driver.id;
            this.name = driver.name;
            this.surname = driver.surname;
            this.country = driver.country;
            this.team = driver.team;
            this.points = driver.points;
            this.behind = driver.behind;
            this.numOfWins = driver.numOfWins;
            return this;
        }

        public Driver build()
        {
            return new Driver(this);
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCountry() {
        return country;
    }

    public String getTeam() {
        return team;
    }

    public int getPoints() {
        return points;
    }

    public int getBehind() {
        return behind;
    }

    public int getNumOfWins() {
        return numOfWins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Driver driver = (Driver) o;

        if (points != driver.points) return false;
        if (behind != driver.behind) return false;
        if (numOfWins != driver.numOfWins) return false;
        if (!id.equals(driver.id)) return false;
        if (name != null ? !name.equals(driver.name) : driver.name != null) return false;
        if (surname != null ? !surname.equals(driver.surname) : driver.surname != null) return false;
        if (country != null ? !country.equals(driver.country) : driver.country != null) return false;
        return team != null ? team.equals(driver.team) : driver.team == null;

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (team != null ? team.hashCode() : 0);
        result = 31 * result + points;
        result = 31 * result + behind;
        result = 31 * result + numOfWins;
        return result;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", country='" + country + '\'' +
                ", team='" + team + '\'' +
                '}';
    }
}
