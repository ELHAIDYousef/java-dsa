package oop;

import java.util.List;

public class Student {
    private String name;
    private List<Float> grades;

    public Student(String name, List<Float> grades) {
        this.name = name;
        this.grades = grades;
    }

    public float calculateAverage() {
        if (grades.isEmpty()) {
            return 0;
        }

        float sum = 0;
        for (float grade : grades) {
            sum += grade;
        }

        return sum / grades.size();
    }
}