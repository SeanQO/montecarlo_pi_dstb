module intfc
{
    sequence<long> resoults;

    interface Worker
    {
        resoults resolveTask(long l, long seed);
        bool callback();
    }


    interface Server{
  
       void callCallBack(Worker* proxy);
       void attach(Worker* workerprx);
       void detach(Worker* workerprx);

    }	


    
}