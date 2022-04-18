package frameworks;

/**
 * 
 * Interfaces and organizes print structures into a uniformed printing system.
 * <li>
 * 
 * @author Joshua Ounalom (TheJodes)
 * @version 1.0.1
 */
public class ConsoleScript{

    private static String checkmark = new String(new byte[]{-30, -100, -123});
    private static String yellowBox = new String(new byte[]{-16, -97, -97, -88});
    private static String blueBox = new String(new byte[]{-16, -97, -97, -90});
    private static String redX = new String(new byte[]{-30, -99, -116});

    public static int errored = 0;
    public static int warned = 0;
	/**
	 * Debug mode.
	 * Determines whether output shall be displayed
	 */
    public static boolean enableMessages = true;
	/**
	 * Debug mode.
	 * Determines whether errors shall be displayed. Higher priority than enableMessages
	 */
    public static boolean enableErrors = true;
    private static long startedTime = System.currentTimeMillis();
    /**
     * Get the current time in time format.
     * @return
     */
    public static String getCurrentTimeFormat(){
        int elapsedTime = (int)((System.currentTimeMillis()-startedTime)/1000);
        int hour = elapsedTime/3600; 
        String formattedString = hour + ":";
        int minute = elapsedTime/60%60;
        if (minute >= 10){
            formattedString += minute + ":";
        }else
            formattedString += "0" + minute + ":";
        int second = elapsedTime%60;
        if (second >= 10){
            formattedString += second;
        }else
            formattedString += "0" + second;
        return formattedString;
    };
    /**
     * Standard print with no priority.
     * @param content
     */
    public static void print(String content){
        if (enableMessages == false)
            return;
        StackTraceElement[] traces = Thread.currentThread().getStackTrace();
        System.out.println("[" + getCurrentTimeFormat() + "][_"+checkmark+"_OUTPUT][" + traces[2].getClassName() + "][" + traces[2].getMethodName() + "]: " + content);
    }
    /**
     * Standard warn with some priority.
     * @param content
     */
    public static void warn(String content){
        if (enableMessages == false)
            return;
        StackTraceElement[] traces = Thread.currentThread().getStackTrace();
        warned ++;
        System.out.println("[" + getCurrentTimeFormat() + "][_"+yellowBox+"_WARN][" + traces[2].getClassName() + "][" + traces[2].getMethodName() + "]: " + content);
    }
    /**
     * Displays stack of an error message
     * @param content
     */
    public static void stack(StackTraceElement ers){
        if (enableMessages == false)
            return;
        StackTraceElement[] traces = Thread.currentThread().getStackTrace();
        System.out.println("[" + getCurrentTimeFormat() + "][_"+blueBox+"_Stack][" + traces[2].getClassName() + "][" + traces[2].getMethodName() + "]: " + ers);
    }
    /**
     * Standard error with high priority.
     * @param content
     */
    public static void error(String content){
        if (enableErrors == false && enableMessages == false)
            return;
        StackTraceElement[] traces = Thread.currentThread().getStackTrace();
        errored ++;
        System.out.println("[" + getCurrentTimeFormat() + "][_"+redX+"_ERROR][" + traces[2].getClassName() + "][" + traces[2].getMethodName() + "]: " + content);
    }
    /**
     * Standard error with high priority.
     * @param content
     */
    public static void error(StackTraceElement[] ers){
        for (StackTraceElement er: ers){
            error(er);
        }
    }
    /**
     * Displays stack of an error message
     * @param content
     */
    public static void stack(StackTraceElement[] ers){
        for (StackTraceElement er: ers){
            stack(er);
        }
    }
    /**
     * Standard error with high priority.
     * @param content
     */
    public static void error(Exception err){
        error(err.getClass().getName() + ": " + err.getMessage());
        stack(err.getStackTrace());
    }
    /**
     * Standard error with high priority.
     * @param content
     */
    public static void error(Object content){
        error(content + "");
    }
    /**
     * Standard print with no priority.
     * @param content
     */
    public static void print(Object content){
        print(content + "");
    }
    /**
     * Standard warn with some priority.
     * @param content
     */
    public static void warn(Object content){
        warn(content + "");
    }
}
