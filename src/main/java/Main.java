public class Main {
    public static void main(String[] args) throws NoSuchFieldException, InterruptedException {
        run(args);
    }
    public static void run(String[] args) throws NoSuchFieldException, InterruptedException {
        Solution solution = new Solution();
        if (solution.validate(args) == 1) {
            System.out.println("solution correct!");
        } else {
            System.out.println("Solution wrong!");
        }
    }
}
