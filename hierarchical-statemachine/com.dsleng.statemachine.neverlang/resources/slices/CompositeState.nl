package neverlang.examples.statemachine;

slice CompositeState {

  role: syntax {
    rule: "state" ID "{" StateChartBody "}" -> CompositeState;
  }

  role: ast {
    production: CompositeState {
      attributes: ID name; ASTNode body;
    }
  }

  role: sem {
    production: CompositeState {
      before: {
        ExecutionContext subcontext = new ExecutionContext(name); // Create a new ExecutionContext for the composite state
        subcontext.setStateChart(body);      // Register the nested body (StateChartBody)
        context.registerSubContext(name, subcontext);   // Attach the subcontext to the current (parent) context
        context.pushNestedContext(subcontext);        // Optionally push it to a context stack if nesting is tracked
      }
      after: {
        ExecutionContext subcontext = context.getSubContext(name);         // Get the subcontext we just created
        subcontext.initialize();         // Initialize the sub-statechart (enter start state)
        subcontext.run();        // Run nested interpreter (handle nested events/transitions)

      }
    }
  }
}
