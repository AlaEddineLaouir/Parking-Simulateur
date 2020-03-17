import Synchronization.ZoneDeStationement;
import Synchronization.ZoneVer;

import javax.swing.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Mehdi on 27/12/2017.
 */
public abstract class ThreadVoiture implements Runnable {

    protected static ZoneVer[] pattren=new ZoneVer[10];
    protected static ZoneDeStationement zoneDeStationement =new ZoneDeStationement();
    protected static ZoneVer sortiePrincipale=new ZoneVer();

    protected static ListDAttente fileDAttente=new ListDAttente();



    static final int CLIENT_ABONNEE=1;
    static final int CLIENT_ENDICAPE=0;
    static final int CLIENT_ORDINAIRE=2;


    protected static boolean avecTempDAttente;

    public static Plan plan;
    protected Voiture v;


    protected long tempDeStationnement;
    protected long tempsDEntrer;
    protected long tempsDattente;


    protected final Lock lock=new ReentrantLock(true);
    protected Condition existeClientAb=lock.newCondition();
    protected Condition zoneLibre=lock.newCondition();
    protected boolean tour=false;

    public ThreadVoiture( long tempDeStationnement, long tempsDEntrer, long tempsDattente) {

        this.tempDeStationnement = tempDeStationnement;
        this.tempsDEntrer = tempsDEntrer;
        this.tempsDattente = tempsDattente;

    }

    public void setTour(boolean tour) {
        this.tour = tour;
    }

 /* private void annuler()
    {
        plan.occupéeDeuxiemLigne();
        v.annulerStationnement1();
        plan.librerDeuxiemLigne();

        plan.occupéesortieAtt1();
        v.annulerStationnement2();
        plan.librersortieAtt1();

        plan.occupéesortieAtt2();
        v.annulerStationnement3();
        plan.occupéesortieAtt2();

        plan.occupéesortieAtt3();
        v.annulerStationnement4();
        plan.librersortieAtt3();
    }
*/
}
