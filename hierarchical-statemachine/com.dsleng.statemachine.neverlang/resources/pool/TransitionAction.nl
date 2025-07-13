module neverlang.examples.statemachine.TransitionAction {
    reference syntax {
        TransitionBody <-- Trigger ","  Guard "," Effect;
    }
    role(evaluation){
        0.{ 
        }.
    }
    role(translate){
        0.{ 
            $0.Text = $1.Text + "," + $2.Text + "," + $3.Text; 
        }.
    }
}
