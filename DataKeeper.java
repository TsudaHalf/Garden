public class DataKeeper {
    int data;//テストプログラムなのでとりあえず1000名様限定とする。
    ReadWriteLock lock;

    public DataKeeper(ReadWriteLock lock) {
	this.lock = lock;//ロック状態であるフラグを立てておく。
    }

    public void write(DataGenerator writer) { //参考ソースでは配列だったが数字の加算に変更
	lock.beginWrite();//ロック開始
	for (int i=0; i<1000; i++) {
	    data = data+1;
	    if(i%5==0) _delay();
	}
	lock.endWrite();//ロック終了・解放
    }
    
    public void read(DataViewer reader) {//データを描画ではなく、コンソールへの出力とする。
	lock.beginRead();//ロック開始
	for (int i=0; i<1000; i++) {
	    System.out.println(data);//ここが変更点
	    if(i%10==0) _delay();
	}
	lock.endRead();//ロック終了・解放
    }

    private void _delay() {//描画時のアニメーション化のための遅延。　見やすさのために意図的にそのまま引用。
	try {
	    Thread.sleep(10);
	} catch (InterruptedException e) {
	}
    }
    /*
    public static void main(String[] args) {//デバッグ用スタンドアローン化のmain


    }
    */
}
