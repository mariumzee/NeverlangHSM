module neverlang.examples.statemachine.usecase.UseCases {
    imports {
        neverlang.examples.statemachine.utils.StateCnt;
    }
    reference syntax {
       UseCases <-- "UseCases" "{" UseCase  "}";
       UseCase <-- "";
       UseCase <-- Identifier "{" EventListDef "}"; 
       UseCase <-- Identifier "{" EventListDef "}" UseCase;
    }
    role(evaluation){
    }
    role(translate){
        0.{ $0.Text = $1.Text; }.
        2.{ $2.Text = ""; }.
        3.{
            String semi = ";";
            String val = $4.identifier;  
            String text = "\nSystem.out.println(\"Processing:" + val + "\")" + semi + "\n" + "s.Start()" + semi + "\n"; 
            $3.Text = text + $5.Text;
        }.
        6.{
            String semi = ";";
            String val = $7.identifier;  
            String text = "\nSystem.out.println(\"Processing:" + val + "\")" + semi + "\n" + "s.Start()" + semi + "\n"; 
            String ev =  $8.Text; 
            String uc = $9.Text;
            $6.Text = text + ev + uc;
        }.
    }
}
