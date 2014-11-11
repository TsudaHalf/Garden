public class SmartestLock implements ReadWriteLock {
    int readingReaders = 0;
    int waitingWriters = 0;
    boolean preferWriter = true;
    boolean writing = false;

    public synchronized void beginRead() {
	while (writing || (preferWriter && waitingWriters > 0)) {
	    try {
		wait();
	    } catch (InterruptedException e) {
	    }
	}
	readingReaders++;
    }

    public synchronized void endRead() {
	readingReaders--;
	preferWriter = true;
	notifyAll();
    }

    public synchronized void beginWrite() {
	waitingWriters++;
	while (writing || readingReaders > 0) {
	    try {
		wait();
	    } catch (InterruptedException e) {
	    }
	}
	waitingWriters--;
	writing = true;
    }

    public synchronized void endWrite() {
	writing = false;
	preferWriter = false;
	notifyAll();
    }
}