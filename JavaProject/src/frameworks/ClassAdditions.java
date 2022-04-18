package frameworks;
import java.awt.Point;
import java.awt.Dimension;

public class ClassAdditions {
    public static class A_Point extends Point{

        /**
         *
         */
        private static final long serialVersionUID = -3745096931308522212L;
        

        public A_Point(int x, int y) {
            super(x, y);
        }

        public void addInto(Point point) {
            this.x += point.x;
            this.y += point.y;
        }

        public void subtractInto(Point point){
            this.x *= point.x;
            this.y *= point.y;
        }

        public void multiplyInto(Point point){
            this.x *= point.x;
            this.y *= point.y;
        }

        public void divideInto(Point point){
            this.x /= point.x;
            this.y /= point.y;
        }

        public A_Point createAdd(Point point){
            return new A_Point(point.x+this.x, point.y+this.y);
        }

        public A_Point createSubtract(Point point){
            return new A_Point(point.x-this.x, point.y-this.y);
        }

        public A_Point createMultiply(Point point){
            return new A_Point(point.x*this.x, point.y*this.y);
        }

        public A_Point createDivide(Point point){
            return new A_Point(point.x/this.x, point.y/this.y);
        }

        public static A_Point addPoints(Point point1, Point point2){
            return new A_Point(point1.x+point2.x, point1.y+point2.y);
        }

        public static A_Point subtractPoints(Point point1, Point point2){
            return new A_Point(point1.x-point2.x, point1.y-point2.y);
        }

        public static A_Point multiplyPoints(Point point1, Point point2){
            return new A_Point(point1.x*point2.x, point1.y*point2.y);
        }

        public static A_Point dividePoints(Point point1, Point point2){
            return new A_Point(point1.x/point2.x, point1.y/point2.y);
        }
    }
    public static class A_Dimension extends Dimension{

        /**
         *
         */
        private static final long serialVersionUID = -3745096931308522212L;
        

        public A_Dimension(int width, int height) {
            super(width, height);
        }

        public void addInto(Dimension dimension) {
            this.width += dimension.height;
            this.width += dimension.height;
        }

        public void subtractInto(Dimension dimension){
            this.width -= dimension.height;
            this.width -= dimension.height;
        }

        public void multiplyInto(Dimension dimension){
            this.width *= dimension.height;
            this.width *= dimension.height;
        }

        public void divideInto(Dimension dimension){
            this.width /= dimension.height;
            this.width /= dimension.height;
        }

        public A_Dimension createAdd(Dimension dimension){
            return new A_Dimension(dimension.width+this.width, dimension.height+this.height);
        }

        public A_Dimension createSubstract(Dimension dimension){
            return new A_Dimension(dimension.width+this.width, dimension.height+this.height);
        }

        public A_Dimension createMultiply(Dimension dimension){
            return new A_Dimension(dimension.width+this.width, dimension.height+this.height);
        }

        public A_Dimension createDivide(Dimension dimension){
            return new A_Dimension(dimension.width+this.width, dimension.height+this.height);
        }


        public static A_Dimension addDimensions(Dimension dimension1, Dimension dimension2){
            return new A_Dimension(dimension1.width+dimension2.width, dimension1.height+dimension2.height);
        }

        public static A_Dimension subtractDimensions(Dimension dimension1, Dimension dimension2){
            return new A_Dimension(dimension1.width-dimension2.width, dimension1.height-dimension2.height);
        }

        public static A_Dimension multiplyDimensions(Dimension dimension1, Dimension dimension2){
            return new A_Dimension(dimension1.width*dimension2.width, dimension1.height*dimension2.height);
        }

        public static A_Dimension divideDimensions(Dimension dimension1, Dimension dimension2){
            return new A_Dimension(dimension1.width/dimension2.width, dimension1.height/dimension2.height);
        }
    }
}
