module intfc
{
    sequence<long> resoults;

    interface Worker
    {
        resoults resolveTask(long l);
        resoults callback(long r);
    }

    interface Subject
    {
        void attach(Worker* workerprx);
        void detach(Worker* workerprx);
    }   

    interface Server
    {
	void sendPoints(Worker* proxy, resoults r);
    }


    
}