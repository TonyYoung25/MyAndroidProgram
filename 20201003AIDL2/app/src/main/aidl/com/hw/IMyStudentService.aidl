// IMyStudentService.aidl
package com.hw;

// Declare any non-default types here with import statements
import com.hw.Student;

interface IMyStudentService {

    Student getStudentInfo(int id);
}
