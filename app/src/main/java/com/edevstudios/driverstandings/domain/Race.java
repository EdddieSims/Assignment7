package com.edevstudios.driverstandings.domain;

import com.edevstudios.driverstandings.interfaces.IRace;

/**
 * Created by Edmund.Simons on 2016/06/04.
 */
public class Race implements IRace
{
    private Long id;
    private String trackName;
    private int laps;
    private Long winnerId;

    public Race(Builder builder)
    {
        this.id = builder.id;
        this.trackName = builder.trackName;
        this.laps = builder.laps;
        this.winnerId = builder.winnerId;
    }

    public static class Builder
    {
        private Long id;
        private String trackName;
        private int laps;
        private Long winnerId;

        public Builder(String value)
        {
            this.trackName = value;
        }

        public Builder id(Long value)
        {
            this.id = value;
            return this;
        }

        public Builder laps(int value)
        {
            this.laps = value;
            return this;
        }

        public Builder winnerId(Long value)
        {
            this.winnerId = value;
            return this;
        }

        public Builder copy(Race value)
        {
            this.id = value.id;
            this.trackName = value.trackName;
            this.laps = value.laps;
            this.winnerId = value.winnerId;
            return this;
        }

        public Race build()
        {
            return new Race(this);
        }
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getTrackName() {
        return trackName;
    }

    @Override
    public int getLaps() {
        return laps;
    }

    @Override
    public Long getWinnerId() {
        return winnerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Race race = (Race) o;

        if (laps != race.laps) return false;
        if (id != null ? !id.equals(race.id) : race.id != null) return false;
        if (trackName != null ? !trackName.equals(race.trackName) : race.trackName != null)
            return false;
        return winnerId != null ? winnerId.equals(race.winnerId) : race.winnerId == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (trackName != null ? trackName.hashCode() : 0);
        result = 31 * result + laps;
        result = 31 * result + (winnerId != null ? winnerId.hashCode() : 0);
        return result;
    }
}
