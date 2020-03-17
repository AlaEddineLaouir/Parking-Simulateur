import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 * Created by Mehdi on 27/12/2017.
 */
public class Plan {

    private final int NOMBRE_EMPLACEMENT=20;
    private final int NOMBRE_EMPLACEMENT_END=5;
    private ArrayList<ThreadVoiture> listDeClientAb=new ArrayList<>();
    private ArrayList<ThreadVoiture> listDesEndicape=new ArrayList<>();


    //---> Les Zones represnete le emlacement des voiture avant la porte d'entrer

    private Semaphore zone1=new Semaphore(01);
    private Semaphore zone2=new Semaphore(01);
    private Semaphore zone3=new Semaphore(01);
    private Semaphore zone4=new Semaphore(01);
    private Semaphore zone5=new Semaphore(01);
    private Semaphore zone6=new Semaphore(01);
    private Semaphore zone7=new Semaphore(01);
    private Semaphore zone8=new Semaphore(01);
    private Semaphore zone9=new Semaphore(01);
    private Semaphore zone10=new Semaphore(01);

    // --> La deuxiemLigne est utiliser par les gens Prioritair pour doublé les auters

    private Semaphore deuxiemeLigne=new Semaphore(1);

    //--> La sortie Principale est utiliser par les voitures pour sortir de park aprés le stationement

    private Semaphore sortiePrincipale=new Semaphore(1);

    //--> La Sortie d'attente est utiliser par les client qui ne veut plus stationé aprés une att
    //--> Elle est découper on trois pour garder la distance de securité enter les voitures

    private Semaphore sortieAtt1=new Semaphore(1);
    private Semaphore sortieAtt2=new Semaphore(1);
    private Semaphore sortieAtt3=new Semaphore(1);

    //--> Represente la zone ou les voiture ce stationne

    private Semaphore zoneDeStationementNormal =new Semaphore(NOMBRE_EMPLACEMENT);
    private Semaphore zoneDeStationementEndicape =new Semaphore(NOMBRE_EMPLACEMENT_END);



    public synchronized void occupéeZone(int i)
    {
        switch (i)
        {
            case 1:
                try{
                    zone1.acquire();
                }catch (InterruptedException ie){}
                break;
            case 2:
                try{
                    zone2.acquire();
                }catch (InterruptedException ie){}
                break;
            case 3:
                try{
                    zone3.acquire();
                }catch (InterruptedException ie){}
                break;
            case 4:
                try{
                    zone4.acquire();
                }catch (InterruptedException ie){}
                break;
            case 5:
                try{
                    zone5.acquire();
                }catch (InterruptedException ie){}
                break;
            case 6:
                try{
                    zone6.acquire();
                }catch (InterruptedException ie){}
                break;
            case 7:
                try{
                    zone7.acquire();
                }catch (InterruptedException ie){}
                break;
            case 8:
                try{
                    zone8.acquire();
                }catch (InterruptedException ie){}
                break;
            case 9:
                try{
                    zone9.acquire();
                }catch (InterruptedException ie){}
                break;
            case 10:
                try{
                    zone10.acquire();
                }catch (InterruptedException ie){}
                break;

        }
    }
    public  synchronized  void liberéeZone(int i)
    {
        switch (i)
        {
            case 1 : zone1.release();break;
            case 2 : zone2.release(); break;
            case 3 : zone3.release();break;
            case 4 : zone4.release(); break;
            case 5 : zone5.release();break;
            case 6 : zone6.release();break;
            case 7 : zone7.release();break;
            case 8 : zone8.release();break;
            case 9 : zone9.release();break;
            case 10: zone10.release();break;
        }
    }

    public synchronized void stationementNormal()
    {
       try{
           zoneDeStationementNormal.acquire();
       }catch (InterruptedException ie){}
    }
    public synchronized void stationementEndicape()
    {
        try{
            zoneDeStationementEndicape.acquire();
        }catch (InterruptedException ie){}
    }
    public synchronized void librerEmplacelent()
    {
        zoneDeStationementNormal.release();

    }
    public synchronized void librerEmplacementEndicape() {zoneDeStationementEndicape.release();}

    public synchronized  void occupéeDeuxiemLigne()
    {
        try{
            deuxiemeLigne.acquire();
        }catch (InterruptedException ie){}
    }
    public synchronized void librerDeuxiemLigne(){deuxiemeLigne.release();}



    public synchronized  void occupéeSortiePr(){try{sortiePrincipale.acquire();}catch (InterruptedException ie){}}
    public synchronized  void librerSortiePr(){sortiePrincipale.release();}

    public boolean existePasClientAb()
    {
        return listDeClientAb.isEmpty();
    }
    public boolean existePasClientEnd()
    {
        return listDesEndicape.isEmpty();
    }


    public synchronized void ajouterClientAb(ThreadVoiture tv)
    {
        if(existePasClientAb())
        {
            tv.setTour(true);
        }
        listDeClientAb.add(tv);
    }
    public synchronized void retirerLePremierClientAb()
    {
        listDeClientAb.get(0);
        if(!existePasClientAb())
        {
            ThreadVoiture tvNew=listDeClientAb.get(0);
            tvNew.setTour(true);
            listDeClientAb.add(0,tvNew);
        }

    }

    public synchronized void ajouterClientEnd(ThreadVoiture tv)
    {
        if(existePasClientEnd())
        {
            tv.setTour(true);
        }
        listDesEndicape.add(tv);
    }
    public synchronized void retirerLePremierClientEnd()
    {
        listDesEndicape.get(0);

        if(!existePasClientEnd())
        {
            ThreadVoiture tv=listDesEndicape.get(0);
            tv.setTour(true);
            listDesEndicape.add(0,tv);
        }
    }










    public synchronized void occupéesortieAtt1()
    {
        try{sortieAtt1.acquire();}catch (InterruptedException ie){}
    }
    public synchronized  void librersortieAtt1(){sortieAtt1.release();}

    public synchronized void occupéesortieAtt2()
    {
        try{sortieAtt2.acquire();}catch (InterruptedException ie){}
    }
    public synchronized  void librersortieAtt2(){sortieAtt2.release();}

    public synchronized void occupéesortieAtt3()
    {
        try{sortieAtt3.acquire();}catch (InterruptedException ie){}
    }
    public synchronized  void librersortieAtt3(){sortieAtt3.release();}

}
