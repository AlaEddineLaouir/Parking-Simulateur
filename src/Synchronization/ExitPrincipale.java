package Synchronization;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Mehdi on 01/01/2018.
 */
public class ExitPrincipale {

    private Lock objectLock=new ReentrantLock(true);
    private Condition exitPrincipaleOcc=objectLock.newCondition();

    private boolean occuper=false;
}
