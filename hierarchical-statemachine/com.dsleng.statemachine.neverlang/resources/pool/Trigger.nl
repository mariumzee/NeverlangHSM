module neverlang.examples.statemachine.Trigger {
    reference syntax {
        Trigger <-- Identifier;
    }
    role(evaluation){
        0.{ 
        }.
    }
    role(translate){
        0.{ 
            $0.Text = "new Trigger(\"" + $1.identifier + "\")"; 
        }.
    }
}
