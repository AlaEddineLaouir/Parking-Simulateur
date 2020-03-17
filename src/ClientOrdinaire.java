/**
 * Created by Mehdi on 29/12/2017.
 */
public class ClientOrdinaire extends ThreadVoiture {


    public ClientOrdinaire( long tempDeStationnement, long tempsDEntrer, long tempsDattente) {
        super(tempDeStationnement, tempsDEntrer, tempsDattente);
        v=new Voiture(CLIENT_ORDINAIRE);

    }

    @Override
    public void run() {

        parcoureDeLaLigne();
        //attendreTqExistePlusPrio();
        //stationnement();
        //sortirApresStationement();


    }
    private void parcoureDeLaLigne()
    {

       // for (int i=1;i<2;i++)
        //{

           // plan.occupéeZone(i);
            v.deplacerAvant();
            //if(i!=1)
               // plan.liberéeZone(i-1);
        //}
    }

    private void attendreTqExistePlusPrio()
    {
        while (!plan.existePasClientAb())
        {
            try{
                existeClientAb.await();
            }catch (InterruptedException ie){}
        }
    }
    private void stationnement()
    {
        plan.stationementNormal();
        v.stationné();
        try{
            Thread.sleep(tempDeStationnement);
        }catch (InterruptedException ie){}
    }

    private void sortirApresStationement()
    {
        plan.occupéeSortiePr();
        plan.librerEmplacelent();
        v.quitter();
        plan.librerSortiePr();
    }


}
