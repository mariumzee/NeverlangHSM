module neverlang.examples.statemachine.Transitions {
    reference syntax {
        TransitionDefList <-- "";
        TransitionDefList <-- TransitionDef;
        TransitionDefList <-- TransitionDef TransitionDefList;
    }
    role(evaluation){
        0.{ 
        }.
    }
    role(translate){
        0.{
            $0.Text = "";
        }.
        1.{
            $1.Text = $2.Text;
        }.
        3.{
            String d = "\n" + $5.Text; 
            $3.Text = $4.Text + d;
        }.
    }
}
