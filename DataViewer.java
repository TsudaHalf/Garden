
//　引用ソースが画像描画機能だったため、あまりにも大々的に改造して原型がなくなっている。

import java.util.Random;

public class DataViewer{
    DataKeeper data;
    int comes;
    int x;
    int interval = -1000;
    Random random = new Random();

    public DataViewer(DataKeeper data) {
	this.data = data;
    }

    public void start() {
	//	new Thread(this).start();
    }

    public void setInterval(int interval) {
	this.interval = interval;
    }

    public void put(int value) {
	synchronized (this) {
	    comes = value; //配列ではなく整数をプラス。
	    x++; //それにより、モニターは閲覧回数をカウントしてゆく。
	}
	//repaint();
    }

    /*
    //// extends Canvas
    public void paint(Graphics g) {
	g.clearRect(0, 0, 360, 200);
	g.setColor(Color.white);
	g.drawRect(0, 0, 360, 200);
	g.drawLine(0, 100, 360, 100);
	if (values != null || x > 0) {
	    g.setColor(Color.green);
	    synchronized (this) {
		for (int i=1; i<x; i++) {
		    g.drawLine(i-1, 100-values[i-1], i, 100-values[i]);
		}
		g.setColor(Color.red);
		g.drawLine(x, 0, x, 200);
	    }
	}
    }
    */

    //// implements Runnable
    public void run() {
	while (true) {
	    x = 0;
	    //repaint();
	    int comes;
	    data.read(this);
	    try {
		int t = (interval < 0) ? random.nextInt(-interval) : interval;
		Thread.sleep(t);
	    } catch (InterruptedException e) {
	    }
	}
    }
}
