package FSA;
public class checkFiniteAuto {
    private State currentState;

    public void defineAutomata(State initialState) {
        currentState = initialState;
    }

    public boolean isInputAccepted(String input) {
        for (char ch : input.toCharArray()) {
            Transition nextTransition = currentState.getNextState(ch);
            if (nextTransition == null) {
                return false;
            }
            System.out.println("Transition: " + currentState.getName() + " -> " + nextTransition.getNextState().getName() + " on symbol '" + ch + "'");
            currentState = nextTransition.getNextState();
        }
        return currentState.isFinal();
    }
}