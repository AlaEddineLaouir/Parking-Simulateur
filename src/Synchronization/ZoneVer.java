package Synchronization;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Mehdi on 04/01/2018.
 */
public class ZoneVer {

    private boolean occupied=false;
    Lock objectLock=new ReentrantLock(true);
    Condition deuxiemLigneOcc=objectLock.newCondition();

    public void occuperZone()
    {
        objectLock.lock();

           try{
               while(occupied)
               {
                   deuxiemLigneOcc.await();

               }
               occupied=true;
           }catch (InterruptedException ie)
           {

           }
            finally {
               objectLock.unlock();
           }



    }

    public void librerZone()
    {
        objectLock.lock();

            occupied=false;
            deuxiemLigneOcc.signal();

        objectLock.unlock();


    }
}
