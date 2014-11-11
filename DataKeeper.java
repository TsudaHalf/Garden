public class DataKeeper {
    int data[] = new int[360];
    ReadWriteLock lock;

    public DataKeeper(ReadWriteLock lock) {
	this.lock = lock;
    }

    public void write(DataGenerator writer) {
	lock.beginWrite();
	for (int i=0; i<data.length; i++) {
	    data[i] = writer.get();
	    if(i%5==0) _delay();
	}
	lock.endWrite();
    }
    
    public void read(DataViewer reader) {
	lock.beginRead();
	for (int i=0; i<data.length; i++) {
	    reader.put(data[i]);
	    if(i%10==0) _delay();
	}
	lock.endRead();
    }

    private void _delay() {
	try {
	    Thread.sleep(10);
	} catch (InterruptedException e) {
	}
    }
}
