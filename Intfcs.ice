module intfc
{
    sequence<long> resoults;

    interface Worker
    {
        resoults resolveTask(long l);
        bool callback();
    }


    interface Server{
  
       void callCallBack(Worker* proxy);
       void attach(Worker* workerprx);
       void detach(Worker* workerprx);

    }	


    
}