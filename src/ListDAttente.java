import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Mehdi on 06/01/2018.
 */
public class ListDAttente {

    private Lock objectLock=new ReentrantLock(true);
    private Condition existeClientAbbonée=objectLock.newCondition();


    private ArrayList<Runnable> clientPrioritaire=new ArrayList<Runnable>();
    private ArrayList<ClientAbonnée> lesClientabonnée=new ArrayList<ClientAbonnée>();
    private ArrayList<ClientEndicape> lesClientEndicape=new ArrayList<ClientEndicape>();


    public void ajouterAbonnée(ClientAbonnée runnable)
    {
        objectLock.lock();
        clientPrioritaire.add(runnable);
        if(lesClientabonnée.size()==0)
        {
            runnable.setTour(true);
        }
        lesClientabonnée.add(runnable);
        System.out.println(clientPrioritaire);
        objectLock.unlock();
    }
    public void retirerabonnée(ClientAbonnée runnable)
    {
        objectLock.lock();
        System.out.println(lesClientabonnée);
        clientPrioritaire.remove(runnable);
        lesClientabonnée.remove(runnable);
        System.out.println(lesClientabonnée);
        if(!lesClientabonnée.isEmpty())
        {
            ClientAbonnée ca= lesClientabonnée.get(0);
            lesClientabonnée.remove(0);
            ca.setTour(true);
            lesClientabonnée.add(0,ca);
        }
        existeClientAbbonée.signal();
        objectLock.unlock();
    }

    public void ajouterEndicape(ClientEndicape runnable)
    {
        objectLock.lock();
        clientPrioritaire.add(runnable);
        if(lesClientEndicape.isEmpty())
        {
            runnable.setTour(true);
        }
        lesClientEndicape.add(runnable);
        objectLock.unlock();

    }
    public void retirerEndicape(ClientEndicape runnable)
    {
        objectLock.lock();
        clientPrioritaire.remove(runnable);
        lesClientEndicape.remove(runnable);
        if(!lesClientEndicape.isEmpty())
        {
            ClientEndicape ce= lesClientEndicape.get(0);
            ce.setTour(true);
            lesClientEndicape.add(0,ce);
        }
        existeClientAbbonée.signal();
        objectLock.unlock();
    }


    private boolean estVideDeClientPrio()
    {
        return clientPrioritaire.isEmpty();
    }

    public void confirmationDeTour()
    {
        objectLock.lock();
        try{
            while (!estVideDeClientPrio())
            {
                existeClientAbbonée.await();
            }
        }catch (InterruptedException ie)
        {}finally {
            objectLock.unlock();
        }
    }




}
