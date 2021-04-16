package com.daeseong.ThreadTask_test.Util;

public abstract class ThreadTask<T1, T2> implements Runnable {

    private T1 mParam;
    private T2 mResult;

    final public void execute(final T1 Param) {

        mParam = Param;
        Thread thread = new Thread(this);
        thread.start();

        try {

            thread.join();
        }catch (InterruptedException e){

            e.printStackTrace();
            onPostExecute(null);
            return;
        }

        onPostExecute(mResult);
    }

    @Override
    public void run() {
        mResult = doInBackground(mParam);
    }

    protected abstract T2 doInBackground(T1 Param);
    protected abstract void onPostExecute(T2 Result);
}