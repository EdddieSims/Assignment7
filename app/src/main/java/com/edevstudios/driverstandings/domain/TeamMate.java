package com.edevstudios.driverstandings.domain;

import com.edevstudios.driverstandings.interfaces.ITeamMate;

/**
 * Created by Edmund.Simons on 2016/03/29.
 */
public class TeamMate implements ITeamMate
{
    private Long id;
    private String name;
    private String surname;
    private String country;
    private String team;
    private int points;
    private int behind;
    private int numOfWins;

    public TeamMate(Builder builder)
    {
        this.id = builder.id;
        this.name = builder.name;
        this.surname = builder.surname;
        this.country = builder.country;
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
        String country;
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

        public Builder copy(TeamMate value)
        {
            this.id = value.id;
            this.name = value.name;
            this.surname = value.surname;
            this.country = value.country;
            this.team = value.team;
            this.points = value.points;
            this.behind = value.behind;
            this.numOfWins = value.numOfWins;
            return this;
        }

        public TeamMate build()
        {
            return new TeamMate(this);
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

        TeamMate teamMate = (TeamMate) o;

        if (points != teamMate.points) return false;
        if (behind != teamMate.behind) return false;
        if (numOfWins != teamMate.numOfWins) return false;
        if (!id.equals(teamMate.id)) return false;
        if (name != null ? !name.equals(teamMate.name) : teamMate.name != null) return false;
        if (surname != null ? !surname.equals(teamMate.surname) : teamMate.surname != null) return false;
        if (country != null ? !country.equals(teamMate.country) : teamMate.country != null) return false;
        return team != null ? team.equals(teamMate.team) : teamMate.team == null;

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
        return "TeamMate{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", country='" + country + '\'' +
                ", team='" + team + '\'' +
                ", points=" + points +
                ", behind=" + behind +
                ", numOfWins=" + numOfWins +
                '}';
    }
}
