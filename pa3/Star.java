package pa3;

import java.lang.*;
import java.io.*;
import java.util.*;
import java.io.Serializable;

public class Star implements Serializable
{
    Double x, y, z;
    Double minDist = -1.0, maxDist = 0.0;
    Double avgDist = 0.0;
    int count = 0;

    Star(Double _x, Double _y, Double _z)
    {
        x = _x;
        y = _y;
        z = _z;
    }

    Double getDist(Double otherData[])
    {
        return Math.sqrt(Math.pow(x-otherData[0],2) + Math.pow(y-otherData[1],2) + Math.pow(z-otherData[2],2));
    }

    void compare(Star trek)
    {
        Double distance = this.getDist(trek.getCoord());
        this.updateStats(distance);
        trek.updateStats(distance);
    }

    void updateStats(Double dist)
    {
        if(minDist == -1 || dist < minDist) minDist = dist;
        if(dist > maxDist)  maxDist = dist;
        avgDist = (avgDist*count + dist)/++count;
    }

    Double[] getCoord()
    {
        Double coord[] = new Double[3];
        coord[0] = x;
        coord[1] = y;
        coord[2] = z;
        return coord;
    }

    void setCoord(Double coord[])
    {
        x = coord[0];
        y = coord[1];
        z = coord[2];
    }

    Double getMinDist()
    {
        return minDist;
    }

    void setMinDist(Double dist)
    {
        minDist = dist;
    }

    Double getMaxDist()
    {
        return maxDist;
    }

    void setMaxDist(Double dist)
    {
        maxDist = dist;
    }

    Double getAvgDist()
    {
        return avgDist;
    }

    void setAvgDist(Double dist)
    {
        avgDist = dist;
    }
    void print()
    {
        System.out.println("(" + x + "," + y + "," + z + ")");
        System.out.println("minDist = " + minDist);
        System.out.println("maxDist = " + maxDist);
        System.out.println("avgdist = " + avgDist);
        System.out.println("Compared " + count + " stars");
    }
}