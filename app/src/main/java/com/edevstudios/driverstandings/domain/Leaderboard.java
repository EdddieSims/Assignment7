package com.edevstudios.driverstandings.domain;

import com.edevstudios.driverstandings.interfaces.ILeaderBoard;

/**
 * Created by Edmund.Simons on 2016/03/29.
 */
public class Leaderboard implements ILeaderBoard
{
    private Long id;
    private String name;
    private String surname;
    private double fastestLapTime;
    private double currLapTime;
    private double totalRaceTime;
    private int totalLaps;
    private int currLap;
    private int lapsRemaining;

    public Leaderboard(Builder builder)
    {
        this.id = builder.id;
        this.name = builder.name;
        this.surname = builder.surname;
        this.fastestLapTime = builder.fastestLapTime;
        this.currLapTime = builder.currLapTime;
        this.totalRaceTime = builder.totalRaceTime;
        this.totalLaps = builder.totalLaps;
        this.currLap = builder.currLap;
        this.lapsRemaining = builder.lapsRemaining;
    }

    public static class Builder
    {
        Long id;
        String name;
        String surname;
        double fastestLapTime;
        double currLapTime;
        double totalRaceTime;
        int totalLaps;
        int currLap;
        int lapsRemaining;

        public Builder(String value)
        {
            this.name = value;
        }

        public Builder id(Long value)
        {
            this.id =value;
            return this;
        }

        public Builder surname(String value)
        {
            this.surname =value;
            return this;
        }

        public Builder fastestLapTime(double value)
        {
            this.fastestLapTime =value;
            return this;
        }

        public Builder currLapTime(double value)
        {
            this.currLapTime =value;
            return this;
        }

        public Builder totalRaceTime(double value)
        {
            this.totalRaceTime =value;
            return this;
        }

        public Builder totalLaps(int value)
        {
            this.totalLaps =value;
            return this;
        }

        public Builder currLap(int value)
        {
            this.currLap =value;
            return this;
        }

        public Builder lapsRemaining(int value)
        {
            this.lapsRemaining =value;
            return this;
        }

        public Builder copy(Leaderboard value)
        {
            this.id = value.id;
            this.name = value.name;
            this.surname = value.surname;
            this.fastestLapTime = value.fastestLapTime;
            this.currLapTime = value.currLapTime;
            this.totalRaceTime = value.totalRaceTime;
            this.totalLaps = value.totalLaps;
            this.currLap = value.currLap;
            this.lapsRemaining = value.lapsRemaining;
            return this;
        }

        public Leaderboard build()
        {
            return new Leaderboard(this);
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

    public double getFastestLapTime() {
        return fastestLapTime;
    }

    public double getCurrLapTime() {
        return currLapTime;
    }

    public double getTotalRaceTime() {
        return totalRaceTime;
    }

    public int getTotalLaps() {
        return totalLaps;
    }

    public int getCurrLap() {
        return currLap;
    }

    public int getLapsRemaining() {
        return lapsRemaining;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Leaderboard that = (Leaderboard) o;

        if (Double.compare(that.fastestLapTime, fastestLapTime) != 0) return false;
        if (Double.compare(that.currLapTime, currLapTime) != 0) return false;
        if (Double.compare(that.totalRaceTime, totalRaceTime) != 0) return false;
        if (totalLaps != that.totalLaps) return false;
        if (currLap != that.currLap) return false;
        if (lapsRemaining != that.lapsRemaining) return false;
        if (!id.equals(that.id)) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return surname != null ? surname.equals(that.surname) : that.surname == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        temp = Double.doubleToLongBits(fastestLapTime);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(currLapTime);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(totalRaceTime);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + totalLaps;
        result = 31 * result + currLap;
        result = 31 * result + lapsRemaining;
        return result;
    }

    @Override
    public String toString() {
        return "Leaderboard{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", fastestLapTime=" + fastestLapTime +
                ", currLapTime=" + currLapTime +
                ", totalRaceTime=" + totalRaceTime +
                ", totalLaps=" + totalLaps +
                ", currLap=" + currLap +
                ", lapsRemaining=" + lapsRemaining +
                '}';
    }
}
