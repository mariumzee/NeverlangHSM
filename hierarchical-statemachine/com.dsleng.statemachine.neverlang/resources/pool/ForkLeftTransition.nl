module neverlang.examples.statemachine.ForkLeftTransition {
    imports {
        neverlang.examples.statemachine.utils.StateCnt;
    }
    reference syntax {
        ForkLeftTransitionDef <-- "Transition" "(" Identifier ")" "{" TransitionBody "}" ";";
    }
    role(evaluation){
        0.{ 
        }.
    }
    role(translate){
        0.{ 
            String semi = ";";
            String nme = "##SN##";
            String stateClass2 = $1.identifier;
            String stateName2 = $$StateCnt.getVariable(stateClass2);
            $0.Text = nme + ".setLeftTransition(" + stateName2 + "," + $2.Text + ")" + semi; 
        }.
    }
}
