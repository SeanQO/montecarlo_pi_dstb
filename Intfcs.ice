module intfc
{
    sequence<long> resoults;

    interface Worker
    {
        bool callback();
    }


    interface Server{
  
       void callCallBack(Worker* proxy);
       void attach(Worker* workerprx);
       void detach(Worker* workerprx);

    }	


    
}