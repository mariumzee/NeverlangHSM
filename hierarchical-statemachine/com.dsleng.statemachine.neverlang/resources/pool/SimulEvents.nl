module neverlang.examples.statemachine.usecase.SimulEvents {
    imports {
        neverlang.examples.statemachine.utils.StateCnt;
    }
    reference syntax {
       EventDef <--  "("  SEventList ")";
       SEventList <-- "";
       SEventList <-- SEvent;
       SEventList <-- SEvent "," SEventList;
       SEvent <-- Identifier;
    }
    role(evaluation){
        0.{ 
        }.
    }
    role(translate){
        0.{ 
            String semi = ";";
            String nme = $$StateCnt.getEventValue();
            String init = "SimulEvents " + nme + " = new SimulEvents()" + semi + "\n";
            String evts = $1.Text;
            evts = evts.replace("##ES##",nme);
            String process = "s.processExternalEvents("+nme+")" + semi;
            $0.Text = init + evts + process; 
        }.
        2.{ $2.Text = ""; }.
        3.{ $3.Text = $4.Text; }.
        5.{ 
            String val = $7.Text;
            $5.Text = $6.Text + "\n" + val;
        }.
        8.{
           String semi = ";";
           //String nme = $$StateCnt.getCurrentEventSet();
           String nme = "##ES##";
           String evt = "new Trigger(\"" + $9.identifier + "\")";
           String process = nme + ".add(" + evt + ")" + semi + "\n"; 
           $8.Text = process;
        }.
    }
}
