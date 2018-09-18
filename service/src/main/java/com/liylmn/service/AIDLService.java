package com.liylmn.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.liylmn.testaidl.Book;
import com.liylmn.testaidl.BookManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：liy_lmn
 * 时间：2018/7/27:9:19
 * 邮箱：749960061@qq.com
 * 说明：日常工作或学习
 */

public class AIDLService extends Service {
    public final String TAG=this.getClass().getSimpleName();
    //包含Book对象的list
    private List<Book> mBooks=new ArrayList<>();
    //由AIDL生成的BookManager
    private final BookManager.Stub mBookManager=new BookManager.Stub() {
        @Override
        public List<Book> getBooks() throws RemoteException {
            synchronized (this){
                Log.e(TAG, "invoking getBooks() method , now the list is : " + mBooks.toString());
                if (mBooks != null) {
                    return mBooks;
                }
                return new ArrayList<>();
            }
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            synchronized (this) {
                if (mBooks == null) {
                    mBooks = new ArrayList<>();
                }
                if (book == null) {
                    Log.e(TAG, "Book is null in In");
                    book = new Book();
                }
                //尝试修改book的参数，主要是为了观察其到客户端的反馈
                book.setPrice(2333);
                if (!mBooks.contains(book)) {
                    mBooks.add(book);
                }
                //打印mBooks列表，观察客户端传过来的值
                Log.e(TAG, "invoking addBooks() method , now the list is : " + mBooks.toString());
//                Intent news = new Intent(AIDLService.this, MainActivity.class);
//
//                news.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Service跳转到Activity 要加这个标记
//
//                startActivity(news);
            }
        }
    };
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(getClass().getSimpleName(), String.format("on bind,intent = %s", intent.toString()));
        return mBookManager;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Book book = new Book();
        book.setName("Android开发艺术探索");
        book.setPrice(28);
        mBooks.add(book);

    }
}
