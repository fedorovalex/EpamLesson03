package task.utils.predicate;

public class MoreHundred implements Predicate {

    @Override
    public boolean checkCondition(int number) {
        return number > 100;
    }
}
