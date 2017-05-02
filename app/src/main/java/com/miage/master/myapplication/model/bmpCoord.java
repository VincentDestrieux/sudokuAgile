package com.miage.master.myapplication.model;

/**
 * Created by azown on 28/04/2017.
 */

public class bmpCoord
{
    int x,y;

    public bmpCoord()
    {
        x=y=0;
    }

    public bmpCoord(int _x, int _y)
    {
        x=_x;
        y=_y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        bmpCoord bmpCoord = (bmpCoord) o;

        if (getX() != bmpCoord.getX()) return false;
        return getY() == bmpCoord.getY();

    }

    @Override
    public int hashCode() {
        int result = getX();
        result = 31 * result + getY();
        return result;
    }

    @Override
    public String toString() {
        return "bmpCoord{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
