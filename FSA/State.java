package FSA;
import java.util.ArrayList;
import java.util.List;

public class State {
    private String name;
    private boolean isFinal;
    private List<Transition> transitions;

    public State(String name) {
        this.name = name;
        this.isFinal = false;
        this.transitions = new ArrayList<>();
    }

    public State(String name, boolean isFinal) {
        this.name = name;
        this.isFinal = isFinal;
        this.transitions = new ArrayList<>();
    }

    public void addTransition(char symbol, State nextState) {
        transitions.add(new Transition(symbol, nextState));
    }

    public String getName() {
        return name;
    }

    public Transition getNextState(char symbol) {
        for (Transition transition : transitions) {
            if (transition.getSymbol() == symbol) {
                return transition;
            }
        }
        return null;
    }

    public boolean isFinal() {
        return isFinal;
    }
}