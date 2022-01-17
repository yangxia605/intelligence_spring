public class MainClass {
    public static int falseNum=0;
    public static int testNum=0;
    public static void main(String[] args) {
        Solution solution = new Solution();

        ${exec}
        ifFalse(falseNum,testNum);
    }


//    public static void exec(int a, int b, int sum, long time, Solution solution) {
//        long start = System.currentTimeMillis();
//        isTrue(solution.solve(a, b) == sum, "test case error,please check your code");
//        long end = System.currentTimeMillis();
//        isTrue(end - start <= time, "execute timeout,please optimize your code");
//    }
public static void exec(int a, int b, int sum, long time, Solution solution) {
    long start = System.currentTimeMillis();
    falseCount(solution.solve(a, b) == sum);
    long end = System.currentTimeMillis();
    isTrue(end - start <= time, "execute timeout,please optimize your code");
    testNum++;
}

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }
    public static void ifFalse(int falseNum,int testNum) {
        if (falseNum > 0) {
            throw new IllegalArgumentException("Passed Case:"+(testNum-falseNum)+" ,Failed Case:"+falseNum);
        }
    }
    public static void falseCount(boolean expression) {
        if (!expression) {
            falseNum++;
        }
    }
}

${code}