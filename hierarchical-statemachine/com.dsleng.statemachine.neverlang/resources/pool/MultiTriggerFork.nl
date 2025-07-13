module neverlang.examples.statemachine.MultiTriggerFork {
    imports {
        neverlang.examples.statemachine.utils.StateCnt;
    }
    reference syntax {
        StateDef <-- "ForkState" ":" Identifier "{" MForkTransitions  "}" ";";
        MForkTransitions <-- "Fork" ":" ForkTransitionDef  "Left" ":" ForkLeftTransitionDef  "Right" ":" ForkRightTransitionDef;
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
            String line1 = "MultiTriggerForkState " + stateName + " = new MultiTriggerForkState(\"" + stateClass + "\")" + semi;  
            String line2 = "addState(" + stateName + ")" + semi; 
            String line3 = $2.Impl;
            line3 = line3.replace(nme,stateName);
            $0.Impl =  line1 + "\n" + line2 + "\n" + line3 + "\n"; 
        }.
        3.{
            String semi = ";";
            String nme = "##SN##";
            String line1 = $4.Text + semi + "\n";
            String line2 = $5.Text + semi + "\n";
            String line3 = $6.Text + semi + "\n";
            $3.Impl =  line1 + "\n" + line2 + "\n" + line3 + "\n"; 
        }.
    }
}
