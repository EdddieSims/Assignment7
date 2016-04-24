package com.edevstudios.driverstandings.domain;

import com.edevstudios.driverstandings.interfaces.IStandings;

/**
 * Created by Edmund.Simons on 2016/03/29.
 */
public class Standings implements IStandings
{
    private Long id;
    private String name;
    private String surname;
    private String team;

    private int points;
    private int behind;
    private int numOfWins;

    public Standings(Builder builder)
    {
        this.id = builder.id;
        this.name = builder.name;
        this.surname = builder.surname;
        this.team = builder.team;
        this.points = builder.points;
        this.behind = builder.behind;
        this.numOfWins = builder.numOfWins;
    }

    public static class Builder
    {
        Long id;
        String name;
        String surname;
        String team;
        int points;
        int behind;
        int numOfWins;

        public Builder(String value)
        {
            this.name = value;
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

        public Builder copy(Standings value)
        {
            this.id = value.id;
            this.name = value.name;
            this.surname = value.surname;
            this.team = value.team;
            this.points = value.points;
            this.behind = value.behind;
            this.numOfWins = value.numOfWins;
            return this;
        }

        public Standings build()
        {
            return new Standings(this);
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

    public int getPoints() {
        return points;
    }

    public String getTeam() {
        return team;
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

        Standings standings = (Standings) o;

        if (points != standings.points) return false;
        if (behind != standings.behind) return false;
        if (numOfWins != standings.numOfWins) return false;
        if (!id.equals(standings.id)) return false;
        if (name != null ? !name.equals(standings.name) : standings.name != null) return false;
        if (surname != null ? !surname.equals(standings.surname) : standings.surname != null) return false;
        return team != null ? team.equals(standings.team) : standings.team == null;

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (team != null ? team.hashCode() : 0);
        result = 31 * result + points;
        result = 31 * result + behind;
        result = 31 * result + numOfWins;
        return result;
    }

    @Override
    public String toString() {
        return "Standings{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", team='" + team + '\'' +
                ", points=" + points +
                ", behind=" + behind +
                ", numOfWins=" + numOfWins +
                '}';
    }
}