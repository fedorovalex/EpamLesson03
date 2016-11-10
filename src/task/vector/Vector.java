package task.vector;

public class Vector {
    private int[] vector;

    public Vector(int size) {
        if (size < 0) {
            size = 0;
        }
        this.vector = new int[size];
    }

    public void setElement(int index, int value) {
        if (!checkElementPosition(index)) {
            throw new IllegalArgumentException("Неверныая позиция.");
        }
        this.vector[index] = value;
    }

    public int getElement(int index) {
        if (!checkElementPosition(index)) {
            throw new IllegalArgumentException("Неверныая позиция.");
        }
        return this.vector[index];
    }

    public int multiply(Vector right) {
        if (right.getSize() != this.getSize()) {
            throw new IllegalArgumentException("Не совпадают размеры векторов.");
        }
        int result = 0;
        for (int i = 0; i < getSize(); i++) {
            result += this.vector[i] * right.getElement(i);
        }
        return result;
    }

    public int getSize() {
        return this.vector.length;
    }

    private boolean checkElementPosition(int index) {
        return index >= 0 && index < getSize();
    }
}
