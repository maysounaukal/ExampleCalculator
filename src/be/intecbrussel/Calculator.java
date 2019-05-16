package be.intecbrussel;

public class Calculator {
private int value;
private int result;
private boolean done = true;
private boolean busy = false;
private Thread t;

public Calculator(){
    Thread thread = new Thread(() -> calculate());
    thread.setDaemon(true);
    thread.start();

}
public  synchronized  void setValue(int value){
    while (busy){
        try{
            wait();//gooit exception
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }this.value = value;
    done = false;
    busy = true;
    notifyAll();
}
public synchronized  int getResult(){
    while(!done){
        try{
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }busy = false;
    notifyAll();
    return  result;
}
private synchronized  void  calculate(){
    while(true) {
        while (done) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }result = value * value;
        done = true;
        notifyAll();

    }
}

    }
