module neverlang.examples.statemachine.StateChart {
    reference syntax {
        StateChart <-- "statechart" Identifier  "{" StateChartBody  "}";
        StateChart <-- "statechart" Identifier  "{" StateChartBody  "}" UseCases;
    }
    role(evaluation){
    }
    role(translate){
        0<template>.{
            package com.dsleng.statemachine.test;

import com.dsleng.statemachine.impl.Effect;
import com.dsleng.statemachine.impl.Guard;
import com.dsleng.statemachine.impl.State;
import com.dsleng.statemachine.impl.SimulEvents;
import com.dsleng.statemachine.impl.MultiTriggerForkState;
import com.dsleng.statemachine.impl.SingleTriggerForkState;
import com.dsleng.statemachine.impl.StateMachine;
import com.dsleng.statemachine.impl.Trigger;

            public class {{$1.identifier}} extends StateMachine {
                public {{$1.identifier}}(){
                    {{$2.Impl}}
                    {{$2.Trans}}
                }
            }
        }.
        3<template>.{
package com.dsleng.statemachine.test;

import com.dsleng.statemachine.impl.Effect;
import com.dsleng.statemachine.impl.Guard;
import com.dsleng.statemachine.impl.State;
import com.dsleng.statemachine.impl.SimulEvents;
import com.dsleng.statemachine.impl.MultiTriggerForkState;
import com.dsleng.statemachine.impl.SingleTriggerForkState;
import com.dsleng.statemachine.impl.StateMachine;
import com.dsleng.statemachine.impl.Trigger;

            public class {{$4.identifier}} extends StateMachine {
                public {{$4.identifier}}(){
                    {{$5.Impl}}
                    {{$5.Trans}}
                }
                public static void main(String[] args) {
                    {{$4.identifier}} s = new {{$4.identifier}}();
                    try {
                        {{$6.Text}}
                    } catch (Exception e) {
                            e.printStackTrace();
                    }
                }
            }
        }.
    }
}
