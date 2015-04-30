package pa3;

import java.lang.*;
import java.io.*;
import java.util.*;

public class StarJava
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

    void compare(Star other)
    {
        Double otherCoord[] = other.getCoord();
        Double dist = getDist(otherCoord);
        updateStats(dist, other)
    }

    void updateStats(Double dist, Star other)
    {
        if(minDist == -1 || dist < minDist) minDist = dist;
        if(dist > maxDist)  maxDist = dist;
        avgDist = (avgDist*count + dist)/++count;
    }

    /*void getUpdate(Double dist)
    {
        if(minDist == -1 || dist < minDist) minDist = dist;
        if(dist > maxDist)  maxDist = dist;
        avgDist = (avgDist*count + dist)/++count;
    }*/

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
        standardOutput <- println("(" + x + "," + y + "," + z + ")");
        //standardOutput <- println("minDist = " + minDist);
        //standardOutput <- println("maxDist = " + maxDist);
        //standardOutput <- println("avgdist = " + avgDist);
        //standardOutput <- println("Compared " + count + " stars");
    }
}