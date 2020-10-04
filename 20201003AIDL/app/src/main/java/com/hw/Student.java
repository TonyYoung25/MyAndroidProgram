package com.hw;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//@Data
//@ToString
//@NoArgsConstructor
public class Student implements Parcelable {

    private static final String TAG = "StudentModel";

    private Integer id;
    private String name;

    public Student() {
    }

    public Student(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    //    protected Student(Parcel in) {
//    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        //解包：读取包中的数据并封装成对象
        @Override
        public Student createFromParcel(Parcel in) {
            Log.i(TAG, "------------------>writeToParcel，解包");
            int id = in.readInt();
            String name = in.readString();
            return new Student(id, name);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    //打包：将当前属性数据打包
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Log.i(TAG, "-------------------->writeToParcel，打包");
        dest.writeInt(id);
        dest.writeString(name);
    }
}
