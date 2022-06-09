module intfc
{
    interface Subject
    {
        void attach(Object worker);
        void detach(Object worker);
    }   

    sequence<long> resoults;

    interface Worker
    {
        resoults resolveTask();
    }
}