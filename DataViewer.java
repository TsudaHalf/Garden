import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;

public class DataViewer extends Canvas implements Runnable {
    DataKeeper data;
    int values[];
    int x;
    int interval = -1000; // ���󥿡��Х�Ems]�����ʤ餽���͡���ʤ�Erandam �ǡ�
    Random random = new Random();

    public DataViewer(DataKeeper data) {
	this.data = data;
	setSize(361, 201);
    }

    public void start() {
	new Thread(this).start();
    }

    public void setInterval(int interval) {
	this.interval = interval;
    }

    public void put(int value) {
	synchronized (this) {
	    // ɽ�����̥���EåɤǼ¹Ԥ���E�EΤ�Ʊ��E���ɬ�ס�
	    values[x] = value;
	    x++;
	}
	repaint();
    }

    //// extends Canvas
    public void paint(Graphics g) {
	g.clearRect(0, 0, 360, 200);
	g.setColor(Color.white);
	g.drawRect(0, 0, 360, 200);
	g.drawLine(0, 100, 360, 100);
	if (values != null || x > 0) {
	    g.setColor(Color.green);
	    synchronized (this) {
		// �̥���Eåɤǥǡ�������������E�EΤ�Ʊ��E���ɬ�ס�
		for (int i=1; i<x; i++) {
		    g.drawLine(i-1, 100-values[i-1], i, 100-values[i]);
		}
		g.setColor(Color.red);
		g.drawLine(x, 0, x, 200);
	    }
	}
    }

    //// implements Runnable
    public void run() {
	while (true) {
	    x = 0;
	    repaint();
	    values = new int[360];
	    data.read(this);
	    try {
		int t = (interval < 0) ? random.nextInt(-interval) : interval;
		Thread.sleep(t);
	    } catch (InterruptedException e) {
	    }
	}
    }
}

