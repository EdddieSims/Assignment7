package com.edevstudios.driverstandings.domain;

import com.edevstudios.driverstandings.interfaces.ITrack;

/**
 * Created by Edmund.Simons on 2016/03/29.
 */
public class Track implements ITrack
{
    private Long id;
    private String country;
    private String trackName;
    private int numOfTurns;
    private double length;

    public Track(Builder builder)
    {
        this.id = builder.id;
        this.country = builder.country;
        this.trackName = builder.trackName;
        this.numOfTurns = builder.numOfTurns;
        this.length = builder.length;
    }

    public static class Builder
    {
        Long id;
        String country;
        String trackName;
        int numOfTurns;
        double length;

        public Builder(String value)
        {
            this.country = value;
        }

        public Builder id(Long value)
        {
            this.id = value;
            return this;
        }

        public Builder trackName(String value)
        {
            this.trackName = value;
            return this;
        }

        public Builder numOfTurns(int value)
        {
            this.numOfTurns = value;
            return this;
        }

        public Builder length(double value)
        {
            this.length = value;
            return this;
        }

        public Builder copy(Track value)
        {
            this.id = value.id;
            this.country = value.country;
            this.trackName = value.trackName;
            this.numOfTurns = value.numOfTurns;
            this.length = value.length;
            return this;
        }

        public Track build()
        {
            return new Track(this);
        }
    }

    public Long getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public double getLength() {
        return length;
    }

    public int getNumOfTurns() {
        return numOfTurns;
    }

    public String getTrackName() {
        return trackName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Track track = (Track) o;

        if (numOfTurns != track.numOfTurns) return false;
        if (Double.compare(track.length, length) != 0) return false;
        if (!id.equals(track.id)) return false;
        if (country != null ? !country.equals(track.country) : track.country != null) return false;
        return trackName != null ? trackName.equals(track.trackName) : track.trackName == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id.hashCode();
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (trackName != null ? trackName.hashCode() : 0);
        result = 31 * result + numOfTurns;
        temp = Double.doubleToLongBits(length);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Track{" +
                "trackName='" + trackName + '\'' +
                ", numOfTurns=" + numOfTurns +
                ", length=" + length +
                '}';
    }
}
