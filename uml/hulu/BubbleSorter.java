package hulu;

public class BubbleSorter implements Sorter {

    private Hulu[] a;

    @Override
    public void load(Hulu[] a) {
        this.a = a;
    }

    private void swap(int i, int j) {
        Hulu temp;
        temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        plan += "" + a[i] + "<->" + a[j] + "\n";
    }

    private String plan = "";

    @Override
    public void sort() {
         int j;
        Hulu temp;
        for (int i = 1; i < a.length; i++) {
            if (a[i].compareTo(a[i - 1]) < 0) {
                temp = a[i];
                for (j = i - 1; j >= 0 && temp.compareTo(a[j]) < 0; j--)
                    swap(j, j + 1);
            }
        }
    }

    @Override
    public String getPlan() {
        return this.plan;
    }

}