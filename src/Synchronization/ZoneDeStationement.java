package Synchronization;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Mehdi on 01/01/2018.
 */
public class ZoneDeStationement {

    private final int NOMBRE_EMPLACEMENT=10;
    private final int NOMBRE_EMPLACEMENT_END=5;

    private int countPlaceNormalOcc=0;
    private int countPalceEndicaOcc=0;


    //--> Represente la zone ou les voiture ce stationne

    private Lock objectLock= new ReentrantLock(true);
    private Condition placeNormalDispo=objectLock.newCondition();
    private Condition placeEndicapeDispo=objectLock.newCondition();

    public  void stationementNormal()
    {
        objectLock.lock();
        try{
            while (countPlaceNormalOcc==NOMBRE_EMPLACEMENT)
            {
                try{
                    placeNormalDispo.await();
                }catch (InterruptedException ie){}
            }
            countPlaceNormalOcc++;
        }finally {
            objectLock.unlock();
        }
    }
    public  void stationementEndicape()
    {
        objectLock.lock();
        try{
            while (countPalceEndicaOcc==NOMBRE_EMPLACEMENT_END)
            {
                try{
                    placeEndicapeDispo.await();
                }catch (InterruptedException ie){}
            }
            countPalceEndicaOcc++;
        }finally {
            objectLock.unlock();
        }
    }
    public  void librerEmplacelent()
    {
        objectLock.lock();
        try{
            countPlaceNormalOcc--;
            placeNormalDispo.signal();
        }finally {
            objectLock.unlock();
        }

    }
    public  void librerEmplacementEndicape() {
        objectLock.lock();
        try
        {
            countPalceEndicaOcc--;
            placeEndicapeDispo.signal();
        }finally {
            objectLock.unlock();
        }
    }
}
