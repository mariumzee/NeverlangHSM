module neverlang.examples.statemachine.Program {
    reference syntax {
        Program <-- StateChart; 
    }
    role(evaluation){
        0.{ 
            System.out.println($0.Text);
        }.
    }
    role(translate){
        0.{
            $0.Text = $1.Text;
        }.
    }
}
