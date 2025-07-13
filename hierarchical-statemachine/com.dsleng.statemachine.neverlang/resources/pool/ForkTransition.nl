module neverlang.examples.statemachine.ForkTransition {
    imports {
        neverlang.examples.statemachine.utils.StateCnt;
    }
    reference syntax {
        ForkTransitionDef <-- "Transition"  "{" TransitionBody "}" ";";
    }
    role(evaluation){
        0.{ 
        }.
    }
    role(translate){
        0.{ 
            String semi = ";";
            String nme = "##SN##";
            $0.Text = nme + ".setForkTransition(" + $1.Text + ")" + semi; 
        }.
    }
}
