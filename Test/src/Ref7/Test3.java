package Ref7;

public class Test3 {

    @BeforeSuite
    public void taskBefore() {
        System.out.println (getClass ().getSimpleName () + " before ");
    }

    @Test(2)
    public void task1() {
        System.out.println (getClass ().getSimpleName () + " task1 ");
    }

    @Test(9)
    public void task2() {
        System.out.println (getClass ().getSimpleName () + " task2 ");
    }

    @Test(8)
    public void task3() {
        System.out.println (getClass ().getSimpleName () + " task3 ");

    }

    @Test(1)
    public void task4() {
        System.out.println (getClass ().getSimpleName () + " task4 ");
    }
}
