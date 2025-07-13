module neverlang.examples.statemachine.SingleTriggerFork {
    imports {
        neverlang.examples.statemachine.utils.StateCnt;
    }
    reference syntax {
        StateDef <-- "ForkState" ":" Identifier "{" ForkTransitions  "}" ";";
        ForkTransitions <-- "Fork" ":" ForkTransitionDef  "LeftState" ":" Identifier  "RightState" ":" Identifier;
    }
    role(evaluation){
        0.{ 
        }.
    }
    role(translate){
        0.{ 
            String semi = ";";
            String nme = "##SN##";
            String stateClass = $1.identifier;
            String stateName = $$StateCnt.getValue(stateClass);
            $0.Decl = "public class " + $1.identifier + " extends State { public " + $1.identifier + "(){super(\"" + $1.identifier + "\"" + ")" + semi + "}}"; 
            String line1 = "SingleTriggerForkState " + stateName + " = new SingleTriggerForkState(\"" + stateClass + "\")" + semi;  
            String line2 = "addState(" + stateName + ")" + semi; 
            String line3 = $2.Impl;
            line3 = line3.replace(nme,stateName);
            $0.Impl =  line1 + "\n" + line2 + "\n" + line3 + "\n"; 
        }.
        3.{
            String semi = ";";
            String nme = "##SN##";
            String line1 = $4.Text + semi + "\n";
            String lval = $5.identifier;
            String rval = $6.identifier;
            String leftState = $$StateCnt.getVariable(lval);
            String rightState = $$StateCnt.getVariable(rval);
            String line2 = nme + ".setLeftTransition(" + leftState + ",new Trigger(\"\"), new Guard(\"\"), new Effect(\"\")"  + ")" + semi; 
            String line3 = nme + ".setRightTransition(" + rightState + ",new Trigger(\"\"), new Guard(\"\"), new Effect(\"\")"  + ")" + semi; 
            $3.Impl =  line1 + "\n" + line2 + "\n" + line3 + "\n"; 
        }.
    }
}
