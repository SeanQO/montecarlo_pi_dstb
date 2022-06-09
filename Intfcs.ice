module intfc
{
    interface Subject
    {
        void attach(Worker worker);
        void detach(Worker worker);
    }   

    interface worker
    {
        sequence<long> resolveTask();
    }
}