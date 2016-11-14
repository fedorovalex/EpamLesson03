package task.utils.predicate;

public class Parity implements Predicate {

    @Override
    public boolean checkCondition(int number) {
        return number % 2 == 0;
    }
}
