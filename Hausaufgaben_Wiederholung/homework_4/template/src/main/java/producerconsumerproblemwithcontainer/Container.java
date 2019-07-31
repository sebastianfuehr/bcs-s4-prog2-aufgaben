package producerconsumerproblemwithcontainer;

import java.util.LinkedList;

public class Container extends LinkedList<Integer> {

    public synchronized Integer remove() throws NullPointerException {
        Integer temp = -1;
        //synchronized (this) {
            try {
                while (this.size() <= 0) this.wait();
                temp = super.remove();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                // TODO ?
            }
            this.notifyAll();
        //}
        return temp;
    }

    @Override
    public synchronized boolean add(Integer integer) {

            try {
                while (this.size() >= 1) this.wait();
                super.add(integer);
            } catch (InterruptedException e) {
                // TODO WTF?
            }
            this.notifyAll();
            return true;

    }

}
