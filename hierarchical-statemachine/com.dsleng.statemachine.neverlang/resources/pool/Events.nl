module neverlang.examples.statemachine.usecase.Events {
    imports {
        neverlang.examples.statemachine.utils.StateCnt;
    }
    reference syntax {
       EventListDef <-- ""; 
       EventListDef <-- EventDef; 
       EventListDef <-- EventDef ","  EventListDef; 
       EventDef <-- Identifier;
    }
    role(evaluation){
        0.{ 
        }.
    }
    role(translate){
        0.{ $0.Text = ""; }.
        1.{ $1.Text = $2.Text; }.
        3.{ 
            String val = $5.Text;
            $3.Text = $4.Text + "\n" + val;
        }.
        6.{
           String semi = ";";
           String evt = "new Trigger(\"" + $7.identifier + "\")";
           String process = "s" + ".processExternalEvent(" + evt + ")" + semi + "\n"; 
           $6.Text = process;
        }.
    }
}
