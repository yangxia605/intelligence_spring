public class MainClass {
    public static void main(String[] args) {
        Solution solution = new Solution();
        //根据用例文件，生成对应的多个调用
        ${exec}
    }

    //这个函数需要人工针对每道题写出来
    public static void exec(int t, int sa, int sb, int sc, int answer, long time, Solution solution) {
        long start = System.currentTimeMillis();
        isTrue(solution.solve(int t, int sa, int sb, int sc) == answer, "有测试用例不满足，请检查");
        long end = System.currentTimeMillis();
        isTrue(end - start <= time, "执行时间超出限制，请优化");
    }

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }
}

${code}