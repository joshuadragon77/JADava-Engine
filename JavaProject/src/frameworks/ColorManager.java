package frameworks;

public class ColorManager {
    public static int getColorValue(double red, double green, double blue){
        int redIntegerValue = (int)Math.floor(red * 256);
        int greenIntegerValue = (int)Math.floor(green * 256) * 256;
        int blueIntegerValue = (int)(Math.floor(blue * 256) * Math.pow(256, 2));

        return redIntegerValue + greenIntegerValue + blueIntegerValue;
    }

    public static int getRedValue(int color){
        return color%256;
    }

    public static int getGreenValue(int color){
        return (int)Math.floor((color/256)%256);
    }

    public static int getBlueValue(int color){
        return (int)Math.floor((color/Math.pow(256, 2))%256);
    }

}
