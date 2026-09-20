package oop;

public class Rectangle {
    private double width;
    private double height;


    public Rectangle(double width, double height) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Dimensions cannot be negative.");
        }

        this.width = width;
        this.height = height;
    }


    public double area(){
        return this.height * this.width;
    }

    public double perimeter(){
        return 2 * (this.width + this.height);
    }



    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void setWidth(double width) {
        if (width < 0) {
            throw new IllegalArgumentException("Width cannot be negative.");
        }

        this.width = width;
    }

    public void setHeight(double height) {
        if (height < 0) {
            throw new IllegalArgumentException("Height cannot be negative.");
        }

        this.height = height;
    }


}
