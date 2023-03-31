package com.daeseong.paging_test.Common;

public abstract class ThreadTask<T1, T2> implements Runnable {

    private static final String TAG = ThreadTask.class.getSimpleName();

    private T1 mParam;
    private T2 mResult;

    final public T2 execute(final T1 Param) {

        mParam = Param;
        Thread thread = new Thread(this);
        thread.start();

        try {
            thread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        return mResult;
    }

    @Override
    public void run() {

        //스레드 처리 내용 호출
        mResult = doInBackground(mParam);
    }

    //스레드 처리 내용 호출
    protected abstract T2 doInBackground(T1 Param);

    protected abstract String doInBackground(String sUrl);
}
