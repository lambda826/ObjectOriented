package state.template;

public abstract class State {

    private Machine machine;

    public State(Machine machine) {
        this.machine = machine;
    }

    public Machine getMachine() {
        return machine;
    }

    abstract void transition1();

    abstract void transition2();

}
