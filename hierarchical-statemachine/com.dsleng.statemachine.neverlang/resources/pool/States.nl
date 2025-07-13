module neverlang.examples.statemachine.States {
    reference syntax {
        StateDefList <-- "";
        StateDefList <-- StateDef;
        StateDefList <-- StateDef StateDefList;
    }
    role(evaluation){
        0.{ 
        }.
    }
    role(translate){
        0.{
            $0.Decl = "";
            $0.Impl = "";
        }.
        1.{
            $1.Decl = $2.Decl;
            $1.Impl = $2.Impl;
        }.
        3.{
            String decl5 = "\n" + $5.Decl; 
            String impl5 = "\n" +  $5.Impl;
            $3.Decl = $4.Decl + decl5;
            $3.Impl = $4.Impl + impl5;
        }.
    }
}
