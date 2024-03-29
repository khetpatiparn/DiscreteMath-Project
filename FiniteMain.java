public class FiniteMain {
    public static void main(String[] args) {
        // Create state
        State s0 = new State("s0");
        State s1 = new State("s1");
        State s2 = new State("s1", true); // final state

        // Define transitions
        s0.addTransition('0', s1);
        s0.addTransition('1', s0);
        s1.addTransition('0', s1);
        s1.addTransition('1', s2);
        s2.addTransition('0', s1);
        s2.addTransition('1', s0);

        checkFiniteAuto fn = new checkFiniteAuto();
        fn.defineAutomata(s0);

        String input = "10101101"; //user's input

        if(fn.isInputAccepted(input)){
            System.out.println(input + " is ACCEPTED.");
        }else {
            System.out.println(input + " is REJECTED!");
        } 
    }
}
