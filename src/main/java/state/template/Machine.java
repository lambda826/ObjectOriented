package state.template;

public class Machine {

    private final State currentState;
    private final State concreteState1;
    private final State concreteState2;

    public Machine() {
        this.concreteState1 = new ConcreteState1(this);
        this.concreteState2 = new ConcreteState2(this);
        currentState = concreteState1;
    }

    public void transition1() {
        currentState.transition1();
    }

    public void transition2() {
        currentState.transition2();
    }
}
