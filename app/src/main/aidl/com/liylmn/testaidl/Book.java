package com.liylmn.testaidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者：liy_lmn
 * 时间：2018/7/26:17:29
 * 邮箱：749960061@qq.com
 * 说明：日常工作或学习
 */

public class Book implements Parcelable {

    private String name;

    private int price;
    public Book(){}
    protected Book(Parcel in) {
        name = in.readString();
        price = in.readInt();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(price);
    }

    public void readFormParcel(Parcel dest){
        name=dest.readString();
        price=dest.readInt();
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
