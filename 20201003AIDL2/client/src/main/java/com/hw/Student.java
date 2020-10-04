package com.hw;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class Student implements Parcelable {
    private static final String TAG = "Student";

    private Integer id;
    private String name;

    public Student(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    //解包：读取包中的数据并封装成对象
    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            Log.i(TAG, "------------------>writeToParcel，解包");

            int id1 = in.readInt();
            String name1 = in.readString();
            return new Student(id1, name1);
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
