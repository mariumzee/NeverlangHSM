module neverlang.examples.statemachine.Transition {
    imports {
        neverlang.examples.statemachine.utils.StateCnt;
    }
    reference syntax {
        TransitionDef <-- "Transition" "(" Identifier "," Identifier ")" "{" TransitionBody "}" ";";
    }
    role(evaluation){
        0.{ 
        }.
    }
    role(translate){
        0.{ 
            String semi = ";";
            String stateClass1 = $1.identifier;
            String stateClass2 = $2.identifier;
            String stateName1 = $$StateCnt.getVariable(stateClass1);
            String stateName2 = $$StateCnt.getVariable(stateClass2);
            $0.Text = stateName1 + ".createTransition(" + stateName2 + "," + $3.Text + ")" + semi; 
        }.
    }
}
