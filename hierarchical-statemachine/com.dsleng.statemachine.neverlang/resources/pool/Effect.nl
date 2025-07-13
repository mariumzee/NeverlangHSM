module neverlang.examples.statemachine.Effect {
    reference syntax {
        Effect <-- Identifier;
    }
    role(evaluation){
        0.{ 
        }.
    }
    role(translate){
        0.{ 
            $0.Text = "new Effect(\"" + $1.identifier + "\")"; 
        }.
    }
}
