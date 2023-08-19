package dao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import adt.*;
import entity.Course;
import java.io.*;
import java.io.File;

/**
 *
 * @author Chew Lip Sin
 */
public class CourseDAO<T> {

    private String fileName = "course.dat"; // For security and maintainability, should not have filename hardcoded here.

    public void saveToFile(ListInterface<T> list){
        File file = new File(fileName);
//        if (file.createNewFile()) {
//            System.out.println(fileName + " File Created");
//        } else {
//            System.out.println("File " + fileName + " already exists");
//        }
        try {
            ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
            ooStream.writeObject(list);
            ooStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nFile not found");
        } catch (IOException ex) {
            System.out.println("\nCannot save to file");
        }
    }

    public ListInterface<Course> retrieveFromFile() {
        File file = new File(fileName);
        ListInterface<Course> courseList = new ArrList<>();
        try {
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            courseList = (ArrList<Course>) (oiStream.readObject());
            oiStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nNo such file.");
        } catch (IOException ex) {
            System.out.println("\nCannot read from file.");
        } catch (ClassNotFoundException ex) {
            System.out.println("\nClass not found.");
        } finally {
            return courseList;
        }
    }
}