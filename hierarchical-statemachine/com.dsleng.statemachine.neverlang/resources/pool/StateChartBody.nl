module neverlang.examples.statemachine.StateChartBody {
    reference syntax {
         StateChartBody <-- "";
         StateChartBody <-- StateDefList TransitionDefList;
    }
    role(evaluation){
        0.{ 
        }.
    }
    role(translate){
        0.{$0.Decl=""; $0.Impl="";$0.Trans="";}.
        1.{$1.Decl=$2.Decl; $1.Impl=$2.Impl;$1.Trans=$3.Text;}.
    }
}
