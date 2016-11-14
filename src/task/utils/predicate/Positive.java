package task.utils.predicate;

public class Positive implements Predicate {

    @Override
    public boolean checkCondition(int number) {
        return number > 0;
    }

}