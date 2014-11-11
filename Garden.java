
/*  Garden
// 開園、閉園を司るクラス。mainメソッドの起動と、ロックエラーを吐く部分を作る。
//
//　学生証番号 947181
//  氏名　　久谷 和輝



    */
public class Garden{
    protected static ReadWriteLock createLock(String lockClassName) {
	ReadWriteLock lock = null;
	try {
	    lock = (ReadWriteLock)Class.forName(lockClassName).newInstance();
	} catch (Exception e) {
	    System.err.println(e);
	    System.exit(1);
	}
	return lock;
    }

    public static void main(String[] args) {
	System.out.println("親スレッド　Garden起動。");//親スレッド起動アナウンス

	//ロック機能を初期化
	String lockType = args[0];
	System.out.println(lockType);//親スレッド起動アナウンス

	//順次プロセスを起動してゆく
	DataKeeper data = new DataKeeper(createLock(lockType));
	DataGenerator dg = new DataGenerator(data);
	DataViewer dv1 = new DataViewer(data);
	DataViewer dv2 = new DataViewer(data);

	dv1.start();
	dv2.start();
	dg.start();
    }
}
