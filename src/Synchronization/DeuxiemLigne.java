package Synchronization;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Mehdi on 01/01/2018.
 */
public class DeuxiemLigne {

    private boolean occupied=false;
    Lock objectLock=new ReentrantLock(true);
    Condition deuxiemLigneOcc=objectLock.newCondition();

    public void occuperDeuxiemLigne()
    {
        objectLock.lock();
        try
        {
            while(occupied)
            {
                try{
                    deuxiemLigneOcc.await();
                }catch (InterruptedException ie){}
            }
            occupied=true;
        }finally {
            objectLock.unlock();
        }
    }

    public void librerDeuxiemLigne()
    {
        objectLock.lock();
        try{
            occupied=false;
            deuxiemLigneOcc.signal();
        }finally {
            objectLock.unlock();
        }

    }
}
