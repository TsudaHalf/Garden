public interface ReadWriteLock {
    public void beginRead();
    public void endRead();

    public void beginWrite();
    public void endWrite();
}