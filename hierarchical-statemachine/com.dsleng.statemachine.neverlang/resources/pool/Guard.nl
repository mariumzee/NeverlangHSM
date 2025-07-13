module neverlang.examples.statemachine.Guard {
    reference syntax {
        Guard <-- Identifier;
    }
    role(evaluation){
        0.{ 
        }.
    }
    role(translate){
        0.{ 
            $0.Text = "new Guard(\"" + $1.identifier + "\")"; 
        }.
    }
}
