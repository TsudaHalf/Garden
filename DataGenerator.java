import java.util.Random;

public class DataGenerator extends Thread {
    DataKeeper data;
    int x = 0;
    int p = 0;

    Random random = new Random();
    int interval = -1000; 

    public DataGenerator(DataKeeper data) {
	this.data = data;
    }

    public void setInterval(int interval) {
	this.interval = interval;
    }

    public int get() { //参考元では座標計算をしていたがカット。
	int y = (int)(100.0 * Math.sin((x-p)*Math.PI/180.0));
	x++;
	return y;
    }

    public void run() {
	while (true) {

	    x = 0;
	    data.write(this);
	    try {
		int t = (interval < 0) ? random.nextInt(-interval) : interval;
		Thread.sleep(t);
	    } catch (InterruptedException e) {
	    }
	    p = (p + 30) % 360;
	}
    }
}
