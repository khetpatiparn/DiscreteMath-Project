public class FiniteMain {
    static boolean isAccepted = true;
    State startState;

    public FiniteMain(String input,Integer start) {
        //Create state
        State s0 = new State("s0");
        State s1 = new State("s1");
        State s2 = new State("s2", true); // final state

        // Define transitions
        s0.addTransition('0', s1);
        s0.addTransition('1', s0);
        s1.addTransition('0', s1);
        s1.addTransition('1', s2);
        s2.addTransition('0', s1);
        s2.addTransition('1', s0);

        checkFiniteAuto fn = new checkFiniteAuto();
        if(start == 0) {
            startState = s0;
        }else if(start == 1) {
            startState = s1;
        }else {
            startState = s2;
        }
        fn.defineAutomata(startState);
        sendAccept(fn, input);

        if (isAccepted) {
            System.out.println(input + " is ACCEPTED.");
        } else {
            System.out.println(input + " is REJECTED.");
        }


    }

    public boolean sendAccept(checkFiniteAuto fn, String input) {
        if(fn.isInputAccepted(input)){
            isAccepted = true;
        }else {
            isAccepted = false;
        }
        return isAccepted;
    }
}
